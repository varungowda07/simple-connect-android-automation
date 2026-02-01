package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import tests.utils.TokenStore;

import static io.restassured.RestAssured.given;


public class AuthApi {
//    static String mobileNumber = "8888888888";
   static TokenStore tokenStore = new TokenStore();
    public static void authApi() {
        login(tokenStore.mobileNumber);
        verifyOtp(tokenStore.mobileNumber,"3289");
        getUserDetails();
        getEmergencyContacts();
        deleteEmergencyContact();
        deletePasscode();
        signOut();
    }


    private static final String BASE_URL1 =
            "https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/";

    private static final String API_KEY = "bGV0bWVsaXZlaW5wZWFjZQ==";

    /**
     * LOGIN API
     */
    public static void login(String mobileNumber) {

        String query = """
            mutation Login($mobile: String!, $whatsappConsent: Boolean!, $loginSource: loginSource) {
              login(mobile: $mobile, whatsappConsent: $whatsappConsent, loginSource: $loginSource) {
                success
                message
                errors {
                  errorCode
                  message
                  statusCode
                }
              }
            }
        """;

        String body = """
            {
              "query": "%s",
              "variables": {
                "mobile": "%s",
                "whatsappConsent": true,
                "loginSource": null
              }
            }
        """.formatted(
                query.replace("\n", " ").replace("\"", "\\\""),
                mobileNumber
        );

        Response response =
                given()
                        .baseUri(BASE_URL)
                        .header("Content-Type", "application/json")
                        .header("x-api-key", API_KEY)
                        .header("x-operation-name", "login")
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        boolean success =
                response.jsonPath().getBoolean("data.login.success");

        if (!success) {
            throw new RuntimeException(
                    "LOGIN FAILED: " +
                            response.jsonPath().getString("data.login.errors[0].message")
            );
        }

        System.out.println("✅ LOGIN SUCCESS → OTP SENT");
    }
    private static final String BASE_URL =
            "https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/user";

    /**
     * VERIFY OTP API
     */
    public static void verifyOtp(String mobileNumber, String otp) {

        String query = """
            mutation VerifyOtp($mobile: String!, $otp: String!) {
              verifyOtp(mobile: $mobile, otp: $otp) {
                success
                message
                data {
                  accessToken
                  refreshToken
                  userId
                }
                errors {
                  errorCode
                  message
                  statusCode
                }
              }
            }
        """;

        String body = """
            {
              "query": "%s",
              "variables": {
                "mobile": "%s",
                "otp": "%s"
              }
            }
        """.formatted(
                query.replace("\n", " ").replace("\"", "\\\""),
                mobileNumber,
                otp
        );

        Response response =
                given()
                        .baseUri(BASE_URL)
                        .header("Content-Type", "application/json")
                        .header("x-api-key", API_KEY)
                        .header("x-operation-name", "verifyOtp")
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Boolean success =
                response.jsonPath().getBoolean("data.verifyOtp.success");

        if (success == null || !success) {
            throw new RuntimeException(
                    "❌ OTP VERIFICATION FAILED: " +
                            response.jsonPath().getString("data.verifyOtp.errors[0].message")
            );
        }

        // ✅ Store values
        String accessToken =
                response.jsonPath().getString("data.verifyOtp.data.accessToken");
        String userId =
                response.jsonPath().getString("data.verifyOtp.data.userId");
        String refreshToken = response.jsonPath().getString("data.verifyOtp.data.refreshToken");

        TokenStore.setAccessToken(accessToken);
        TokenStore.setUserId(userId);
        TokenStore.setRefreshToken(refreshToken);

        System.out.println("✅ OTP VERIFIED");
        System.out.println("🔑 Access Token Stored");
        System.out.println("🆔 User ID Stored → " + userId);
    }
    public static void getUserDetails() {

        String userId = TokenStore.getUserId();
        String accessToken = TokenStore.getAccessToken();

        if (userId == null || accessToken == null) {
            System.out.println("❌ userId or accessToken is null — skipping getUserDetails");
            return;
        }

        String query = """
        query GetUserDetails($getUserDetailsId: ID) {
          getUserDetails(id: $getUserDetailsId) {
            success
            message
            data {
              primaryVin
            }
            errors {
              errorCode
              message
              statusCode
            }
          }
        }
    """;

        String body = """
        {
          "query": "%s",
          "variables": {
            "getUserDetailsId": "%s"
          }
        }
    """.formatted(
                query.replace("\n", " ").replace("\"", "\\\""),
                userId
        );

        Response response =
                given()
                        .baseUri("https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/user/user-docs")
                        .header("Content-Type", "application/json")
                        .header("x-api-key",API_KEY)
                        .header("x-operation-name", "getUserDetails")
                        .header("Authorization", "Bearer " + accessToken)
                        .body(body)
                        .when()
                        .post();


        // ✅ HARD GUARD — do NOT parse JSON if HTTP failed
        if (response.statusCode() != 200) {
            System.out.println("⚠️ GetUserDetails HTTP FAILED → " + response.statusCode());
            System.out.println(response.asPrettyString());
            return;
        }

        Boolean success =
                response.jsonPath().getBoolean("data.getUserDetails.success");

        // ✅ GraphQL-level failure
        if (success == null) {
            System.out.println("⚠️ success field missing (GraphQL error)");
            System.out.println(response.asPrettyString());
            return;
        }

        if (!success) {
            System.out.println("❌ GetUserDetails returned success=false");
            System.out.println(response.asPrettyString());
            return;
        }

        // ✅ SAFE to read VIN now
        String vin =
                response.jsonPath().getString("data.getUserDetails.data.primaryVin");

        if (vin == null || vin.isEmpty()) {
            System.out.println("⚠️ VIN missing in response");
            return;
        }

        TokenStore.setVin(vin);
        System.out.println("✅ USER DETAILS FETCHED → VIN = " + vin);
    }

    public static void getEmergencyContacts() {
        String userId = TokenStore.getUserId();
        String accessToken = TokenStore.getAccessToken();
        if (userId == null || accessToken == null) {
            throw new RuntimeException("❌ userId or accessToken NOT available");
        }

        String query = """
        query GetEmergencyContacts($userId: ID!) {
            getEmergencyContacts(userID: $userId) {
                data {
                    id
                    isPrimary
                    name
                    phone
                    userID
                }
                errors {
                    errorCode
                    message
                    statusCode
                }
                message
                success
            }
        }
    """;

        String body = """
        {
          "query": "%s",
          "variables": {
            "userId": "%s"
          }
        }
    """.formatted(
                query.replace("\n", " ").replace("\"", "\\\""),
                userId
        );

        Response response =
                given()
                        .baseUri("https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/user/user-docs")
                        .header("Content-Type", "application/json")
                        .header("x-api-key", API_KEY)
                        .header("x-operation-name", "getEmergencyContacts")
                        .header("Authorization", "Bearer " + accessToken)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Boolean success =
                response.jsonPath().getBoolean("data.getEmergencyContacts.success");

        if (!success) {
            throw new RuntimeException(
                    "❌ GetEmergencyContacts FAILED: " +
                            response.jsonPath().getString("data.getEmergencyContacts.errors[0].message")
            );
        }

        // Pick FIRST emergency contact (usually primary)
        String emergencyContactId =
                response.jsonPath().getString("data.getEmergencyContacts.data[0].id");

        if (emergencyContactId == null) {
            System.out.println("ℹ️ No emergency contacts found");
            return;
        }

        TokenStore.setEmergencyContactId(emergencyContactId);

        System.out.println("✅ Emergency Contact ID stored → " + emergencyContactId);
    }
    public static void deleteEmergencyContact() {

        String emergencyContactId = TokenStore.getEmergencyContactId();
        String accessToken = TokenStore.getAccessToken();

        if (emergencyContactId == null) {
            System.out.println("ℹ️ No emergency contact to delete");
            return;
        }

        if (accessToken == null) {
            throw new RuntimeException("❌ Access token NOT available");
        }

        String mutation = """
        mutation DeleteEmergencyContact($deleteEmergencyContactId: ID!) {
          deleteEmergencyContact(id: $deleteEmergencyContactId) {
            success
            message
            data {
              id
              userID
              name
              phone
              isPrimary
            }
            errors {
              errorCode
              message
              statusCode
            }
          }
        }
    """;

        String body = """
        {
          "query": "%s",
          "variables": {
            "deleteEmergencyContactId": "%s"
          }
        }
    """.formatted(
                mutation.replace("\n", " ").replace("\"", "\\\""),
                emergencyContactId
        );

        Response response =
                given()
                        .baseUri("https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/user/user-docs")
                        .header("Content-Type", "application/json")
                        .header("x-api-key", API_KEY)
                        .header("x-operation-name", "deleteEmergencyContact")
                        .header("Authorization", "Bearer " + TokenStore.getAccessToken())
//                        .header("Authorization", "Bearer " + accessToken)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Boolean success =
                response.jsonPath().getBoolean("data.deleteEmergencyContact.success");

        if (!success) {
            throw new RuntimeException(
                    "❌ DeleteEmergencyContact FAILED: " +
                            response.jsonPath().getString("data.deleteEmergencyContact.errors[0].message")
            );
        }

        System.out.println("✅ Emergency Contact deleted successfully → " + emergencyContactId);

        // Optional: clear stored ID
        TokenStore.setEmergencyContactId(null);
    }
    public static void deletePasscode() {

        String vin = TokenStore.getVin();
        String accessToken = TokenStore.getAccessToken();

        if (vin == null) {
            System.out.println("ℹ️ VIN not available → Skipping delete passcode");
            return;
        }

        if (accessToken == null) {
            throw new RuntimeException("❌ Access token NOT available");
        }

        String mutation = """
        mutation DeletePasscode($vin: String!) {
          deletePasscode(vin: $vin) {
            success
            message
            data {
              vin
            }
            errors {
              message
              status
              keyword
            }
          }
        }
    """;

        String body = """
        {
          "query": "%s",
          "variables": {
            "vin": "%s"
          }
        }
    """.formatted(
                mutation.replace("\n", " ").replace("\"", "\\\""),
                vin
        );

        Response response =
                given()
                        .baseUri("https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/simpleconnect")
                        .header("Content-Type", "application/json")
//                        .header("x-api-key", API_KEY)
                        .header("x-operation-name", "deletePasscode")
                        .header("Authorization", "Bearer " + accessToken)
                        .body(body)
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        Boolean success =
                response.jsonPath().getBoolean("data.deletePasscode.success");

        if (!success) {
            throw new RuntimeException(
                    "❌ DeletePasscode FAILED: " +
                            response.jsonPath().getString("data.deletePasscode.errors[0].message")
            );
        }

        System.out.println("✅ Passcode deleted successfully for VIN → " + vin);
    }
    public static void signOut() {

        String query = """
        mutation SignOut($refreshToken: String, $accessToken: String) {
          signOut(refreshToken: $refreshToken, accessToken: $accessToken) {
            success
            message
            errors {
              errorCode
              message
              statusCode
            }
          }
        }
    """;

        String body = """
        {
          "query": "%s",
          "variables": {
            "refreshToken": "%s"
          }
        }
    """.formatted(
                query.replace("\n", " ").replace("\"", "\\\""),
                TokenStore.getRefreshToken()
//                TokenStore.getAccessToken()
        );

        Response response =
                given()
                        .baseUri("https://wo5cdjz6re.execute-api.us-east-1.amazonaws.com/stage/api/graphql/user/user-docs")
                        .header("Content-Type", "application/json")
//                        .header("x-api-key", API_KEY)
//                        .header("x-operation-name", "SignOut")
//                        .header("Authorization", "Bearer " + TokenStore.getAccessToken())
                        .body(body)
                        .when()
                        .post()
                        .then()
                        // ⬇️ IMPORTANT: SignOut can return 200 or 204 depending on backend
                        .statusCode(200)
                        .extract()
                        .response();

        boolean success =
                response.jsonPath().getBoolean("data.signOut.success");

        if (!success) {
            throw new RuntimeException(
                    "SIGN OUT FAILED: " +
                            response.jsonPath().getString("data.signOut.errors[0].message")
            );
        }

        System.out.println("🚪 SIGN OUT SUCCESSFUL");
    }


}

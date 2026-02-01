package tests.utils;

public class TokenStore {

    private static String accessToken;
    private static String userId;
    private static String vin;
    private static String emergencyContactId;
    private static String refreshToken;
    public String mobileNumber = "9999999999";
    public static String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String token) {
        accessToken = token;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String id) {
        userId = id;
    }

    public static String getVin() {
        return vin;
    }

    public static void setVin(String vehicleVin) {
        vin = vehicleVin;
    }

    public static String getEmergencyContactId() {
        return emergencyContactId;
    }

    public static void setEmergencyContactId(String id) {
        emergencyContactId = id;
    }
    public static void setRefreshToken(String token) {
        refreshToken = token;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }
}

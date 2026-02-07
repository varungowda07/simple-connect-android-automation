package tests.profile.viewprofile;

public class ProfileFlow {
    ProfileTests profile = new ProfileTests();
    ViewProfile viewProfile = new ViewProfile();
    EditProfileTests editProfile = new EditProfileTests();
    EditProfilePhoto editProfilePhoto = new EditProfilePhoto();
    EditFullName editFullName = new EditFullName();
    EditPincode editPincode = new EditPincode();
    EditEmail editEmail = new EditEmail();
    public void profileFlow() {
        profile.profile();
        viewProfile.viewProfile();
        editProfile.editProfile();
        editProfilePhoto.editProfilePhoto();
        editFullName.editFullName();
        editPincode.editPincode();
        editEmail.editEmail();
    }
}

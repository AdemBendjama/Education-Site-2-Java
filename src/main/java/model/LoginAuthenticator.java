package model;

public class LoginAuthenticator {
    private final UserManager userManager;
    private final AdminManager adminManager;

    //
    public LoginAuthenticator() {
        this.userManager = new UserManager();
        this.adminManager = new AdminManager();
    }

    // check the login info
    // return the privilege level of the account if found
    public String authenticate(String email, String password) {
        // check if the account belongs to an admin
        boolean correctAdminCred = adminManager.checkCredentials(email, password);

        if (correctAdminCred) {
            return "admin";

        }

        // check if the account belongs to a normal user [teacher,student]
        boolean correctUserCred = userManager.checkCredentials(email, password);

        if (correctUserCred) {
            return userManager.getRank(email);

        }

        //
        return null;

    }


}

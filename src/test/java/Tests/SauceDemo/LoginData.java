package tests.SauceDemo;

public class LoginData {
    public static final String SAUCE_DEMO_APP = "https://www.saucedemo.com";
    public static final String SAUCE_USERS_PASSWORD = "secret_sauce";

     public enum UserType {
         STANDARD_USER("standard_user"),
        LOCKED_USER("locked_out_user"),
        ERROR_USER("error_user");

         private final String user_type;

         UserType(String user_type) {
            this.user_type = user_type;
        }
        public String getUserType(){
            return user_type;
        }

    }
}

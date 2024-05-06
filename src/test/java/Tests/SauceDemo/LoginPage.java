package Tests.SauceDemo;

import base.BasePage;
import base.FrameworkConfig;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {
    String txtUserName = "[data-test='username']";
    String txtPassword = "[data-test='password']";
    String btnLogin = "[data-test='login-button']";
    String errorMessage = "[data-test='error']";

    public LoginPage(FrameworkConfig frameworkConfig) {
        super(frameworkConfig);
    }

    @Step("Login with username {0} and password {1}")
    public void Login(String userName, String password) {
        this.playwrightUI.fill_textbox(txtUserName, userName,"Fill User Name");
        this.playwrightUI.fill_textbox(txtPassword, password,"Fill User Password");
        this.playwrightUI.click_button(btnLogin,"Click Login");
    }
}

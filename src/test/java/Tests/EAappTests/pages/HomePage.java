package tests.EAappTests.pages;

import base.BasePage;
import base.FrameworkConfig;
import io.qameta.allure.Step;

public class HomePage extends BasePage {
    String lnkEmployee = "text='Employee List'";
    String btnCreateNew = "text='Create New'";
    String lnkLogin = "text='Login'";
    String lnkLogOff = "text='Log off'";
    String btnDelete = "input[type=\"submit\"]";

    private final FrameworkConfig frameworkConfig;
    public HomePage(FrameworkConfig frameworkConfig) {
        super(frameworkConfig);
        this.frameworkConfig = frameworkConfig;
    }

    @Step("Click Login")
    public void ClickLogin() {
        this.playwrightUI.click_button(lnkLogin,"ClickLoginLink");
    }

    @Step("Click employee list")
    public void ClickEmployeeList() {
        this.playwrightUI.click_button(lnkEmployee,"ClickEmployeeList");
    }

    @Step("Click create new user")
    public void ClickCreateNew() {
        this.playwrightUI.click_button(btnCreateNew,"ClickCreateNewUser");
    }

    @Step("Click LogOff")
    public void ClickLogOff() {
        this.playwrightUI.click_button(lnkLogOff,"ClickLogOff");
    }

    @Step("Delete created user")
    public void DeleteCreateUser(String userName) {
        int last_user = this.frameworkConfig.page.locator("tr").count();
        String delete_locator = String.format(
                "body > div.container.body-content > table > tbody > tr:nth-child(%s) > td:nth-child(6) > a:nth-child(3)",
                last_user);
        this.playwrightUI.click_button(delete_locator,"ClickDeleteUser");
        this.playwrightUI.click_button(btnDelete,"Click delete button");
    }

}

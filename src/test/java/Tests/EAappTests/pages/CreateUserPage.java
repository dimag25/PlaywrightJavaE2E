package Tests.EAappTests.pages;

import base.BasePage;
import base.FrameworkConfig;
import io.qameta.allure.Step;

public class CreateUserPage extends BasePage {

    String txtName = "input[name=\"Name\"]";

    String txtSalary = "input[name=\"Salary\"]";

    String txtDuration = "input[name=\"DurationWorked\"]";

    String txtGrade = "select[name=\"Grade\"]";

    String txtEmail = "input[name=\"Email\"]";

    String btnCreate = "input[type=\"submit\"]";

    public CreateUserPage(FrameworkConfig frameworkConfig) {
        super(frameworkConfig);
    }

    @Step("Create new user")
    public void CreateUser(String name, String salary, String Duration, String grade, String email) {
        this.playwrightUI.fill_textbox(txtName, name,"Fill User Name");
        this.playwrightUI.fill_textbox(txtSalary, salary,"Fill User Salary");
        this.playwrightUI.fill_textbox(txtDuration, Duration,"Fill User Duration");
        this.playwrightUI.select_option_by_value(txtGrade, grade,"Select User Grade");
        this.playwrightUI.fill_textbox(txtEmail, email,"Fill User Email");
        this.playwrightUI.click_button(btnCreate,"Click submit new user");
    }
}

package tests.EAappTests;

import base.BaseTest;
import base.FrameworkConfig;
import base.FrameworkInitalize;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import reporter.AllureReporter;
import tests.EAappTests.pages.CreateUserPage;
import tests.EAappTests.pages.HomePage;
import tests.EAappTests.pages.LoginPage;

import static PlaywrightApis.ApiPlaywrightWrapper.closePlaywright;
import static PlaywrightApis.ApiPlaywrightWrapper.createPlaywright;

public class UITests extends BaseTest {

    private HomePage homePage;
    private CreateUserPage createUserPage;
    private FrameworkConfig frameworkConfig;

    @BeforeClass
    public void setupPlaywright() {
        frameworkConfig =
                new FrameworkConfig(
                        new FrameworkInitalize().InitializePlaywright(),
                        createPlaywright());
        frameworkConfig.playwrightUI.navigate_to_url(TestsData.EA_APP_WEBSITE);
    }

    @BeforeMethod
    public void setupTest() {
        homePage = new HomePage(frameworkConfig);
        createUserPage = new CreateUserPage(frameworkConfig);
        LoginPage loginPage = new LoginPage(frameworkConfig);
        homePage.ClickLogin();
        loginPage.Login(TestsData.ADMIN_USERNAME, TestsData.ADMIN_PASSWORD);
    }

    @Test
    @Feature("Users UI TESTS")
    @Story("USERS CREATION")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Add user and deletes it")
    public void testCreateAndDeleteUser() {
        homePage.ClickEmployeeList();
        //Create New User
        homePage.ClickCreateNew();
        createUserPage.CreateUser(TestsData.USER_NAME, TestsData.USER_SALARY,
                TestsData.USER_DURATION, TestsData.USER_GRADE, TestsData.USER_EMAIL);
        //Delete User
        homePage.DeleteCreateUser(TestsData.USER_NAME);
        //Log off
        homePage.ClickLogOff();

    }

    @AfterMethod
    public void saveTestVideo(ITestResult result){
        AllureReporter.attachVideo(result.getMethod().getMethodName(),
                String.valueOf(frameworkConfig.page.video().path()));
    }

    @AfterClass
    public void cleanUp() {
        frameworkConfig.page.close();
        closePlaywright();
    }
}

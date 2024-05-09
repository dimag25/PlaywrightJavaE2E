package tests.SauceDemo;

import base.BaseTest;
import base.FrameworkConfig;
import base.FrameworkInitalize;
import com.microsoft.playwright.Tracing;
import config.Settings;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporter.AllureReporter;

import java.nio.file.Paths;

import static PlaywrightApis.ApiPlaywrightWrapper.closePlaywright;
import static PlaywrightApis.ApiPlaywrightWrapper.createPlaywright;
import static tests.SauceDemo.LoginData.SAUCE_DEMO_APP;

public class LoginTests extends BaseTest {

    private LoginPage loginPage;
    private FrameworkConfig frameworkConfig;

    @BeforeClass
    public void setupPlaywright() {
        frameworkConfig =
                new FrameworkConfig(
                        new FrameworkInitalize().InitializePlaywright(),
                        createPlaywright());
    }

    @BeforeMethod
    public void setupTest() {
        // Start tracing before navigating or performing actions
        frameworkConfig.page.context().tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        frameworkConfig.playwrightUI.navigate_to_url(SAUCE_DEMO_APP);
        loginPage = new LoginPage(frameworkConfig);
    }

    @DataProvider(name = "invalidUserCredentials")
    public Object[][] getInvalidUserCredentials() {
        return new Object[][] {
                {LoginData.UserType.STANDARD_USER.getUserType(), "invalid_password", "Epic sadface: Username and password do not match any user in this service"},
                {LoginData.UserType.LOCKED_USER.getUserType(), LoginData.SAUCE_USERS_PASSWORD, "Epic sadface: Sorry, this user has been locked out."}
                // Add more invalid user credentials and expected error messages as needed
        };
    }

    @Test(dataProvider = "invalidUserCredentials")
    @Story("Users Login")
    @Feature("Users InValid Login Test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Login with invalid users data")
    public void test_invalid_logins(String username, String password,String expectedErrorMessage) {
        loginPage.Login(username,password);
        // Get the actual error message from the page
        String actualErrorMessage = frameworkConfig.page.textContent(loginPage.errorMessage);
        // Assert that the actual error message matches the expected error message
        assert actualErrorMessage.equals(expectedErrorMessage) : "Unexpected error message: " + actualErrorMessage;
    }

    @Test
    @Feature("Users Valid Login Test")
    @Story("Users Login")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test Login with valid user data")
    public void test_valid_login() {
        String username = LoginData.UserType.STANDARD_USER.getUserType();
        loginPage.Login(username, LoginData.SAUCE_USERS_PASSWORD);
        // Verify successful login (e.g., check for presence of a logged-in element)
        boolean loggedIn = frameworkConfig.page.isVisible(".inventory_list");
        // Assert login status
        assert loggedIn : "Login failed for user: " + username;
    }

    @AfterMethod
    public void saveTestVideoAndTrace(ITestResult result){
        String testName = result.getMethod().getMethodName();
        AllureReporter.attachVideo(testName, String.valueOf(frameworkConfig.page.video().path()));
        // Stop tracing and save the trace to a file
        frameworkConfig.page.context().tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get(String.format("%s/%s_trace.zip", Settings.VideosPath, testName))));
    }

    @AfterClass
    public void cleanUp() {
        frameworkConfig.page.close();
        closePlaywright();
    }


}

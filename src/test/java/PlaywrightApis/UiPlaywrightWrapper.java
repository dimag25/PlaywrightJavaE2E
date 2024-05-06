package PlaywrightApis;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import reporter.AllureReporter;

public class UiPlaywrightWrapper {
    private final Page page;

    public UiPlaywrightWrapper(Page page){
        this.page = page;
    }

    @Step("Fill textbox: {0} , value: {1}")
    public void fill_textbox(String locator, String text, String screenshot){
        this.page.fill(locator, text);
        AllureReporter.takeScreenshotAndAttach(screenshot, this.page);
    }

    @Step("Select from list : {0} , value: {1}")
    public void select_option_by_value(String locator,String option,String screenshot){
        this.page.selectOption(locator,option);
        AllureReporter.takeScreenshotAndAttach(screenshot, this.page);
    }

    @Step("Click button: {0}")
    public void click_button(String locator,String screenshot){
        this.page.click(locator);
        AllureReporter.takeScreenshotAndAttach(screenshot, this.page);
    }

    @Step("navigate to url: {0}")
    public void navigate_to_url(String url){
        this.page.navigate(url);
        AllureReporter.takeScreenshotAndAttach(url, this.page);
    }
}

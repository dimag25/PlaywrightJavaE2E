package base;

import PlaywrightApis.ApiPlaywrightWrapper;
import PlaywrightApis.UiPlaywrightWrapper;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameworkConfig {

    public Page page;
    public Playwright playwright;
    public UiPlaywrightWrapper playwrightUI;
    public ApiPlaywrightWrapper playwrightAPI;

    public FrameworkConfig(Page localPage,Playwright playwright){
        this.page = localPage;
        this.playwright = playwright;
        this.playwrightUI = new UiPlaywrightWrapper(this.page);
    }
}

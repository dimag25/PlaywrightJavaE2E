package base;

import com.microsoft.playwright.*;

import static PlaywrightApis.ApiPlaywrightWrapper.createPlaywright;

public class BrowserInitalize {


    public Browser GetBrowser(String browserName, BrowserType.LaunchOptions launchOptions) {
        Playwright playwright = createPlaywright();
        BrowserType browserType = null;

        if(browserName.equalsIgnoreCase("chromium"))
            browserType = playwright.chromium();
        if(browserName.equalsIgnoreCase("firefox"))
            browserType = playwright.firefox();
        if(browserName.equalsIgnoreCase("webkit"))
            browserType = playwright.webkit();

        return browserType.launch(launchOptions);
    }

    public BrowserContext GetBrowserContext(Browser browser, Browser.NewContextOptions newContextOptions) {
        return browser.newContext(newContextOptions);
    }

    public Page GetPage(BrowserContext browserContext) { return browserContext.newPage(); }
}

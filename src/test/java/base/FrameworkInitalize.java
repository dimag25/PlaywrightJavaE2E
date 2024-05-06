package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import config.Settings;

import java.nio.file.Paths;

public class FrameworkInitalize {

    public Page InitializePlaywright() {

        //Initalize the browser
        BrowserInitalize browserInitalize = new BrowserInitalize();

        //Set the launch Options
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = Settings.Headless;
        launchOptions.devtools = Settings.DevTools;

        //Get Browser
        Browser browser  = browserInitalize.GetBrowser(Settings.BrowserName, launchOptions);

        //Get browserContext
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.locale = Settings.Locale;
        contextOptions.setRecordVideoDir(Paths.get("videos/")).setRecordVideoSize(640, 480);
        BrowserContext context = browserInitalize.GetBrowserContext(browser, contextOptions);
        //Get Page
        return browserInitalize.GetPage(context);

    }
}

package base;

import com.microsoft.playwright.*;
import config.Settings;

import java.nio.file.Paths;

public class FrameworkInitalize {

    public Page InitializePlaywright() {

        //Initalize the browser
        BrowserInitalize browserInitalize = new BrowserInitalize();

        //Set the launch Options
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.headless = true;
        launchOptions.devtools = Settings.DevTools;

        //Get Browser
        Browser browser  = browserInitalize.GetBrowser(Settings.BrowserName, launchOptions);

        //Get browserContext
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions();
        contextOptions.locale = Settings.Locale;
        int width = Integer.parseInt(Settings.VideoSize.split(",")[0]);
        int height = Integer.parseInt(Settings.VideoSize.split(",")[1]);
        contextOptions.setRecordVideoDir(Paths.get(Settings.VideosPath)).setRecordVideoSize(width, height);
        BrowserContext context = browserInitalize.GetBrowserContext(browser, contextOptions);
        //Get Page
        return browserInitalize.GetPage(context);

    }
}

package base;

import PlaywrightApis.UiPlaywrightWrapper;

public class BasePage {
    protected final UiPlaywrightWrapper playwrightUI;
    public BasePage(FrameworkConfig frameworkConfig){
        this.playwrightUI = new UiPlaywrightWrapper(frameworkConfig.page);
    }
}

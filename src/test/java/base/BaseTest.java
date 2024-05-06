package base;

import config.ConfigReader;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void globalTestsSetup(){
        ConfigReader.PopulateSettings();
    }
}

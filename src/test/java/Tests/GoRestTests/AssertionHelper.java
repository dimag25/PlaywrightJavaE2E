package tests.GoRestTests;

import io.qameta.allure.Step;

import static org.testng.AssertJUnit.assertEquals;

public class AssertionHelper {

    @Step("Assert actual: {0} == expected: {1}")
    public static void assertObjectEquals(Object actual,Object expected){
        assertEquals(actual, expected);
    }
}

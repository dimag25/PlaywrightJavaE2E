package reporter;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;

public class AllureReporter
{
    public static void takeScreenshotAndAttach(String name, Page page) {
        // Take screenshot using Playwright
        byte[] screenshot = page.screenshot();
        // Attach screenshot to Allure report
        Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
    }

    // Inside your test method or setup/teardown methods
    public static void attachVideo(String name, String videoPath) {
        try {
            // Read the video file
            FileInputStream videoContent = new FileInputStream(videoPath);
            Allure.addAttachment(name, "video/webm", videoContent, "webm");
        } catch (Exception e) {
            System.err.println("Error attaching video to Allure report: " + e.getMessage());
        }
    }

    // Inside your test method or setup/teardown methods
    public static void attachRequestResponse(String requestTitle, String requestBody, String responseBody) {
        // Attach request data to Allure report
        Allure.addAttachment(String.format("[Request] %s",requestTitle),"application/json", requestBody);
        // Attach response data to Allure report
        Allure.addAttachment(String.format("[Response] %s",requestTitle),"application/json", responseBody);
    }


    // Inside your test method or setup/teardown methods
    public static void attachGetRequestResponse(String requestTitle, String response) {
        // Attach request and response data to Allure report
        Allure.addAttachment(requestTitle,"application/json", response);
    }

    public static void addLogAttachment(String name, String message) {
        Allure.addAttachment(name, message);
    }
}

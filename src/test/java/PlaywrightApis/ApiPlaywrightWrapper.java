package PlaywrightApis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;
import static Tests.GoRestTests.ApiTestsData.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static reporter.AllureReporter.attachGetRequestResponse;
import static reporter.AllureReporter.attachRequestResponse;

public class ApiPlaywrightWrapper {
    private static Playwright playwright;
    private static APIRequestContext request;
    private static final Gson gson = new Gson();

    public static Playwright createPlaywright() {
        playwright = Playwright.create();
        return playwright;
    }

    public static void createAPIRequestContext() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        // Add authorization token to all requests.
        headers.put("Authorization", "Bearer " + API_TOKEN);
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                // All requests we send go to this API endpoint.
                .setBaseURL(API_BASE_URL)
                .setExtraHTTPHeaders(headers));
    }

    public static JsonObject GetRequest(String endpoint){
        APIResponse response = request.get(endpoint);
        attachGetRequestResponse(String.format("GET %s %s", endpoint, response.status()), response.text());
        assertTrue(response.ok(), response.text());
        assertEquals(response.status(),200);
        return gson.fromJson(response.text(), JsonObject.class);
    }

    public static JSONArray GetRequestArray(String endpoint){
        APIResponse response = request.get(endpoint);
        attachGetRequestResponse(String.format("GET %s %s", endpoint, response.status()), response.text());
        assertTrue(response.ok(), response.text());
        assertEquals(response.status(),200);
        return new JSONArray(response.text());
    }

    public static JsonObject PostRequest(String endpoint, String data){
        APIResponse response = request.post(endpoint, RequestOptions.create().setData(data));
        attachRequestResponse(String.format("POST %s %s", endpoint, response.status()), data, response.text());
        assertTrue(response.ok(), response.text());
        assertEquals(response.status(),201);
        return gson.fromJson(response.text(), JsonObject.class);
    }

    public static JsonObject PutRequest(String endpoint, String data){
        APIResponse response = request.put(endpoint, RequestOptions.create().setData(data));
        attachRequestResponse(String.format("PUT %s %s", endpoint, response.status()), data, response.text());
        assertTrue(response.ok(), response.text());
        assertEquals(response.status(),200);
        return gson.fromJson(response.text(), JsonObject.class);
    }

    public static void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}

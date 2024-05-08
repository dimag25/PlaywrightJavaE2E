package tests.GoRestTests;

import base.BaseTest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.qameta.allure.*;
import reporter.AllureReporter;

import java.util.*;

import static PlaywrightApis.ApiPlaywrightWrapper.*;
import static tests.GoRestTests.ApiTestsData.*;
import static tests.GoRestTests.AssertionHelper.assertObjectEquals;

public class APITests extends BaseTest {

    private final Gson gson = new Gson();

    @BeforeSuite
    void beforeAll() {
        createPlaywright();
        createAPIRequestContext();
    }

    @Test
    @Feature("Users REST API")
    @Story("USERS CREATION")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Add user then get the user to see of all the details are correct")
    void test_create_User(){
        String user_data = generate_user_data();
        JsonObject newUser = PostRequest(USERS_ENDPOINT, user_data);
        JsonObject createdUser = GetRequest(String.format("%s/%s",USERS_ENDPOINT, newUser.get("id")));
        AllureReporter.addLogAttachment(" created user ", String.valueOf(createdUser));
        JsonObject generated_user_obj = gson.fromJson(user_data, JsonObject.class);
        assertObjectEquals(createdUser.get("name"), generated_user_obj.get("name"));
        assertObjectEquals(createdUser.get("email"), generated_user_obj.get("email"));
    }

    @Test
    @Feature("Users REST API")
    @Story("USERS UPDATE DATA")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Update your user and change the name, then get and see if all the details are correct")
    void test_update_user()    {
        JSONArray users  = GetRequestArray(USERS_ENDPOINT);
        JSONObject random_user = users.getJSONObject(users.length() - 1);
        AllureReporter.addLogAttachment("\n original user data: ", String.valueOf(random_user));
        String user_data = generate_user_data();
        AllureReporter.addLogAttachment("\n new user data: {}", user_data);
        JsonObject updated_user = PutRequest(
                String.format("%s/%s", USERS_ENDPOINT, random_user.get("id")), user_data);
        JsonObject generated_user_obj = gson.fromJson(user_data, JsonObject.class);
        assertObjectEquals(updated_user.get("name"),generated_user_obj.get("name"));
        assertObjectEquals(updated_user.get("email"),generated_user_obj.get("email"));
    }

    @Test
    @Feature("Users REST API")
    @Story("GET 10 RANDOM UNIQUE USER NAMES")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Select 10 random distinct users and print their names")
    void test_10_random_distinct_users() {
        JSONArray users  = GetRequestArray(USERS_ENDPOINT);
        List<String> user_names = new ArrayList<>();
        Set<String> sampledNamesSet = new HashSet<>();

        for (int i = 0; i < users.length(); i++) {
            user_names.add(String.valueOf(users.getJSONObject(i).get("name")));
        }
        // Shuffle the list to randomize the order
        Collections.shuffle(user_names);
        // Select the first 10 distinct names
        int userNumber = 1;
        for (String name : user_names) {
            if (sampledNamesSet.size() < 10 && !sampledNamesSet.contains(name)) {
                sampledNamesSet.add(name);
                AllureReporter.addLogAttachment(String.format("User%s",userNumber), name);
                userNumber += 1;
            }
        }
        assertObjectEquals(sampledNamesSet.size(),10);
    }

    @AfterSuite
    void afterAll() {
        disposeAPIRequestContext();
        closePlaywright();
    }
}

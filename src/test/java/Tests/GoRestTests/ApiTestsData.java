package tests.GoRestTests;

import java.util.UUID;

import static java.lang.String.format;

public class ApiTestsData {
    public static final String API_TOKEN = "e2684965e4b313ee7b8e465d36c0bd4815561c6a16840d99eb9f83714077e365";
    public static final String API_BASE_URL = "https://gorest.co.in/public/v2/";
    public static final String USERS_ENDPOINT = API_BASE_URL + "users";

    public static String generate_user_data() {
        UUID uuid = UUID.randomUUID();
        String unique_name = format("DimaGurevich_%s", uuid);
        String unique_email = format("gurevich@%s", uuid);
        return format("{\"name\": \"%s\", \"gender\": \"male\", \"email\": \"%s\", \"status\":\"active\"}",
                unique_name, unique_email);
    }
}

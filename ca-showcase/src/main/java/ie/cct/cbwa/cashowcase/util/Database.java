package ie.cct.cbwa.cashowcase.util;

import ie.cct.cbwa.cashowcase.model.Trip;
import ie.cct.cbwa.cashowcase.model.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    public static List<User> users = Arrays.asList(
            new User("John", "john", "123456"),
            new User("Bruce", "bruce", "password"),
            new User("Jane", "jane", "654321"),
            new User("Vivien", "vivien", "secretpassword")
    );

    public static Map<String, Trip> trips = new HashMap<>();
}

package utilities;

import org.testng.annotations.DataProvider;

public class DataSet {
    // User credentials
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String VALID_PASSWORD = "secret_sauce";
    public static final String INVALID_PASSWORD = "wrong_password";

    // Checkout information
    public static final String CHECKOUT_FIRST_NAME = "John";
    public static final String CHECKOUT_LAST_NAME = "Doe";
    public static final String CHECKOUT_POSTAL_CODE = "12345";

    // Sort options
    public static final String SORT_NAME_AZ = "Name (A to Z)";
    public static final String SORT_NAME_ZA = "Name (Z to A)";
    public static final String SORT_PRICE_LOW_HIGH = "Price (low to high)";
    public static final String SORT_PRICE_HIGH_LOW = "Price (high to low)";

    // Error messages
    public static final String LOCKED_OUT_ERROR = "Epic sadface: Sorry, this user has been locked out.";
    public static final String INVALID_LOGIN_ERROR = "Epic sadface: Username and password do not match any user in this service";

    // Success messages
    public static final String CHECKOUT_COMPLETE_MESSAGE = "Thank you for your order!";
}


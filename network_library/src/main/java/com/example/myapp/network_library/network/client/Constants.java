package com.example.myapp.network_library.network.client;

public interface Constants {
    String MERCHANT_DOMAIN_ADDRESS = "nagarro.com";
    String RANDOM_IMAGE_URL = "https://picsum.photos/600/400/?random";
    boolean IS_TESTING = true;
    String BASE_URL = "localhost";
    String CATEGORY = "category";
    String MERCHANT_ID = "merchant_id";

    interface TESTING_DATA {
        String userEmail = "piyush.gupta01@nagarro.com";
        String password = "password";
    }

    interface COLLECTION_CONSTANTS {
        String CATEGORIES = "categories";
        String MERCHANTS = "merchant";
        String DEALS = "deals";
        String ADDRESS = "address";
        String USERS = "users";
    }

    interface BUNDLE_KEYS {
        String DEAL_DETAILS = "deal_details";
        String CATEGORY_TYPE = "category_type";
        String MERCHANT_ID = "merchant_id";
    }

    interface ERROR_CODES {
        String USER_NOT_FOUND = "ERROR_USER_NOT_FOUND";
    }
}

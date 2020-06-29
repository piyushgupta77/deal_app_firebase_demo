package com.example.myapp.framework.data.repositories;

import com.example.myapp.framework.model.Deal;
import com.example.myapp.network_library.model.SignInDto;
import com.example.myapp.network_library.network.client.FirebaseNetworkClient;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

public class FirebaseRepository {

    private final FirebaseNetworkClient firebaseNetworkClient;

    @Inject
    FirebaseRepository(FirebaseNetworkClient networkClient) {
        this.firebaseNetworkClient = networkClient;
    }

    public Observable<Boolean> createUserName(String username, String password) {
        return firebaseNetworkClient.createUserName(username , password);
    }

    public Observable<SignInDto> signIn(String username, String password) {
        return firebaseNetworkClient.signIn(username , password);
    }

    public Observable<Boolean> sendVerificationEmail(String username, String password) {
        return firebaseNetworkClient.sendVerificationEmail(username , password);
    }

    public Observable<Boolean> resetPassword(String username) {
        return firebaseNetworkClient.resetPassword(username);
    }

    public Observable<List<Deal>> makeQuery(String qeuryCollection, String dealCategory) {
        return firebaseNetworkClient.makeQuery(qeuryCollection, dealCategory);
    }

    public Observable<Map<String, Object>> getCollection(String collection) {
        return firebaseNetworkClient.getCollection(collection);
    }

    public Observable<List<Deal>> getMerchantDeals(String collection, String merchantId) {
        return firebaseNetworkClient.getMerchantDeals(collection, merchantId);
    }

    public Observable<String> addDeal(String collection, Deal deal) {
        return firebaseNetworkClient.addDeal(collection,deal);
    }

    public Observable<Boolean> signOut() {
        return firebaseNetworkClient.signOut();
    }
}

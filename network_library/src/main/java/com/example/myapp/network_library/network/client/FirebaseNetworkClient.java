package com.example.myapp.network_library.network.client;

import android.support.annotation.NonNull;

import com.example.myapp.framework.model.Deal;
import com.example.myapp.network_library.model.SignInDto;
import com.example.myapp.network_library.model.exceptions.LoginFailureException;
import com.example.myapp.network_library.model.exceptions.UserAlreadyExistException;
import com.example.myapp.network_library.model.exceptions.UserNotFoundException;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class FirebaseNetworkClient {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Inject
    public FirebaseNetworkClient(){
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public Observable<Boolean> createUserName(String username, String password) {
        PublishSubject<Boolean> publishSubject = PublishSubject.create();
        firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnSuccessListener(authResult -> {
                    publishSubject.onNext(true);
                    publishSubject.onComplete();
                }).addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthUserCollisionException) {
                        publishSubject.onError(new UserAlreadyExistException());
                    }else{
                        publishSubject.onError(new Exception());
                    }
                    publishSubject.onComplete();
                });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<Boolean> resetPassword(String username) {
        PublishSubject<Boolean> publishSubject = PublishSubject.create();
        firebaseAuth.sendPasswordResetEmail(username)
                .addOnSuccessListener(aVoid -> {
                    publishSubject.onNext(true);
                    publishSubject.onComplete();
                }).addOnFailureListener(e -> {
                    publishSubject.onError(e);
                    publishSubject.onComplete();
                });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<SignInDto> signIn(String email, String password) {
        PublishSubject<SignInDto> publishSubject = PublishSubject.create();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    SignInDto signInDto = new SignInDto();
                    signInDto.setEmailVerified(authResult.getUser().isEmailVerified());
                    signInDto.setEmail(authResult.getUser().getEmail());
                    publishSubject.onNext(signInDto);
                    publishSubject.onComplete();
                })
                .addOnFailureListener(e -> {
                    if (e instanceof FirebaseAuthInvalidUserException) {
                        FirebaseAuthInvalidUserException firebaseAuthInvalidUserException = (FirebaseAuthInvalidUserException) e;
                        if (Constants.ERROR_CODES.USER_NOT_FOUND.equals(firebaseAuthInvalidUserException.getErrorCode())) {
                            publishSubject.onError(new UserNotFoundException());
                        } else {
                            publishSubject.onError(new LoginFailureException());
                        }
                    } else {
                        publishSubject.onError(new LoginFailureException());
                    }
                    publishSubject.onComplete();
                });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<Boolean> sendVerificationEmail(String email, String password) {
        PublishSubject<Boolean> publishSubject = PublishSubject.create();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    authResult.getUser().sendEmailVerification();
                    publishSubject.onNext(true);
                    publishSubject.onComplete();
                })
                .addOnFailureListener(e -> {
                    publishSubject.onError(e);
                    publishSubject.onComplete();
                });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<List<Deal>> makeQuery(String collection, String dealCategory) {
        PublishSubject<List<Deal>> publishSubject = PublishSubject.create();
        CollectionReference reference = FirebaseFirestore.getInstance().collection(collection);
        Task<QuerySnapshot> query = reference.whereEqualTo(Constants.CATEGORY, dealCategory).get();
        query.addOnSuccessListener(queryDocumentSnapshots -> {
            List<Deal> dealList = new ArrayList<>();
            if (!CollectionUtils.isEmpty(queryDocumentSnapshots.getDocuments())) {
                dealList = new ArrayList<>(queryDocumentSnapshots.getDocuments().size());
                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                    dealList.add(documentSnapshot.toObject(Deal.class));
                }
            }
            publishSubject.onNext(dealList);
            publishSubject.onComplete();
        }).addOnFailureListener(e -> {
            publishSubject.onError(e);
            publishSubject.onComplete();
        });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<Map<String, Object>> getCollection(String collection) {
        PublishSubject<Map<String, Object>> publishSubject = PublishSubject.create();
        CollectionReference reference = FirebaseFirestore.getInstance().collection(collection);
        Task<QuerySnapshot> query = reference.get();
        query.addOnSuccessListener(queryDocumentSnapshots -> {
            List<DocumentSnapshot> documentSnapshots = queryDocumentSnapshots.getDocuments();
            if (!CollectionUtils.isEmpty(documentSnapshots)) {
                for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                    Map<String, Object> documentSnapshotData = documentSnapshot.getData();
                    publishSubject.onNext(documentSnapshotData);
                }
            }
            publishSubject.onComplete();
        }).addOnFailureListener(e -> {
            publishSubject.onError(e);
            publishSubject.onComplete();
        });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<String> addDeal(String collection, Deal deal) {
        PublishSubject<String> publishSubject = PublishSubject.create();
        firebaseFirestore.collection(collection).add(deal)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        publishSubject.onNext(documentReference.getId());
                        publishSubject.onComplete();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                publishSubject.onError(e);
                publishSubject.onComplete();
            }
        });
        return publishSubject.hide().firstOrError().toObservable();
    }

    public Observable<Boolean> signOut() {
        firebaseAuth.signOut();
        return Observable.just(true);
    }

    public Observable<List<Deal>> getMerchantDeals(String collection, String merchantId) {
        PublishSubject<List<Deal>> publishSubject = PublishSubject.create();
        CollectionReference reference = FirebaseFirestore.getInstance().collection(collection);
        Task<QuerySnapshot> query = reference.whereEqualTo(Constants.MERCHANT_ID, merchantId).get();
        query.addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!CollectionUtils.isEmpty(queryDocumentSnapshots.getDocuments())) {
                    List<Deal> dealList = new ArrayList<>(queryDocumentSnapshots.getDocuments().size());
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        dealList.add(documentSnapshot.toObject(Deal.class));
                    }
                    publishSubject.onNext(dealList);
                }
                publishSubject.onComplete();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                publishSubject.onError(e);
                publishSubject.onComplete();
            }
        });
        return publishSubject.hide().firstOrError().toObservable();
    }
}

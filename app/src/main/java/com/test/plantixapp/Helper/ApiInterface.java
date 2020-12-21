package com.test.plantixapp.Helper;

import com.test.plantixapp.Model.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    // https://jsonplaceholder.typicode.com/posts
    // I'm using the json response from the above URl for the card details in the list view.
    @GET("/posts")
    Call<List<UserData>> getUsers();
}

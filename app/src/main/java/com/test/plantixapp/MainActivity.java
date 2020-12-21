package com.test.plantixapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.plantixapp.Helper.ApiClient;
import com.test.plantixapp.Helper.ApiInterface;
import com.test.plantixapp.Model.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    List<UserData> userDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_user_list);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        localUser();
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sw = findViewById(R.id.toggle_switch);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                getUsers();
            } else {
                localUser();
            }
        });

    }

    public void getUsers() {

        Call<List<UserData>> call = apiInterface.getUsers();
        call.enqueue(new Callback<List<UserData>>() {

            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<List<UserData>> call, Response<List<UserData>> response) {
                initUserdata(response.body());
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<List<UserData>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void initUserdata(List<UserData> UserData) {
        userDataList = null;
        userDataList = new ArrayList<>();
        userDataList.addAll(UserData);
        initRecyclerView();
    }

    private void localUser() {
        userDataList = null;
        userDataList = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            userDataList.add(new UserData(i ,i, "title" + i, getDescription(i)));
        }
        initRecyclerView();
    }

    private String getDescription(int i) {
        return i + ". Lorem ipsum dolor sit amet, consectetur adipiscing elit. In vitae condimentum mi. Maecenas laoreet tempus arcu vitae facilisis. Nulla facilisi. Duis iaculis ullamcorper enim ut aliquam. Suspendisse tincidunt dapibus viverra. Etiam tincidunt neque diam, sit amet aliquet dui pretium sed. Phasellus sed ultrices elit. Etiam commodo ultrices justo in euismod. Etiam eu mattis orci. Pellentesque nec congue ipsum. Maecenas finibus sed purus et lacinia.";
    }


    private void initRecyclerView() {
        UserListAdapter tokenListAdapter = new UserListAdapter(userDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(tokenListAdapter);
    }


}
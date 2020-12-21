package com.test.plantixapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.plantixapp.Model.UserData;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    List<UserData> userDataList;
    Context context;

    public UserListAdapter(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_card, parent, false);
        context = parent.getContext();
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        UserData userData = userDataList.get(position);

        // converting the lowercase string to capitalizing the first letter
        String title = String.valueOf(userData.getTitle().charAt(0)).toUpperCase() + userData.getTitle().substring(1);
        holder.title.setText(title);
        holder.description.setText(userData.getBody());
        holder.id.setText("Id : " + userData.getId());

    }

    @Override
    public int getItemCount() {
        return userDataList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView id;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_desc);
            id = itemView.findViewById(R.id.tv_id);

        }
    }
}

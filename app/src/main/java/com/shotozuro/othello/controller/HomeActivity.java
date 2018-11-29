package com.shotozuro.othello.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.shotozuro.othello.R;
import com.shotozuro.othello.controller.retrofit.RetrofitClientInstance;
import com.shotozuro.othello.controller.retrofit.User;
import com.shotozuro.othello.controller.retrofit.UserService;
import com.shotozuro.othello.model.Board;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    Spinner spinnerPlayer1, spinnerPlayer2;
    String player1, player2;
    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinnerPlayer1 = findViewById(R.id.spinner_1);
        spinnerPlayer2 = findViewById(R.id.spinner_2);

        getPlayers();
    }

    public void onSubmit (View v) {
        player1 = spinnerPlayer1.getSelectedItem().toString();
        player2 = spinnerPlayer2.getSelectedItem().toString();

        if (!player1.equals(player2)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("playerOneName", player1);
            intent.putExtra("playerTwoName", player2);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Choose another name..", Toast.LENGTH_SHORT).show();
        }

    }

    private void getPlayers () {
        UserService service = RetrofitClientInstance.getRetrofitInstance().create(UserService.class);
        Call<List<User>> call = service.getAllUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                initSpinnerPlayer(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Try Again..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerPlayer (List<User> users) {
        List<String> names = new ArrayList<>();

        for( User user: users) {
            names.add(user.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, names);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerPlayer1.setAdapter(dataAdapter);
        spinnerPlayer2.setAdapter(dataAdapter);
    }
}

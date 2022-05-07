package com.techroof.searchrishtaadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.techroof.searchrishtaadmin.Models.AppliedUsers;

public class HomeActivity extends AppCompatActivity {

    private CardView crdActivatedusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        crdActivatedusers=findViewById(R.id.crd_activated_plan);

        crdActivatedusers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent movetoAppliedUsers=new Intent(HomeActivity.this, ShowAppliedUsers.class);
                startActivity(movetoAppliedUsers);
            }
        });
    }
}
package com.techroof.searchrishtaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techroof.searchrishtaadmin.Adapters.AppliedFragmentViewPagerAdapter;
import com.techroof.searchrishtaadmin.AppliedFragments.ActivatedFragment;
import com.techroof.searchrishtaadmin.AppliedFragments.PendingFragment;

import java.util.HashMap;

public class ActivatingUsers extends AppCompatActivity {

    ImageView img;
    String getImgurl,Uid;
    private ProgressDialog progress;
    private Button btnActivatingplan;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activating_users);
        img=findViewById(R.id.img_applied_proof);
        btnActivatingplan=findViewById(R.id.btn_activated_plan);
        firestore=FirebaseFirestore.getInstance();
        showLoadingDialog();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            getImgurl = extras.getString("img");
            Uid=extras.getString("moveid");
            dismissLoadingDialog();
           // Toast.makeText(getApplicationContext(), ""+displayapproved, Toast.LENGTH_SHORT).show();
        }

        Glide.with(getApplicationContext()).load(getImgurl).into(img);
        btnActivatingplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Updatestatusactivated();
               Updatestatusactivatedusers();

            }
        });



    }

    private void Updatestatusactivated() {

        DocumentReference documentReference = firestore.collection("UserProofDetails").document(Uid);
        HashMap hasshmap = new HashMap<>();
        hasshmap.put("Status", "Activated");
        documentReference.update(hasshmap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

                Toast.makeText(getApplicationContext(), "DATA UPDATED", Toast.LENGTH_LONG).show();
                Intent moveTohome=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(moveTohome);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Sorry Data Haven't updated!", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void Updatestatusactivatedusers() {

        DocumentReference documentReference = firestore.collection("users").document(Uid);
        HashMap hasshmap = new HashMap<>();
        hasshmap.put("activatedstatus", "Activated");
        documentReference.update(hasshmap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {

                Toast.makeText(getApplicationContext(), "DATA UPDATED", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Sorry Data Haven't updated!", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(this);
            //  progress.setTitle("IN PROGRESS");
            progress.setCanceledOnTouchOutside(false);
            progress.setMessage("LOADING...");
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }
}
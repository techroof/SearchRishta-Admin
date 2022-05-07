package com.techroof.searchrishtaadmin.AppliedFragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techroof.searchrishtaadmin.Adapters.AppliedFragmentRecyclerViewAdapter;
import com.techroof.searchrishtaadmin.Models.AppliedUsers;
import com.techroof.searchrishtaadmin.R;

import java.util.ArrayList;

public class ActivatedFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private ArrayList<AppliedUsers> requestDataArrayList;
    private AppliedFragmentRecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView showRv;
    private FirebaseFirestore firestore;
    private LinearLayoutManager layoutManagershowrequests;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String requestsCategory, catVal;
    private ProgressDialog progress;


    public ActivatedFragment() {
        // Required empty public constructor
    }

    public static ActivatedFragment newInstance(String param1, String param2) {
        ActivatedFragment fragment = new ActivatedFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_activated, container, false);
        requestDataArrayList = new ArrayList<>();
        showRv = view.findViewById(R.id.activated_rv);
        firestore = FirebaseFirestore.getInstance();
        //Toast.makeText(getContext(), "yes" + catVal, Toast.LENGTH_LONG).show();
        showLoadingDialog();
        getActivatedplans();
        return view;
    }

    private void getActivatedplans() {
        firestore.collection("UserProofDetails")
                .whereEqualTo("Status", "Activated")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                    AppliedUsers listData = documentSnapshot.toObject(AppliedUsers.class);
                    requestDataArrayList.add(listData);


                }

                layoutManagershowrequests = new LinearLayoutManager(getContext(),
                        LinearLayoutManager.VERTICAL, false);
                showRv.setLayoutManager(layoutManagershowrequests);
                recyclerViewAdapter = new AppliedFragmentRecyclerViewAdapter(requestDataArrayList, getContext());
                showRv.setAdapter(recyclerViewAdapter);
                dismissLoadingDialog();

            }
        });

    }

    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(getActivity());
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
    }}

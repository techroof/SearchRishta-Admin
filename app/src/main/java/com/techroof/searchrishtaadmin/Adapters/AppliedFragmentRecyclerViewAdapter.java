package com.techroof.searchrishtaadmin.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.techroof.searchrishtaadmin.ActivatingUsers;
import com.techroof.searchrishtaadmin.Models.AppliedUsers;
import com.techroof.searchrishtaadmin.R;

import java.util.ArrayList;

public class AppliedFragmentRecyclerViewAdapter extends RecyclerView.Adapter<AppliedFragmentRecyclerViewAdapter.ViewAdapter> {
    private static final String TAG = "RecyclerViewAdapter";


    private ArrayList<AppliedUsers> ShowRequestData;
    private Context context;

    public AppliedFragmentRecyclerViewAdapter(ArrayList<AppliedUsers> ShowRequestData, Context context) {
        this.ShowRequestData = ShowRequestData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: 1");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pending_recyclerview, parent, false);
        return new ViewAdapter(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {


        Log.d(TAG, "onBindViewHolder: called");
        //ProductsData ld = ProductlistData.get(position);
        AppliedUsers ld = ShowRequestData.get(position);
        holder.textView.setText(ld.getName());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movecategory = ld.getUid();
                String moveimg=ld.getImage();
                Log.d(TAG, "onClick: " + ld.getUid());
                Intent intent = new Intent(context.getApplicationContext(), ActivatingUsers.class );
                intent.putExtra("moveid", movecategory);
                intent.putExtra("img",moveimg);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                notifyItemRemoved(holder.getAdapterPosition());

            }
        });


    }

    @Override
    public int getItemCount() {
        return ShowRequestData.size();
    }

    public class ViewAdapter extends RecyclerView.ViewHolder {
        TextView textView;
        CardView relativeLayout;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tvname);
            relativeLayout = itemView.findViewById(R.id.crdView);

        }
    }

}

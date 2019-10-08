package com.bhardwaj.abhishek.payrollapp.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.abhishek.payrollapp.Model.Earnings;
import com.bhardwaj.abhishek.payrollapp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerListAdapter extends FirestoreRecyclerAdapter<Earnings, RecyclerListAdapter.RecyclerListViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<Earnings> mEarnings;


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public RecyclerListAdapter(@androidx.annotation.NonNull FirestoreRecyclerOptions<Earnings> options) {
        super(options);

    }


    @NonNull
    @Override
    public RecyclerListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);

        return new RecyclerListViewHolder(v);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerListViewHolder holder, int position, @NonNull Earnings model) {

        holder.date.setText(String.valueOf(model.getDate()));
        holder.payStubId.setText(model.getPayStubId());
        holder.amount.setText(String.valueOf(model.getAmount()));

    }



    public class RecyclerListViewHolder extends RecyclerView.ViewHolder {

        TextView amount,payStubId,date;
        private int mPosition;

        public RecyclerListViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            payStubId = itemView.findViewById(R.id.uniqueId);
            date = itemView.findViewById(R.id.date);
        }


    }
}

package com.bhardwaj.abhishek.payrollapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bhardwaj.abhishek.payrollapp.Adapters.RecyclerListAdapter;
import com.bhardwaj.abhishek.payrollapp.Model.Earnings;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class EarningFragment extends Fragment {
    Context c;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String companies,userId;

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference ;//= firestore.collection("Companies/PP180000/Users/1500/PayStubs/A00202");
    private RecyclerListAdapter recyclerListAdapter;
    String path;
    public EarningFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static EarningFragment newInstance(String param1, String param2) {
        EarningFragment fragment = new EarningFragment();


        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

         final View view = inflater.inflate(R.layout.earning_fragment, container, false);
         recyclerView = view.findViewById(R.id.recyclerView);


        progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
        startAsyncTask();





        return view;
    }

    private void startAsyncTask() {
        new FetchDataAsync().execute();
    }

    private class FetchDataAsync extends AsyncTask<Void,Integer,FirestoreRecyclerOptions<Earnings>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected FirestoreRecyclerOptions<Earnings> doInBackground(Void... voids) {
            Bundle extras = getArguments();
            companies = extras.getString("companyId");
            userId = extras.getString("uId");
            collectionReference = firestore.collection("Companies").document(companies)
                    .collection("Users").document(userId).collection("PayStubs");
            Query query = collectionReference;
            FirestoreRecyclerOptions<Earnings> options = new FirestoreRecyclerOptions.Builder<Earnings>()
                    .setQuery(query, Earnings.class)
                    .build();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }


            return options;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }
        @Override
        protected void onPostExecute(FirestoreRecyclerOptions<Earnings> earningFirestoreRecyclerOptions) {
            super.onPostExecute(earningFirestoreRecyclerOptions);

            Toast.makeText(getContext(), "onPostExecute", Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
            recyclerListAdapter = new RecyclerListAdapter(earningFirestoreRecyclerOptions,getContext());
            recyclerListAdapter.startListening();
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(recyclerListAdapter);
            recyclerListAdapter.setOnItemClickListener(new RecyclerListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                    Earnings earnings = documentSnapshot.toObject(Earnings.class);
                    String id = documentSnapshot.getId();
                    Toast.makeText(c, "Position"+position + "ID :"+id, Toast.LENGTH_SHORT).show();
                    String path = documentSnapshot.getReference().getPath();


                }
            });

        }
    }






    @Override
    public void onAttach(Context context) {
        c = context;
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    @Override
    public void onStop() {
        super.onStop();
        recyclerListAdapter.stopListening();
    }
}

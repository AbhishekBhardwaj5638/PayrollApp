package com.bhardwaj.abhishek.payrollapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class VerifyAccount extends AppCompatActivity {
    EditText companyId,userId;
    TextView verifyCompanyId, help;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    String id,uId;
    boolean validCompanyId=false;

    CollectionReference collectionReference = firestore.collection("Companies");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_verify_account);
        companyId = (EditText)findViewById(R.id.company_id);
        userId = (EditText)findViewById(R.id.user_id);
        verifyCompanyId = (TextView)findViewById(R.id.verify_company_id);
        help = (TextView) findViewById(R.id.help);

        verifyCompanyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=companyId.getText().toString();
               checkCompanyId();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void checkCompanyId() {
        id=companyId.getText().toString().trim();
        collectionReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Verify Account", document.getId() + " => " + document.getData());
                                if(document.getId().equals(id)){
                                    verifyCompanyId.setText("Company Id is Verified");
                                    validCompanyId=true;
                                    break;
                                }
                                else{
                                    verifyCompanyId.setText("Invalid Company Id");
                                    validCompanyId=false;
                                }
                            }
                        } else {
                            Log.d("VerifyAccount", "Error getting documents: ", task.getException());
                            Toast.makeText(VerifyAccount.this, "Company Id does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        { case R.id.save:
            {
                uId = userId.getText().toString().trim();
                checkCompanyId();
                if (validCompanyId) {
                    try {
                        collectionReference.document(id).collection("Users").get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()){

                                            for (QueryDocumentSnapshot document : task.getResult()){

                                                if (document.getId().equals(uId)){

                                                    Intent i = new Intent(VerifyAccount.this,MainActivity.class);
                                                    String ref = collectionReference.document(id).collection("Users").document(uId).getPath();
                                                    i.putExtra("companyId",companyId.getText().toString());
                                                    i.putExtra("uId",uId);
                                                    startActivity(i);

                                                }
                                                else{
                                                    Toast.makeText(VerifyAccount.this, "User Id does not exist", Toast.LENGTH_SHORT).show();
                                                }

                                            }}

                                    }
                                });
                    }catch (Exception e){
                        Log.d("getting User Id",e.getMessage());
                        Toast.makeText(this, "User Id is invalid", Toast.LENGTH_SHORT).show();

                    }

                }else {
                    Toast.makeText(this, "Invalid Company Id", Toast.LENGTH_SHORT).show();
                }
                            }
        }
        return super.onOptionsItemSelected(item);
    }
}

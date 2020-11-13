package com.example.gd1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    Button signout;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    TextView demail,dnum;
    String num;
    FirebaseDatabase firebaseDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bootom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        signout=findViewById(R.id.Signout);
       demail=findViewById(R.id.demail);
        mAuth = FirebaseAuth.getInstance();
      // firebaseUser=mAuth.getCurrentUser();
       dnum=findViewById(R.id.dnum);
      // dnum.setText(num);
     //   demail.setText(firebaseUser.getEmail());
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getInstance().getReference("UserData")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = "";
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                     value=postSnapshot.getValue().toString();
                }
                demail.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        signout.setOnClickListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem mitem) {

                switch (mitem.getItemId())
                {
                    case R.id.profile:

                        return  true;
                    case R.id.homee:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        mAuth.signOut();
        Intent intent=new Intent(Profile.this,Login.class);
        startActivity(intent);
    }
}

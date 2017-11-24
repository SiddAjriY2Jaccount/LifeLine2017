package lifeline2017.example.com.lifeline2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AcceptDetails extends AppCompatActivity implements View.OnClickListener{

    Button upload;
    EditText namee,phonee;
    Spinner bloodgroup;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_details);
        upload = (Button) findViewById(R.id.upload);
        namee = (EditText) findViewById(R.id.name);
        phonee = (EditText) findViewById(R.id.phone);
        bloodgroup = (Spinner) findViewById(R.id.bloodgrp);

        upload.setOnClickListener(this);
    }

    @Override
    public void onClick( View view)
    {
        if(view == upload){
            uploaddetails();
            finish();
            startActivity(new Intent(this,MapsActivity.class));
        }

    }

    private void uploaddetails() {
        String username = namee.getText().toString().trim();
        String phoneno= phonee.getText().toString().trim();
        String bgg = bloodgroup.getSelectedItem().toString();
        mAuth =FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String UID= user.getUid();
        myRef.child(bgg).child(UID).child("BloodGroup").setValue(bgg);
        myRef.child(bgg).child(UID).child("Name").setValue(username);
        myRef.child(bgg).child(UID).child("Phno").setValue(phoneno);
        myRef.child(bgg).child(UID).child("Location").child("Latitude").setValue("True");
        myRef.child(bgg).child(UID).child("Location").child("Longitude").setValue("True");
        myRef.child("Users").child(UID).setValue(bgg);


    }
}

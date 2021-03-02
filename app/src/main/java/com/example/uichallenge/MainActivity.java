package com.example.uichallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTxtName, edtTxtEmail,edtTxtPassword ,edtTxtRePassword ;
    private Button btnRegister ,btnPickImage;
    private TextView txtName ,txtPassword,txtEmail,txtRePassword,txtLicense, txtGender ,txtCountry;
    private ImageView profileImage;
    private CheckBox checkAgreement;
    private Spinner spinnerCountry;
    private RadioGroup rgGender;
    private ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiViews();
        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Yet to take talk", Toast.LENGTH_SHORT).show();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intiRegister();
            }
        });
    }

    private void intiRegister(){
        Log.d(TAG, "intiRegister: Started");
        if(validateData()){
            if(checkAgreement.isChecked()){
                showSnackBar();
            }else{
                Toast.makeText(this, "You Need to agree to License Agreement", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar(){
        Log.d(TAG, "showSnackBar: Started");
        txtName.setVisibility(View.GONE);
        txtEmail.setVisibility(View.GONE);
        txtPassword.setVisibility(View.GONE);
        txtRePassword.setVisibility(View.GONE);

        String Name = edtTxtName.getText().toString();
        String Email = edtTxtEmail.getText().toString();
        String Country = spinnerCountry.getSelectedItem().toString();
        String Gender = "";
        switch (rgGender.getCheckedRadioButtonId()){
            case R.id.rbMale:
            Gender = "Male";
            break;
            case R.id.rbFemale:
                Gender = "Female";
                break;
            case R.id.rbOther:
                Gender = "Others";
                        break;
            default:
                Gender = "Unknown";
                break;
        }

        String Snacktext = "Name: " + Name + "\n" +
                "Email: " + Email + "\n" +
                "Gender: " +  Gender  + "\n"
                + "Country: " + Country ;

        Snackbar.make(parent,Snacktext,Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtTxtName.setText("");
                        edtTxtEmail.setText("");
                        edtTxtPassword.setText("");
                        edtTxtRePassword.setText("");
                    }
                }).show();
    }

    private boolean validateData(){
        Log.d(TAG, "validateData: Started");
        if(edtTxtName.getText().toString().equals("")){
            txtName.setVisibility(View.VISIBLE);
            txtName.setText("Enter Your Name:");
            return false;
        }

        if(edtTxtEmail.getText().toString().equals("")){
            txtEmail.setVisibility(View.VISIBLE);
            txtEmail.setText("Enter Your Email:");
            return  false;
        }

        if(edtTxtPassword.getText().toString().equals("")){
            edtTxtPassword.setVisibility(View.VISIBLE);
            txtPassword.setText("Enter Password");
            return false;
        }

        if(edtTxtRePassword.getText().toString().equals("")){
            edtTxtRePassword.setVisibility(View.VISIBLE);
            txtRePassword.setText("Re-enter Password");
            return false;
        }

        if(!edtTxtPassword.getText().toString().equals(edtTxtRePassword.getText().toString())){
            txtRePassword.setVisibility(View.VISIBLE);
            txtRePassword.setText("Password Doesn't Match");
            return false;
        }
        return true;
    }


    
    private void intiViews(){
        Log.d(TAG, "intiViews: Started");
        edtTxtName = findViewById(R.id.edtTxtName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtRePassword = findViewById(R.id.edtTxtRePassword);

        btnRegister = findViewById(R.id.btnRegister);
        btnPickImage  = findViewById(R.id.btnPickImage);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtRePassword = findViewById(R.id.txtRePassword);

//        txtCountry = findViewById(R.id.txtCountry);
//        txtGender = findViewById(R.id.txtGender);
//        txtLicense = findViewById(R.id.txtLicense);
//        profileImage = findViewById(R.id.profileImage);

        checkAgreement = findViewById(R.id.checkAgreement);
        rgGender = findViewById(R.id.rgGender);
        spinnerCountry = findViewById(R.id.spinnerCountry);
        parent = findViewById(R.id.parent);

    }
}



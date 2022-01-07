package com.example.car_service_wecarcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class addApActivity extends AppCompatActivity {

    EditText vehicleNo,vehicleName,appointmentDate,appointmentTime,anySpecialInstruction;
    Button btnAdd,btnBack;

    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ap);

        vehicleNo = (EditText) findViewById(R.id.txtVno);
        vehicleName = (EditText) findViewById(R.id.txtVname);
        appointmentDate = (EditText) findViewById(R.id.txtApdate);
        appointmentTime = (EditText) findViewById(R.id.txtAptime);
        anySpecialInstruction = (EditText) findViewById(R.id.txtAsi);

       btnAdd = (Button) findViewById(R.id.btnAdd);
       btnBack = (Button) findViewById(R.id.btnBack);



       //initialize validation Style
       awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

       //add validation
        awesomeValidation.addValidation(this,R.id.txtVno,
                RegexTemplate.NOT_EMPTY,R.string.invalid_vno);

        awesomeValidation.addValidation(this,R.id.txtVname,
                RegexTemplate.NOT_EMPTY,R.string.invalid_vehicleName);


        awesomeValidation.addValidation(this,R.id.txtApdate,
                RegexTemplate.NOT_EMPTY,R.string.invalid_appointmentDate);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check Validation
                if (awesomeValidation.validate()){
                    //on Success
                    Toast.makeText(getApplicationContext()
                    ,"Form Validation Succesfull..",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext()
                    ,"Validation Faild.",Toast.LENGTH_SHORT).show();
                }
            }
        });




       btnAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               insertData();
               clearAll();

           }
       });

       btnBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

    }




    //insert Data
    private void insertData()
    {
        Map<String,Object> map =new HashMap<>();

        map.put("vehicleNo",vehicleNo.getText().toString());
        map.put("vehicleName",vehicleName.getText().toString());
        map.put("appointmentDate",appointmentDate.getText().toString());
        map.put("appointmentTime",appointmentTime.getText().toString());
        map.put("anySpecialInstruction",anySpecialInstruction.getText().toString());

        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").
                getReference().child("appoinment").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(addApActivity.this, "Data insertion Successfully", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(addApActivity.this, "Error Data insertion ", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll()
    {
        vehicleNo.setText("");
        vehicleName.setText("");
        appointmentDate.setText("");
        appointmentTime.setText("");
        anySpecialInstruction.setText("");


    }

}
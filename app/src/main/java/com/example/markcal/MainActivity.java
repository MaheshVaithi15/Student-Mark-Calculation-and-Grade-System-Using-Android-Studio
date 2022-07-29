package com.example.markcal;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13;
    Button btn1,btn2,btn3;
    double avg;
    String grade;
    double total;
    int strol;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed1 = findViewById(R.id.strol);
        ed2 = findViewById(R.id.mark1);
        ed3 = findViewById(R.id.lmark1);
        ed4 = findViewById(R.id.mark2);
        ed5 = findViewById(R.id.lmark2);
        ed6 = findViewById(R.id.mark3);
        ed7 = findViewById(R.id.lmark3);
        ed8 = findViewById(R.id.mark4);
        ed9 = findViewById(R.id.mark5);
        ed10 = findViewById(R.id.mark6);
        //ed11 = findViewById(R.id.total);
        ed12 = findViewById(R.id.avg);
        ed13 = findViewById(R.id.grade);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        db = FirebaseFirestore.getInstance();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> user = new HashMap<>();
                user.put("Grade",grade);
                user.put("Average",avg);
                user.put("ID",strol);


// Add a new document with a generated ID
                db.collection("users")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this, "Data Stored", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                                //startActivity(intent);
                                //finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(MainActivity.this, "Error -", Toast.LENGTH_SHORT).show();
                            }
                        });


                stmarks();
            }

        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clear();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nxt();
            }
        });

    }


    public void stmarks() {




        int m1, lm1, m2, lm2, m3, lm3, m4, m5, m6;

        m1 = Integer.parseInt(ed2.getText().toString());
        lm1 = Integer.parseInt(ed3.getText().toString());
        m2 = Integer.parseInt(ed4.getText().toString());
        lm2 = Integer.parseInt(ed5.getText().toString());
        m3 = Integer.parseInt(ed6.getText().toString());
        lm3 = Integer.parseInt(ed7.getText().toString());
        m4 = Integer.parseInt(ed8.getText().toString());
        m5 = Integer.parseInt(ed9.getText().toString());
        m6 = Integer.parseInt(ed10.getText().toString());



        total = m1 * (0.75) + lm1 * (0.25) + m2 * (0.75) + lm2 * (0.25) + m3 * (0.75) + lm3 * (0.25) + m4 + m5 + m6;


        //ed11.setText(String.valueOf(total));

        avg = total/ 6;

        ed12.setText(String.valueOf(avg));



        if (avg > 90) {
            ed13.setText("S");

        } else if (avg > 80) {
            ed13.setText("A");

        } else if (avg > 70) {
            ed13.setText("B");

        } else if (avg > 60) {
            ed13.setText("C");

        } else if (avg > 50) {
            ed13.setText("D");

        } else if (avg > 40) {
            ed13.setText("E");

        } else {
            ed13.setText("FAIL");

        }


    }




        public void clear()
    {
        ed1.setText("");
        ed2.setText("");
        ed3.setText("");
        ed4.setText("");
        ed5.setText("");
        ed6.setText("");
        ed7.setText("");
        ed8.setText("");
        ed9.setText("");
        ed10.setText("");
        //ed11.setText("");
        ed12.setText("");
        ed13.setText("");

        ed1.requestFocus();

    }

    public void nxt()
    {
        Intent intent =new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
        finish();


    }
}



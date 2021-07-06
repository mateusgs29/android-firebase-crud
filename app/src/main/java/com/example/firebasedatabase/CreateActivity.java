package com.example.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateActivity extends AppCompatActivity {

    EditText createName;
    EditText createCPF;
    EditText createEmail;
    EditText createPhone;
    Button btnCreate;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        createName = findViewById(R.id.create_name);
        createCPF = findViewById(R.id.create_cpf);
        createEmail = findViewById(R.id.create_email);
        createPhone = findViewById(R.id.create_phone);
        btnCreate = findViewById(R.id.btn_create);
        btnCancel = findViewById(R.id.btn_create_cancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
            }
        });
    }
}
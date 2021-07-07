package com.example.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CreateActivity extends AppCompatActivity {

    private EditText createName;
    private EditText createEmail;
    private EditText createPhone;
    private Button btnCreate;
    private Button btnCancel;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        dbRef = FirebaseDatabase.getInstance().getReference();

        createName = findViewById(R.id.create_name);
        createEmail = findViewById(R.id.create_email);
        createPhone = findViewById(R.id.create_phone);
        btnCreate = findViewById(R.id.btn_create);
        btnCancel = findViewById(R.id.btn_create_cancel);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(createName.getText().toString())) {
                        Toast.makeText(CreateActivity.this, "Nome não informado", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(createEmail.getText().toString())) {
                        Toast.makeText(CreateActivity.this, "E-mail não informado", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(createPhone.getText().toString())) {
                        Toast.makeText(CreateActivity.this, "Telefone não informado", Toast.LENGTH_SHORT).show();
                    } else {
                        People p = new People();
                        p.setUid(UUID.randomUUID().toString());
                        p.setName(createName.getText().toString());
                        p.setEmail(createEmail.getText().toString());
                        p.setPhone(createPhone.getText().toString());
                        dbRef.child("People").child(p.getUid()).setValue(p);
                        clearInputs();
                        startActivity(new Intent(CreateActivity.this, ListActivity.class));
                    }
                } catch (Exception e) {
                    Toast.makeText(CreateActivity.this, "Não foi possível cadastrar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
            }
        });
    }

    private void clearInputs() {
        createName.setText("");
        createEmail.setText("");
        createPhone.setText("");
    }
}
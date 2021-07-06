package com.example.firebasedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class UpdateDeleteActivity extends AppCompatActivity {

    EditText editName;
    EditText editCPF;
    EditText editEmail;
    EditText editPhone;
    Button btnEdit;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        editName = findViewById(R.id.edit_name);
        editCPF = findViewById(R.id.edit_cpf);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
    }
}
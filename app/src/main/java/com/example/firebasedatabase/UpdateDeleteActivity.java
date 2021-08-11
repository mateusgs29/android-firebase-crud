package com.example.firebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.UUID;

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editEmail;
    private EditText editPhone;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnCancel;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        dbRef = FirebaseDatabase.getInstance().getReference();
        String peopleUId = getIntent().getStringExtra("peopleUId");
        DatabaseReference people = dbRef.child("People").child(peopleUId);

        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnCancel = findViewById(R.id.btn_edit_cancel);

        people.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()) {
                    editName.setText(Objects.requireNonNull(snapshot.child("name").getValue()).toString());
                    editEmail.setText(Objects.requireNonNull(snapshot.child("email").getValue()).toString());
                    editPhone.setText(Objects.requireNonNull(snapshot.child("phone").getValue()).toString());
                } else {
                    Toast.makeText(UpdateDeleteActivity.this, "Os dados não carregaram", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editName.getText().toString())) {
                    Toast.makeText(UpdateDeleteActivity.this, "Nome não informado", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editEmail.getText().toString())) {
                    Toast.makeText(UpdateDeleteActivity.this, "E-mail não informado", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(editPhone.getText().toString())) {
                    Toast.makeText(UpdateDeleteActivity.this, "Telefone não informado", Toast.LENGTH_SHORT).show();
                } else {
                    People p = new People();
                    p.setUid(peopleUId);
                    p.setName(editName.getText().toString());
                    p.setEmail(editEmail.getText().toString());
                    p.setPhone(editPhone.getText().toString());
                    people.setValue(p);
                    clearInputs();
                    startActivity(new Intent(UpdateDeleteActivity.this, ListActivity.class));
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                people.removeValue();
                clearInputs();
                startActivity(new Intent(UpdateDeleteActivity.this, ListActivity.class));
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInputs();
                startActivity(new Intent(UpdateDeleteActivity.this, ListActivity.class));
            }
        });
    }

    private void clearInputs() {
        editName.setText("");
        editEmail.setText("");
        editPhone.setText("");
    }
}
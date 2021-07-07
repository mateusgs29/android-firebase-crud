package com.example.firebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView list;
    private Button btnToCreate;

    private List<People> listPeople = new ArrayList<People>();
    private ArrayAdapter<People> arrayAdapterPeople;

    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbRef = FirebaseDatabase.getInstance().getReference();

        list = findViewById(R.id.list_people);
        btnToCreate = findViewById(R.id.btn_list_to_create);

        dbRef.child("People").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listPeople.clear();
                for (DataSnapshot objSnapshot:snapshot.getChildren()) {
                    People p = objSnapshot.getValue(People.class);
                    listPeople.add(p);
                }
                arrayAdapterPeople = new ArrayAdapter<People>(ListActivity.this,
                        android.R.layout.simple_list_item_1, listPeople);
                list.setAdapter(arrayAdapterPeople);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnToCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, CreateActivity.class));
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                People peopleSelected = (People) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListActivity.this, UpdateDeleteActivity.class);
                intent.putExtra("peopleUId", peopleSelected.getUid());
                startActivity(intent);
            }
        });
    }
}
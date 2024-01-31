package com.example.list;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private Spinner spinnerNames;
    private Button btnInsert, btnDelete, btnSearch;
    private ArrayList<String> nameList;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        spinnerNames = findViewById(R.id.spinnerNames);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnSearch = findViewById(R.id.btnSearch);

        // Initialize nameList and set up spinnerAdapter
        nameList = new ArrayList<>();
        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nameList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNames.setAdapter(spinnerAdapter);

        // Button click listeners
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertName();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedName();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchName();
            }
        });
    }

    private void insertName() {
        String newName = editTextName.getText().toString().trim();
        if (!newName.isEmpty()) {
            nameList.add(newName);
            spinnerAdapter.notifyDataSetChanged();
            editTextName.getText().clear();
            Toast.makeText(this, "Name inserted: " + newName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteSelectedName() {
        String selectedName = (String) spinnerNames.getSelectedItem();
        if (selectedName != null) {
            nameList.remove(selectedName);
            spinnerAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Name deleted: " + selectedName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a name to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchName() {
        String searchedName = editTextName.getText().toString().trim();
        if (!searchedName.isEmpty() && nameList.contains(searchedName)) {
            int index = nameList.indexOf(searchedName);
            spinnerNames.setSelection(index);
            Toast.makeText(this, "Name found: " + searchedName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Name not found: " + searchedName, Toast.LENGTH_SHORT).show();
        }
    }
}

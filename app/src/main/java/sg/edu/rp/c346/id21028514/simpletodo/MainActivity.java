package sg.edu.rp.c346.id21028514.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    Button btnDlt;
    Button btnClear;
    EditText etAddDlt;
    Spinner spnAddDlt;
    ListView lvResult;
    ArrayList<String> alToDoList;
    ArrayAdapter<String> aaToDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.buttonAdd);
        btnDlt = findViewById(R.id.buttonDlt);
        btnClear = findViewById(R.id.buttonClear);
        etAddDlt = findViewById(R.id.editTextAddDlt);
        spnAddDlt = findViewById(R.id.spinnerAddDlt);
        lvResult = findViewById(R.id.lvEndResult);

        alToDoList = new ArrayList<String>();

        aaToDoList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alToDoList);
        lvResult.setAdapter(aaToDoList);

        spnAddDlt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etAddDlt.setHint("Type in a new task here");
                        btnDlt.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etAddDlt.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDlt.setEnabled(true);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAddDlt.getText().toString().length() != 0) {
                    String newTask = etAddDlt.getText().toString();
                    alToDoList.add(newTask);
                    aaToDoList.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Field is empty", Toast.LENGTH_LONG).show();
                }

            }

        });
        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(etAddDlt.getText().toString());

                if (alToDoList.isEmpty() == true) {
                    Toast.makeText(MainActivity.this, "You don't have any task", Toast.LENGTH_LONG).show();
                } else if (position > alToDoList.size()) {
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                } else {
                    alToDoList.remove(position);
                    aaToDoList.notifyDataSetChanged();
                }

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDoList.clear();
                aaToDoList.notifyDataSetChanged();

            }

        });


    }
}
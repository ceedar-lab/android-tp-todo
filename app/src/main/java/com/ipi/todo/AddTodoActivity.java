package com.ipi.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ipi.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddTodoActivity extends AppCompatActivity {

    /**********  Attributes  **********/

    private EditText editAddTodo;
    private Spinner spinUrgency;
    private Button btnAdd, btnCancel, btnClear;
    private Integer id = 0;

    public static final String KEY_TODO = "todo";
    public static final Integer ADD = 1;
    public static final Integer CANCEL = 0;

    /**********  Methods  **********/

    /**
     * Lancement de l'activité.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Récupération des éléments.
        editAddTodo = findViewById(R.id.editAddTodo);
        spinUrgency = findViewById(R.id.spinUrgency);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        btnClear = findViewById(R.id.btnClear);

        // Création du spinner.
        String[] urgency = new String[] {
                "Low Urgency",
                "Medium Urgency",
                "High Urgency"
        };

        final List<String> urgenciesList = new ArrayList<>(Arrays.asList(urgency));

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item, urgenciesList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinUrgency.setAdapter(spinnerArrayAdapter);

        // Création d'une tâche au clic sur ADD.
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoText = editAddTodo.getText().toString();
                if (todoText.length() < 3) {
                    Toast.makeText(getApplicationContext(), "Texte pas assez long ! (min. 3 caractères)", Toast.LENGTH_SHORT).show();
                } else {
                    Todo todo = new Todo(id, todoText, spinUrgency.getSelectedItem().toString());

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(KEY_TODO, todo);

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    id++;
                    intent.putExtras(bundle);
                    setResult(ADD, intent);
                    finish();
                }
            }
        });

        // Retour à la MainActivity au clic sur CANCEL.
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(CANCEL, intent);
                finish();
            }
        });

        // Effacement du TextView au clic sur CLEAR.
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAddTodo.setText("");
            }
        });
    }

    /**
     * Retour sur la MainActivity au clic sur le bouton retour.
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
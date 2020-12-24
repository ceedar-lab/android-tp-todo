package com.ipi.todo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ipi.todo.pojos.Todo;

import java.util.ArrayList;
import java.util.List;

import static com.ipi.todo.AddTodoActivity.ADD;

public class MainActivity extends AppCompatActivity {

    /**********  Attributes  **********/

    private TextView tvTodos, tvFirst;
    private List<Todo> todoCollection = new ArrayList<>();
    private String todoList = "";
    private String todoFirst = "La liste de tâches est vide";

    private final static String TAG = "TodoActivity";
    private final static String TODO_LIST = "TodoList", TODO_FIRST = "TodoFirst";

    /**********  Methods  **********/

    /**
     * Démarrage de l'application.
     * Récupération de la liste de tâches grâce au savedInstanceState si l'app n'a pas été détruite.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate() called");

        tvTodos = findViewById(R.id.tvTodos);
        tvFirst = findViewById(R.id.tvFirst);

        if (savedInstanceState != null) {
            todoList = savedInstanceState.getString(TODO_LIST);
            todoFirst = savedInstanceState.getString(TODO_FIRST);
        }

        tvTodos.setText(todoList);
        tvFirst.setText(todoFirst);
    }

    /**
     * Récupération des tâches crées dans le AddTodoActivity.
     * Retour à l'écran d'accueil avec affichage des tâches déjà crées en cas de clic sur CANCEL.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ADD) {
            Todo todo = (Todo)data.getSerializableExtra(AddTodoActivity.KEY_TODO);
            todoList = "";
            todoFirst = "";
            todoCollection.add(todo);
            if (todoCollection.size() == 1) todoList = todoCollection.get(0).getName()+ " / " +todoCollection.get(0).getUrgency()+ "\n";
            else for (Todo todos : todoCollection) todoList += todos.getName()+ " / " +todos.getUrgency()+ "\n";
            tvTodos.setText(todoList);
            tvFirst.setText(todoFirst);
        } else {
            todoList = "";
            todoFirst = "";
            if (todoCollection.size() == 0) todoFirst = "La liste de tâches est vide";
            else if (todoCollection.size() == 1) todoList = todoCollection.get(0).getName()+ " / " +todoCollection.get(0).getUrgency()+ "\n";
            else for (Todo todos : todoCollection) todoList += todos.getName()+ " / " +todos.getUrgency()+ "\n";
            tvTodos.setText(todoList);
            tvFirst.setText(todoFirst);
        }
    }

    /**
     * Sauvegarde de l'état de l'activité est mise en pause.
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TodoList", todoList);
        outState.putString("TodoFirst", todoFirst);

        Log.d(TAG, "onSaveInstanceState() called");
    }

    /**
     * Affichage du menu.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Lancement du AddTodoActivity si l'utilisateur clique dans le menu.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAddTodo:
                Intent intent = new Intent(MainActivity.this, AddTodoActivity.class);
                startActivityForResult(intent, 2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**********  Logs Display  **********/

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy() called");
    }
}
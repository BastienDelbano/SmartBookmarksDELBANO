package org.diiage.delbano.smartbookmarksdelbano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        final AddCommentActivity THIS = this;


        //Recuperation des livres depuis la base
        SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);
        ArrayList<Book> books = new ArrayList<>();
        books.addAll(helper.getAllBooks());

        final EditText page = findViewById(R.id.edPage);
        final EditText comment = findViewById(R.id.edCom);
        Button btnValidComment  = findViewById(R.id.btnValidComment);
        btnValidComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(THIS);
                //Ajout d'un commentaire dans la base
                //ICI je rentre en brute l'id du livre puisque je n'ai pas encore géré le spinner
                helper.addComment(2, Integer.parseInt(page.getText().toString()), comment.getText().toString());
                Intent intent = new Intent(AddCommentActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

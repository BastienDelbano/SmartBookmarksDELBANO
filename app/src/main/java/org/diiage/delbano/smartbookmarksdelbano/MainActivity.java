package org.diiage.delbano.smartbookmarksdelbano;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Appel de la création des tables
        SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);
        helper.getWritableDatabase();

        //Recuperation des livres et des commentaires depuis la base pour réaliser les statisques
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<Comment> comments = new ArrayList<>();
        books.addAll(helper.getAllBooks());
        comments.addAll(helper.getAllComment());

        final TextView txtStats = findViewById(R.id.txtStats);
        txtStats.setText("Il y a "+books.size()+" livre(s), "+comments.size()+" commentaire(s), et une moyenne de * commentaire(s) par livre");

        //redirection vers la page des commentaires
        Button btnComments = findViewById(R.id.btnComments);
        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
                startActivity(intent);
            }
        });

        //redirection sur la page de création d'un commentaire
        Button btnAddComment  = findViewById(R.id.btnAddComment);
        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCommentActivity.class);
                startActivity(intent);
            }
        });
    }
}

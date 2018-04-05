package org.diiage.delbano.smartbookmarksdelbano;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        //Recuperation des commentaire depuis la base
        SmartBookmarksDbHelper helper = new SmartBookmarksDbHelper(this);
        ArrayList<Comment> comments = new ArrayList<>();
        comments.addAll(helper.getAllComment());

        //Comment c1 = new Comment(1,4,123,"psdfjsdkjsdf ekfqdflksdfj", "11-03-2133");
        //Comment c2 = new Comment(2,2,433,"poirjlcljlqch qpkffjqef sdppvjsdv sdoifjj", "21-04-2133");
        //comments.add(c1);
        //comments.add(c2);

        ListView lstComment = findViewById(R.id.lvComments);
        CommentAdapter ca = new CommentAdapter(this, comments);
        lstComment.setAdapter(ca);
    }
}

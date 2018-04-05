package org.diiage.delbano.smartbookmarksdelbano;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bastien on 05/04/2018.
 */

public class SmartBookmarksDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String SMARTBOOKMARKS_DB = "smartbookmarks.db";
    public static final String TABLE_BOOKS = "books";
    public static final String TABLE_COMMENTS = "comments";

    public SmartBookmarksDbHelper(Context context) {
        super(context, SMARTBOOKMARKS_DB, null, DATABASE_VERSION);
    }


    /**
     * Création des tables books et comments et insertion des livres
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creation des tables et remplissage de données dans books
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_BOOKS+" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, title TEXT NOT NULL, author TEXT NOT NULL, genre TEXT NOT NULL)");
        sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_COMMENTS+" (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, bookid INTEGER NOT NULL, page INTEGER NOT NULL, comment TEXT NOT NULL, date DATETIME NOT NULL)");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(1,'Les fleurs du mal','Charles Baudelaire','Poèmes')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(2,'Germinal','Emile Zola','Roman')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(3,'Les misérables','Victor Hugo','Roman')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(4,'1984','George Orwell','Science-Fiction')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(5,'Le Meilleur des mondes','Aldous Huxley','Science-Fiction')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(6,'Vingt mille lieues sous les mers','Jules Verne','Aventure')");
        sqLiteDatabase.execSQL("INSERT INTO "+TABLE_BOOKS+" VALUES(7,'Les Trois Mousquetaires','Alexandre Dumas','Aventure')");
    }

    /**
     * Recupere les books de la bdd et les transformes en objet Book
     * @return la liste de tous les livres
     */
    public ArrayList<Book> getAllBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_BOOKS, null);
        ArrayList<Book> books = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book b = new Book(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            books.add(b);
        }
        cursor.close();
        return books;
    }

    /**
     * Recupere les comments de la bdd et les transformes en objet Comment
     * @return la liste de tous les commentaires
     */
    public ArrayList<Comment> getAllComment(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_COMMENTS, null);
        ArrayList<Comment> comments = new ArrayList<>();

        while (cursor.moveToNext()) {
            Comment c = new Comment(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getString(3), cursor.getString(4));
            comments.add(c);
        }
        cursor.close();
        return comments;
    }

    /**
     * Ajoute un commentaire en base de données
     * @param bookId
     * @param page
     * @param comment
     */
    public void addComment(int bookId, int page, String comment){
        SQLiteDatabase db = this.getReadableDatabase();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        ContentValues contentValue = new ContentValues();
        contentValue.put("bookId", bookId);
        contentValue.put("page", page);
        contentValue.put("comment", comment);
        contentValue.put("date", formattedDate);
        db.insert(TABLE_BOOKS, null, contentValue);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

package org.diiage.delbano.smartbookmarksdelbano;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bastien on 05/04/2018.
 */

public class Comment implements Serializable{

    private int id;
    private int bookId;
    private int page;
    private String content;
    private String date;

    public Comment(int id, int bookId, int page, String content, String date) {
        this.id = id;
        this.bookId = bookId;
        this.page = page;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getPage() {
        return page;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

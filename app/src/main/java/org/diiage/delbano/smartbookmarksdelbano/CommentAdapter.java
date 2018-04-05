package org.diiage.delbano.smartbookmarksdelbano;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bastien on 05/04/2018.
 */

public class CommentAdapter extends BaseAdapter {
    ArrayList<Comment> comments;
    Activity activity;

    public CommentAdapter (Activity activity, ArrayList<Comment> comments){
        this.activity = activity;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        LayoutInflater inflater = this.activity.getLayoutInflater();
        if (view == null){
            view = inflater.inflate(R.layout.item_comment, viewGroup, false);

            holder = new ViewHolder();
            holder.txtComment = (TextView)view.findViewById(R.id.txtComment);
            holder.txtBookTitle = (TextView)view.findViewById(R.id.txtBookTitle);
            holder.txtPageNumber = (TextView)view.findViewById(R.id.txtPageNumber);
            holder.txtDateComment = (TextView)view.findViewById(R.id.txtDateComment);

            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        Comment comment = (Comment) getItem(i);

        holder.txtComment.setText(String.valueOf(comment.getContent()));
        holder.txtBookTitle.setText(String.valueOf(comment.getBookId()));
        holder.txtPageNumber.setText(String.valueOf(comment.getPage()));
        holder.txtDateComment.setText(String.valueOf(comment.getDate()));

        return view;
    }

    private static class ViewHolder {
        public TextView txtComment;
        public TextView txtBookTitle;
        public TextView txtPageNumber;
        public TextView txtDateComment;

    }
}

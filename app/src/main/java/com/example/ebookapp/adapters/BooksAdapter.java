package com.example.ebookapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ebookapp.R;
import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.databinding.BookListItemBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksHolder> {

    private Context mCtx;
    private List<Book> mBookList;

    public BooksAdapter(Context mCtx, List<Book> mBookList) {
        this.mCtx = mCtx;
        this.mBookList = mBookList;
    }

    @NonNull
    @Override
    public BooksHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        BookListItemBinding bookListItemBinding= DataBindingUtil.inflate(LayoutInflater.from(mCtx)
                , R.layout.book_list_item,parent,false);

        return new BooksHolder(bookListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksHolder holder, int position) {

        Book book=mBookList.get(position);
        holder.bookListItemBinding.setBook(book);
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public class BooksHolder extends RecyclerView.ViewHolder{

        private BookListItemBinding bookListItemBinding;
        public BooksHolder(BookListItemBinding bookListItemBinding) {
            super(bookListItemBinding.getRoot());
            this.bookListItemBinding=bookListItemBinding;

        }
    }

}

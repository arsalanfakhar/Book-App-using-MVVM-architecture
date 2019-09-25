package com.example.ebookapp.database;

import com.example.ebookapp.database.entity.Book;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao()
public interface BookDAO {
    @Insert
    public long addBook(Book book);

    @Delete
    public void deleteBook(Book book);

    @Update
    public void updateBook(Book book);

    @Query("Select * from tbl_books")
    public LiveData<List<Book>> getAllBooks();

    //live data means it will be done on background thread and it is live
    @Query("Select * from tbl_books where category=:catid")
    public LiveData<List<Book>> getCategoryBooks(long catid);

    @Query("Select * from tbl_books where id=:bookId")
    public LiveData<Book>  getBook(long bookId);

}

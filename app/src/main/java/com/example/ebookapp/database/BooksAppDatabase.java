package com.example.ebookapp.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Book.class, Category.class},version = 1)
public abstract class BooksAppDatabase extends RoomDatabase {

    public abstract BookDAO getBooksDAO();

    public abstract CategoryDAO getCategoryDAO();

    private static BooksAppDatabase instance;

    public static synchronized BooksAppDatabase getInstance(Context context){
     if(instance==null){
         instance= Room.databaseBuilder(context,BooksAppDatabase.class,"books_database").
                    //it means when we are changing or migrating data it will delete old table and make new one
                 fallbackToDestructiveMigration().addCallback(callback).
                         build();
     }

     return instance;
    }

    //to initialize the database with some dummy data
    private static RoomDatabase.Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsynTask(instance).execute();

        }
    };

    private static class InitialDataAsynTask extends AsyncTask<Void,Void,Void>{

        private BookDAO bookDAO;
        private CategoryDAO categoryDAO;

        public InitialDataAsynTask(BooksAppDatabase booksAppDatabase) {
            bookDAO=booksAppDatabase.getBooksDAO();
            categoryDAO=booksAppDatabase.getCategoryDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Category category1=new Category();
            category1.setCategory_name("Text Books");
            category1.setCategory_description("Lorem ipsum");

            Category category2=new Category();
            category2.setCategory_name("Novels");
            category2.setCategory_description("Lorem ipsum");

            Category category3=new Category();
            category3.setCategory_name("Stories");
            category3.setCategory_description("Lorem ipsum");

            categoryDAO.addCategory(category1);
            categoryDAO.addCategory(category2);
            categoryDAO.addCategory(category3);


            Book book1=new Book();
            book1.setMbookname("Castle of Love");
            book1.setMbookprice(20);
            book1.setCategory_id(1);

            Book book2=new Book();
            book2.setMbookname("Bella chao");
            book2.setMbookprice(10);
            book2.setCategory_id(2);

            bookDAO.addBook(book1);
            bookDAO.addBook(book2);

            return null;
        }

    }
}

package com.example.ebookapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.ebookapp.database.BookDAO;
import com.example.ebookapp.database.BooksAppDatabase;
import com.example.ebookapp.database.CategoryDAO;
import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EBookShopRepository {
    private CategoryDAO categoryDAO;
    private BookDAO bookDAO;

    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> books;
    private LiveData<Book> book;

    //Initialize all the things here
    public EBookShopRepository(Application application) {
        BooksAppDatabase booksAppDatabase=BooksAppDatabase.getInstance(application);
        categoryDAO=booksAppDatabase.getCategoryDAO();
        bookDAO=booksAppDatabase.getBooksDAO();
    }

    public LiveData<List<Category>> getCategories() {
        return categoryDAO.getAllCategories();
    }

    public LiveData<List<Book>> getBooks(long category) {
        return bookDAO.getCategoryBooks(category);
    }

    public LiveData<Book> getBook(long bookId) {
        return bookDAO.getBook(bookId);
    }

    public void insertCategory(Category category){
        new InsertCategoryAsyncTask(categoryDAO).execute();
    }

    public void deleteCategory(Category category){
        new DeleteCategoryAsyncTask(categoryDAO).execute();
    }
    public void insertBook(Book book){
        new BookInsertCategoryAsyncTask(bookDAO).execute();
    }
    public void deleteBook(Book book){
        new BookDeleteAsyncTask(bookDAO).execute();
    }
    public void updateBook(Book book){
        new BookUpdateAsyncTask(bookDAO).execute();
    }


    private static class InsertCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDAO categoryDAO;

        public InsertCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.addCategory(categories[0]);
            return null;
        }
    }
    private static class DeleteCategoryAsyncTask extends AsyncTask<Category,Void,Void>{

        private CategoryDAO categoryDAO;

        public DeleteCategoryAsyncTask(CategoryDAO categoryDAO) {
            this.categoryDAO = categoryDAO;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDAO.deleteCategory(categories[0]);
            return null;
        }
    }
    private static class BookInsertCategoryAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public BookInsertCategoryAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.addBook(books[0]);
            return null;
        }
    }

    private static class BookDeleteAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public BookDeleteAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.deleteBook(books[0]);
            return null;
        }
    }

    private static class BookUpdateAsyncTask extends AsyncTask<Book,Void,Void>{

        private BookDAO bookDAO;

        public BookUpdateAsyncTask(BookDAO bookDAO) {
            this.bookDAO = bookDAO;
        }

        @Override
        protected Void doInBackground(Book... books) {
            bookDAO.updateBook(books[0]);
            return null;
        }
    }


}

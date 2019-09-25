package com.example.ebookapp.viewmodel;

import android.app.Application;

import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;
import com.example.ebookapp.repository.EBookShopRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

//for room database
public class MainActivityViewModel extends AndroidViewModel {

    private EBookShopRepository bookShopRepository;

    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> bookofASelectedCategory;
    private LiveData<Book> bookWithAnId;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        bookShopRepository=new EBookShopRepository(application);
    }

    public LiveData<List<Category>> getCategories() {
        categories=bookShopRepository.getCategories();
        return categories;
    }

    public LiveData<List<Book>> getBookofASelectedCategory(long category) {
        bookofASelectedCategory=bookShopRepository.getBooks(category);
        return bookofASelectedCategory;
    }

    public LiveData<Book> getBookWithAnId(long bookId) {
        bookWithAnId=bookShopRepository.getBook(bookId);
        return bookWithAnId;
    }

    public void addNewBook(Book book){
        bookShopRepository.insertBook(book);
    }

    public void updateBook(Book book){
        bookShopRepository.updateBook(book);
    }

    public void deleteBook(Book book){
        bookShopRepository.deleteBook(book);
    }

    public void addCategory(Category category){
        bookShopRepository.insertCategory(category);
    }

}

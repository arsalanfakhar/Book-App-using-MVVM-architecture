package com.example.ebookapp.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ebookapp.MainActivity;
import com.example.ebookapp.R;
import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;
import com.example.ebookapp.repository.EBookShopRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.loader.app.LoaderManager;

//for room database
public class MainActivityViewModel extends AndroidViewModel {

    private EBookShopRepository bookShopRepository;

    private LiveData<List<Category>> categories;
    private LiveData<List<Book>> bookofASelectedCategory;
    private LiveData<Book> bookWithAnId;
    private LiveData<Category> categoryWithId;
    private MutableLiveData<Category> selectedCategory=new MutableLiveData<>();


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        bookShopRepository=new EBookShopRepository(application);

        //Initialize all the things here
        categories=bookShopRepository.getCategories();


        //Now set the selected category in Viewmodel
        this.selectedCategory.setValue(new Category(1,"sad","asd"));


    }

    public LiveData<List<Category>> getCategories() {
        Log.v("Data load","true");

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

    public LiveData<Category> getCatById(long catId){
        categoryWithId=bookShopRepository.getCategoryById(catId);
        return categoryWithId;
    }

    public Category getSelectedCategory() {
        return selectedCategory.getValue();
    }

    public void setSelectedCategory(Category category) {
        selectedCategory.setValue(category);
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

//    @BindingAdapter("android:text")
//    public static void setText(EditText view, String value) {
//        view.setText(value);
//    }
//
//    @InverseBindingAdapter(attribute = "android:text")
//    public static int getText(EditText view) {
//        return Integer.parseInt(view.getText().toString());
//    }
//    @BindingAdapter("onItemSelected")
//    public void itemSelect(Spinner spinner){
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplication(),"bell",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}

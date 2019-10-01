package com.example.ebookapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.ebookapp.adapters.BooksAdapter;
import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;
import com.example.ebookapp.databinding.ActivityMainBinding;
import com.example.ebookapp.viewmodel.MainActivityViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityClickHandler clickHandler;
    private MainActivityViewModel mainActivityViewModel;
    private BooksAdapter booksAdapter;
    public static final int ADDCODE=0;
    public static final int EDITCODE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setLifecycleOwner(this);
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.emptyView.setVisibility(View.GONE);

        mainActivityViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                ArrayAdapter<Category> listArrayAdapter=new ArrayAdapter<Category>(MainActivity.this,R.layout.spinner_item,categories);
                listArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                binding.spinner.setAdapter(listArrayAdapter);

                //Now set the selected category in Viewmodel
                //mainActivityViewModel.setSelectedCategory(categories.get(0));

                Toast.makeText(MainActivity.this,"Spinner created",Toast.LENGTH_SHORT).show();

                loadBooksOfCategory();
            }
        });

        clickHandler=new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);



        binding.spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener            () {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                Category selectedCategory= (Category) item;

                //Update the category in ViewModel

                mainActivityViewModel.setSelectedCategory(selectedCategory);
                loadBooksOfCategory();
            }
        });
        initRecylerView();
    }

    private void initRecylerView(){
        booksAdapter=new BooksAdapter(this,new ArrayList<Book>());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(booksAdapter);

        booksAdapter.setClickListener(new BooksAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Book book) {
                Intent intent=new Intent(MainActivity.this,AddBook.class);
                intent.putExtra(AddBook.BOOK_ID,book.getMbookid());
                intent.putExtra(AddBook.BOOK_CAT_ID,""+book.getCategory_id());
                intent.putExtra(AddBook.BOOK_NAME,book.getMbookname());
                intent.putExtra(AddBook.BOOK_UNIT_PRICE,book.getMbookprice());
                Log.v("intentStr",""+book.getCategory_id());
                startActivityForResult(intent,EDITCODE);
            }
        });

    }


    //Call this method to load books of a category
    public void loadBooksOfCategory(){
        long categoryId=mainActivityViewModel.getSelectedCategory().getMcategory_id();
        Log.v("Catid:",""+categoryId);

        mainActivityViewModel.getBookofASelectedCategory(categoryId).observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                if(books.isEmpty()){
                    binding.emptyView.setVisibility(View.VISIBLE);
                }
                else {
                    binding.emptyView.setVisibility(View.GONE);
                }

                booksAdapter.setmBookList(books);
            }
        });
    }


    public class MainActivityClickHandler {
        Context mCtx;

        public MainActivityClickHandler(Context mCtx) {
            this.mCtx = mCtx;
        }
        public void onFABclicked(View view){
            //reset viewmodelcategory
            mainActivityViewModel.setSelectedCategory(new Category(1,"sad","asd"));

            Intent intent=new Intent(MainActivity.this,AddBook.class);
            startActivityForResult(intent,ADDCODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==ADDCODE && data!=null){
            Toast.makeText(this,"addcode",Toast.LENGTH_SHORT).show();
            Book book=new Book();


            book.setMbookname(data.getStringExtra(AddBook.BOOK_NAME));

            String price=data.getStringExtra(AddBook.BOOK_UNIT_PRICE);
            Log.v("price",price);

            book.setMbookprice(Integer.valueOf(price));

            book.setCategory_id(data.getLongExtra(AddBook.BOOK_CAT_ID,1));
            mainActivityViewModel.addNewBook(book);

        }
        else if(resultCode==RESULT_OK && requestCode==EDITCODE && data!=null){

            Book book=new Book();

            book.setMbookname(data.getStringExtra(AddBook.BOOK_NAME));

            String price=data.getStringExtra(AddBook.BOOK_UNIT_PRICE);
            Log.v("price",price);

            book.setMbookprice(Integer.valueOf(price));

            book.setCategory_id(data.getLongExtra(AddBook.BOOK_CAT_ID,1));
            book.setMbookid(data.getLongExtra(AddBook.BOOK_ID,1));
            mainActivityViewModel.updateBook(book);
            Toast.makeText(this,"editcode",Toast.LENGTH_SHORT).show();
        }

    }
}

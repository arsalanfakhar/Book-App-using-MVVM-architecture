package com.example.ebookapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebookapp.database.entity.Book;
import com.example.ebookapp.database.entity.Category;
import com.example.ebookapp.databinding.ActivityAddBookBinding;
import com.example.ebookapp.viewmodel.MainActivityViewModel;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddBook extends AppCompatActivity {

    private ActivityAddBookBinding addBookBinding;
    private MainActivityViewModel mainActivityViewModel;
    private AddookClickHandler clickHandler;
    private Intent intent;
    private boolean selection_added=false;
    private String intentCat;
    private Book book;

    public static final String BOOK_ID="id";
    public static final String BOOK_NAME="name";
    public static final String BOOK_UNIT_PRICE="unit_price";
    public static final String BOOK_CAT_ID="cat_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBookBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_book);
        addBookBinding.setLifecycleOwner(this);
        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);
        clickHandler=new AddookClickHandler(this);
        addBookBinding.setClickHandler(clickHandler);

        book=new Book();
        addBookBinding.setBook(book);

        //check intent
        intent=getIntent();
        //edit books
        if(intent.hasExtra(BOOK_ID)){
            setTitle("Edit a Book");
            selection_added=true;
            intentCat=intent.getStringExtra(BOOK_CAT_ID);
            book.setMbookname(intent.getStringExtra(BOOK_NAME));
            book.setMbookprice(intent.getIntExtra(BOOK_UNIT_PRICE,0));
            //addBookBinding.materialSpinner2.setSelectedIndex(intent.getIntExtra(BOOK_CAT_ID,1));
        }
        else {
            setTitle("Add a Book");
        }


        mainActivityViewModel.getCategories().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                categories.add(new Category(0,"Add a Category","new cat"));
                ArrayAdapter<Category> listArrayAdapter=new ArrayAdapter<Category>(AddBook.this,R.layout.spinner_item,categories);
                listArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                addBookBinding.materialSpinner2.setAdapter(listArrayAdapter);

                //Now set the selected category in Viewmodel
                //mainActivityViewModel.setSelectedCategory(categories.get(0));


                //When converting a variable always check it
                if(selection_added && !(TextUtils.isEmpty(intentCat))){
                    int id=Integer.valueOf(intentCat);
                    if(id>0)
                        id--;
                    Log.v("intentrecevove",""+id);
                    addBookBinding.materialSpinner2.setSelection(id);
                    selection_added=false;
                }


          //      Toast.makeText(AddBook.this,""+addBookBinding.materialSpinner2.getSelectedIndex(),Toast.LENGTH_SHORT).show();


            }
        });
        addBookBinding.materialSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category category= (Category) adapterView.getSelectedItem();
                Toast.makeText(AddBook
                        .this,""+category,Toast.LENGTH_SHORT).show();

                if(category.toString().equals("Add a Category")){
                    makeDialog();

                }
                else {
                    //Update the category in ViewModel

                    mainActivityViewModel.setSelectedCategory(category);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//
//        addBookBinding.materialSpinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
//
//                Toast.makeText(AddBook
//                        .this,""+item,Toast.LENGTH_SHORT).show();
//                if(item.toString().equals("Add a Category")){
//                    makeDialog();
//
//                }
//                else {
//                    Category selectedCategory= (Category) item;
//
//                    //Update the category in ViewModel
//
//                    mainActivityViewModel.setSelectedCategory(selectedCategory);
//                }
//
//            }
//        });


//
//        addBookBinding.btnHide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               if(addBookBinding.txtSwap.getVisibility()!=View.GONE){
//                    addBookBinding.txtSwap.setVisibility(View.GONE);
//               }
//            }
//        });
//        addBookBinding.btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(addBookBinding.txtSwap.getVisibility()!=View.VISIBLE){
//                    addBookBinding.txtSwap.setVisibility(View.VISIBLE);
//                }
//            }
//        });

    }

    public void makeDialog(){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View view=layoutInflaterAndroid.inflate(R.layout.add_category_dialog,null);

        AlertDialog.Builder catedoryinputbuiler=new AlertDialog.Builder(AddBook.this);
        catedoryinputbuiler.setView(view);

        //declare text for category name and desciption
        final EditText catname=view.findViewById(R.id.category_name_txt);
        final EditText catdescrip=view.findViewById(R.id.category_description_txt);

        catedoryinputbuiler.setCancelable(true)
                .setPositiveButton("Add Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        final AlertDialog alertDialog=catedoryinputbuiler.create();
        alertDialog.getWindow().getAttributes().windowAnimations=R.style.DialogSlide;
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(catname.getText().toString())){
                    Toast.makeText(AddBook.this, "Enter category name!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    alertDialog.dismiss();
                }
            }
        });


    }

    public class AddookClickHandler {
        Context context;

        public AddookClickHandler(Context context) {
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void onSubmitClicked(View view){
            if(TextUtils.isEmpty(book.getMbookname()) ){
                Toast.makeText(context,"Name field cannot be empty",Toast.LENGTH_SHORT).show();
            }
            else {
                Intent returnIntent=new Intent();
                returnIntent.putExtra(BOOK_NAME,book.getMbookname());
                if(!TextUtils.isEmpty(addBookBinding.bookPrice.getText()))
                    returnIntent.putExtra(BOOK_UNIT_PRICE,addBookBinding.bookPrice.getText().toString());
                Category category= (Category) addBookBinding.materialSpinner2.getSelectedItem();

                returnIntent.putExtra(BOOK_CAT_ID,category.getMcategory_id());
                setResult(RESULT_OK,returnIntent);
                finish();
            }
            Toast.makeText(context,"Book name:"+book.getMbookname()+book.getMbookprice(),Toast.LENGTH_SHORT).show();
        }
    }

}

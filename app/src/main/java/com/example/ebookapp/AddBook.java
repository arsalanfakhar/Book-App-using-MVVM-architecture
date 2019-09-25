package com.example.ebookapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ebookapp.databinding.ActivityAddBookBinding;
import com.jaredrummler.materialspinner.MaterialSpinner;

public class AddBook extends AppCompatActivity {

    private ActivityAddBookBinding addBookBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBookBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_book);

        addBookBinding.materialSpinner2.setItems("Classic", "Jelly Bean", "KitKat", "Lollipop", "Add a Category");

        addBookBinding.materialSpinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(item.toString()=="Add a Category"){
                    //Toast.makeText(AddBook.this,"Clicked",Toast.LENGTH_SHORT).show();
                    //open dialog
                    makeDialog();
                }
            }
        });
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

}

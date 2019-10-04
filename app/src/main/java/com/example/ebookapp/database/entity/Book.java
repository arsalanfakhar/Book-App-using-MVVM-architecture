package com.example.ebookapp.database.entity;




import android.os.Build;

import java.util.Objects;
import java.util.Observable;

import androidx.annotation.RequiresApi;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tbl_books")
public class Book extends BaseObservable {

    //Remember all names for variables should be lowercase

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long mbookid;

    @ColumnInfo(name = "name")
    private String mbookname;

    @ColumnInfo(name = "unit_price")
    private int mbookprice;

    @ColumnInfo(name = "category")
    @ForeignKey(entity = Category.class,parentColumns="id",childColumns = "category",onDelete = CASCADE)
    private long category_id;


    @Ignore()
    public Book(){

    }

    public Book(long mbookid, String mbookname, int mbookprice, long category_id) {
        this.mbookid = mbookid;
        this.mbookname = mbookname;
        this.mbookprice = mbookprice;
        this.category_id = category_id;
    }


    @Bindable
    public long getMbookid() {
        return mbookid;
    }

    public void setMbookid(long mbookid) {
        this.mbookid = mbookid;
        notifyPropertyChanged(BR.mbookid);
    }

    @Bindable
    public String getMbookname() {
        return mbookname;
    }

    public void setMbookname(String mbookname) {
        this.mbookname = mbookname;
        notifyPropertyChanged(BR.mbookname);
    }


    @Bindable
    public int getMbookprice() {
        return mbookprice;
    }

    public void setMbookprice(int mbookprice) {
        this.mbookprice = mbookprice;
        notifyPropertyChanged(BR.mbookprice);
    }


    @Bindable
    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
        notifyPropertyChanged(BR.category_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getMbookid() == book.getMbookid() &&
                getMbookprice() == book.getMbookprice() &&
                getCategory_id() == book.getCategory_id() &&
                getMbookname().equals(book.getMbookname());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getMbookid(), getMbookname(), getMbookprice(), getCategory_id());
    }


}

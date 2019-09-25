package com.example.ebookapp.database.entity;




import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "tbl_books")
public class Book{

    //Remember all names for variables shoud be lowercase

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

    public long getMbookid() {
        return mbookid;
    }

    public void setMbookid(long mbookid) {
        this.mbookid = mbookid;
    }

    public String getMbookname() {
        return mbookname;
    }

    public void setMbookname(String mbookname) {
        this.mbookname = mbookname;
    }

    public int getMbookprice() {
        return mbookprice;
    }

    public void setMbookprice(int mbookprice) {
        this.mbookprice = mbookprice;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}

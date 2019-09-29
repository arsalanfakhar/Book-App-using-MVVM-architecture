package com.example.ebookapp.database.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_categories")
public class Category{

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private long mcategory_id;

    @ColumnInfo(name = "name")
    private String category_name;

    @ColumnInfo(name = "description")
    private String category_description;

    @Ignore
    public Category(){

    }

    public Category(long mcategory_id, String category_name, String category_description) {
        this.mcategory_id = mcategory_id;
        this.category_name = category_name;
        this.category_description = category_description;
    }

    public long getMcategory_id() {
        return mcategory_id;
    }

    public void setMcategory_id(long mcategory_id) {
        this.mcategory_id = mcategory_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_description() {
        return category_description;
    }

    public void setCategory_description(String category_description) {
        this.category_description = category_description;
    }

    @NonNull
    @Override
    public String toString() {
        return this.category_name;
    }
}

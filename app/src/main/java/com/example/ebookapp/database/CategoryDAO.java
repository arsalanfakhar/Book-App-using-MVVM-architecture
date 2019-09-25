package com.example.ebookapp.database;

import com.example.ebookapp.database.entity.Category;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao()
public interface CategoryDAO {

    @Insert
    public long addCategory(Category... category);

    @Delete
    public void deleteCategory(Category category);

    //live data means it will be done on background thread and it is live
    @Query("Select * from tbl_categories")
    public LiveData<List<Category>>  getAllCategories();


}

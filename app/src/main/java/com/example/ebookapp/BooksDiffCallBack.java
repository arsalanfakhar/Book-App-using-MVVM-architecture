package com.example.ebookapp;

import com.example.ebookapp.database.entity.Book;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class BooksDiffCallBack extends DiffUtil.Callback {

    private List<Book> mOldList,mNewList;

    public BooksDiffCallBack(List<Book> mOldList, List<Book> mNewList) {
        this.mOldList = mOldList;
        this.mNewList = mNewList;
    }


    @Override
    public int getOldListSize() {
        return mOldList==null?0:mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList==null?0:mNewList.size();
    }

    //They can be used to compare when an item is added or deleted.
    //As ids of the item will change
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

        // if ids are same nothing happen but if at any position they are different view is automatically updated
        return mOldList.get(oldItemPosition).getMbookid()==mNewList.get(newItemPosition).getMbookid();
    }

    //They can be used to compare when an item is updated in list
    //bacause after updation ids remain same
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        //this will compare the contents and inform us about updation

        //but now there is one problem as equals only compare the memory position it will give false every time
        //So we need to overide book equals method
        return mOldList.get(oldItemPosition).equals(mNewList.get(newItemPosition));

    }

    //if the contents are updated of an object this method is called
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }


}

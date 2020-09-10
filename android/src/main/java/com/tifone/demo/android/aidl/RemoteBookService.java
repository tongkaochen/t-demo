package com.tifone.demo.android.aidl;


import android.os.RemoteException;

import java.util.ArrayList;

/**
 * Create by Tifone on 2020/9/10.
 */
public class RemoteBookService extends IRemoteBookService.Stub {

    private ArrayList<Book> mBookList;

    public RemoteBookService() {
        mBookList = new ArrayList<>();
    }

    @Override
    public Book getBook(String bookName) throws RemoteException {
        for (Book b : mBookList) {
            if (b.getName().equals(bookName)) {
                return b;
            }
        }

        return null;
    }

    @Override
    public void addBook(Book book) throws RemoteException {
        mBookList.add(book);
    }
}

// IRemoteBookService.aidl
package com.tifone.demo.android.aidl;

import com.tifone.demo.android.aidl.Book;

interface IRemoteBookService {
    Book getBook(String bookName);
    void addBook(in Book book);
}

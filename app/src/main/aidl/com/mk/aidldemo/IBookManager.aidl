// IBookManager.aidl
package com.mk.aidldemo;
import com.mk.aidldemo.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}

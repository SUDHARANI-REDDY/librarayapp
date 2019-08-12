package com.example.libraryapp;

public class Bookstodb {
    String bookid;
    String book_name;
    String book_author;
    String book_edition;
    String book_quantity;

    public Bookstodb()
    {

    }

    public Bookstodb(String bookid, String book_name, String book_author, String book_edition, String book_quantity) {
        this.bookid = bookid;
        this.book_name = book_name;
        this.book_author = book_author;
        this.book_edition = book_edition;
        this.book_quantity = book_quantity;
    }

    public String getBookid() {
        return bookid;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public String getBook_edition() {
        return book_edition;
    }

    public String getBook_quantity() {
        return book_quantity;
    }
}

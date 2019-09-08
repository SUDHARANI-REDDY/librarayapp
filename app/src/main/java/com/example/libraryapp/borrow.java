package com.example.libraryapp;

public class borrow {
    String bookname,bookid,bookauthor,bookedition,date,usn;

    public borrow() {
    }

    public borrow(String bookname, String bookid, String bookauthor, String bookedition, String date, String usn) {
        this.bookname = bookname;
        this.bookid = bookid;
        this.bookauthor = bookauthor;
        this.bookedition = bookedition;
        this.date = date;
        this.usn = usn;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookauthor() {
        return bookauthor;
    }

    public void setBookauthor(String bookauthor) {
        this.bookauthor = bookauthor;
    }

    public String getBookedition() {
        return bookedition;
    }

    public void setBookedition(String bookedition) {
        this.bookedition = bookedition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }
}

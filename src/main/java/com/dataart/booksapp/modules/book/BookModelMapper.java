package com.dataart.booksapp.modules.book;

/**
 * Created by vlobyntsev on 03.06.2016.
 */
public class BookModelMapper {

    public static Book mapFromView(BookViewModel bookViewModel){
        Book book = new Book();
        book.setIdBook(bookViewModel.getIdBook());
        book.setDescription(bookViewModel.getDescription());
        book.setIsbn(bookViewModel.getIsbn());
        book.setTitle(bookViewModel.getTitle());
        return book;
    }

}

package com.dataart.booksapp.ui.book.autocomplete;

import com.dataart.booksapp.domain.author.AuthorService;
import com.dataart.booksapp.domain.author.AuthorViewModel;
import com.dataart.booksapp.domain.genre.GenreService;
import com.dataart.booksapp.domain.genre.GenreViewModel;
import com.dataart.booksapp.ui.book.data.BookLocalData;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 24.06.2016.
 */
@ManagedBean
@SessionScoped
@Named
public class BookAutocompletePresenter implements Serializable {

    @EJB
    private GenreService genreService;

    @EJB
    private AuthorService authorService;

    @Inject
    private BookLocalData bookLocalData;

    public List<AuthorViewModel> completeAuthor(String namePrefix) {
        List<AuthorViewModel> authors = bookLocalData.getCurrentEntity().getAuthors();
        String test = "test";
        return authorService.findByNamePrefix(namePrefix)
                .stream()
                .filter(a->!bookLocalData.getCurrentEntity().getAuthors().contains(a))
                .collect(Collectors.toList());
    }

    public List<GenreViewModel> completeGenre(String namePrefix) {
        return genreService.findByNamePrefix(namePrefix)
                .stream()
                .filter(g -> !bookLocalData.getCurrentEntity().getGenres().contains(g))
                .collect(Collectors.toList());
    }

}

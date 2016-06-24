package com.dataart.booksapp.domain.genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class GenreModelMapper {

    public static List<Integer> mapViewListToIds(List<GenreViewModel> genreViewModels){
        return genreViewModels.stream().map(GenreViewModel::getIdGenre).collect(Collectors.toList());
    }

    public static List<GenreViewModel> mapFromDomainList(List<Genre> domainList){
        return domainList.stream().map(GenreModelMapper::mapFromDomain).collect(Collectors.toList());
    }

    static Genre mapFromView(GenreViewModel genreViewModel){
        Genre genre = new Genre();
        genre.setIdGenre(genreViewModel.getIdGenre());
        genre.setName(genreViewModel.getName());
        return genre;
    }

    static GenreViewModel mapFromDomain(Genre genre){
        GenreViewModel genreViewModel = new GenreViewModel();
        genreViewModel.setIdGenre(genre.getIdGenre());
        genreViewModel.setName(genre.getName());
        return genreViewModel;
    }
}

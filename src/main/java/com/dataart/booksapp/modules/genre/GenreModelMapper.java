package com.dataart.booksapp.modules.genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class GenreModelMapper {

    public static GenreViewModel mapFromDomain(Genre genre){
        GenreViewModel genreViewModel = new GenreViewModel();
        genreViewModel.setIdGenre(genre.getIdGenre());
        genreViewModel.setName(genre.getName());
        return genreViewModel;
    }


    public static List<Integer> mapViewListToIds(List<GenreViewModel> genreViewModels){
        return genreViewModels.stream().map(GenreViewModel::getIdGenre).collect(Collectors.toList());
    }

    public static List<GenreViewModel> mapFromDomainList(List<Genre> domainList){
        List<GenreViewModel> mappedList = new ArrayList<>();
        for(Genre genre : domainList){
            mappedList.add(mapFromDomain(genre));
        }
        return mappedList;
    }


    public static Genre mapFromView(GenreViewModel genreViewModel){
        Genre genre = new Genre();
        genre.setIdGenre(genreViewModel.getIdGenre());
        genre.setName(genre.getName());
        return genre;
    }
}

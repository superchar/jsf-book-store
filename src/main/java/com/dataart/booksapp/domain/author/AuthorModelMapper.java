package com.dataart.booksapp.domain.author;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class AuthorModelMapper {

    public static List<Integer> mapViewListToIds(List<AuthorViewModel> authorViewModels){
        return authorViewModels.stream().map(AuthorViewModel::getAuthorId).collect(Collectors.toList());
    }

    public static List<AuthorViewModel> mapFromDomainModelList(List<Author> domainModelList){
        return domainModelList.stream().map(AuthorModelMapper::mapFromDomain).collect(Collectors.toList());
    }

    static AuthorViewModel mapFromDomain(Author author){
        AuthorViewModel authorViewModel = new AuthorViewModel();
        authorViewModel.setAuthorId(author.getIdAuthor());
        authorViewModel.setFirstName(author.getFirstName());
        authorViewModel.setLastName(author.getLastName());
        authorViewModel.setAuthorInfo(author.getAuthorInfo());
        return authorViewModel;
    }

    static Author updateAuthorAccordingViewModel(Author author,AuthorViewModel authorViewModel){
        author.setFirstName(authorViewModel.getFirstName());
        author.setLastName(authorViewModel.getLastName());
        author.setAuthorInfo(authorViewModel.getAuthorInfo());
        return author;
    }

    static Author mapFromView(AuthorViewModel authorViewModel){
        Author author = new Author();
        author.setIdAuthor(author.getIdAuthor());
        author.setFirstName(authorViewModel.getFirstName());
        author.setLastName(authorViewModel.getLastName());
        author.setAuthorInfo(authorViewModel.getAuthorInfo());
        return author;
    }

}

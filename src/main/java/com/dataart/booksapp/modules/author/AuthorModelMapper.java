package com.dataart.booksapp.modules.author;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class AuthorModelMapper {

    public static AuthorViewModel mapFromDomain(Author author){
        AuthorViewModel authorViewModel = new AuthorViewModel();
        authorViewModel.setAuthorId(author.getIdAuthor());
        authorViewModel.setFirstName(author.getFirstName());
        authorViewModel.setLastName(author.getLastName());
        authorViewModel.setAuthorInfo(author.getAuthorInfo());
        return authorViewModel;
    }

    public static List<Integer> mapViewListToIds(List<AuthorViewModel> authorViewModels){
        List<Integer> authorsIds = new ArrayList<>();
        for(AuthorViewModel authorViewModel: authorViewModels){
            authorsIds.add(authorViewModel.getAuthorId());
        }
        return authorsIds;
    }

    public static List<AuthorViewModel> mapFromDomainModelList(List<Author> domainModelList){
        List<AuthorViewModel> mappedList = new ArrayList<>();
        for(Author author : domainModelList){
            mappedList.add(mapFromDomain(author));
        }
        return mappedList;
    }

    public static Author mapFromView(AuthorViewModel authorViewModel){
        Author author = new Author();
        author.setIdAuthor(author.getIdAuthor());
        author.setFirstName(authorViewModel.getFirstName());
        author.setLastName(authorViewModel.getLastName());
        author.setAuthorInfo(authorViewModel.getAuthorInfo());
        return author;
    }

}

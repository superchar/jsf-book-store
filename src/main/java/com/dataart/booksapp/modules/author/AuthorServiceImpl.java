package com.dataart.booksapp.modules.author;

import javax.annotation.ManagedBean;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.function.Function;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@ManagedBean
@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorRepository authorRepository;

    public List<AuthorViewModel> findByNamePrefix(String namePrefix){
        return AuthorModelMapper.mapFromDomainModelList(authorRepository.findByNamePrefix(namePrefix));

    }

    public AuthorViewModel findById(int id){
        return AuthorModelMapper.mapFromDomain(authorRepository.findById(id));
    }
}

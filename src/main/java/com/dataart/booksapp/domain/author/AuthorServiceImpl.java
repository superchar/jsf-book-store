package com.dataart.booksapp.domain.author;

import com.dataart.booksapp.domain.general.GeneralMapper;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Stateless
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Inject
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    public List<AuthorViewModel> findByNamePrefix(String namePrefix) {
        return AuthorModelMapper.mapFromDomainModelList(authorRepository.findByNamePrefix(namePrefix));
    }

    public List<AuthorViewModel> getAuthorsInRange(int from, int quantity) {
        return AuthorModelMapper.mapFromDomainModelList(authorRepository.getAuthorsInRange(from, quantity));
    }

    public AuthorViewModel findById(int id) {
        return AuthorModelMapper.mapFromDomain(authorRepository.findById(id));
    }

    public long getCount() {
        return authorRepository.getCount();
    }

    public void addAuthor(AuthorViewModel addedAuthorModel) {
        authorRepository.addAuthor(AuthorModelMapper.mapFromView(addedAuthorModel));
    }

    public void editAuthor(AuthorViewModel authorViewModel) throws NotExistsException {
        Author author = GeneralMapper.loadModelFromViewModel(authorViewModel,AuthorViewModel::getAuthorId,authorRepository::findById);
        if(wasAuthorChangedAfterEditing(author,authorViewModel)){
            author = AuthorModelMapper.updateAuthorAccordingViewModel(author,authorViewModel);
            authorRepository.edit(author);
        }
    }

    @Override
    public void delete(AuthorViewModel authorViewModel) throws NotExistsException {
        Author author = loadAuthorFromViewModel(authorViewModel);
        authorRepository.delete(author);
    }

    private Author loadAuthorFromViewModel(AuthorViewModel authorViewModel) throws NotExistsException{
        return GeneralMapper.loadModelFromViewModel(authorViewModel,AuthorViewModel::getAuthorId,authorRepository::findById);
    }

    private boolean wasAuthorChangedAfterEditing(Author oldAuthor, AuthorViewModel editedAuthor){
        return !oldAuthor.getFirstName().equals(editedAuthor.getFirstName()) ||
                !oldAuthor.getLastName().equals(editedAuthor.getLastName()) ||
                !oldAuthor.getAuthorInfo().equals(editedAuthor.getAuthorInfo());
    }
}

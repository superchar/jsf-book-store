package com.dataart.booksapp.domain.author;

import com.dataart.booksapp.domain.general.GeneralMapper;
import com.dataart.booksapp.domain.general.Preconditions;
import com.dataart.booksapp.domain.general.exceptions.NotExistsException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vlobyntsev on 01.06.2016.
 */
@Stateless
public class AuthorServiceImpl implements AuthorService {

    @Inject
    private AuthorRepository authorRepository;

    public List<AuthorViewModel> findByNamePrefix(String namePrefix) {
        return AuthorModelMapper.mapFromDomainModelList(authorRepository.findByNamePrefix(namePrefix));
    }

    public List<AuthorViewModel> getInRange(int from, int quantity) {
        Preconditions.throwIllegalArgumentIfNegativeValue(from, quantity);
        return AuthorModelMapper.mapFromDomainModelList(authorRepository.getInRange(from, quantity));
    }

    public AuthorViewModel findById(int id) {
        return AuthorModelMapper.mapFromDomain(authorRepository.findById(id));
    }

    public long getCount() {
        return authorRepository.getCount();
    }

    public void add(AuthorViewModel addedAuthorModel) {
        authorRepository.add(AuthorModelMapper.mapFromView(addedAuthorModel));
    }

    public void edit(AuthorViewModel authorViewModel){
        Author author = loadAuthorFromViewModel(authorViewModel);
        if (wasAuthorChangedAfterEditing(author, authorViewModel)) {
            author = AuthorModelMapper.updateAuthorAccordingViewModel(author, authorViewModel);
            authorRepository.edit(author);
        }
    }

    private Author loadAuthorFromViewModel(AuthorViewModel authorViewModel) throws NotExistsException {
        return GeneralMapper.loadModelFromViewModel(authorViewModel, AuthorViewModel::getAuthorId, authorRepository::findById);
    }

    private boolean wasAuthorChangedAfterEditing(Author oldAuthor, AuthorViewModel editedAuthor) {
        return !oldAuthor.getFirstName().equals(editedAuthor.getFirstName()) ||
                !oldAuthor.getLastName().equals(editedAuthor.getLastName()) ||
                !oldAuthor.getAuthorInfo().equals(editedAuthor.getAuthorInfo());
    }
}

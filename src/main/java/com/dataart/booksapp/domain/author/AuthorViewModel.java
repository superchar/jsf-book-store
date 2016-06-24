package com.dataart.booksapp.domain.author;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class AuthorViewModel {

    private int authorId;

    private String firstName;

    private String lastName;

    private String authorInfo;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAuthorInfo() {
        return authorInfo;
    }

    public void setAuthorInfo(String authorInfo) {
        this.authorInfo = authorInfo;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof AuthorViewModel){
            AuthorViewModel otherAuthorViewModel = (AuthorViewModel) other;
            return this.getAuthorId() == otherAuthorViewModel.getAuthorId();
        }
        return false;
    }
}

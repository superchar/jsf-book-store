package com.dataart.booksapp.domain.genre;

/**
 * Created by vlobyntsev on 02.06.2016.
 */
public class GenreViewModel {

    private int idGenre;

    private String name;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof GenreViewModel){
            GenreViewModel model = (GenreViewModel) other;
            return this.getIdGenre() == model.getIdGenre();
        }
        return false;
    }
}

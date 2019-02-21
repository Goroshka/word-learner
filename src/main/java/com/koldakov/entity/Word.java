package com.koldakov.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Word {

    @Id
    @GeneratedValue
    private Long id;
    private String russian;
    private String latvian;

    // for hibernate
    public Word() {
    }

    public Word(String russian, String latvian) {
        this.russian = russian;
        this.latvian = latvian;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public String getLatvian() {
        return latvian;
    }

    public void setLatvian(String latvian) {
        this.latvian = latvian;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Objects.equals(id, word.id) &&
                Objects.equals(russian, word.russian) &&
                Objects.equals(latvian, word.latvian);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, russian, latvian);
    }

    @Override
    public String toString() {
        return "Word{" +
                "russian='" + russian + '\'' +
                ", latvian='" + latvian + '\'' +
                '}';
    }
}

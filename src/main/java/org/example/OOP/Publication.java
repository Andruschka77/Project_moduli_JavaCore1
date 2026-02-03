package org.example.OOP;

public abstract class Publication {

    private String title; // Название публикации
    private String author; // Автор публикации
    private int year; // Год выпуска
    private static int publicationCount = 0; // Счетчик созданных публикаций

    public Publication(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public abstract String getType(); // Возвращает тип публикации

    @Override
    public String toString() {
        return getAuthor() + " автор книги " + getTitle() + ", выпущенной в " + getYear() + " году!";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication publication = (Publication) obj;
        if (year != publication.year) return false;
        if (title != null ? !title.equals(publication.title) : publication.title != null) return false;
        return author != null ? author.equals(publication.author) : publication.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + year;
        return result;
    }

    public static void setPublicationCount() {
        publicationCount++;
    }

    public static int getPublicationCount() {
        return publicationCount;
    }

}
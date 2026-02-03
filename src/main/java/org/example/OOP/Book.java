package org.example.OOP;

public class Book extends Publication implements Printable {

    private String ISBN; // Книжный номер

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        this.ISBN = ISBN;
    }

    @Override
    public String getType() {
        return "Book";
    }

    @Override
    public String toString() {
        return "Book{title='" + getTitle() + "', author='" + getAuthor() + "', year=" + getYear() + ", ISBN='" + ISBN + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Book book = (Book) obj;
        return ISBN != null ? ISBN.equals(book.ISBN) : book.ISBN == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (ISBN != null ? ISBN.hashCode() : 0);
        return result;
    }

    @Override
    public void printDetails() {
        System.out.println("Автор произведения: " + getAuthor());
        System.out.println("Название произведения: " + getTitle());
        System.out.println("Год выпуска: " + getYear());
        System.out.print("Книжный номер: " + ISBN);
    }

}
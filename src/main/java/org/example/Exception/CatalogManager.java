package org.example.Exception;

import java.util.ArrayList;
import java.util.List;

public class CatalogManager {

    private final List<Book> catalog = new ArrayList<Book>();

    public void addBook(String title, String author, int copies) {
        catalog.add(new Book(title, author, copies));
        System.out.println("Книга добавлена в каталог!");
    }

    public void takeBook(String title) throws ItemNotFoundException, NoAvailableCopiesException {
        boolean bookFound = false;
        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                if (book.getAvailableCopies() == 0) {
                    throw new NoAvailableCopiesException();
                }
                System.out.println("Взяли книгу из библиотеки: " + book.getAuthor() + " " + book.getTitle());
                book.reduceAvailableCopies();
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            throw new ItemNotFoundException();
        }
    }

    public void returnBook(String title) throws ItemNotFoundException {
        boolean bookFound = false;
        for (Book book : catalog) {
            if (book.getTitle().equals(title)) {
                System.out.println("Вернули книгу в библиотеку: " + book.getAuthor() + " " + book.getTitle());
                book.increaseAvailableCopies();
                bookFound = true;
                break;
            }
        }
        if (!bookFound) {
            throw new ItemNotFoundException();
        }
    }

    public List<Book> getAllBooks() {
        return catalog;
    }

}

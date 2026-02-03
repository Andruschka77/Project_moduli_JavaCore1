package org.example.OOP;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Publication> publications; // Список всех публикаций

    public Library() {
        publications = new ArrayList<>();
    }

    public void addPublication(Publication pub) { // Добавление публикации в каталог
        publications.add(pub);
        Publication.setPublicationCount();
    }

    public void listPublications() { // Вывод всех публикаций
        int cnt = 1;
        for (Publication pub : publications) {
            System.out.println(cnt + ") " + pub.toString());
            cnt++;
        }
    }

    public void searchByAuthor(String author) { // Поиск публикаций по автору
        System.out.println("\nИнформация о найденном авторе:");
        for (Publication pub : publications) {
            if (pub.getAuthor().equals(author)) {
                Printable printable = (Printable) pub;
                System.out.println("\nТип публикации - " + pub.getType());
                printable.printDetails();
                System.out.println();
            }
        }
    }

}
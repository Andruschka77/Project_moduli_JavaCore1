package org.example.Exception;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CatalogManager catalog = new CatalogManager();
        int choice, availableCopies;
        String title, author;

        try {
            do {
                System.out.println("\nМеню для пользователя:\n1) Вывести каталог\n2) Добавить объект в каталог\n" +
                        "3) Выдать объект\n4) Вернуть объект\n5) Выход");
                System.out.print("Выберите: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println();
                        List<Book> books = catalog.getAllBooks();
                        if (books.isEmpty()) {
                            System.out.println("Каталог пустой!");
                        }
                        for (int i = 0; i < books.size(); i++) {
                            System.out.println(i + 1 + ") " + books.get(i).toString());
                        }
                        break;
                    case 2:
                        try {
                            System.out.print("\nВведите имя автора - ");
                            author = sc.nextLine();
                            System.out.print("Введите название произведения - ");
                            title = sc.nextLine();
                            System.out.print("Введите количество доступных экземпляров - ");
                            availableCopies = Integer.parseInt(sc.nextLine());

                            if (availableCopies < 0) {
                                throw new IllegalArgumentException();
                            }

                            catalog.addBook(title, author, availableCopies);
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Ввод не соответствует ожидаемому типу данных!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Ошибка: Количество экземпляров не может быть отрицательным!");
                        }
                        break;
                    case 3:
                        System.out.print("\nВведите название произведения, которое хотите взять - ");
                        title = sc.nextLine();
                        try {
                            catalog.takeBook(title);
                        } catch (ItemNotFoundException e) {
                            System.out.println("Ошибка: объекта нет в каталоге!");
                        } catch (NoAvailableCopiesException e) {
                            System.out.println("Ошибка: нет свободных экземпляров");
                        }
                        break;
                    case 4:
                        System.out.print("\nВведите название произведения, которое хотите вернуть - ");
                        title = sc.nextLine();
                        try {
                            catalog.returnBook(title);
                        } catch (ItemNotFoundException e) {
                            System.out.println("Ошибка: объекта нет в каталоге!");
                        }
                        break;
                    case 5:
                        System.out.println("\nДо свидания!");
                        break;
                    default:
                        System.out.println("\nНеверный выбор!");
                        break;
                }
            } while (choice != 5);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Ввод не соответствует ожидаемому типу данных!");
        }
    }
}
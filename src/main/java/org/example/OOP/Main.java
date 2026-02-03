package org.example.OOP;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, type, year;
        String author, title;
        Library library = new Library();
        Publication pub = null;

        do {
            System.out.println("\nМеню для пользователя:\n1) Добавить новую публикацию\n2) Вывести список всех публикаций\n" +
                    "3) Поиск публикации по автору\n4) Вывести общее количество публикаций\n0) Выход");
            System.out.print("Выберите: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("\nВыберите тип публикации: 1 – Book, 2 – Magazine, 3 – Newspaper");
                    type = Integer.parseInt(sc.nextLine());
                    System.out.print("Введите имя автора - ");
                    author = sc.nextLine();
                    System.out.print("Введите название произведения - ");
                    title = sc.nextLine();
                    System.out.print("Введите год выпуска - ");
                    year = Integer.parseInt(sc.nextLine());

                    if (type == 1) {
                        System.out.print("Введите ISBN - ");
                        String ISBN = sc.nextLine();
                        pub = new Book(title, author, year, ISBN);
                    }
                    else if (type == 2) {
                        System.out.print("Введите номер выпуска - ");
                        int issueNumber = Integer.parseInt(sc.nextLine());
                        pub = new Magazine(title, author, year, issueNumber);
                    }
                    else if (type == 3) {
                        System.out.print("Введите день публикации - ");
                        String publicationDay = sc.nextLine();
                        pub = new Newspaper(title, author, year, publicationDay);
                    }
                    else {
                        System.out.println("Вы неверное ввели тип публикации!");
                    }
                    library.addPublication(pub);
                    break;
                case 2:
                    System.out.println("\nСписок всех публикаций: ");
                    library.listPublications();
                    break;
                case 3:
                    System.out.print("\nВведите имя автора - ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;
                case 4:
                    System.out.println("\nОбщее количество публикаций - " + Publication.getPublicationCount());
                    break;
                case 0:
                    System.out.println("\nДо свидания!");
                    break;
                default:
                    System.out.println("\nНеверный выбор!");
                    break;
            }

        } while (choice != 0);
    }
}
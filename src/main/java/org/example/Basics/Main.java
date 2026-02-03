package org.example.Basics;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        String[] names = new String[150];
        String[] phoneNumbers = new String[150];
        int index = 0;
        String name, phone;

        do {
            System.out.println("\nМеню для пользователя:\n1) Добавить контакт\n2) Просмотреть контакты\n" +
                    "3) Найти контакт\n4) Удалить контакт\n5) Выйти");
            System.out.print("Выберите: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (index != names.length) {
                        System.out.print("\nВведите имя пользователя - ");
                        name = sc.next();
                        System.out.print("Введите номер телефон пользователя - ");
                        phone = sc.next();
                        names[index] = name;
                        phoneNumbers[index] = phone;
                        index += 1;
                    }
                    else {
                        System.out.println("\nКоличество контактов максимально, новых добавить нельзя!");
                    }
                    break;
                case 2:
                    System.out.println();
                    for (int i = 0; i < index; i++) {
                        System.out.println(i + 1 + ". " + names[i] + " - " + phoneNumbers[i]);
                    }
                    break;
                case 3:
                    boolean flag = false;
                    System.out.print("\nВведите имя пользователя для поиска - ");
                    name = sc.next();
                    for (int i = 0; i < index; i++) {
                        if (names[i].equals(name)) {
                            System.out.println(i + 1 + ". Номер телефона пользователя - " + phoneNumbers[i]);
                            flag = true;
                        }
                    }
                    if (!flag) {
                        System.out.println("\nТакого контакта не существует!");
                    }
                    break;
                case 4:
                    boolean flag2 = false;
                    int index2 = 0;
                    System.out.print("\nВведите имя пользователя для удаления - ");
                    name = sc.next();
                    for (int i = 0; i < index; i++) {
                        if (names[i].equals(name)) {
                            index2 = i;
                            flag2 = true;
                            break;
                        }
                    }
                    if (!flag2) {
                        System.out.println("\nТакого контакта не существует!");
                    }
                    else {
                        for (int i = index2; i < index - 1; i++) {
                            names[i] = names[i + 1];
                            phoneNumbers[i] = phoneNumbers[i + 1];
                        }
                        index -= 1;
                        System.out.println("\nУдаление завершено!");
                    }
                    break;
                case 5:
                    System.out.println("\nДо свидания!");
                    break;
                default:
                    System.out.println("\nНеверный выбор!");
                    break;
            }
        } while(choice != 5);
    }
}
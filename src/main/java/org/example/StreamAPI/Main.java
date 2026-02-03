package org.example.StreamAPI;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product(1L, "Мяч", "Toys", new BigDecimal("500")),
                new Product(2L, "Кукла", "Toys", new BigDecimal("800")),
                new Product(3L, "Конструктор", "Toys", new BigDecimal("1200")),
                new Product(4L, "Java для начинающих", "Books", new BigDecimal("1500")),
                new Product(5L, "Алгоритмы и структуры данных", "Books", new BigDecimal("2000")),
                new Product(6L, "Философия Java", "Books", new BigDecimal("2500")),
                new Product(7L, "Ноутбук", "Electronics", new BigDecimal("50000")),
                new Product(8L, "Смартфон", "Electronics", new BigDecimal("30000")),
                new Product(9L, "Наушники", "Electronics", new BigDecimal("5000")),
                new Product(10L, "Футболка", "Clothing", new BigDecimal("1000")),
                new Product(11L, "Джинсы", "Clothing", new BigDecimal("3000")),
                new Product(12L, "Кроссовки", "Clothing", new BigDecimal("4000")),
                new Product(13L, "Детское питание", "Children's products", new BigDecimal("300")),
                new Product(14L, "Подгузники", "Children's products", new BigDecimal("800")),
                new Product(15L, "Игрушка-погремушка", "Children's products", new BigDecimal("200")),
                new Product(16L, "Детская кроватка", "Children's products", new BigDecimal("5000"))
        );

        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, LocalDate.of(2021, 2, 10), LocalDate.of(2021, 2, 15), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(0), products.get(3), products.get(6)))));
        orders.add(new Order(2L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 20), "PENDING",
                new HashSet<>(Arrays.asList(products.get(1), products.get(4), products.get(7)))));
        orders.add(new Order(3L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 25), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(2), products.get(5), products.get(8)))));
        orders.add(new Order(4L, LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 10), "SHIPPED",
                new HashSet<>(Arrays.asList(products.get(0), products.get(9)))));
        orders.add(new Order(5L, LocalDate.of(2021, 2, 20), LocalDate.of(2021, 2, 28), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(10), products.get(11), products.get(3)))));
        orders.add(new Order(6L, LocalDate.of(2021, 3, 1), LocalDate.of(2021, 3, 10), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(4), products.get(7), products.get(1)))));
        orders.add(new Order(7L, LocalDate.of(2021, 3, 14), LocalDate.of(2021, 3, 18), "PENDING",
                new HashSet<>(Arrays.asList(products.get(5), products.get(8), products.get(2)))));
        orders.add(new Order(8L, LocalDate.of(2021, 3, 15), LocalDate.of(2021, 3, 22), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(6), products.get(9), products.get(0)))));
        orders.add(new Order(9L, LocalDate.of(2021, 4, 5), LocalDate.of(2021, 4, 12), "SHIPPED",
                new HashSet<>(Arrays.asList(products.get(7), products.get(10)))));
        orders.add(new Order(10L, LocalDate.of(2021, 2, 25), LocalDate.of(2021, 3, 5), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(8), products.get(11), products.get(4)))));
        orders.add(new Order(11L, LocalDate.of(2021, 3, 10), LocalDate.of(2021, 3, 15), "DELIVERED",
                new HashSet<>(Arrays.asList(products.get(12), products.get(13), products.get(0)))));
        orders.add(new Order(12L, LocalDate.of(2021, 4, 5), LocalDate.of(2021, 4, 12), "SHIPPED",
                new HashSet<>(Arrays.asList(products.get(14), products.get(15), products.get(3)))));

        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Иван Петров", 1L, new HashSet<>(orders.subList(0, 5))),
                new Customer(2L, "Анна Смирнова", 2L, new HashSet<>(orders.subList(1, 6))),
                new Customer(3L, "Сергей Козлов", 1L, new HashSet<>(orders.subList(2, 7))),
                new Customer(4L, "Мария Иванова", 3L, new HashSet<>(orders.subList(3, 8))),
                new Customer(5L, "Дмитрий Соколов", 2L, new HashSet<>(orders.subList(4, 9))),
                new Customer(6L, "Ольга Николаева", 2L, new HashSet<>(Arrays.asList(
                        orders.get(10), orders.get(11), orders.get(0), orders.get(1), orders.get(2)
                )))
        );

        // Задание 1
        List<Product> productsBooks = products.stream()
                .filter(x -> "Books".equals(x.getCategory()) && x.getPrice().compareTo(BigDecimal.valueOf(100)) > 0)
                .collect(Collectors.toList());
        System.out.println("(1 задание) Продукты из категории 'Books' дороже 100:");
        productsBooks.forEach(x -> System.out.println(x.getName() + " - " + x.getPrice()));

        // Задание 2
        List<Order> ordersWithChildrenProducts = orders.stream()
                .filter(x -> x.getProducts().stream()
                        .anyMatch(category -> "Children's products".equals(category.getCategory())))
                .collect(Collectors.toList());
        System.out.print("\n(2 задание) Заказы с детскими товарами:");
        ordersWithChildrenProducts.forEach(x -> {
            System.out.print("\nЗаказ ID: " + x.getId() + "\nСписок покупок: ");
            String productNames = x.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", "));
            System.out.print(productNames);
        });

        // Задание 3
        BigDecimal productsToys = products.stream()
                .filter(x -> "Toys".equals(x.getCategory()))
                .map(x -> x.getPrice().multiply(new BigDecimal("0.9")))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n\n(3 задание) Общая стоимость всех игрушек со скидкой: " + productsToys);

        // Задание 4
        LocalDate startDate = LocalDate.of(2021, 2, 1);
        LocalDate endDate = LocalDate.of(2021, 4, 1);
        List<Product> products2Level = customers.stream()
                .filter(customer -> customer.getLevel() == 2L)
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().isAfter(startDate) && order.getOrderDate().isBefore(endDate))
                .flatMap(order -> order.getProducts().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\n(4 задание) Продукты, заказанные клиентами 2-го уровня с 01-фев-2021 по 01-апр-2021:");
        products2Level.forEach(x -> System.out.println(x.getName() + " - " + x.getPrice()));

        // Задание 5
        List<Product> cheapProducts = products.stream()
                .filter(x -> "Books".equals(x.getCategory()))
                .sorted(Comparator.comparing(Product::getPrice))
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("\n(5 задание) Самые дешевые продукты из категории 'Books': ");
        cheapProducts.forEach(x -> System.out.println(x.getName() + " - " + x.getPrice()));

        // Задание 6
        List<Order> threeLatestOrders = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\n(6 задание) 3 самых последних заказа:");
        threeLatestOrders.forEach(x -> System.out.println("Заказ ID - " + x.getId() + ", Дата - " + x.getOrderDate() +
                ", Статус - " + x.getStatus() + ", Продуктов - " + x.getProducts().size()));

        // Задание 7
        LocalDate data = LocalDate.of(2021, 3, 15);
        List<Order> orders2021 = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(data))
                .distinct()
                .collect(Collectors.toList());
        System.out.print("\n(7 задание) Список заказов, сделанных 15-марта-2021:");
        orders2021.forEach(x -> {
            System.out.print("\nId заказа: " + x.getId() + "\nСписок покупок: ");
            String productNames1 = x.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", "));
            System.out.print(productNames1);
        });

        // Задание 8
        BigDecimal totalFebruaryOrdersSum = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .filter(order -> order.getOrderDate().getMonthValue() == 2 &&
                        order.getOrderDate().getYear() == 2021)
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n\n(8 задание) Общая сумма всех заказов за февраль 2021: " + totalFebruaryOrdersSum);

        // Задание 9
        LocalDate data2 = LocalDate.of(2021, 3, 14);
        List<BigDecimal> orderSums = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .filter(order -> order.getOrderDate().equals(data2))
                .distinct()
                .flatMap(order -> order.getProducts().stream())
                .map(Product::getPrice)
                .collect(Collectors.toList());
        System.out.println("\n(9 задание) Средний платеж по заказам, сделанных 14 марта 2021: " +
                orderSums.stream().collect(Collectors.averagingDouble(BigDecimal::doubleValue)));

        // Задание 10
        List<Product> productsBooks2 = products.stream()
                .filter(product -> "Books".equals(product.getCategory()))
                .collect(Collectors.toList());
        System.out.println("\n(10 задание) Количество товаров категории 'Books': " + productsBooks2.size());
        System.out.println("Сумма цен товаров категории 'Books': " + productsBooks2.stream()
                .map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
        System.out.println("Средняя цена одной книги: " + productsBooks2.stream()
                .map(Product::getPrice)
                .collect(Collectors.averagingDouble(BigDecimal::doubleValue)));
        System.out.println("Цена самой дешевой книги: " + productsBooks2.stream()
                .map(Product::getPrice)
                .min(BigDecimal::compareTo).get());
        System.out.println("Цена самой дорогой книги: " + productsBooks2.stream()
                .map(Product::getPrice)
                .max(BigDecimal::compareTo).get());

        // Задание 11
        Map<Long, Integer> orderProductCountMap = customers.stream()
                .flatMap(customer -> customer.getOrders().stream())
                .distinct()
                .collect(Collectors.toMap(
                        Order::getId,
                        order -> order.getProducts().size()
                ));
        System.out.println("\n(11 задание) Id заказа и кол-во товаров в заказе:");
        orderProductCountMap.forEach((id, productCount) -> {
            System.out.println("Id: " + id + " -> " + productCount + " товара");
        });

        // Задание 12
        Map<Customer, List<Order>> customersWithPendingOrders = customers.stream()
                .collect(Collectors.toMap(
                        customer -> customer,
                        customer -> customer.getOrders().stream().collect(Collectors.toList())
                ));
        System.out.println("\n(12 задание) Покупатель и список его заказов:");
        customersWithPendingOrders.forEach((k, v) -> {
            System.out.println(k.getName() + " -> " + v.stream()
                    .flatMap(order -> order.getProducts().stream())
                    .map(Product::getName)
                    .collect(Collectors.joining(", ")));
        });

        // Задание 13
        Map<Order, Double> orderTotalMap = orders.stream()
                .collect(Collectors.toMap(
                        order -> order,
                        order -> order.getProducts().stream()
                                .mapToDouble(product -> product.getPrice().doubleValue())
                                .sum()
                ));
        System.out.println("\n(13 задание) Заказ и общая сумма продуктов заказа:");
        orderTotalMap.forEach((k, v) -> {
            System.out.println("Id: " + k.getId() + " -> " + v);
        });

        // Задание 14
        Map<String, List<String>> categoryProductsMap = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(
                                Product::getName,
                                Collectors.toList()
                        )
                ));
        System.out.println("\n(14 задание) Категория и список товаров этой категории:");
        categoryProductsMap.forEach((k, v) -> {
            System.out.println("Категория: " + k + " -> [" + v.stream().collect(Collectors.joining(", ")) + "]");
        });

        // 15 задание
        Map<String, Product> mostExpensiveByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)),
                                Optional::get
                        )
                ));
        System.out.println("\n(15 задание) Категория и ее самый дорогой продукт:");
        mostExpensiveByCategory.forEach((k, v) -> {
            System.out.println("Категория: " + k + " -> " + v.getName() + ", цена: " + v.getPrice());
        });
    }

}
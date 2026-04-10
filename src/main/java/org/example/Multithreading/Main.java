package org.example.Multithreading;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();

        for (int i = 0; i < 100; i++) {
            List<Integer> numbers = List.of(6, 2, 3, 4, 5);
            processor.submitTask(numbers);
        }

        while (processor.getActiveTasksCount() > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Текущее количество активных задач: " + processor.getActiveTasksCount());
        }

        System.out.println("Все задачи завершены!");
        Set<String> nameTasks = processor.getAllNameTasks();
        for (String name : nameTasks) {
            System.out.printf(
                    "Имя задачи - %s, результат - %s%n",
                    name,
                    processor.getTaskResult(name).orElseThrow()
            );
        }
    }
}
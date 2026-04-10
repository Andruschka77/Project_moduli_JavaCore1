package org.example.Multithreading;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class DataProcessor {

    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final AtomicInteger taskCounter = new AtomicInteger(0);
    private final AtomicInteger activeTaskCounter = new AtomicInteger(0);
    private final Map<String, Integer> results = new HashMap<>();

    public void submitTask(List<Integer> numbers) {
        String taskName = "task_" + taskCounter.incrementAndGet();
        CalculateSumTask task = new CalculateSumTask(numbers, taskName);
        activeTaskCounter.incrementAndGet();
        executor.submit(() -> {
            try {
                Integer result = task.call();
                synchronized (results) {
                    results.put(taskName, result);
                }
                System.out.printf("Задача %s завершена. Результат: %s\n", taskName, result);
            } catch (Exception e) {
                System.out.printf("Ошибка при выполнении задачи %s: %s%n", taskName, e.getMessage());
            } finally {
                activeTaskCounter.decrementAndGet();
            }
        });
    }

    public Integer getActiveTasksCount() {
        return activeTaskCounter.get();
    }

    public Optional<Integer> getTaskResult(String taskName) {
        synchronized (results) {
            if (results.containsKey(taskName)) {
                return Optional.of(results.get(taskName));
            } else {
                return Optional.empty();
            }
        }
    }

    public Set<String> getAllNameTasks() {
        synchronized (results) {
            return results.keySet();
        }
    }

}

package org.example.Multithreading;

import java.util.List;
import java.util.concurrent.Callable;

public class CalculateSumTask implements Callable<Integer> {

    private final List<Integer> numbers;
    private final String nameTask;

    public CalculateSumTask(
            List<Integer> numbers,
            String nameTask
    ) {
        this.numbers = numbers;
        this.nameTask = nameTask;
    }

    @Override
    public Integer call() {
        System.out.printf(
                "Имя задачи - %s%nИмя текущего потока - %s%n",
                nameTask,
                Thread.currentThread().getName()
        );
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return numbers
                .stream()
                .reduce(0, Integer::sum);
    }

}
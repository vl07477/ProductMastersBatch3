package Homework.six.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberProcessor {
    private final List<Integer> numbers;

    public NumberProcessor(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);
    }

    public int findMin() {
        return Collections.min(numbers); //ищем минимальное значение из ArrayList<>(numbers)
    }

    public int findMax() {
        return Collections.max(numbers); //ищем максимальное значение мз ArrayList<>(numbers)
    }

    public List<Integer> sortAscending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted); //запускаем сортировку значений по возрастанию ArrayList<>(numbers)
        return sorted;
    }

    public List<Integer> sortDescending() {
        List<Integer> sorted = new ArrayList<>(numbers);
        sorted.sort(Collections.reverseOrder());//сортирум значения по убыванию ArrayList<>(numbers)
        return sorted;
    }

    public boolean contains(int value) {
        return numbers.contains(value); //проверка на наличие значения
    }

    public List<Integer> filterGreaterThan(int threshold) {
        List<Integer> filtered = new ArrayList<>();
        for (int number : numbers) { //фильтр значений ArrayList<>(numbers) больше выбранного числа
            if (number > threshold) {
                filtered.add(number);
            }
        }
        return filtered;
    }

    public int sum() {
        int total = 0;
        for (int number : numbers) {
            total += number; //сумма всех значений ArrayList<>(numbers)
        }
        return total;
    }
}


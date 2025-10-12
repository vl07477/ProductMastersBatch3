package Homework.six.easy;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(15, 7, 4, 21, 31, 20, 11, 1, 44);
        NumberProcessor processor = new NumberProcessor(input);

        System.out.println("Поиск минимума: " + processor.findMin());
        System.out.println("Поиск максимума: " + processor.findMax());
        System.out.println("Сортировка по возрастанию: " + processor.sortAscending());
        System.out.println("Сортировка по убыванию: " + processor.sortDescending());
        System.out.println("Поиск элемента: содержит 4? " + processor.contains(4));
        List<Integer> filtered = processor.filterGreaterThan(100);
        if (filtered.isEmpty()) {
            System.out.println("Таких значений нет");
        } else {
            System.out.println("Фильтр элементов: числа больше 100: " + filtered);
        }
        System.out.println("Сумма: " + processor.sum());
    }
}

package Homework.five.medium;
import java.util.ArrayList;
import java.util.List;
public class Main {

    public static void main(String[] args) {
        List<DayOfWeek> days = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            days.add(day);
        }

        System.out.println("Days: ");
        for (DayOfWeek day : days) {
            System.out.println(day.getRuName());
        }
        DayOfWeek today = DayOfWeek.Saturday;
        System.out.println("Сегодня: " + today.getRuName());
        System.out.println("Выходной? " + isWeekend(today));
    }

    public static boolean isWeekend(DayOfWeek day) {
        int num = day.getDayNumber();
        return num == 6 || num == 7;
    }
}

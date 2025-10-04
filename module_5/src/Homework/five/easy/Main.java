package Homework.five.easy;

public class Main {
    public static void main(String[] args) {
        DayOfWeek today = DayOfWeek.SATURDAY; //from enum
        System.out.println("Today: " + today);
        for (DayOfWeek day: DayOfWeek.values() ){
            System.out.println(day);
        }
    }
}

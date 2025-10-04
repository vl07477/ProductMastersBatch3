package Homework.five.medium;


public enum DayOfWeek {
    Monday("Понедельник", 1),
    Tuesday("Вторник", 2),
    Wednesday("Среда", 3),
    Thursday("Четверг", 4),
    Friday("Пятница", 5),
    Saturday("Суббота", 6),
    Sunday("Воскресенье", 7); // ← ← ← ← вот тут ОБЯЗАТЕЛЬНО точка с запятой!

    private final String ruName;
    private final int dayNumber;

    DayOfWeek(String ruName, int dayNumber) {
        this.ruName = ruName;
        this.dayNumber = dayNumber;
    }

    public String getRuName() {
        return ruName;
    }

    public int getDayNumber() {
        return dayNumber;
    }
}
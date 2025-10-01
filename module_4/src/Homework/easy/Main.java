package Homework.easy;

public class Main {
    public static void main(String[] args) {
        Printer<String> printer = new StringPrinter();
        printer.print("Privet, mir!");
        printer.print("""
                new HW (week4)!""");
    }
}

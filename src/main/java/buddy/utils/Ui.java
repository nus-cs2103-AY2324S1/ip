package buddy.utils;

public class Ui {

    public static void printGreeting(String name) {
        String greeting = String.format("Hello! I'm %s\n", name);
        String inquiry = "What would you like to do?\n";
        System.out.println(greeting + inquiry);
        // System.out.println("You have the following buddy.tasks:");
    }

    public static void printFarewell() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}

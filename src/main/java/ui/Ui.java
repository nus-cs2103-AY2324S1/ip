package ui;

/**
 * Deals with interactions with user
 */
public class Ui {

    public Ui() {

    }
    public void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }


    public void printStart() {
        String name = "BOB";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();
    }

    public void printEnd() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();
    }

    public void printInvalidCommand() {
        printHorizontalLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printHorizontalLine();
    }


}

package linus.util;

import java.util.Scanner;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    public static void print(String message) {
        System.out.println(
                "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\n"
                        + message + "\n"
                        + "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"
        );
    }

    public static void showWelcomeMessage() {
        String name = "LINUS";
        print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }

    public static void showExitMessage() {
        print("Bye. Hope to see you again soon!");
    }

    public static void showLoadingError() {
        print("The file system experienced an unexpected error.");
    }

    public static String readInput() {
        return sc.nextLine();
    }
}

package Ui;

import java.util.Scanner;

public class Ui {
    public static int CHAT_WIDTH = 80;

    public static void helloWorld() {
        System.out.println("Hello! I'm Jing Sheng");
        System.out.println("What can I do for you?");
    }

    public static String getUserInput(Scanner scanner) {
        printDividerLine();
        System.out.print("You: ");
        return scanner.nextLine();
    }

    public static void getBotMessage() {
        printDividerLine();
        System.out.println("Bot: ");
    }

    public static void showByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void showConfused() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printDividerLine() {
        System.out.println("═".repeat(CHAT_WIDTH));
    }
}

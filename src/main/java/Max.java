import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Max {
    private static String line = "____________________________________________________________";
    private static Task[] myList = new Task[100];
    private static int numOfItems = 1;
    public static class Task {
        private String item;
        private boolean done;
        public Task(String item) {
            this.item = item;
            this.done = false;
        }
        private void mark() {
            this.done = true;
            System.out.println("     Good job on completing your task!");
            System.out.println("       " + this);
            System.out.println(Max.line);
        }
        private void unmark() {
            this.done = false;
            System.out.println("     Okay, I've marked this as not done yet!");
            System.out.println("       " + this);
            System.out.println(Max.line);
        }

        @Override
        public String toString() {
            String doneStatus = this.done ? "X" : " ";
            return "[" + doneStatus + "] " + item;
        }
    }
    public static void greet() {
        String logo = ".___  ___.      ___      ___   ___ \n" +
                "|   \\/   |     /   \\     \\  \\ /  / \n" +
                "|  \\  /  |    /  ^  \\     \\  V  /  \n" +
                "|  |\\/|  |   /  /_\\  \\     >   <   \n" +
                "|  |  |  |  /  _____  \\   /  .  \\  \n" +
                "|__|  |__| /__/     \\__\\ /__/ \\__\\ \n" +
                "                                   ";
        System.out.println("     Hello from\n" + logo);
        System.out.println(Max.line);
        System.out.println("     Hello! I'm Max! \n" + "     How may I assist you?");
        System.out.println(Max.line);
    }

    public static void add(String string) {
        Max.myList[numOfItems] = new Task(string);
        numOfItems++;
        System.out.println("     added: " + string);
        System.out.println(Max.line);
    }

    public static void list() {
        System.out.println("     Here are all your tasks:");
        for (int i = 1; i < numOfItems; i++) {
            System.out.println("     " + i + ". " + myList[i]);
        }
        System.out.println(Max.line);
    }

    public static void exit() {
        System.out.println("     Bye! Please come again!");
        System.out.println(Max.line);
    }
    public static void main(String[] args) {
        Max.greet();

        Scanner input = new Scanner(System.in);
        boolean scannerOpen = true;

        // User is interacting with chatbot
        while (scannerOpen) {
            String command = input.nextLine();

            if (command.equals("bye")) {
                scannerOpen = false;
            } else if (command.equals("list")) {
                Max.list();
            } else if (command.contains("unmark")) {
                int taskNumber = parseInt(command.substring(7));
                myList[taskNumber].unmark();
            } else if (command.contains("mark")) {
                int taskNumber = parseInt(command.substring(5));
                myList[taskNumber].mark();
            } else {
                Max.add(command);
            }
        }

        // User has exited the chatbot
        input.close();
        Max.exit();
    }
}

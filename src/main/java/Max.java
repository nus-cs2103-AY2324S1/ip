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
    public static class Todo extends Task {
        public Todo(String item) {
            super(item);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
    public static class Deadline extends Task {
        private String by;
        public Deadline(String item, String by) {
            super(item);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }
    public static class Event extends Task {
        private String from, to;
        public Event(String item, String from, String to) {
            super(item);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to +")";
        }
    }
    public static void greet() {
        System.out.println("     Hello from");
        System.out.println("       /\\/\\   __ ___  __");
        System.out.println("      /    \\ / _` \\ \\/ /");
        System.out.println("     / /\\/\\ \\ (_| |>  <");
        System.out.println("     \\/    \\/\\__,_/_/\\_\\");
        System.out.println("     How may I assist you?");
        System.out.println(Max.line);
    }

    public static void add(String task) {
        // Parse command based on type of task (Todo, Deadline, Event)
        if (task.startsWith("todo")) {
            String description = task.substring(5).trim();
            Max.myList[numOfItems] = new Todo(description);
        } else if (task.startsWith("deadline")) {
            int byIndex = task.indexOf("/by");
            String item = task.substring(0, byIndex - 1).trim();
            String by = task.substring(byIndex + 3).trim();
            Max.myList[numOfItems] = new Deadline(item, by);
        } else if (task.startsWith("event")) {
            int fromIndex = task.indexOf("/from");
            int toIndex = task.indexOf("/to");
            String item = task.substring(0, fromIndex - 1).trim();
            String from = task.substring(fromIndex + 5, toIndex -1).trim();
            String to = task.substring(toIndex + 3).trim();
            Max.myList[numOfItems] = new Event(item, from, to);
        }

        // Visual feedback from chatbot
        System.out.println("     I gotchu. I've added this task:");
        System.out.println("       " + Max.myList[numOfItems]);
        System.out.println("     Now you have " + numOfItems + " tasks in the list.");
        System.out.println(Max.line);

        // Increment pointer
        numOfItems++;
    }

    public static void list() {
        System.out.println("     Here are all your tasks:");

        // Iterate through array of tasks and enumerate them
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
                // User wants to exit the chatbot
                scannerOpen = false;
            } else if (command.equals("list")) {
                Max.list();
            } else if (command.contains("unmark")) {
                // Determine which task to unmark
                int taskNumber = parseInt(command.substring(7));
                myList[taskNumber].unmark();
            } else if (command.contains("mark")) {
                // Determine which task to mark
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

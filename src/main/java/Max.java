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
    public static class MaxException extends Exception {
        public MaxException(String msg) {
            super(msg);
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

    public static void add(String task) throws MaxException {
        // Parse command based on type of task (Todo, Deadline, Event)
        if (task.startsWith("todo")) {
            // Error checking: empty fields
            if (task.length() < 6) {
                throw new MaxException("     Watch out -- todo description cannot be empty.");
            }

            String description = task.substring(5).trim();

            Max.myList[numOfItems] = new Todo(description);
        } else if (task.startsWith("deadline")) {
            int byIndex = task.indexOf("/by");

            // Error checking: no /by tag
            if (byIndex == -1) {
                throw new MaxException("     Try again... deadline must include a '/by' tag!");
            }

            String item = task.substring(0, byIndex - 1).trim();
            String by = task.substring(byIndex + 3).trim();

            // Error checking: empty fields
            if (item.isEmpty() || by.isEmpty()) {
                throw new MaxException("     Oops... Deadline item or 'by' date cannot be empty.");
            }

            Max.myList[numOfItems] = new Deadline(item, by);
        } else if (task.startsWith("event")) {
            int fromIndex = task.indexOf("/from");
            int toIndex = task.indexOf("/to");

            // Error checking: no /from or /to tag
            if (fromIndex == -1 || toIndex == -1) {
                throw new MaxException("     Hey! Event must contain '/from' and '/to' tags.");
            }

            String item = task.substring(0, fromIndex - 1).trim();
            String from = task.substring(fromIndex + 5, toIndex -1).trim();
            String to = task.substring(toIndex + 3).trim();

            // Error checking: empty fields
            if (item.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new MaxException("     Oh no! Event item, 'from' date, or 'to' date cannot be empty.");
            }

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
            try {
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
                } else if (command.contains("event") || command.contains("todo") ||
                        command.contains("deadline")) {
                    Max.add(command);
                } else {
                    throw new MaxException("     Oh no! I cannot recognise that command.");
                }
            } catch (MaxException e) {
                System.out.println(e.getMessage());
                System.out.println(Max.line);
            }
        }

        // User has exited the chatbot
        input.close();
        Max.exit();
    }
}

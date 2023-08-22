import java.util.Scanner;
public class CR7 {
    // Function to print a horizontal line
    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void unmarkAsDone() {
            isDone = false;
        }

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }

    public static class ToDo extends Task {
        public ToDo(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Event extends Task {

        protected String start;
        protected String end;

        public Event(String description, String start, String end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }


    public static void main(String[] args) {
        printHorizontalLine(40);
        System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
        String input = "";
        Task[] tasks = new Task[100];
        int counter = 0;
        while (!input.equals("bye")) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();
            if (input.equals("bye")) {
                printHorizontalLine(40);
                System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
                printHorizontalLine(40);
                break;
            }
            if (input.equals("list")) {
                printHorizontalLine(40);
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < counter + 1; i++) {
                    Task x = tasks[i-1];
                    System.out.println(i + "." + x.toString());
                }
                System.out.println();
                printHorizontalLine(40);
            } else {
                String[] words = input.split(" ");
                String first = words[0];
                if (first.equals("mark")) {
                    int s = Integer.valueOf(words[1]);
                    Task k = tasks[s - 1];
                    k.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + k.toString() + "\n");
                    printHorizontalLine(40);
                }
                if (first.equals("unmark")) {
                    int s = Integer.valueOf(words[1]);
                    Task k = tasks[s - 1];
                    k.unmarkAsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + k.toString() + "\n");
                    printHorizontalLine(40);
                }
                if (first.equals("todo")) {
                    String desc = input.substring(5);
                    Task t = new ToDo(desc);
                    tasks[counter] = t;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + counter + " tasks in the list");
                }
                if (first.equals("deadline")) {
                    String desc = input.substring(9, input.indexOf("/") - 1);
                    String by = input.substring(input.lastIndexOf("/by ") + 4);
                    Task t = new Deadline(desc, by);
                    tasks[counter] = t;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + counter + " tasks in the list");
                }
                if (first.equals("event")) {
                    String desc = input.substring(6, input.indexOf("/") - 1);
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    String start = input.substring(fromIndex + 6, toIndex).trim();
                    String end = input.substring(toIndex + 4).trim();
                    Task t = new Event(desc, start, end);
                    tasks[counter] = t;
                    counter++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + t.toString());
                    System.out.println("Now you have " + counter + " tasks in the list");
                }
            }
        }
    }
}

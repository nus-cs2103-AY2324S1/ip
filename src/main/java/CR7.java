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

        public String getDescription() {
            return this.description;
        }

        public void markAsDone() {
            isDone = true;
        }

        public void unmarkAsDone() {
            isDone = false;
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
                    System.out.println(i + ".[" + x.getStatusIcon() + "] " + x.getDescription());
                }
                System.out.println();
                printHorizontalLine(40);
            } else if (input.startsWith("mark ")) {
                int s = Integer.valueOf(input.substring(5));
                Task k = tasks[s-1];
                k.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [X] " + k.getDescription() + "\n");
                printHorizontalLine(40);
            } else if (input.startsWith("unmark ")) {
                int s = Integer.valueOf(input.substring(7));
                Task k = tasks[s-1];
                k.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  [ ] " + k.getDescription() + "\n");
                printHorizontalLine(40);
            } else {
                Task t = new Task(input);
                tasks[counter] = t;
                counter++;
                printHorizontalLine(40);
                System.out.println("added: " + input + "\n");
                printHorizontalLine(40);
            }
        }
    }
}

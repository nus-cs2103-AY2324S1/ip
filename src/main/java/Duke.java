import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static String name = "WallE";
    private static class Task {
        private String name;
        private boolean isDone;
        public Task(String name, boolean isDone) {
            this.name = name;
            this.isDone = isDone;
        }
        public String getName() {
            return this.name;
        }
        public boolean isDone() {
            return this.isDone;
        }
        public void markAsDone() {
            this.isDone = true;
        }
        public void markAsUndone() {
            this.isDone = false;
        }
        public String getStatusIcon() {
            return this.isDone() ? "[X]" : "[]";
        }

        @Override
        public String toString() {
            return String.format("%s %s", this.getStatusIcon(), this.getName());

        }
    }
    private static List<Task> tasks = new ArrayList<>();
    public static void printDivider(boolean isIndented) {
        if (isIndented)
            System.out.println('\t' + "_________________________________________");
        else
            System.out.println("_________________________________________");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printDivider(true);
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
        printDivider(true);
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                printDivider(true);
                if (!input.equals("list")) {
                    String[] inputWords = input.split(" ");
                    if (inputWords[0].equals("mark") || inputWords[0].equals("unmark")) {
                        try {
                            int id = Integer.valueOf(inputWords[1]) - 1;
                            switch (inputWords[0]) {
                                case "mark":
                                    tasks.get(id).markAsDone();
                                    System.out.println("\tNice! I've marked this task as done:");
                                    System.out.println("\t\t" + tasks.get(id).toString());
                                    break;
                                case "unmark":
                                    tasks.get(id).markAsUndone();
                                    System.out.println("\tOk, I've marked this task as not done yet:");
                                    System.out.println("\t\t" + tasks.get(id).toString());
                            }
                        } catch (Exception e) {
                            throw e;
                        }
                    } else {
                        tasks.add(new Task(input, false));
                        System.out.println("\tadded: " + input);
                    }
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format("\t%d.%s", i + 1, task.toString()));
                    }
                }
                printDivider(true);
            } else {
                break;
            }
        } while (true);

        printDivider(true);
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider(true);
    }
}

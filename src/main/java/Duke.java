import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private String line = "\n_____________________________________________________\n";
    private ArrayList<Task> lst = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Duke bot = new Duke();
        bot.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println(line);

        System.out.println("Hello! I'm Gerald_Bot\n" + "What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                this.exit();
                break;
            } else if (input.equals("list")) {
                this.printList();
            } else if (input.startsWith("mark")) {
                String[] parsedString = input.split(" ");
                try {
                    int num = Integer.parseInt(parsedString[1]);
                    if (num > lst.size() || num <= 0) {
                        System.out.println(line);
                        System.out.println("Task do not exist in the list!");
                        System.out.println(line);
                        continue;
                    }
                    Task selectedTask = lst.get(num - 1);
                    this.markCompletion(selectedTask);
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(e);
                    System.out.println(line);
                }
            } else if (input.startsWith("unmark")) {
                String[] parsedString = input.split(" ");
                try {
                    int num = Integer.parseInt(parsedString[1]);
                    if (num > lst.size() || num <= 0) {
                        System.out.println(line);
                        System.out.println("Task do not exist in the list!");
                        System.out.println(line);
                        continue;
                    }
                    Task selectedTask = lst.get(num - 1);
                    this.unmarkCompletion(selectedTask);
                } catch (NumberFormatException e) {
                    System.out.println(line);
                    System.out.println(e);
                    System.out.println(line);
                }
            } else {
                this.addTask(input);
            }
        }
    }

    public void addTask(String input) {
        Task newTask = new Task(input);
        System.out.println(line);
        System.out.println("added: " + input);
        lst.add(newTask);
        System.out.println(line);
    }

    public void printList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + ". " + lst.get(i).toString() + "\n");
        }
        System.out.println(line);
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public void markCompletion(Task task) {
        if (task.getStatusIcon().equals("X")) {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n");
            System.out.println("\t" + task.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n");
            task.toggleCompletion();
            System.out.println("\t" + task.toString());
            System.out.println(line);
        }
    }

    public void unmarkCompletion(Task task) {
        if (task.getStatusIcon().equals(" ")) {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n");
            System.out.println("\t" + task.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n");
            task.toggleCompletion();
            System.out.println("\t" + task.toString());
            System.out.println(line);
        }
    }
}
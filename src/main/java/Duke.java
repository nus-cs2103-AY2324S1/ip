import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static void addTask(String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        System.out.println("added: " + input);
    }
    private static void listTask() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + "[" + task.getStatusIcon() + "] " + task);
        }
    }

    private static void modifyTask(String input, int index) {
        Task task = tasks.get(index - 1);
        if (input.equals("mark")) {
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] "+ task);
        } else {
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet: \n" + "[" + task.getStatusIcon() + "] " + task);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BaekBot. \nWhat can I do for you?");
        while (true) {
            String echoInput = scanner.nextLine();
            if (echoInput.equals("bye")) {
                break;
            } else if (echoInput.equals("list")) {
                listTask();
            } else if (echoInput.substring(0, 4).equals("mark") || echoInput.substring(0, 6).equals("unmark")) {
                String temp[] = echoInput.split(" ");
                modifyTask(temp[0], Integer.parseInt(temp[1]));
            } else {
                addTask(echoInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}

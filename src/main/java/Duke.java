import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList list = new TaskList();

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();

        while(true) {
            if (input.equals("bye")) {
                break;
            }
            else if (input.equals("list")) {
                list.printList();
            }
            else if (input.startsWith("mark") || input.startsWith("unmark")) {
                String[] inputSplit = input.split(" ");
                if (inputSplit.length == 1) {
                    System.out.println("Input the task number!");
                } else if (inputSplit.length > 2) {
                    System.out.println("Invalid command");
                } else {
                    int taskNo = Integer.parseInt(inputSplit[1]) - 1;

                    if (input.startsWith("mark")) {
                        list.setTaskComplete(taskNo);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.getTask(taskNo));
                    } else {
                        list.setTaskIncomplete(taskNo);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.getTask(taskNo));
                    }
                }
            }  else {
                list.addTask(new Task(input));
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }
}

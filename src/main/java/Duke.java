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
                    } else {
                        list.setTaskIncomplete(taskNo);
                    }
                }
            } else {
                if (input.startsWith("todo")) {
                    input = input.substring(5);
                    list.addTask(new ToDoTask(input));
                    System.out.println("added: " + input);
                } else if (input.startsWith("deadline")) {
                    input = input.substring(9);
                    String[] inputArr;
                    inputArr = input.split(" /by ");
                    list.addTask(new DeadlineTask(inputArr[0], inputArr[1]));
                } else if (input.startsWith("event")) {
                    input = input.substring(6);
                    String[] inputArr;
                    inputArr = input.split(" /from ");
                    String description = inputArr[0];
                    inputArr = inputArr[1].split(" /to ");
                    list.addTask(new EventTask(description, inputArr[0], inputArr[1]));
                } else {
                    list.addTask(new Task(input));
                }
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

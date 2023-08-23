import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> todoList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I am Dukey.\n"
                + "What can I do for you?");

        while (true) {
            String response = scanner.nextLine();

            if (response.isEmpty()) {
                System.out.println("You entered nothing! Try again!");
            } else if (response.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (response.equalsIgnoreCase("list")) {
                int i;
                for (i = 1; i < todoList.size() + 1; i++) {
                    System.out.println(i + ". " + todoList.get(i - 1).toString());
                }
            } else if (response.startsWith("mark")) {
                try {
                    String[] parts = response.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    todoList.get(taskIndex).markDone();
                    System.out.println("Good Job! I have marked this task as done!");
                    System.out.println(todoList.get(taskIndex).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                }
            } else if (response.startsWith("unmark")) {
                try {
                    String[] parts = response.split(" ");
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    todoList.get(taskIndex).markUndone();
                    System.out.println("Aw man! I have marked this task as undone. We go again!");
                    System.out.println(todoList.get(taskIndex).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("This task does not exist!");
                }
            } else if (response.startsWith("deadline")) {
                Deadline newDeadline = new Deadline(response);
                todoList.add(newDeadline);
                System.out.println("added: " + newDeadline.toString());
            } else if (response.startsWith("event")) {
                Event newEvent = new Event(response);
                todoList.add(newEvent);
                System.out.println("added: " + newEvent.toString());
            } else {
                ToDo newTodo = new ToDo(response);
                todoList.add(newTodo);
                System.out.println("added: " + newTodo.toString());
                }
            }
        scanner.close();
        }
    }






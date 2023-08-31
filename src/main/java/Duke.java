import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList list = null;

        Storage storage = new Storage();
        list = new TaskList(storage.getFile());

        System.out.println("Hello! I'm Bob");
        System.out.println("What can I do for you?");
        String input = scanner.nextLine();

        while(true) {
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    list.printList();
                } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                    String[] inputSplit = input.split(" ");
                    if (inputSplit.length == 1) {
                        throw new DukeException("Input the task number");
                    } else if (inputSplit.length > 2) {
                        throw new DukeException("Invalid command");
                    } else {
                        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
                        if (input.startsWith("mark")) {
                            list.setTaskComplete(taskNo);
                        } else {
                            list.setTaskIncomplete(taskNo);
                        }
                    }
                } else if (input.startsWith("delete")) {
                    String[] inputSplit = input.split(" ");
                    if (inputSplit.length == 1) {
                        throw new DukeException("Input the task number");
                    } else if (inputSplit.length > 2) {
                        throw new DukeException("Invalid command");
                    } else {
                        int taskNo = Integer.parseInt(inputSplit[1]) - 1;
                        list.deleteTask(taskNo);
                    }
                } else {
                    if (input.startsWith("todo")) {
                        if (input.length() == 4 || input.equals("todo ")) {
                            throw new DukeException("Description of a todo cannot be empty");
                        }
                        input = input.substring(5);
                        list.addTask(new ToDoTask(input));
                    } else if (input.startsWith("deadline")) {
                        if (input.length() == 8 || input.equals("deadline ")) {
                            throw new DukeException("Description of a deadline cannot be empty");
                        }
                        input = input.substring(9);
                        String[] inputArr;
                        inputArr = input.split(" /by ");
                        list.addTask(new DeadlineTask(inputArr[0], inputArr[1]));
                    } else if (input.startsWith("event")) {
                        if (input.length() == 5 || input.equals("event ")) {
                            throw new DukeException("Description of an event cannot be empty");
                        }
                        input = input.substring(6);
                        String[] inputArr;
                        inputArr = input.split(" /from ");
                        String description = inputArr[0];
                        inputArr = inputArr[1].split(" /to ");
                        list.addTask(new EventTask(description, inputArr[0], inputArr[1]));
                    } else {
                        throw new DukeException("I don't know what that means");
                    }
                }
                input = scanner.nextLine();
            } catch (DukeException e) {
                System.out.println(e);
                input = scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Please key in a valid index");
                input = scanner.nextLine();
            }
        }
        list.close();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }
}

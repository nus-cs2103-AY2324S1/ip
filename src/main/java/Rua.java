import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;


public class Rua {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "____________________________________________________________\n" +
                " Hello! I'm Rua, your ChatBot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbye= "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        String saveDir = "src/main/data/";
        String saveAddress = "src/main/data/tasks.txt";//Note that class path is pointing to the ip directory
        TaskList taskList = new TaskList(saveAddress);
        System.out.println(greeting);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println("____________________________________________________________\n");
            try {
                if (command.equals("list")) {
                    System.out.println(taskList);
                } else if (command.startsWith("todo")) {
                    String[] arr = command.split(" ", 2);
                    if (arr.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }
                    Todo newTodo = new Todo(arr[1]);
                    taskList = taskList.add(newTodo);
                } else if (command.startsWith("deadline")) {
                    String[] arr = command.split(" ", 2);
                    if (arr.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    String[] info = arr[1].split(" /by ", 2);
                    if (info.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    Deadline newDeadline = new Deadline(info[0], info[1]);
                    taskList = taskList.add(newDeadline);
                } else if (command.startsWith("event")) {
                    String[] arr = command.split(" ", 2);
                    if (arr.length == 1) {
                        throw new EmptyDescriptionException("event");
                    }
                    String[] info = arr[1].split(" /from ", 2);
                    if (info.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    String[] time = info[1].split(" /to ", 2);
                    if (time.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    Event newEvent = new Event(info[0], time[0], time[1]);
                    taskList = taskList.add(newEvent);
                } else if (command.startsWith("mark")) {
                    String indexStr = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(indexStr);
                    taskList = taskList.mark(index);
                } else if (command.startsWith("unmark")) {
                    String indexStr = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(indexStr);
                    taskList = taskList.unmark(index);
                } else if (command.startsWith("delete")) {
                    String indexStr = command.replaceAll("[^0-9]", "");
                    int index = Integer.parseInt(indexStr);
                    taskList = taskList.delete(index);
                } else {
                    throw new InvalidCommandException();
                }
            }
            catch (FileNotFoundException exp) {
                System.out.println("Target file not found. Do you want to create it now? Please type yes or no\n");
                String create = sc.nextLine();
                while (!create.equals("yes") && !create.equals("no")) {
                    System.out.println("Incorrect input. Please type yes or no\n");
                }
                if (create.equals("yes")) {
                    File fileCreator = new File(saveDir);
                    fileCreator.mkdir();
                    System.out.println("File successfully created. Progress saved.\n");
                    try {
                        taskList.save();
                    }
                    catch (IOException ioexp) {
                        System.out.println("Some error occurs and progress is not saved.\n");
                    }
                } else {
                    break;
                }
            }
            catch (Exception exp) {
                System.out.println(exp);
            }
            System.out.println("____________________________________________________________\n");
            command = sc.nextLine();
        }
        System.out.println(goodbye);
    }
}

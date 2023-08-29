import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Duke is a class in-charge of task management.
 * It allows users to add, delete, mark, unmark, specify, and list tasks.
 */
public class Duke {
    /**
     * The main function of the Duke application.
     *
     * @param args Command line arguments
     * @throws DukeException If an error has occurred in the Duke application.
     */
    public static void main(String[] args) throws DukeException {
        System.out.println("Hello! I'm Chatty\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = readData();
        
        while (true) {
            String userInput = scanner.nextLine();
            try {
                String[] words = userInput.split(" ");
                if (words.length == 2) {
                    if (words[0].equals("mark")) {
                        int taskIndex = Integer.parseInt(words[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < taskList.size()) {
                            Task task = taskList.get(taskIndex);
                            task.markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n " + task);
                            continue;
                        } else {
                            throw new InvalidRangeException("Invalid task index. You have " +
                                    taskList.size() + " tasks in the list.");
                        }
                    }

                    if (words[0].equals("unmark")) {
                        int taskIndex = Integer.parseInt(words[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < taskList.size()) {
                            Task task = taskList.get(taskIndex);
                            task.markAsUndone();
                            System.out.println("OK, I've marked this task as not done yet:\n " + task);
                            continue;
                        } else {
                            throw new InvalidRangeException("Invalid task index. You have " +
                                    taskList.size() + " tasks in the list.");
                        }
                    }

                    if (words[0].equals("delete")) {
                        int taskIndex = Integer.parseInt(words[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < taskList.size()) {
                            Task deletedTask = taskList.remove(taskIndex);
                            System.out.println("Noted. I've removed this task:\n " + deletedTask
                                    + "\nNow you have " + taskList.size() + " tasks in the list.");
                            continue;
                        } else {
                            throw new InvalidRangeException("Invalid task index. You have " +
                                    taskList.size() + " tasks in the list.");
                        }
                    }
                }

                if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < taskList.size(); j++) {
                        int index = j + 1;
                        System.out.println(index + "." + taskList.get(j).toString());
                    }
                } else if (userInput.equals("bye")) {
                    break;
                } else if (isValid(userInput)) {
                    Task newTask = createNewTask(userInput);
                    taskList.add(newTask);
                    System.out.println("Got it. I've added this task:\n " + newTask
                            + "\nNow you have " + taskList.size() + " tasks in the list.");
                } else {
                    // ☹ OOPS!!! I'm sorry, but I don't know what that means :-(
                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Checks if the user input is a valid task type.
     *
     * @param userInput The user's input.
     * @return True if the input is a valid task type, false otherwise.
     */
    private static boolean isValid(String userInput) {
        return userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event");
    }

    /**
     * Creates a new task based on the user's input.
     *
     * @param userInput The user's input.
     * @return A new Task object.
     * @throws DukeException If userInput does not meet task requirements.
     */
    private static Task createNewTask(String userInput) throws DukeException {
        Task newTask;
        //Remove first word
        String[] arr = userInput.split(" ", 2);

        if (arr.length != 2 || arr[1].isEmpty()) {
            // ☹ OOPS!!! The description of a _____ cannot be empty.
            throw new EmptyTaskException(arr[0]);
        } else {
            if (arr[0].equals("todo")) {
                newTask = new ToDo(arr[1]);
            } else if (arr[0].equals("deadline")) {
                String[] a = arr[1].split(" /by ");
                if (a.length != 2 || a[1].isEmpty()) {
                    throw new EmptyDateException(arr[0]);
                }
                newTask = new Deadline(a[0], a[1]);
            } else {
                String[] a = arr[1].split(" /from ");
                if (a.length != 2 || a[1].isEmpty()) {
                    throw new EmptyDateException(arr[0]);
                }

                String[] fromto = a[1].split("/to ");
                if (fromto.length != 2 || fromto[1].isEmpty()) {
                    throw new NoEndDateException("☹ OOPS!!! Please provide a end date for your event.");
                }

                newTask = new Event(a[0], fromto[0], fromto[1]);
            }
        }

        return newTask;
    }

    private static ArrayList<Task> readData() throws DukeException {
        String filePath = "./src/main/java/data/duke.txt";
        ArrayList<String> dataArray = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File(filePath);

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                dataArray.add(input);
            }

            scanner.close();
            String[] data = dataArray.toArray(new String[dataArray.size()]);

            for (String item : data) {
                taskList.add(loadData(item));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
            System.out.println("File not found: " + filePath);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        return taskList;
    }

    private static Task loadData(String dataInput) throws DukeException {
        Task newTask;
        String[] arr = dataInput.split("\\|");

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }


        if (arr[0].equals("T")) {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("todo");
            }

            newTask = new ToDo(arr[2]);
        } else if (arr[0].equals("D")) {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("deadline");
            }
            if (arr.length != 4|| arr[3].isEmpty()) {
                throw new EmptyDateException("deadline");
            }

            newTask = new Deadline(arr[2], arr[3]);
        } else {
            if (arr.length < 3 || arr[2].isEmpty()) {
                throw new EmptyTaskException("event");
            }
            if (arr.length < 4 || arr[3].isEmpty()) {
                throw new EmptyDateException("event");
            }
            if (arr.length != 5 || arr[4].isEmpty()) {
                throw new NoEndDateException("☹ OOPS!!! Please provide a end date for your event.");
            }

            newTask = new Event(arr[2], arr[3], arr[4]);
        }

        // mark as done
        if (arr[1].equals("1")) {
            newTask.markAsDone();
        }

        return newTask;
    }
}

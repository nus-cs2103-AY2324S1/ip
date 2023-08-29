import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String helloString = "Hello! I'm AdaBot.\nWhat do you want to do today?";
    private static final String byeString = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static String addTask(Task task) {
        tasks.add(task);
        String res = "Got it. I've added this task:\n";
        res += String.format("  %s\n", task.toString());
        res += String.format("Now you have %d tasks in the list.", tasks.size());
        return res;
    }

    private static String deleteTask(int index) {
        Task task = tasks.remove(index);
        String res = "Noted. I've removed this task:\n";
        res += String.format("  %s\n", task.toString());
        res += String.format("Now you have %d tasks in the list.", tasks.size());
        return res;
    }

    public static void print(String s) {
        System.out.println(s);
    }

    public static void printError(String s) {
        System.out.println("OOPS!!! " + s);
    }

    public static void main(String[] args) {
        try {
            tasks = FileHandler.read();
        } catch (DukeException e) {
            printError(e.toString());
        }

        Scanner input = new Scanner(System.in);
        print(helloString);
        String response = "";

        while (input.hasNextLine()) {
            response = input.nextLine();
            String[] queries = response.trim().split("\\s+");
            if (queries[0].equals("bye")) {
                break;
            } 
            try {
                switch (queries[0]) {
                case "list":
                    if (tasks.size() == 0) {
                        print("There is no task in your list.");
                        break;
                    }
                    print("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        print(String.format("%d.%s", i + 1, tasks.get(i).toString()));
                    }
                    break;
                case "mark":
                    try {
                        int index = Integer.parseInt(queries[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("Invalid task index");
                        }
                        tasks.get(index).markDone();
                        print("Nice! I've marked this task as done:");
                        print("  " + tasks.get(index).toString());
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "unmark":
                    try {
                        int index = Integer.parseInt(queries[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("Invalid task index");
                        }
                        tasks.get(index).unmarkDone();
                        print("OK, I've marked this task as not done yet:");
                        print("  " + tasks.get(index).toString());
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "delete":
                    try {
                        int index = Integer.parseInt(queries[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("Invalid task index");
                        }
                        print(deleteTask(index));
                    } catch (NumberFormatException e) {
                        throw new DukeException(e);
                    }
                    break;
                case "deadline":
                    print(addTask(Deadline.create(queries)));
                    break;
                case "event":
                    print(addTask(Event.create(queries)));
                    break;
                case "todo":
                    print(addTask(ToDo.create(queries)));
                    break;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printError(e.toString());
            }
        }

        try {
            FileHandler.write(tasks);
        } catch (DukeException e) {
            printError(e.toString());
        }

        print(byeString);
        input.close();
    }
}

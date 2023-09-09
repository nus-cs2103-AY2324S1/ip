import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;
import java.io.IOException;



public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello from Branch-Level-7\n");
        System.out.println("_____________________________________\n");
        System.out.println("Hello! I 'm Jarvis.\n");
        System.out.println("What can I do for you?\n");
        System.out.println("_____________________________________\n");

        Storage storage = null;
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            storage  = new Storage(FILE_PATH);
            tasks = storage.loadTasks();
        } catch (IOException e) {
            System.exit(0);
        } catch (DukeException dukeException) {
            System.out.println("An error occurred while loading data. Starting with an empty task list.");
        }


        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine();

            try {

                if (input.equals("bye")) {
                    System.out.println("_____________________________________\n");
                    System.out.println("Bye. Hope to see you again soon!\n");
                    System.out.println("_____________________________________\n");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("_____________________________________\n");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        int tem_order = i + 1;
                        System.out.println(tem_order + "." + tasks.get(i));
                    }

                    System.out.println("_____________________________________\n");
                } else if (input.startsWith("mark")) {
                    if (input.length() <= 5) {
                        throw new DukeException("☹ OOPS!!! The description of a mark cannot be empty.");
                    }
                    String[] tem = input.split(" ");
                    int input_num = Integer.parseInt(tem[1]) - 1;
                    if (input_num >= 0 && input_num < tasks.size()) {
                        tasks.get(input_num).mark();
                        System.out.println("_____________________________________\n");
                        System.out.println("Nice! I've marked this task as done:\n");
                        System.out.println("  " + tasks.get(input_num));
                        System.out.println("_____________________________________\n");
                    } else {
                        throw new DukeException("Invalid input number. :( Please provide a valid task number.");
                    }
                } else if (input.startsWith("unmark")) {
                    if (input.length() <= 7) {
                        throw new DukeException("☹ OOPS!!! The description of a unmark cannot be empty.");
                    }
                    String[] tem = input.split(" ");
                    int input_num = Integer.parseInt(tem[1]) - 1;
                    if (input_num >= 0 && input_num < tasks.size()) {
                        tasks.get(input_num).unmark();
                        System.out.println("_____________________________________\n");
                        System.out.println("OK, I've marked this task as not done yet:\n");
                        System.out.println("  " + tasks.get(input_num));
                        System.out.println("_____________________________________\n");
                    } else {
                        throw new DukeException("Invalid input number. :( Please provide a valid task number.");
                    }
                } else if (input.startsWith("todo")) {
                    if (input.length() <= 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = input.substring(5).trim();
                    tasks.add(new Todo(description));
                    System.out.println("_____________________________________\n");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    String task_type = (tasks.size() > 1 ? " tasks" : " task");
                    System.out.println("Now you have " + tasks.size() + task_type + " in the list.\n");
                    System.out.println("_____________________________________\n");
                } else if (input.startsWith("deadline")) {
                    if (input.length() <= 9) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] tem = input.split("/by");
                    String description = tem[0].substring(9).trim();
                    String time = tem[1].trim();
                    tasks.add(new Deadline(description, time));
                    System.out.println("_____________________________________\n");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    String task_type = (tasks.size() > 1 ? " tasks" : " task");
                    System.out.println("Now you have " + tasks.size() + task_type + " in the list.\n");
                    System.out.println("_____________________________________\n");
                } else if (input.startsWith("event")) {
                    if (input.length() <= 6) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] tem_1 = input.split("/from");
                    String[] tem_2 = tem_1[1].split("/to");
                    String description = tem_1[0].substring(6).trim();
                    String start = tem_2[0].trim();
                    String end = tem_2[1].trim();
                    tasks.add(new Event(description, start, end));
                    System.out.println("_____________________________________\n");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1).toString());
                    String task_type = (tasks.size() > 1 ? " tasks" : " task");
                    System.out.println("Now you have " + tasks.size() + task_type + " in the list.\n");
                    System.out.println("_____________________________________\n");
                } else if (input.startsWith("delete")) {
                    if (input.length() <= 6) {
                        throw new DukeException("☹ OOPS!!! The description of a delete cannot be empty.");
                    }
                    int task_num = Integer.parseInt(input.substring(7)) - 1;
                    if (task_num >= 0 && task_num < tasks.size()) {
                        Task removedTask = tasks.remove(task_num);
                        System.out.println("_____________________________________\n");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("_____________________________________\n");
                    } else {
                        throw new DukeException("Invalid input number. :( Please provide a valid task number.");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                if (storage != null) {
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Error while saving tasks: " + e.getMessage());
                    }
                }

            } catch (DukeException e) {
                System.out.println("_____________________________________\n");
                System.out.println(e.getMessage() + "\n");
                System.out.println("_____________________________________\n");
            }
        }
    }


}

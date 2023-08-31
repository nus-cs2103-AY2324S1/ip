import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks;

        try {
            tasks = Utils.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't load tasks from file. Starting with an empty list.");
            tasks = new ArrayList<>();  // Start with empty list if file not found
        }

        while (true) {
            try {
                String userInput = scanner.nextLine();

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }

                if ("list".equalsIgnoreCase(userInput)) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println((i + 1) + "." + task);
                    }
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("mark")) {
                    processTask(userInput, tasks, true);
                    try {
                        Utils.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }

                } else if (userInput.startsWith("unmark")) {
                    processTask(userInput, tasks, false);
                    try {
                        Utils.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }

                } else if (userInput.startsWith("todo")) {
                    String description = userInput.substring(4).trim();

                    if (description.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);

                    try {
                        Utils.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTodo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("deadline")) {
                    String content = userInput.substring(8).trim();
                    int index = content.indexOf("/by");

                    if (index == -1) {
                        throw new DukeException("Please use '/by' to specify the deadline time.");
                    } else {
                        String description = content.substring(0, index).trim();
                        String by = content.substring(index + 4).trim();
                        Deadline newDeadline = new Deadline(description, by);
                        tasks.add(newDeadline);

                        try {
                            Utils.saveTasks(tasks); // Save the updated tasks to file
                        } catch (IOException e) {
                            System.out.println("Error saving tasks: " + e.getMessage());
                        }

                        System.out.println("____________________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newDeadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } else if (userInput.startsWith("event")) {
                    String content = userInput.substring(5).trim();

                    String[] parts = content.split("/from | /to ");

                    if (parts.length < 3) {
                        throw new DukeException("Please use the format: event [description] /from [start time] /to [end time]");
                    }

                    String description = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    Event newEvent = new Event(description, from, to);
                    tasks.add(newEvent);

                    try {
                        Utils.saveTasks(tasks); // Save the updated tasks to file
                    } catch (IOException e) {
                        System.out.println("Error saving tasks: " + e.getMessage());
                    }

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newEvent);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (userInput.startsWith("delete")) {
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                        if (taskNumber <= 0 || taskNumber > tasks.size()) {
                            throw new DukeException("Invalid task number!");
                        }

                        Task deletedTask = tasks.get(taskNumber - 1);

                        tasks.remove(taskNumber - 1);

                        try {
                            Utils.saveTasks(tasks); // Save the updated tasks to file
                        } catch (IOException e) {
                            System.out.println("Error saving tasks: " + e.getMessage());
                        }

                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println("  " + deletedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    } catch (NumberFormatException e) {
                        System.out.println("____________________________________________________________");
                        System.out.println("Please provide a valid task number.");
                        System.out.println("____________________________________________________________");
                    } catch (DukeException de) {
                        System.out.println("____________________________________________________________");
                        System.out.println(de.getMessage());
                        System.out.println("____________________________________________________________");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException de) {
                System.out.println("____________________________________________________________");
                System.out.println(de.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void processTask(String userInput, ArrayList<Task> tasks, boolean mark) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }

            Task task = tasks.get(taskNumber - 1);
            if (mark) {
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.unmarkAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.description);
            System.out.println("____________________________________________________________");
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That task doesn't exist!");
        }
    }
}


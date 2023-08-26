import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Duke {

    private static void saveTasks(ArrayList<Task> list) {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            for (Task task : list) {
                fileWriter.write(task.saveTask() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String saveFormat = scanner.nextLine();
                    tasks.add(Task.loadData(saveFormat));
                }
                scanner.close();
            }
        } catch (IOException e) {
            // Handle the exception, e.g., print an error message
        }
        return tasks;
    }
    public static void main(String[] args) {

        System.out.println("Hello! I'm May");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        //ArrayList<Task> list = new ArrayList<>();
        ArrayList<Task> list = loadTasks();

        while (true) {

            String command = scanner.nextLine();

            if (command.isEmpty()) {
                try {
                    throw new DukeException("OOPS!!! The description cannot be empty.");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            // exits program
            } else if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            // delete task
            } else if (command.toLowerCase().startsWith("delete")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                Task removedTask = list.remove(number);

                // print
                System.out.println("Noted. I've removed this task:");
                System.out.println(removedTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");

            // list tasks
            } else if (command.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i).toString());
                }

            // mark task as done
            } else if (command.toLowerCase().startsWith("mark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list.get(number).markAsDone();

                // print
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list.get(number).toString());

            // unmark task
            } else if (command.toLowerCase().startsWith("unmark")) {
                int number = Integer.parseInt(command.split(" ")[1]) - 1;
                list.get(number).markAsNotDone();

                // print
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(number).toString());

            // add todo
            } else if (command.toLowerCase().startsWith("todo")) {
                String todo = command.substring(4).trim();
                if (todo.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    Todo newTodo = new Todo(todo);
                    list.add(newTodo);

                    //print
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }

            // add deadline
            } else if (command.toLowerCase().startsWith("deadline")) {
                String deadline = command.substring(8).trim();

                if (deadline.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // separate the task and its deadline
                    String[] sub = deadline.split("/by");

                    String description = sub[0].trim();
                    String by = sub[1].trim();

                    Deadline newDeadline = new Deadline(description, by);
                    list.add(newDeadline);

                    // print
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }

            // add event
            } else if (command.toLowerCase().startsWith("event")) {
                String event = command.substring(5).trim();

                if (event.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // separate event and timing
                    String[] sub = event.split("/from");
                    String description = sub[0].trim();
                    String timing = sub[1].trim();

                    // separate start time and end time
                    String[] fromTo = timing.split("/to");
                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();

                    Event newEvent = new Event(description, from, to);
                    list.add(newEvent);

                    // print
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }

            } else {
                try {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

             saveTasks(list);
        }
    }
}

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HELLO_MESSAGE = "Hello, it's Spot!";
        final String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
        final String LIST_MESSAGE = "Spot's got a list of your tasks, here!";
        System.out.println(HELLO_MESSAGE);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                if (input.startsWith("list")) {
                    System.out.println(LIST_MESSAGE);
                    for (int i = 0; i < tasks.size(); i++) {
                        int position = i + 1;
                        System.out.println(position + ". " + tasks.get(i).toString());
                    }
                } else if (input.startsWith("mark")) {
                    int position = Integer.valueOf(input.substring(5));
                    tasks.get(position - 1).markAsDone();
                } else if (input.startsWith("unmark")) {
                    int position = Integer.valueOf(input.substring(7));
                    tasks.get(position - 1).markAsNotDone();
                } else {
                    if (input.startsWith("todo")) {
                        try {
                            String description = input.substring(4);
                            if (description.isEmpty()) {
                                throw new DukeException("Spot wonders if you've " +
                                        "forgotten the description?");
                            }
                            ToDo newTask = new ToDo(description);
                            tasks.add(newTask);
                            System.out.println("Spot will add this new task to your list: "
                                    + "\n" + "  " + newTask.toString());
                            System.out.println("Tasks in list: " + tasks.size());
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (input.startsWith("deadline")) {
                        try {
                            String[] keywords = input.substring(8).split("/by");
                            if (keywords.length < 2) {
                                throw new DukeException("Spot thinks you're missing a description," +
                                        " a deadline, or both!");
                            }
                            Deadline newTask = new Deadline(keywords[0], keywords[1]);
                            tasks.add(newTask);
                            System.out.println("Spot will add this new task to your list: "
                                    + "\n" + "  " + newTask.toString());
                            System.out.println("Tasks in list: " + tasks.size());
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (input.startsWith("event")) {
                        try {
                            String[] keywords = input.substring(5).split("/from|/to");
                            if (keywords.length < 3) {
                                throw new DukeException("Spot can't find a description," +
                                        " a start time, and/or an end time!");
                            }
                            Event newTask = new Event(keywords[0], keywords[1], keywords[2]);
                            tasks.add(newTask);
                            System.out.println("Spot will add this new task to your list: "
                                    + "\n" + "  " + newTask.toString());
                            System.out.println("Tasks in list: " + tasks.size());
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        try {
                            throw new DukeException("Spot doesn't know what that is!");
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else {
                break;
            }
        }
        System.out.println(GOODBYE_MESSAGE);
    }
}

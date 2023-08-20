import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        final String HELLO_MESSAGE = "Hello, it's Spot!";
        final String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
        final String LIST_MESSAGE = "Spot's got a list of your tasks, here!";
        final String EMPTY_MESSAGE = "You don't have any tasks for now! Want Spot to help find some?";
        System.out.println(HELLO_MESSAGE);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                if (input.startsWith("list")) {
                    if (tasks.size() == 0) {
                        System.out.println(EMPTY_MESSAGE);
                    } else {
                        System.out.println(LIST_MESSAGE);
                        for (int i = 0; i < tasks.size(); i++) {
                            int position = i + 1;
                            System.out.println(position + ". " + tasks.get(i).toString());
                        }
                    }
                } else if (input.startsWith("mark")) {
                    try {
                        int position = Integer.valueOf(input.substring(5));
                        if (position < 0 || position > tasks.size()) {
                            throw new DukeException("Spot thinks that task doesn't exist!");
                        }
                        tasks.get(position - 1).markAsDone();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("unmark")) {
                    try {
                        int position = Integer.valueOf(input.substring(7));
                        if (position < 0 || position > tasks.size()) {
                            throw new DukeException("Spot thinks that task doesn't exist!");
                        }
                        tasks.get(position - 1).markAsNotDone();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int position = Integer.valueOf(input.substring(7));
                        if (position < 0 || position > tasks.size()) {
                            throw new DukeException("Spot thinks that task doesn't exist!");
                        }
                        Task toRemove = tasks.remove(position - 1);
                        System.out.println("Spot has removed this task: " + "\n" + toRemove.toString());
                        System.out.println("Tasks in list: " + tasks.size());
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    if (input.startsWith("todo")) {
                        try {
                            if (input.length() <= 5) {
                                throw new DukeException("Spot wonders if you've " +
                                        "forgotten the description?");
                            }
                            ToDo newTask = new ToDo(input.substring(5).trim());
                            tasks.add(newTask);
                            System.out.println("Spot will add this new task to your list: "
                                    + "\n" + "  " + newTask.toString());
                            System.out.println("Tasks in list: " + tasks.size());
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (input.startsWith("deadline")) {
                        try {
                            if (input.length() <= 9) {
                                throw new DukeException("Spot needs more details than that!");
                            }
                            String[] keywords = input.substring(9).trim().split("/by");
                            if (keywords[0].isEmpty()) {
                                throw new DukeException("Spot wonders if you've " +
                                        "forgotten the description?");
                            }
                            if (keywords.length < 2) {
                                throw new DukeException("Spot thinks you're missing a deadline!");
                            }
                            Deadline newTask = new Deadline(keywords[0], keywords[1]);
                            System.out.println(keywords[0]);
                            tasks.add(newTask);
                            System.out.println("Spot will add this new task to your list: "
                                    + "\n" + "  " + newTask.toString());
                            System.out.println("Tasks in list: " + tasks.size());
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (input.startsWith("event")) {
                        try {
                            if (input.length() <= 6) {
                                throw new DukeException("Spot needs more details than that!");
                            }
                            String[] keywords = input.substring(6).trim().split("/from|/to");
                            if (keywords[0].isEmpty()) {
                                throw new DukeException("Spot wonders if you've " +
                                        "forgotten the description?");
                            }
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

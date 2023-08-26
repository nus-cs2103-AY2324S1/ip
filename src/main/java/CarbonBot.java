import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarbonBot {
    private static final String saveFilePath = "./data/tasks.txt";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList;

        try {
            taskList = deserializeTasks();
        } catch (DukeException ex) {
            System.out.println("Something is wrong with your save file :/");
            return;
        }

        // Greeting Message
        printDivider();
        System.out.println("Hello! I'm CarbonBot");
        System.out.println("What can I do for you?");
        printDivider();

        // Keep fetching for user's input
        while(true) {
            String input = sc.nextLine();

            // Ignore if the input was empty
            if (input.isBlank()) {
                continue;
            }

            // Fetches the first word as the command string
            String cmd = input.split(" ")[0];


            int taskIdx;
            String desc;

            printDivider();
            try {
                switch (cmd) {
                    case "bye":
                        // bye: Exits the program after bidding farewell!
                        System.out.println("Bye. Hope to see you again soon!");
                        printDivider();
                        sc.close();
                        return;
                    case "list":
                        // list: Prints out the list of tasks (and their status)
                        System.out.println("Here are the tasks in your list:");
                        printList(taskList);
                        break;
                    case "todo":
                        // todo: Adds a TODO Task to the list

                        // Get the description from all the characters after "todo"
                        desc = input.substring("todo".length()).trim();

                        // Validates if the description is empty (or only whitespaces)
                        if (desc.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            addTask(taskList, new Todo(desc));
                        }
                        break;
                    case "deadline":
                        // deadline: Adds a deadline Task to the list

                        int indexOfBy = input.indexOf("/by");
                        // Validates the existence of /by syntax
                        if (indexOfBy == -1) {
                            throw new DukeException("☹ OOPS!!! Please specify the deadline using /by.");
                        }

                        desc = input.substring("deadline".length(), indexOfBy).trim();
                        String by = input.substring(indexOfBy + "/by".length()).trim();
                        if (desc.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        if (by.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The 'by' of a deadline cannot be empty.");
                        }

                        try {
                            LocalDateTime byDt = parseDateTimeString(by);
                            addTask(taskList, new Deadline(desc, byDt));
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("☹ OOPS!!! The 'by' datetime was not in a valid format.");
                        }
                        break;
                    case "event":
                        // event: Adds a event Task to the list

                        int indexOfFrom = input.indexOf("/from");
                        int indexOfTo = input.indexOf("/to");
                        if (indexOfFrom == -1 || indexOfTo == -1) {
                            throw new DukeException("☹ OOPS!!! Please specify the start and end of the" +
                                    " event using /from and /to.");
                        }
                        if (indexOfFrom > indexOfTo) {
                            throw new DukeException("☹ OOPS!!! Please specify the /from before the /to!");
                        }

                        desc = input.substring("event ".length(), indexOfFrom).trim();
                        String from = input.substring(indexOfFrom + "/from".length(), indexOfTo).trim();
                        String to = input.substring(indexOfTo + "/to".length()).trim();

                        if (desc.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        if (from.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The 'from' of an event cannot be empty.");
                        }
                        if (to.isBlank()) {
                            throw new DukeException("☹ OOPS!!! The 'to' of an event cannot be empty.");
                        }

                        try {
                            LocalDateTime fromDt = parseDateTimeString(from);
                            LocalDateTime toDt = parseDateTimeString(to);
                            addTask(taskList, new Event(desc, fromDt, toDt));
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("☹ OOPS!!! The given datetime was not in a valid format.");
                        }
                        break;
                    case "delete":
                        deleteTask(taskList, input);
                        break;
                    case "mark":
                        updateTaskStatus(taskList, input, true);
                        break;
                    case "unmark":
                        updateTaskStatus(taskList, input, false);
                        break;
                    default:
                        // Unrecognised Command
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                                "\nMy supported commands are: list, mark, unmark, todo, deadline, event, bye.");
                }
            } catch (DukeException ex) {
                // Prints the error message if a DukeException is encountered
                System.out.println(ex.getMessage());
            }
            printDivider();
        }
    }


    private static void printDivider() {
        String DIVIDER = "____________________________________________________________";
        System.out.println(DIVIDER);
    }

    private static LocalDateTime parseDateTimeString(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private static void deleteTask(List<Task> tasks, String input) throws DukeException {
        // Validates if the user has specified the index to be updated
        if (input.split(" ").length < 2) {
            throw new DukeException("No index was provided. Please enter the task index to be updated.");
        }

        // -1, due to 0-indexing of the List
        try {
            int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIdx < 0 || taskIdx >= tasks.size()) {
                throw new DukeException("Index provided was out-of-bounds. Use the index number" +
                        " labelled for the task in the command 'list'!");
            }
            Task task = tasks.get(taskIdx);
            tasks.remove(task);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.println(getListSize(tasks));
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please provide a valid integer for the index.");
        }
        saveTasks(tasks);
    }

    // Adds a todo task to the list
    private static void addTask(List<Task> tasks, Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(getListSize(tasks));
        saveTasks(tasks);
    }


    private static void saveTasks(List<Task> tasks) {
        try {
            File file = new File(saveFilePath);
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println("OH NO..., " + e.getMessage());
                }
            }

            FileWriter fw = new FileWriter(saveFilePath);
            fw.write(serializeTasks(tasks));
            fw.close();
        } catch (IOException e) {
            System.out.println("OH NO..., " + e.getMessage());
        }
    }

    private static String serializeTasks(List<Task> tasks) {
        String s = "";      //TODO: Replace with StringBuilder
        for (Task task : tasks) {
            s += task.serialize() + "\n";
        }
        return s;
    }

    private static List<Task> deserializeTasks() throws DukeException {
        List<Task> taskList = new ArrayList<Task>();
        File f = new File(saveFilePath); // create a File for the given file path
        Scanner s = null; // create a Scanner using the File as the source
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            // No existing save-file
            return taskList;
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] cols = line.split(" \\| ");
            try {
                Task task;
                switch (cols[0]) {
                    case "T":
                        task = new Todo(cols[2]);
                        break;
                    case "D":
                        try {
                            LocalDateTime byDt = parseDateTimeString(cols[3]);
                            task = new Deadline(cols[2], byDt);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("☹ OOPS!!! The 'by' datetime was not in a valid format.");
                        }
                        break;
                    case "E":
                        try {
                            LocalDateTime fromDt = parseDateTimeString(cols[3]);
                            LocalDateTime toDt = parseDateTimeString(cols[4]);
                            task = new Event(cols[2], fromDt, toDt);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("☹ OOPS!!! The 'by' datetime was not in a valid format.");
                        }
                        break;
                    default:
                        throw new DukeException("Invalid File Format");
                }

                if (cols[1].equals("1")) {
                    task.markAsDone();
                }
                taskList.add(task);
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new DukeException("Invalid File Format");
            }
        }

        return taskList;
    }

    // Get number of tasks in the list
    private static String getListSize(List<Task> tasks) {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }

    // Lists all the commands saved in the arraylist
    private static void printList(List<Task> tasks) {
        int idx = 1;
        for(Task t : tasks) {
            System.out.println(String.format("%d.%s", idx, t));
            idx++;
        }
    }

    // Marks the Task as Done or Not Done
    private static void updateTaskStatus(List<Task> tasks, String input, boolean isDone) throws DukeException {
        // Validates if the user has specified the index to be updated
        if (input.split(" ").length < 2) {
            throw new DukeException("No index was provided. Please enter the task index to be updated.");
        }

        try {
            int taskIdx = Integer.parseInt(input.split(" ")[1]);
            if (taskIdx > 0 && taskIdx <= tasks.size()) {
                // The -1 is because of the list 0-indexing
                Task t = tasks.get(taskIdx - 1);

                if (isDone) {
                    // Mark task as done
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    // Mark task as not done
                    t.markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println(t);
            } else {
                // Do not process the command if the index was out of bounds
                throw new DukeException("Index provided was out-of-bounds. Use the index number" +
                        " labelled for the task in the command 'list'!");
            }
        } catch (NumberFormatException ex) {
            // Ensure the index is integer
            throw new DukeException("Please provide a valid integer for the index.");
        }
        saveTasks(tasks);
    }

}

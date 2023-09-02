import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents Duke, a Personal Assistant Chatbot that helps a person to keep track of
 * various things. The name Duke was chosen as a placeholder name, in honor of Duke,
 * the Java Mascot. The current name of the Chatbot is John.
 */
public class Duke {
    /**
     * Our main method for the Chatbot to work.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // substring: begIndex (inclusive) up to the endIndex (exclusive)
        createFile();
        ArrayList<Task> taskList = retrieveData();
        int itemsAdded = taskList.size();

        System.out.println("Hello friend :> My name is John, nice to meet you! " +
                "What do you have to do today?");

        Scanner sc = new Scanner(System.in); // do not put this in the while loop

        while (true) {
            System.out.print("Reply John: ");
            String command = sc.nextLine(); // using .next() is wrong - only reads first word

            if (command.equals("bye")) {
                System.out.println("Bye for now, hope to see you soon.");
                save(taskList);
                break;
            } else {
                try {
                    if (command.equals("list")) {
                        printList(taskList, itemsAdded);
                    } else if (command.startsWith("mark ")) { // space behind is needed!, number index = 5
                        printMark(command, taskList, itemsAdded);
                    } else if (command.startsWith("unmark ")) { // number index = 7
                        printUnmark(command, taskList, itemsAdded);
                    } else if (command.startsWith("todo ")) { // description starting index = 5
                        addToDo(command, taskList, itemsAdded);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("deadline ")) { // description starting index = 9
                        addDeadline(command, taskList, itemsAdded);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("event ")) { // description starting index = 6
                        addEvent(command, taskList, itemsAdded);
                        itemsAdded++; // increment number of items
                    } else if (command.startsWith("delete ")) { // number index = 7
                        deleteTask(command, taskList, itemsAdded);
                        itemsAdded--; //decrement number of items
                    } else {
                        if (command.startsWith("todo")) {
                            throw new InvalidDescriptionException("todo");
                        } else if (command.startsWith("deadline")) {
                            throw new InvalidDescriptionException("deadline");
                        } else if (command.startsWith("event")) {
                            throw new InvalidDescriptionException("event");
                        } else {
                            throw new InvalidCommandException();
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    public static void createFile() {
        try {
            // Create if it doesn't exist
            Files.createDirectories(Paths.get("./data/"));
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating data folder or file.");
        }
    }

    public static ArrayList<Task> retrieveData() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File file = new File("./data/duke.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String dataLine = scanner.nextLine();
                Task task = dataToTask(dataLine);
                taskList.add(task);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found. Creating a new one.");
        }

        return taskList;
    }

    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter("./data/duke.txt");

            for (Task task : taskList) {
                // Write each task with specified format
                writer.write(taskData(task) + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public static String taskData(Task task) {
        String taskType = task instanceof ToDo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0";
        String description = task.description;

        StringBuilder data = new StringBuilder();
        data.append(taskType).append(" | ").append(status).append(" | ").append(description);

        // Add additional information for Deadline and Event tasks
        if (task instanceof Deadline) {
            LocalDateTime deadlineDateTime = ((Deadline) task).by;
            String formattedDateTime = deadlineDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            data.append(" | ").append(formattedDateTime);
        } else if (task instanceof Event) {
            data.append(" | ").append(((Event) task).from).append(" | ").append(((Event) task).to);
        }

        return data.toString();

    }

    public static Task dataToTask(String data) {
        String[] info = data.split(" \\| ");
        String taskType = info[0];
        String status = info[1];
        String description = info[2];

        Task task;
        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                task = new Deadline(description, info[3]);
                break;
            case "E":
                task = new Event(description, info[3], info[4]);
                break;
            default:
                throw new IllegalArgumentException("☹ OOPS!!! Invalid task in data");
        }

        if (status.equals("1")) {
            task.markAsDone();
        }

        return task;
    }

    public static void printList(ArrayList<Task> taskList, int itemsAdded) {
        System.out.println("Here are the tasks in your list:");

        for (int i = 1; i <= itemsAdded; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
            // adding toString() to use the overridden one in Task, etc.
        }
    }

    public static void printMark(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        int taskPos = Integer.parseInt(command.substring(5)) - 1;
        // convert substring to int, -1 for index

        // only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < itemsAdded) {
            taskList.get(taskPos).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + "[X] "
                    + taskList.get(taskPos).description);
        } else {
            throw new InvalidNumberException();
        }
    }

    public static void printUnmark(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        // convert substring to int, -1 for index


        // only numbers >= 0 and < total number are valid
        if (taskPos >= 0 && taskPos < itemsAdded) {
            taskList.get(taskPos).unmark();
            System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] "
                    + taskList.get(taskPos).description);
        } else {
            throw new InvalidNumberException();
        }
    }

    public static void addToDo(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        String description = command.substring(5);

        if (description.isEmpty()) {
            throw new InvalidDescriptionException("todo");
        }

        taskList.add(new ToDo(description)); // add new command

        System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

    }

    public static void addDeadline(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        // indexOf: searches for the substring and returns the index of the first character
        if (command.contains(" /by ")) {
            String description = command.substring(9, command.indexOf(" /by "));
            String by = command.substring(command.indexOf(" /by ") + 5);
            // from " " to the specified date is 5

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("deadline");
            }

            if (by.isEmpty()) {
                throw new InvalidDeadlineException();
            }

            taskList.add(new Deadline(description, by)); // add new command

            System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                    + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");
        } else {
            throw new InvalidDeadlineException();
        }
    }

    public static void addEvent(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        if (command.contains(" /from ") && command.contains(" /to ")) {
            String description = command.substring(6, command.indexOf(" /from "));
            // from " " to 'from' date is 7
            String from = command.substring(command.indexOf(" /from ") + 7, command.indexOf(" /to "));
            // from " " to 'to' date is 5
            String to = command.substring(command.indexOf(" /to ") + 5);

            if (description.isEmpty()) {
                throw new InvalidDescriptionException("event");
            }

            if (from.isEmpty() || to.isEmpty()) {
                throw new InvalidEventException();
            }

            taskList.add(new Event(description, from, to)); // add new command

            System.out.println("Got it. I've added this task:\n" + "  " + taskList.get(itemsAdded)
                    + "\nNow you have " + (itemsAdded + 1) + " tasks in the list.");

        } else {
            throw new InvalidEventException();
        }
    }

    public static void deleteTask(String command, ArrayList<Task> taskList, int itemsAdded) throws Exception {
        int taskPos = Integer.parseInt(command.substring(7)) - 1;
        // convert substring to int, -1 for index
        if (taskPos >= 0 && taskPos < itemsAdded) {
            Task deleted = taskList.remove(taskPos);

            System.out.println("Noted. I've removed this task:\n" + "  " + deleted
                    + "\nNow you have " + (itemsAdded - 1) + " tasks in the list.");

        } else {
            throw new InvalidNumberException();
        }

    }

}
package javai;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;



/**
 * The Storage class handles loading tasks from and writing tasks to a file.
 */
public class Storage {
    private String filePath;

    private Ui ui = new Ui();

    /**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified by the file path.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {

        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } else {

            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String load = sc.nextLine();
                    String taskType = load.substring(1, 2);
                    String completionType = load.substring(4, 5);
                    String description = "";
                    String[] words = load.substring(7).split(" ");
                    int iterator = 0;
                    if (taskType.equals("T")) {
                        addTodo(iterator, words, tasks, completionType, description);
                    } else if (taskType.equals("D")) {
                        addDeadline(iterator, words, tasks, completionType, description);
                    } else if (taskType.equals("E")) {
                        addEvent(iterator, words, tasks, completionType, description);
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        }
        return tasks;
    }

    /**
     * Writes a list of tasks to a file specified by a file path.
     *
     * @param tasks The list of Task objects to be written to the file.
     */
    public static void taskListWriter(ArrayList<Task> tasks) {

        File file = new File("./src/main/txtFolder/JavAI.txt");

        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Adds a todo task to the list of tasks.
     * @param iterator The iterator to iterate through the words array.
     * @param words The array of words in the user input.
     * @param tasks The list of tasks.
     * @param completionType The completion status of the task.
     * @param description The description of the task.
     */
    public void addTodo(int iterator, String[] words, ArrayList<Task> tasks, String completionType,
                        String description) {
        while (iterator < words.length) {
            description += words[iterator] + " ";
            iterator++;
        }
        assert !description.isEmpty() : "Description should not be empty";
        Todo todo = new Todo(description);
        if (completionType.equals("X")) {
            todo.markAsDone();
        }
        tasks.add(todo);
    }

    /**
     * Adds a deadline task to the list of tasks.
     * @param iterator The iterator to iterate through the words array.
     * @param words The array of words in the user input.
     * @param tasks The list of tasks.
     * @param completionType The completion status of the task.
     * @param description The description of the task.
     */
    public void addDeadline(int iterator, String[] words, ArrayList<Task> tasks, String completionType,
                            String description) {
        String endDate = "";
        String endTime = "";
        while (!words[iterator].equals("by:")) {
            if (!words[iterator].equals("(")) {
                description += words[iterator] + " ";
                iterator++;
            } else {
                iterator++;
            }
        }
        iterator++;
        for (int i = 0; i < 3; i++) {
            endDate += words[iterator] + " ";
            iterator++;
        }
        endTime = words[iterator];
        String endTimeWithoutColon = endTime.replace(":", "");
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy ");
        LocalDate date = LocalDate.parse(endDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(outputFormatter);
        assert !description.isEmpty() : "Description should not be empty";
        Deadline deadline = new Deadline(description.trim(), formattedDate, endTimeWithoutColon);
        if (completionType.equals("X")) {
            deadline.markAsDone();
        }
        tasks.add(deadline);
    }

    /**
     * Adds an event task to the list of tasks.
     * @param iterator The iterator to iterate through the words array.
     * @param words The array of words in the user input.
     * @param tasks The list of tasks.
     * @param completionType The completion status of the task.
     * @param description The description of the task.
     */
    public void addEvent(int iterator, String[] words, ArrayList<Task> tasks, String completionType,
                         String description) {
        String from = "";
        String to = "";
        while (!words[iterator].equals("from:")) {
            if (!words[iterator].equals("(")) {
                description += words[iterator] + " ";
                iterator++;
            } else {
                iterator++;
            }
        }
        iterator++;
        while (!words[iterator].equals("to:")) {
            from += words[iterator] + " ";
            iterator++;
        }
        iterator++;
        while (iterator < words.length) {
            if (words[iterator].equals(")")) {
                iterator++;
            } else {
                to += words[iterator] + " ";
                iterator++;
            }
        }
        assert !description.isEmpty() : "Description should not be empty";
        Event event = new Event(description.trim(), from, to);
        if (completionType.equals("X")) {
            event.markAsDone();
        }
        tasks.add(event);
    }


}

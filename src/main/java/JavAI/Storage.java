package javai;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Storage class handles loading tasks from and writing tasks to a file.
 */
public class Storage {
    String filePath;

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
                        while (iterator < words.length) {
                            description += words[iterator] + " ";
                            iterator++;
                        }
                        Todo todo = new Todo(description);
                        if (completionType.equals("X")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else if (taskType.equals("D")) {
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
                        Deadline deadline = new Deadline(description.trim(), formattedDate, endTimeWithoutColon);
                        if (completionType.equals("X")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else if (taskType.equals("E")) {
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
                        Event event = new Event(description.trim(), from, to);
                        if (completionType.equals("X")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
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

}

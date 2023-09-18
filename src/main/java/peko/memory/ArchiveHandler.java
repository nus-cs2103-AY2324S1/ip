package peko.memory;

import peko.exceptions.InvalidTaskException;
import peko.tasks.Deadline;
import peko.tasks.Event;
import peko.tasks.Task;
import peko.tasks.ToDos;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * The ArchiveHandler class is responsible for managing the archival of tasks and retrieval of archived tasks.
 * It provides methods to archive tasks and load archived tasks from a file.
 * Archived tasks are stored in a text file in a specific format.
 * This class supports the storage and retrieval of various task types, such as Todos, Deadlines, and Events.
 *
 * Usage:
 * - To archive a task, call the 'archive' method, providing the task to be archived.
 * - To load archived tasks, use the 'fileManager' method to read and parse the archived task data.
 * - This class ensures that each task is archived only once.
 */
public class ArchiveHandler {

    private static File file = new File("src/main/Archive.txt");
    private static PrintWriter printWriter;
    private static ArrayList<Task> list = new ArrayList<>();
    private static HashMap<String, Boolean> map = new HashMap<>();
    /**
     * Archives a task by storing it in the archive file.
     *
     * This method takes a Task object, stores it in the archive file, and marks it as archived to prevent duplicate archiving.
     * It performs the following steps:
     * 1. Calls the 'fileManager' method to initialize the archive file and load existing archived tasks.
     * 2. Prints the string representation of the task to the console.
     * 3. Checks if the task has already been archived using its string representation as a key in the 'map' data structure.
     * 4. If the task is not already archived, it converts the task to a string representation using 'toStore' method.
     * 5. Appends the string representation to the archive file with a newline character.
     *
     * @param task The Task object to be archived.
     * @throws RuntimeException If there is an IOException while writing to the archive file.
     */
    public static void archive(Task task) {
        fileManager();
        System.out.println(task.toString());
        if (!map.getOrDefault(task.toString(), false)) {

            String toStore = task.toStore() + "\n";
            try {
                Writer temp;
                temp = new BufferedWriter(new FileWriter("src/main/Archive.txt", true));
                temp.append(toStore);
                temp.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Manages the archive file by initializing it and loading existing archived tasks.
     *
     * This method performs the following tasks:
     * 1. Checks if the archive file exists. If not, it creates a new file named "List.txt" in the same directory.
     * 2. If the archive file exists, it reads its contents and parses them to recover archived tasks.
     * 3. It uses a Scanner to read lines from the archive file, splitting each line into task data.
     * 4. Converts the task data back to Task objects using the 'stringToTask' method.
     * 5. If a valid Task is recovered, it adds it to the 'list' of archived tasks and marks it as archived in the 'map'.
     *
     * @throws RuntimeException If there is an IOException while reading the archive file or if the archive file does not exist.
     */
    private static void fileManager() {
        if (!file.exists()) {

            File temp = new File(file.getParentFile(), "List.txt");
            file = temp;
        } else {
            try {
                Scanner scanner = new Scanner(file.getAbsoluteFile());
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    String[] arr = s.split(" \\| ");
                    Task t = stringToTask(arr);
                    if (t != null && !map.getOrDefault(t.toString(), false)) {
                        System.out.println(t);
                        map.put(t.toString(), true);
                        list.add(t);
                    }

                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Converts an array of task data strings into a Task object.
     *
     * This method takes an array of strings containing task data and converts it into a Task object, which can be of type ToDos, Deadline, or Event.
     * The task data is expected to be in a specific format, and this method handles the conversion.
     *
     * @param arr An array of strings containing task data.
     * @return A Task object representing the parsed task data, or null if the data is incomplete or invalid.
     * @throws InvalidTaskException If the task data represents an invalid task type.
     * @throws IndexOutOfBoundsException If the task data array does not contain enough elements.
     */
    private static Task stringToTask(String[] arr) {
        Task t;
        try {

            switch (arr[0]) {
                case "T":
                    t = new ToDos( arr[2]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "D":
                    t = new Deadline(arr[2] + " /by " + arr[3]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                case "E":
                    t = new Event(arr[2] + " /from " + arr[3] + " /to " + arr[4]);
                    if (arr[1] == "1") {
                        t.setMark();
                    }
                    return t;
                default:
                    throw new InvalidTaskException();
            }
        } catch (InvalidTaskException e) {
            System.out.println(e);
            System.out.println("There's an error in the list, Pain peko, I'll delete it");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Incomplete task an error in the list, Pain peko, I'll delete it");
        }
        return  null;
    }

    public static String readArchive() {
        fileManager();
        String out = "";
        for(Task t : list) {
            out += t.toString() + "\n";
        }

        return out == "" ? "Nani mo nai Peko" : out;
    }

}

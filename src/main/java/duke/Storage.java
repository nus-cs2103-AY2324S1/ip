package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file in the user's hard disk and saving tasks in the file.
 */
public class Storage {
    private static File taskList;
    private static String filepath;

    /**
     * Constructs new <code>Storage</code> object.
     * @param filepath the filepath to the file that stores existing tasks.
     *                 New file created if the file specified does not exist at the start.
     */
    public Storage(String filepath) {
        File tasks = new File(filepath);
        if (!tasks.exists()) {
            try {
                tasks.createNewFile();
            } catch (IOException e) {
                System.out.println("File cannot be created!");
            }
        }
        this.taskList = tasks;
        this.filepath = filepath;
    }

    /**
     * Loads data from the hard disk when the chatbot starts up.
     * Saves any pre-existing tasks in the file to the task list.
     * @throws DukeException when data from the file cannot be converted to a <code>Task</code> object.
     */
    public static void load() throws DukeException {
        try {
            Scanner s = new Scanner(taskList);
            String savedStrings;
            while (s.hasNextLine()) {
                savedStrings = s.nextLine();
                String[] arr = savedStrings.split("\\|");
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i].trim();
                }
                String taskType = arr[0];
                switch (taskType) {
                case "T":
                    Todo t = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        t.mark();
                    }
                    TaskList.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(arr[2], arr[3]);
                    if (arr[1].equals("1")) {
                        d.mark();
                    }
                    TaskList.add(d);
                    break;
                case"E":
                    String startEnd = arr[3];
                    String[] duration = startEnd.split("-");
                    String start = duration[0];
                    String end = duration[1];
                    Event e = new Event(arr[2], start, end);
                    if (arr[1].equals("1")) {
                        e.mark();
                    }
                    TaskList.add(e);
                    break;
                default:
                    System.out.println("Wrong input recognised :((");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the tasks in the hard disk automatically whenever the task list changes.
     */
    public static void save() {
        try {
            FileWriter writer = new FileWriter(filepath);
            for (Task t : TaskList.toDo) {
                writer.write(t.getSavedString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot save to file :(");
            e.printStackTrace();
        }
    }
}

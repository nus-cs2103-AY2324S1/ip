package rua.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

import rua.task.Deadline;
import rua.task.Event;
import rua.task.Task;
import rua.task.Todo;
import rua.task.TaskList;
import rua.exception.InvalidTypeException;

public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object which reads and writes to a file located at the filePath.
     *
     * @param filePath The path of the file to be loaded and saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a Task corresponding to a given String.
     *
     * @param str A String that represents a Task object.
     * @return The corresponding Task object.
     * @throws InvalidTypeException if the task type is not supported.
     */
    static Task stringToTask(String str) throws InvalidTypeException{
        String[] features = str.split(" \\| ");
        Task output;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM dd yyyy");
        switch (features[0]) {
        case "T":
            output = new Todo(features[2], features[1].equals("1"));
            break;
        case "D":
            output = new Deadline(features[2], LocalDate.parse(features[3], dateFormat),
                    features[1].equals("1"));
            break;
        case "E":
            output = new Event(features[2], LocalDate.parse(features[3], dateFormat),
                    LocalDate.parse(features[4], dateFormat), features[1].equals("1"));
            break;
        default:
            output = null;
            throw new InvalidTypeException(features[0]);
        }
        return output;
    }

    /**
     * Returns a Task corresponding to a given String.
     *
     * @param task A Task object that needs to be translated into a String.
     * @return The corresponding String.
     * @throws InvalidTypeException if the task type is not supported.
     */
    static String taskToString(Task task) throws InvalidTypeException {
        String output = task.getType() + " | " + (task.isMarked() ? 1 : 0)
                + " | " + task.getDescription();
        switch (task.getType()) {
        case "T":
            break;
        case "D":
            output += " | " + ((Deadline) task).getDue();
            break;
        case "E":
            output += " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
            break;
        default:
            output = null;
            throw new InvalidTypeException(task.getType());
        }
        return output;
    }

    /**
     * Returns the ArrayList of tasks represented by the input String located in the filePath.
     *
     * @return The corresponding ArrayList of Task objects.
     * @throws InvalidTypeException if the task type is not supported.
     * @throws FileNotFoundException if the filePath is invalid.
     */
    public ArrayList<Task> load() throws InvalidTypeException, FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(stringToTask(sc.nextLine()));
        }
        return tasks;
    }

    /**
     * Saves the string representing a list of tasks at the filePath.
     *
     * @param tasks A TaskList object that needs to be translated into String and saved.
     * @throws InvalidTypeException if the task type is not supported.
     * @throws IOException if the filePath is invalid.
     */
    public void save(TaskList tasks) throws InvalidTypeException, IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String saveTxt = "";
            ArrayList<Task> tasksList = tasks.getTasks();
            for (int i = 0; i < tasksList.size(); i++) {
                saveTxt += taskToString(tasksList.get(i)) + "\n";
            }
            fw.write(saveTxt);
            fw.close();
        } catch (FileNotFoundException exp) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Target file not found. Do you want to create it now? Please type yes or no\n");
            String create = sc.nextLine();
            while (!create.equals("yes") && !create.equals("no")) {
                System.out.println("Incorrect input. Please type yes or no\n");
            }
            if (create.equals("yes")) {
                int index = filePath.lastIndexOf("/");
                String saveDir = filePath.substring(0, index);
                File fileCreator = new File(saveDir);
                fileCreator.mkdir();
                System.out.println("File successfully created. Progress saved.\n");
                try {
                    save(tasks);
                } catch (IOException ioExp) {
                    System.out.println("Some error occurs and progress is not saved.\n");
                }
            }
        }
    }
}

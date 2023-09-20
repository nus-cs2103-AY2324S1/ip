package ruiz.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ruiz.task.Deadline;
import ruiz.task.Event;
import ruiz.task.Task;
import ruiz.task.ToDo;

/**
 * Class manages all things that due with the storing of tasks on the hard disk.
 */
public class Storage {
    private Parser parser;
    private String filePath;
    private File textFile;

    /**
     * A constructor for the Storage class.
     * @param filePath provides the path of the stored file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.textFile = new File(filePath);
        this.parser = new Parser();
    }

    /**
     * Saves the tasks that the user has entered into the chatbot.
     * @param tasks The list of tasks that is entered in to the chatbot.
     * @throws IOException If the filePath is not valid.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.formatSaveTaskString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads the tasks from the pre-existing tasks file on the hard disk.
     * @return The list of tasks that were stored in the pre-existing tasks file on the hard disk.
     * @throws FileNotFoundException If the file at the given filepath does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        Scanner s = new Scanner(this.textFile);
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNext()) {
            String taskContent = s.nextLine();
            String[] input = parser.splitInputFromFile(taskContent);
            String category = input[0];
            String marked = input[1];
            String description = input[2];
            String location = input[input.length - 1];
            switch (category) {
            case "T":
                ToDo todo = new ToDo(description, location);
                taskList.add(todo);
                if (!marked.equals("0")) {
                    todo.mark();
                }
                break;
            case "D":
                String by = input[3];
                Deadline deadline = new Deadline(description, by, location);
                taskList.add(deadline);
                if (!marked.equals("0")) {
                    deadline.mark();
                }
                break;
            case "E":
                String from = input[3];
                String to = input[4];
                Event event = new Event(description, from, to, location);
                taskList.add(event);
                if (!marked.equals("0")) {
                    event.mark();
                }
                break;
            default:
            }
        }
        return taskList;
    }

}

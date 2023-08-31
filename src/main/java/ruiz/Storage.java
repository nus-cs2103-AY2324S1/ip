package ruiz;

import ruiz.task.Deadlines;
import ruiz.task.Events;
import ruiz.task.Task;
import ruiz.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class manages all things that due with the storing of tasks on the hard disk.
 */
public class Storage {
    Parser parser;
    String filePath;
    File textFile;

    /**
     * A constructor for the Storage class.
     * @param filePath provides the path of the stored file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.textFile = new File(filePath);
        parser = new Parser();
    }

    /**
     * Saves the tasks that the user has entered into the chatbot.
     * @param tasks The list of tasks that is entered in to the chatbot.
     * @throws IOException If the filePath is not valid.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            fw.write(task.saveTaskString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Loads the tasks from the pre-existing tasks file on the hard disk.
     * @return The list of tasks that were stored in the pre-existing tasks file on the hard disk.
     * @throws FileNotFoundException If the file at the given filepath does not exist.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNext()) {
            String taskContent = s.nextLine();
            String[] input = parser.splitInputFromFile(taskContent);
            String category = input[0];
            String marked = input[1];
            String description = input[2];
            switch (category) {
            case "T":
                ToDos todo = new ToDos(description);
                taskList.add(todo);
                if (!marked.equals("0")) {
                    todo.mark();
                }
                break;
            case "D":
                String by = input[3];
                Deadlines deadline = new Deadlines(description, by);
                taskList.add(deadline);
                if (!marked.equals("0")) {
                    deadline.mark();
                }
                break;
            case "E":
                String from = input[3];
                String to = input[4];
                Events event = new Events(description, from, to);
                taskList.add(event);
                if (!marked.equals("0")) {
                    event.mark();
                }
                break;
            }
        }
        return taskList;
    }

}

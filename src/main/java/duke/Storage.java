package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

//deals with loading tasks from the file and saving tasks in the file
public class Storage {
    private String filepath;
    private TaskList list;
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    //load tasks from the file into the chatbot
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> dukeList = new ArrayList<>();
        File file = new File(filepath);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String task = fileScanner.nextLine();
                    Task dtask = Taskparser.parseTask(task); //convert format of tasks in file to list
                    dukeList.add(dtask);
                }
            }
        }
        return dukeList;
    }

    // Save tasks to a data file
    public void save(TaskList list) {
        try (PrintWriter out = new PrintWriter(filepath)) {
            for (int i = 0; i < list.count; i++) {
                String taskString = Taskparser.taskToString(list.getTask(i)); //convert task to string
                out.println(taskString);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}

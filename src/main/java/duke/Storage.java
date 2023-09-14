package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

/**
 * A class that contains the save and load methods.
 */
public class Storage {
    /**
     * Loads the content of the file into the ChatBot.
     *
     * @param filepath directs to the file containing the saved content
     * @return a TaskList that contains the tasks from the content
     */
    public static TaskList load(String filepath) {
        TaskList taskList = new TaskList();
        File file = new File(filepath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text.equals("")) {
                    continue;
                }
                Parser.handleInput(text, taskList, true);
            }
        } catch (FileNotFoundException ex) {
            //ignore;
        }
        return taskList;
    }

    /**
     * Saves the task-list into the file whenever a change is made.
     *
     * @param taskList an array of tasks
     */

    public static void save (TaskList taskList) {
        String filepath = "data/duke.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("");
            if (taskList.size() != 0) {
                for (int i = 0; i < taskList.size(); i++) {
                    taskList.getTask(i).save(filepath);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
}

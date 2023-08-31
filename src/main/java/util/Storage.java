package util;

import task.Task;
import task.TaskList;

import java.io.File;
import java.io.PrintWriter;

public class Storage {

    public String filePath = new File("").getAbsolutePath() + "/data/EpochMind.txt";


    /**
     * Saves the task list to a txt file at the path
     *
     * @param taskList The task list to write to file
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdir();
        }
        try (PrintWriter pw = new PrintWriter(file);) {
            for (Task task : taskList.getTasks()) {
                pw.println(task.toString());
            }
        } catch (Exception e) {
            System.out.println("The Mind has failed to save the tasks");
        }
    }

    /**
     * Saves the task list to a txt file at the path
     *
     * @param taskList The task list to write to file
     */
    public void save(TaskList taskList, String filePath) {
        File file = new File(filePath);
        try (PrintWriter pw = new PrintWriter(file);) {
            File parentDirectory = file.getParentFile();
            if (!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }
            for (Task task : taskList.getTasks()) {
                pw.println(task.toString());
            }
        } catch (Exception e) {
            System.out.println("The Mind has failed to save the tasks. Check thy path.");
        }
    }
}

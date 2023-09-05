package bareum;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class implements the thing that loads and saves tasks to the hard disk.
 */
public class Storage {
    /**
     * File path of the file to load tasks from and save tasks to.
     */
    private String filePath;
    /**
     * The file to load tasks from and save tasks to.
     */
    private File storedTasks;

    /**
     * Creates an instance of the Storage class for loading and saving tasks.
     * @param filePath File path of the file to load tasks from and save tasks to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks saved on the hard disk using the file path.
     * @param taskList Task list to load the tasks to.
     */
    public void loadSavedTaskList(TaskList taskList) {
        File storedTasks = new File(filePath);
        if (!storedTasks.exists()) {
            try {
                storedTasks.getParentFile().mkdirs();
                storedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.storedTasks = storedTasks;

        StringBuilder savedTasks = new StringBuilder();
        int currChar;

        try {
            FileReader fr = new FileReader(storedTasks);
            while ((currChar = fr.read()) != -1) {
                savedTasks.append((char) currChar);
            }

            String[] allTaskInputs = savedTasks.toString(). split("\n");
            for (int i = 0; i < allTaskInputs.length; i++) {
                String[] taskInputs = allTaskInputs[i].split("\\|");
                if (taskInputs[0].equals("T")) {
                    taskList.addTask(TodoTask.makeTodo(taskInputs));
                } else if (taskInputs[0].equals("D")) {
                    taskList.addTask(DeadlineTask.makeDeadline(taskInputs));
                    // enum: done not done to prevent invalid input
                } else if (taskInputs[0].equals("E")) {
                    taskList.addTask(EventTask.makeEvent(taskInputs));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save a single task to the hard disk.
     * @param newTask Task to save to the hard disk.
     */
    public void saveNewTask(Task newTask) {
        try {
            FileWriter fw = new FileWriter(storedTasks, true);
            fw.write(newTask.toSavedString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save all the tasks from the task list into the hard disk.
     * @param taskList Task list to save the tasks from.
     */
    public void saveAllTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(storedTasks);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toSavedString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
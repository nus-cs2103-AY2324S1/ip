package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Storage for the task list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new file in the storage.
     *
     * @param tasks List of tasks.
     */
    public void createFile(TaskList tasks) {
        try {
            File myObj = new File(filePath);
            myObj.createNewFile();
            FileWriter myWriter = new FileWriter(filePath);
            Integer taskSize = tasks.size();
            for (int i = 0; i < taskSize; i++) {
                myWriter.write(String.valueOf(tasks.get(i)) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the list from the storage.
     *
     * @return Array list of tasks.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File newFile = new File(filePath);
            Scanner myReader = new Scanner(newFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String taskType = data.substring(1, 2);
                String marking = data.substring(4, 5);
                String taskDesc = data.split("]")[2].trim();
                if (taskType.equals("D")) {
                    Deadline.setNewDeadline(taskDesc, taskList);
                } else if (taskType.equals("T")) {
                    Todo.setNewTodo(taskDesc, taskList);
                } else if (taskType.equals("E")) {
                    Event.setNewEvent(taskDesc, taskList);
                }

                if (marking.equals("X")) {
                    taskList.get(taskList.size() - 1).mark();
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }
}

package bruno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import bruno.exceptions.BrunoException;
import bruno.task.Task;

/**
 * The Storage class is responsible for all actions relating to the bruno.txt file, such as loading the
 * file at the start of the application, and writing to the file whenever changes are made to the task list.
 */
public class Storage {

    private String dirPath;
    private String fileName;
    private UI ui;
    private TaskList taskList;

    /**
     * Creates a new instance of the Storage class using the specified directory path and file name.
     *
     * @param dirPath The path of the directory where the file will be stored.
     * @param fileName The name of the file that is loaded, or the tasks are written to.
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        taskList = new TaskList(this, ui);
    }

    /**
     * Writes to the bruno.txt file by reading all tasks in the task list.
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.dirPath + this.fileName);
            List<Task> list = taskList.getList();
            for (Task task : list) {
                fileWriter.write(task.getFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the bruno.txt file and populates the list of tasks.
     *
     * @throws BrunoException Thrown if file to be read is not in proper format.
     */
    public void loadFile(TaskList taskList) throws BrunoException {
        try {
            File file = new File(this.dirPath + this.fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] task = s.split("\\|");
                List<Task> list = taskList.getList();
                switch (task[0]) {
                case "T":
                    list.add(new bruno.task.ToDo(task[2]));
                    break;
                case "D":
                    list.add(new bruno.task.Deadline(task[2], task[3]));
                    break;
                case "E":
                    list.add(new bruno.task.Event(task[2], task[3], task[4]));
                    break;
                default:
                    throw new bruno.exceptions.BrunoIncorrectFormatException();
                }
                taskList.setList(list);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

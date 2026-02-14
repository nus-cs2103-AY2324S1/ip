package bruno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import bruno.exceptions.BrunoException;
import bruno.task.Deadline;
import bruno.task.Event;
import bruno.task.Task;
import bruno.task.ToDo;

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
        assert !dirPath.isEmpty() : "Directory path is not specified";
        this.dirPath = dirPath;
        assert !fileName.isEmpty() : "File name is not specified";
        this.fileName = fileName;
        File directory = new File(this.dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        assert directory.exists() : "Directory could not be created";
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
            assert file.exists() : "File could not be created";
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] task = s.split("\\|");
                List<Task> list = taskList.getList();
                populateList(task, list);
                taskList.setList(list);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void populateList(String[] task, java.util.List<bruno.task.Task> list)
            throws bruno.exceptions.BrunoIncorrectFormatException {
        switch (task[0]) {
        case "T":
            String note = "";
            if (task.length == 3) {
                note = "";
            } else {
                note = task[3];
            }
            list.add(new ToDo(task[2], note));
            break;
        case "D":
            if (task.length == 4) {
                list.add(new Deadline(task[2], task[3], ""));
            } else {
                list.add(new Deadline(task[2], task[4], task[3]));
            }
            break;
        case "E":
            if (task.length == 5) {
                list.add(new Event(task[2], task[3], task[4], ""));
            } else {
                list.add(new Event(task[2], task[4], task[5], task[3]));
            }
            break;
        default:
            throw new bruno.exceptions.BrunoIncorrectFormatException();
        }
    }
}

package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.KoraException;
import duke.list.TaskList;
import duke.list.CommandList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Storage class is to load and save the tasks in the hard disk automatically.
 */
public class Storage {
    private String path;
    private TaskList taskList;
    private Scanner scanner;

    /**
     * Class constructor of Storage.
     * @param filePath Location of the data stored.
     */
    public Storage(String filePath) throws KoraException {
        path = filePath;
        taskList = new TaskList();
        createFile();
        initialiseScanner();
    }

    /**
     * Initialises scanner.
     * @throws KoraException When there is error in scanner.
     */
    public void initialiseScanner() throws KoraException {
        try {
            File f = new File(path);
            this.scanner = new Scanner(f);
        } catch (IOException e) {
                throw new KoraException("I-SangHae! Unable to scan :(");
        }
    }

    /**
     * Creates folder and file if they don't exist.
     * @throws KoraException When unable to create the folder and file.
     */
    public void createFile() throws KoraException {
        File f = new File("./data");
        if (!f.exists()) {
            f.mkdir();
        }

        try {
            File ff = new File(path);
            if (!ff.exists()) {
                ff.createNewFile();
            }
        } catch (IOException e) {
            throw new KoraException("I-SangHae! Unable to create file :(");

        }
    }

    /**
     * Loads tasks from data stored.
     * @return list of tasks frm the txt file.
     * @throws KoraException When unable to scan.
     */
    public List<Task> loadTask() throws KoraException {
        List<Task> list = new ArrayList<>();
        String[] array;
        try {
            while (scanner.hasNext()) {
                array = scanner.nextLine().split(" / ");
                if (array[0].equals("")) {
                    continue;
                }
                list.add(checkTask(array));
                taskList.addTask(checkTask(array));
            }
        } catch (NullPointerException e) {
            return list;
        }
        return list;
    }


    /**
     * Saves tasks in the current task list.
     * @param task Current task to be added.
     * @throws KoraException When unable to write to the file.
     */
    public void saveTask(Task task) throws KoraException {

        try (FileWriter fw = new FileWriter(path, false)) {
            taskList.addTask(task);
            fw.write(taskList.saveFormat());
        } catch (IOException e) {
            throw new KoraException("I-SangHae! Couldn't add the task :(");
        }
    }

    /**
     * Saves all tasks in the task list in the current task list.
     * @param taskList Task list to be saved.
     * @throws KoraException When unable to write to the file.
     */
    public void saveTaskList(TaskList taskList) throws KoraException {
        try (FileWriter fw = new FileWriter(path, false)) {
            this.taskList = taskList;
            fw.write(taskList.saveFormat());
        } catch (IOException e) {
            throw new KoraException("I-SangHae! Couldn't add the task list :(");
        }
    }

    /**
     * Creates task according to different type and with data from array with strings from user input.
     * @param array String array from user input.
     * @return Task
     * @throws KoraException When the type of task is not valid.
     */
    public Task checkTask(String[] array) throws KoraException {
        //assert array.length >= 2 : "The command is too short.";
        Task currentTask;
        if (array[0].contains("E")) {
            currentTask = new Event(array[2], array[3], array[4]);
        } else if (array[0].contains("D")) {
            currentTask = new Deadline(array[2], array[3]);
        } else if (array[0].contains("T")) {
            currentTask = new ToDo(array[2]);
        } else {
            throw new KoraException("Omo! Check if the task is valid!");
        }


        if (array[1].equals("[X]")) {
            currentTask.setMarked();
        } else {
            currentTask.setUnmarked();
        }

        return currentTask;
    }

    /**
     * Loads command list from the txt file.
     * @return List of string arrays containing the command details.
     */
    public List<String[]> loadCommand() {
        List<String[]> list = new ArrayList<>();
        String[] array;
        try {
            while (scanner.hasNext()) {
                array = scanner.nextLine().split(" / ");
                if (array[0].equals("")) {
                    continue;
                }
                list.add(array);
            }
        } catch (NullPointerException e) {
            return list;
        }
        return list;
    }

    /**
     * Saves the current command list to the txt file.
     * @param commandList List that stores all the command names and types.
     * @throws KoraException When unable to write to the file.
     */
    public void saveCommand(CommandList commandList) throws KoraException {
        try (FileWriter fw = new FileWriter(path, false)) {
            fw.write(commandList.saveFormat());
        } catch (IOException e) {
            throw new KoraException("I-SangHae! Couldn't add the command :(");
        }
    }


}

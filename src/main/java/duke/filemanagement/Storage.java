package duke.filemanagement;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

/**
 * Represents a storage to store the task file content that can perform different operations to the content.
 */
public class Storage {
    private String filepath;

    /**
     * Constructor of storage.
     * @param filepath Filepath that leads to the task file to be loaded into bot.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Writes the content into the task file provided in the filepath during construction of Storage.
     * @param content Content to be written into task file.
     */
    public void saveFile(String content) {
        try {
            FileWriter myWriter = new FileWriter(filepath, false);
            myWriter.write(content);
            myWriter.close();
        } catch (FileNotFoundException e) {
            File f = new File("src/main/data");
            if (f.mkdir()) {
                saveFile(content);
            } else {
                System.out.println("An error occurred. File is not written.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("An error occurred. File is not written.");
            e.printStackTrace();
        }
    }

    /**
     * Returns content inside the task file in filepath used during construction of Storage.
     * @return Content of task file.
     * @throws FileNotFoundException File is not found in the filepath.
     */
    public String readFile() throws FileNotFoundException {
        try {
            File myObj = new File(filepath);
            Scanner myReader = new Scanner(myObj);
            StringBuilder data = new StringBuilder();
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine()).append("\n");
            }
            myReader.close();
            return data.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }

    /**
     * Gets type of task from a line extracted from task file.
     * @param line Line containing task details.
     * @return Type of the task represented by line.
     */
    public String getTaskType(String line) {
        String[] lineSplit = line.split("\\[");
        return lineSplit[1].substring(0, 1);
    }

    /**
     * Gets mark status of task from a line extracted from task file.
     * @param line Line containing task details.
     * @return Boolean representing whether the task is marked.
     */
    public boolean getMarkStatus(String line) {
        String[] lineSplit = line.split("\\[");
        return lineSplit[2].charAt(0) == 'X';
    }

    /**
     * Gets task details of task from a line extracted from task file.
     * @param line Line containing task details.
     * @return Details of the tasks.
     */
    public String getTaskDetails(String line) {
        String[] lineSplit = line.split("\\[");
        return  lineSplit[2].split("] ")[1];
    }

    /**
     * Produces a deadline task based on the taskDetails and mark status input.
     * @param taskDetails Details of task extracted from task file.
     * @param markStatus Mark status of task extracted from task file.
     * @return A deadline task aligned with taskDetails and mark status.
     */
    public Deadline produceDeadlineTask(String taskDetails, boolean markStatus) {
        String[] taskDetailsSplit = taskDetails.split(" \\(by: ");
        String description = taskDetailsSplit[0];
        String by = taskDetailsSplit[1].substring(0, taskDetailsSplit[1].length() - 1);
        return new Deadline(description, markStatus, by);
    }

    /**
     * Produces an event task based on the taskDetails and mark status input.
     * @param taskDetails Details of task extracted from task file.
     * @param markStatus Mark status of task extracted from task file.
     * @return An event task aligned with taskDetails and mark status.
     */
    public Event produceEventTask(String taskDetails, boolean markStatus) {
        // Extract description from task details
        String[] taskDetailsSplit = taskDetails.split(" \\(from: ");
        String description = taskDetailsSplit[0];

        // Split details into from and to
        String[] taskDetailsSplit2 = taskDetailsSplit[1].split(" to: ");
        String from = taskDetailsSplit2[0];
        String to = taskDetailsSplit2[1]
                .substring(0, taskDetailsSplit[1].split(" to: ")[1].length() - 1);
        return new Event(description, markStatus, from, to);
    }

    /**
     * Load content of task file into TaskList. This will populate the TaskList with the necessary Task objects.
     * @param taskList TaskList to store the tasks in the task file.
     */
    public void loadFileToTaskManager(TaskList taskList) {
        try {
            String savedText = readFile();
            Scanner scanner = new Scanner(savedText);
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                // line is represented as: "A. [B][C] Details" where A is index of task, B is type of task, C is mark status
                String taskType = getTaskType(line);
                boolean markStatus = getMarkStatus(line);
                String taskDetails = getTaskDetails(line);
                Task taskToAdd = null;

                switch (taskType) {
                    case "T":
                        taskToAdd = new ToDo(taskDetails, markStatus);
                        break;
                    case "D":
                        taskToAdd = produceDeadlineTask(taskDetails, markStatus);
                        break;
                    case "E":
                        taskToAdd = produceEventTask(taskDetails, markStatus);
                        break;
                }
                taskList.addTask(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

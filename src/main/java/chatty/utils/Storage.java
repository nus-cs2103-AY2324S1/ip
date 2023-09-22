package chatty.utils;

import java.io.*;

import java.util.Scanner;

import chatty.exception.ChattyException;
import chatty.exception.InvalidTaskNumberException;
import chatty.task.Deadline;
import chatty.task.Event;
import chatty.task.TaskList;
import chatty.task.ToDo;

/**
 * Responsible for saving and loading tasks to/from a hardware storage.
 * The task list will autoload when the user restarts the Chatty chatbot.
 */
public class Storage {

    protected static String FILENAME = "data/alias.txt";
    private final String filePath;
    private final File file;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        this.file.getParentFile().mkdirs();

        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Save the added tasks to the storage file.
     *
     * @param taskList The list with all the tasks to be saved.
     */
    public void saveTask(TaskList taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));
            StringBuilder tasks = new StringBuilder();

            for (int i = 0; i < taskList.listSize(); i++) {
                tasks.append(taskList.showTask(i + 1)).append('\n');
            }
            writer.write(tasks.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save tasks: " + e.getMessage());
        }
    }

    /**
     * Saves a new alias for a command to the alias file.
     *
     * @param alias   The alias to be saved.
     * @param command The associated command.
     */
    public void saveAlias(String alias, String command) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true));
            writer.write(command + "=" + alias);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save alias: " + e.getMessage());
        }
    }

    /**
     * Updates an existing alias in the alias file.
     *
     * @param newAlias The new alias to replace the existing one.
     * @throws ChattyException When there is an issue updating the alias.
     */
    public void updateAlias(String newAlias) throws ChattyException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILENAME));
            String line;
            StringBuilder fileContent = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String command = parts[1].trim();
                    if (command.equalsIgnoreCase(command)) {
                        // Update the line with the new alias
                        line = newAlias + "=" + command;
                    }
                }
                fileContent.append(line).append("\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            writer.write(fileContent.toString());
            writer.close();
        } catch (IOException e) {
            throw new ChattyException("unable to update alias");
        }
    }


    /**
     * Loads aliases from an alias file and populates the COMMAND_ALIAS map.
     *
     * @param aliasFile The path to the alias file to be loaded.
     * @throws ChattyException When there is an issue loading aliases from the file.
     */
    public void loadAlias(String aliasFile) throws ChattyException {
        try {
            Scanner scanner = new Scanner(new File(aliasFile));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String command = parts[0].trim().toLowerCase();
                    String alias = parts[1].trim();
                    Parser.COMMAND_ALIAS.put(command, alias);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new ChattyException("Unable to load alias!");
        }
    }

    /**
     * Load the tasks from the storage file when the user starts the bot again.
     *
     * @param taskList The list that contains the added tasks.
     * @throws IOException When the tasks cannot be loaded.
     * @throws ChattyException When the tasks cannot be loaded.
     */
    public void loadTask(TaskList taskList) throws IOException, ChattyException {
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNext()) {
                String task = scanner.nextLine();
                char taskType = task.charAt(1);

                switch (taskType) {
                case 'T':
                    loadTodoTask(taskList, task);
                    break;
                case 'D':
                    loadDeadlineTask(taskList, task);
                    break;
                case 'E':
                    loadEventTask(taskList, task);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + taskType);
                }
            }
        } catch (IOException e) {
            throw new ChattyException("Unable to load task");
        }
    }

    /**
     * Handles the case when the task to be loaded is a todo task.
     *
     * @param taskList The list with the added tasks.
     * @param task The task to be added.
     * @throws InvalidTaskNumberException When the index of the task is not valid.
     */
    private void loadTodoTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        String taskDescription = task.substring(7);
        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new ToDo(taskDescription));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }


    /**
     * Handles the case when the task to be loaded is a deadline task.
     *
     * @param taskList The list with the added tasks.
     * @param task The task to be added.
     * @throws InvalidTaskNumberException When the index of the task is not valid.
     */
    private void loadDeadlineTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        int colonIndex = task.indexOf(":");
        String taskDescription = task.substring(7, colonIndex - 4);
        String deadline = task.substring(colonIndex + 4, task.length() - 1);
        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new Deadline(taskDescription, deadline));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }


    /**
     * Handles the case when the task to be loaded is an event task.
     *
     * @param taskList The list with the added tasks.
     * @param task The task to be added.
     * @throws InvalidTaskNumberException When the index of the task is not valid.
     */
    private void loadEventTask(TaskList taskList, String task) throws InvalidTaskNumberException {
        int colon1Index = task.indexOf(':');
        int colon2Index = task.indexOf(':', colon1Index + 1);

        String taskDescription = task.substring(7, colon1Index - 6);
        String startDate = task.substring(colon1Index + 2, colon2Index - 3);
        String endDate = task.substring(colon2Index + 2, task.length() - 1);

        boolean isDone = task.charAt(4) == 'X';
        taskList.addTask(new Event(taskDescription, startDate, endDate));
        if (isDone) {
            taskList.markTask(taskList.listSize(), true);
        }
    }
}

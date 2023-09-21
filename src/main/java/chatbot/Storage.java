package chatbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import chatbot.exception.InvalidFileFormatException;
import chatbot.exception.InvalidTaskNumberException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Todo;

public class Storage {
    /**
     * Loads file input and adds respective tasks into chatbot taskList.
     */
    public void loadTasksFromFile(TaskList taskList) throws InvalidFileFormatException,
            IOException, SecurityException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File data = new File("data/tasks.txt");
        if (data.exists()) {
            Scanner dataScanner = new Scanner(data);
            while (dataScanner.hasNextLine()) {
                String task = dataScanner.nextLine();
                if (task.startsWith("[T]")) {
                    loadTodoFromFile(taskList, task);
                } else if (task.startsWith("[D]")) {
                    loadDeadlineFromFile(taskList, task);
                } else if (task.startsWith("[E]")) {
                    loadEventFromFile(taskList, task);
                } else {
                    throw new InvalidFileFormatException();
                }
            }
            dataScanner.close();
        } else {
            data.createNewFile();
        }
    }

    private void loadEventFromFile(TaskList taskList, String task) throws InvalidFileFormatException {
        int from = task.indexOf("(from: ");
        int to = task.indexOf("to: ");
        String description = task.substring(7, from - 1);
        LocalDate start = LocalDate.parse(task.substring(from + 7, to - 1),
                DateTimeFormatter.ofPattern("MMM d yyyy"));
        LocalDate end = LocalDate.parse(task.substring(to + 4, task.length() - 1),
                DateTimeFormatter.ofPattern("MMM d yyyy"));

        checkFileInputIsNotBlank(description);

        taskList.add(new Event(description, start, end));
    }

    private void loadDeadlineFromFile(TaskList taskList, String task) throws InvalidFileFormatException {
        int by = task.indexOf("(by: ");
        String description = task.substring(7, by - 1);
        LocalDate deadline = LocalDate.parse(task.substring(by + 5, task.length() - 1),
                DateTimeFormatter.ofPattern("MMM d yyyy"));

        checkFileInputIsNotBlank(description);

        taskList.add(new Deadline(description, deadline));
    }

    private void loadTodoFromFile(TaskList taskList, String task) throws InvalidFileFormatException {
        String description = task.substring(7);

        checkFileInputIsNotBlank(description);

        taskList.add(new Todo(description));
    }

    /**
     * Saves current taskList to data file.
     */
    public void saveTasks(TaskList taskList) throws IOException, InvalidTaskNumberException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.taskRep(i) + "\n");
        }
        fw.close();
    }

    /**
     * Checks that given String inputs from the loaded file are not blank.
     * Throws InvalidFileFormatException if they are.
     *
     * @param inputs The String inputs to check.
     * @throws InvalidFileFormatException If any of the inputs are blank.
     */
    private void checkFileInputIsNotBlank(String... inputs) throws InvalidFileFormatException {
        for (String input : inputs) {
            if (input.isBlank()) {
                throw new InvalidFileFormatException();
            }
        }
    }
}

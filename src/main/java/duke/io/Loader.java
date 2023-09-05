package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.exceptions.UnknownCommandException;
import duke.exceptions.FileIoException;
import duke.exceptions.ErrorMessages;
public class Loader {
    private static final String TODO_FLAG = "[T]";
    private static final String DEADLINE_FLAG = "[D]";
    private static final String EVENT_FLAG = "[E]";
    private static final String IS_DONE_FLAG = "[X]";
    private static final String NOT_DONE_FLAG = "[ ]";

    private final String completeFilePath;
    private final Path parentDirectory;
    private final File dataFile;
    public Loader(String filePath) {
        String baseDirectoryPath = Paths.get("").toAbsolutePath().toString();
        this.completeFilePath = Paths.get(baseDirectoryPath, filePath).toString();
        this.parentDirectory = Paths.get(baseDirectoryPath, Paths.get(filePath).getParent().toString());
        this.dataFile = new File(this.completeFilePath);
    }

    public TaskList load() throws UnknownCommandException, FileIoException {
        TaskList tasks = new TaskList();

        if (!dataFile.exists()) {
            return tasks;
        }

        try {
            List<String> fileContents = extractFileContents(dataFile);
            for (String contentLine : fileContents) {
                interpretAndAddTask(tasks, contentLine);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new FileIoException(ErrorMessages.STORAGE_ERROR);
        }
    }

    private List<String> extractFileContents(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> fileLines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            fileLines.add(line);
        }

        return fileLines;
    }

    private void interpretAndAddTask(TaskList tasks, String lineContent) throws UnknownCommandException {
        String[] taskData = lineContent.split("\\s\\|\\s");
        boolean isTaskCompleted = taskData[1].equals(IS_DONE_FLAG);

        switch (taskData[0]) {
            case TODO_FLAG:
                addTask(tasks, new Todo(taskData[2].strip()), isTaskCompleted);
                break;
            case DEADLINE_FLAG:
                addTask(tasks, new Deadline(taskData[2].trim(), taskData[3]), isTaskCompleted);
                break;
            case EVENT_FLAG:
                addTask(tasks, new Event(taskData[2].strip(), taskData[3], taskData[4]), isTaskCompleted);
                break;
            default:
                throw new UnknownCommandException("OOPS!!! Unrecognized task type: " + taskData[0]);
        }
    }

    private static void addTask(TaskList list, Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        }
        list.addTask(task);
    }
}
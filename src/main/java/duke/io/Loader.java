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
        String taskType = taskData[0];
        boolean isTaskCompleted = taskData[1].equals(IS_DONE_FLAG);

        switch (taskType) {
            case TODO_FLAG:
                readTodo(tasks, taskData[2], isTaskCompleted);
                break;
            case DEADLINE_FLAG:
                readDeadline(tasks, taskData[2], isTaskCompleted, taskData[3]);
                break;
            case EVENT_FLAG:
                readEvent(tasks, taskData[2], isTaskCompleted, taskData[3], taskData[4]);
                break;
            default:
                String errorMessage = "OOPS!!! Unrecognized task type: ";
                throw new UnknownCommandException( errorMessage + taskType);
        }
    }

    public static void readTodo(TaskList list, String description, boolean isDone) {
        Todo todo = new Todo(description.strip());

        if (isDone) {
            todo.markAsDone();
        }

        list.addTask(todo);
    }

    public static void readDeadline(TaskList list, String description, boolean isDone, String date) {
        Deadline deadline = new Deadline(description.trim(), date);

        if (isDone) {
            deadline.markAsDone();
        }

        list.addTask(deadline);
    }

    public static void readEvent(TaskList list, String description, boolean isDone, String from, String to) {
        Event event = new Event(description.strip(), from, to);

        if (isDone) {
            event.markAsDone();
        }

        list.addTask(event);
    }
}

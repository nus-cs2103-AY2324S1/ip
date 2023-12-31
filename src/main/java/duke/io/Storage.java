package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import duke.exceptions.ErrorMessages;
import duke.exceptions.FileIoException;
import duke.exceptions.UnknownCommandException;
import duke.parsers.TimeParser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Represents the storage class responsible for reading from and writing to a specific file.
 */
public class Storage {
    private static final String TODO_FLAG = "[T]";
    private static final String DEADLINE_FLAG = "[D]";
    private static final String EVENT_FLAG = "[E]";
    private static final String IS_DONE_FLAG = "[X]";
    private static final String NOT_DONE_FLAG = "[ ]";

    private static final String INVALID_TYPE_ERROR = "Type of event can only be [T], [D], or [E]";
    private static final String INVALID_STATUS_ERROR = "IsDone flag of event can only be [ ], or [X]";

    private final String completeFilePath;
    private final Path parentDirectory;
    private final File dataFile;
    /**
     * Creates a new storage instance.
     *
     * @param filePath The relative path of the data file.
     */
    public Storage(String filePath) {
        String baseDirectoryPath = Paths.get("").toAbsolutePath().toString();
        this.completeFilePath = Paths.get(baseDirectoryPath, filePath).toString();
        this.parentDirectory = Paths.get(baseDirectoryPath, Paths.get(filePath).getParent().toString());
        this.dataFile = new File(this.completeFilePath);
    }

    /**
     * Loads tasks from the specified file into a TaskList.
     *
     * @return The TaskList with tasks loaded from the file.
     * @throws UnknownCommandException If there's an unrecognized task type.
     * @throws FileIoException If an IO error occurs.
     */
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

    /**
     * Extracts content from the given file.
     *
     * @param file The file to extract content from.
     * @return A list of file content lines.
     * @throws FileNotFoundException If the file is not found.
     */
    private List<String> extractFileContents(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        List<String> fileLines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            fileLines.add(line);
        }

        return fileLines;
    }

    /**
     * Interprets a given content line and adds the interpreted task to the TaskList.
     *
     * @param tasks TaskList to add the interpreted task to.
     * @param lineContent The content line to interpret.
     * @throws UnknownCommandException If the task type in the content line is unrecognized.
     */
    private void interpretAndAddTask(TaskList tasks, String lineContent) throws UnknownCommandException {
        String[] taskData = lineContent.split("\\s\\|\\s");
        boolean isTaskCompleted = taskData[1].equals(IS_DONE_FLAG);

        switch (taskData[0]) {
        case TODO_FLAG:
            addTask(tasks, new Todo(taskData[2].strip()), isTaskCompleted);
            break;
        case DEADLINE_FLAG:
            LocalDateTime ddl = TimeParser.parseToLocalDateTime(taskData[3]);
            addTask(tasks, new Deadline(taskData[2].trim(), ddl), isTaskCompleted);
            break;
        case EVENT_FLAG:
            LocalDateTime from = TimeParser.parseToLocalDateTime(taskData[3]);
            LocalDateTime to = TimeParser.parseToLocalDateTime(taskData[4]);
            addTask(tasks, new Event(taskData[2].strip(), from, to), isTaskCompleted);
            break;
        default:
            throw new UnknownCommandException("OOPS!!! Unrecognized task type: " + taskData[0]);
        }
    }

    /**
     * Checks if the given tag and status are valid for storage format.
     *
     * @param tag The task type tag.
     * @param isDone The task completion status.
     */
    private static void checkStorageFormat(String tag, String isDone) {
        assert Objects.equals(tag, TODO_FLAG) || Objects.equals(tag, DEADLINE_FLAG)
                || Objects.equals(tag, EVENT_FLAG) : INVALID_TYPE_ERROR;

        assert Objects.equals(isDone, IS_DONE_FLAG) || Objects.equals(isDone, NOT_DONE_FLAG) : INVALID_STATUS_ERROR;
    }

    private static void addTask(TaskList list, Task task, boolean isDone) {
        if (isDone) {
            task.markAsDone();
        }
        list.addTask(task);
    }

    /**
     * Saves the tasks from a given TaskList to the data file.
     *
     * @param taskList The TaskList to save.
     * @throws FileIoException If an IO error occurs.
     */
    public void save(TaskList taskList) throws FileIoException {
        createParentFolderIfNotExists();
        StringBuilder record = new StringBuilder();

        for (int i = 0; i < taskList.getNumberOfTasks(); i++) {
            Task task = taskList.getTask(i);
            record.append(task.saveString()).append(System.lineSeparator());
        }

        try {
            writeToFile(record.toString());
        } catch (IOException e) {
            throw new FileIoException(ErrorMessages.STORAGE_ERROR);
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        Files.write(Paths.get(completeFilePath), textToAdd.getBytes());
    }

    private void createParentFolderIfNotExists() {
        if (Files.notExists(parentDirectory)) {
            try {
                Files.createDirectories(parentDirectory);
            } catch (IOException e) {
                System.out.println("Error occurred while creating directory");
            }
        }
    }
}

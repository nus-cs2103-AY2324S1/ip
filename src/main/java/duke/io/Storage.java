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
    public Storage(String filePath) {
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
                System.out.println("Error occurred in creating directory");
            }
        }
    }
}

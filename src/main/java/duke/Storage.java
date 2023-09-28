package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks
 * in the file.
 */
public class Storage {
    private String filePath;
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = "./data/duke.txt";

    public Storage() {

        File dataDirectory = new File(DIRECTORY_PATH);
        File dataFile = new File(FILE_PATH);

        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating a new file:" + e);
            }
        }
    }

    public void appendTasksToFile(TaskList taskList)
            throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < taskList.listSize(); i++) {
            Task task = taskList.getTask(i);
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> loadPreviousTasks()
            throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                Task task = parseTaskFromLine(line);
                tasks.add(task);
            } catch (DukeException e) {
                System.err.println("Error parsing task from file: " + e.getMessage());
            }

        }
        s.close();
        return tasks;
    }

    public Task parseTaskFromLine(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DukeException("Invalid data format in the file.");
        }
        char taskType = parts[0].charAt(0);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
        case 'T':
            return parseToDoTask(description, taskType, isDone);
        case 'D':
            if (parts.length < 4) {
                throw new DukeException("Invalid data format for a deadline task");
            }
            String deadlineBy = parts[3];
            return parseDeadlineTask(description, deadlineBy, taskType, isDone);
        case 'E':
            if (parts.length < 4) {
                throw new DukeException("Invalid data format for an event task.");
            }
            String[] eventParts = parts[3].split("-");
            String eventFrom = eventParts[0];
            String eventTo = eventParts[1];
            return parseEventTask(description, eventFrom, eventTo, taskType, isDone);
        default:
            throw new DukeException("Invalid task type in data file.");
        }
    }

    private Task parseToDoTask(String description, char taskType, boolean isDone) {
        ToDo toDoTask = new ToDo(description, taskType);
        if (isDone) {
            toDoTask.markAsDone();
        } else {
            toDoTask.markAsNotDone();
        }
        return toDoTask;
    }

    private Task parseDeadlineTask(String description, String deadlineBy,
            char taskType, boolean isDone) {
        Deadline deadlineTask = new Deadline(description, deadlineBy, taskType);
        if (isDone) {
            deadlineTask.markAsDone();
        } else {
            deadlineTask.markAsNotDone();
        }
        return deadlineTask;
    }

    private Task parseEventTask(String description, String eventFrom, String eventTo,
            char taskType, boolean isDone) {
        Event eventTask = new Event(description, eventFrom, eventTo, taskType);
        if (isDone) {
            eventTask.markAsDone();
        } else {
            eventTask.markAsNotDone();
        }
        return eventTask;
    }


}


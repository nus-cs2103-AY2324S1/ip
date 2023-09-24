package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void appendTasksToFile(TaskList taskList)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.listSize(); i++) {
            Task task = taskList.getTask(i);
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    public ArrayList<Task> loadPreviousTasks()
            throws FileNotFoundException {
        File f = new File(filePath);
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
                ToDo toDoTask = new ToDo(description, taskType);
                if (isDone) {
                    toDoTask.markAsDone();
                } else {
                    toDoTask.markAsNotDone();
                }
                return toDoTask;

            case 'D':
                if (parts.length < 4) {
                    throw new DukeException("Invalid data format for a deadline task");
                }
                String deadlineBy = parts[3];
                Deadline deadlineTask = new Deadline(description, deadlineBy, taskType);
                if (isDone) {
                    deadlineTask.markAsDone();
                } else {
                    deadlineTask.markAsNotDone();
                }
                return deadlineTask;

            case 'E':
                if (parts.length < 4) {
                    throw new DukeException("Invalid data format for an event task.");
                }
                String[] eventParts = parts[3].split("-");
                String eventFrom = eventParts[0];
                String eventTo = eventParts[1];
                Event eventTask = new Event(description, eventFrom, eventTo, taskType);
                if (isDone) {
                    eventTask.markAsDone();
                } else {
                    eventTask.markAsNotDone();
                }
                return eventTask;

            default:
                throw new DukeException("Invalid task type in data file.");
        }
    }
}


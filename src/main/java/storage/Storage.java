package storage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
import utility.DateTimeParser;
import utility.TextFileHandler;

/**
 * Utility class for reading and writing tasks to/from a textfile.
 *
 * @author Ho Khee Wei
 */
public abstract class Storage {
    /** Path to the textfile. */
    public static final String TASK_FILE_PATH = "data/tasks.txt";

    /** Character indicator that separates one elements in the textfile. */
    public static final String TASK_FILE_SEPARATOR = "-";

    /** Standard format for time and date. */
    public static final String TASK_FILE_TB_FORMAT = "dd/MM/yyyy HHmm";

    /**
     * Reads task data from the specified file and returns a TaskList containing the
     * tasks.
     *
     * @return A TaskList containing tasks read from the file.
     */
    public static TaskList readFromFile() {
        TaskList res = new TaskList();

        try {
            String[] lines = TextFileHandler.readLines(TASK_FILE_PATH);
            for (String line : lines) {
                String[] task = line.split(TASK_FILE_SEPARATOR);
                String taskType = task[2];
                String status = task[1];
                String description = task[0];

                if (taskType.equals("T")) {
                    res.addTask(new Todo(description));
                } else if (taskType.equals("D")) {
                    String time = task[3];
                    res.addTask(new Deadline(description, DateTimeParser.parse(time)));
                } else if (taskType.equals("E")) {
                    String from = task[3];
                    String to = task[4];
                    res.addTask(new Event(description, DateTimeParser.parse(from),
                            DateTimeParser.parse(to)));
                }

                if (status.equals("1")) {
                    res.markDone(res.size() - 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Writes the tasks from a TaskList to the specified file in the predefined
     * format.
     *
     * @param list The TaskList containing tasks to be written to the file.
     */
    public static void writeToFile(TaskList list) {
        String output = "";

        for (int i = 0; i < list.size(); i++) {
            Task task = list.getTask(i);
            output += task.getDescription();

            if (task.isDone()) {
                output += TASK_FILE_SEPARATOR + "1";
            } else {
                output += TASK_FILE_SEPARATOR + "0";
            }

            if (task instanceof Todo) {
                output += TASK_FILE_SEPARATOR + "T";
            } else if (task instanceof Deadline) {
                output += TASK_FILE_SEPARATOR + "D";
                Deadline deadline = (Deadline) task;
                output += TASK_FILE_SEPARATOR
                        + deadline.getCompleteBy().format(DateTimeFormatter.ofPattern(TASK_FILE_TB_FORMAT));
            } else if (task instanceof Event) {
                output += TASK_FILE_SEPARATOR + "E";
                Event event = (Event) task;
                output += TASK_FILE_SEPARATOR
                        + event.getStartTime().format(DateTimeFormatter.ofPattern(TASK_FILE_TB_FORMAT));
                output += TASK_FILE_SEPARATOR
                        + event.getEndTime().format(DateTimeFormatter.ofPattern(TASK_FILE_TB_FORMAT));
            }
            output += System.lineSeparator();
        }

        try {
            TextFileHandler.writeText(TASK_FILE_PATH, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

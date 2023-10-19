package qi.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import qi.qiexception.QiException;
import qi.task.Deadline;
import qi.task.Event;
import qi.task.Todo;
import qi.tasklist.TaskList;

/**
 * Represents the I/O process with a specific file.
 */
public class Storage {
    private final File file;

    /**
     * Takes in the path to file which needs to be read from and written to.
     *
     * @param filePath String representation of the file path.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        File directory = file.getParentFile();

        // Create directory to the file if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cannot create file!");
        }
    }

    /**
     * Takes in a task list to update it with the data on the hard disk.
     *
     * @param list TaskList where data on the hard disk are be loaded to.
     * @throws QiException If the file cannot be accessed.
     */
    public void load(TaskList list) throws QiException {
        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNext()) {
                String task = sc.nextLine();
                assert task != null && task.length() > 8;
                assert task.charAt(1) == 'T' || task.charAt(1) == 'D' || task.charAt(1) == 'E';
                assert task.charAt(4) == ' ' || task.charAt(4) == 'X';
                assert task.charAt(0) == '[' && task.charAt(3) == '['
                        && task.charAt(2) == ']' && task.charAt(5) == ']'
                        && task.charAt(6) == ' ';

                if (task.charAt(1) == 'T') {
                    loadTodo(task, list);
                    continue;
                }

                if (task.charAt(1) == 'D') {
                    loadDeadline(task, list);
                    continue;
                }

                loadEvent(task, list);
            }
        } catch (IOException e) {
            throw new QiException("Cannot read file!");
        }
    }

    private boolean isDone(String task) {
        return task.charAt(4) == 'X';
    }

    private void markTask(TaskList list, String task) {
        try {
            list.mark(list.size(), isDone(task));
        } catch (QiException e) {
            /*
             * This line will never be reach since
             * the task ID is within bound
             */
        }
    }

    private void loadTodo(String task, TaskList list) {
        list.addTask(new Todo(task.substring(7)));
        markTask(list, task);
    }

    private void loadDeadline(String task, TaskList list) {
        int idx = task.indexOf("(by:");
        list.addTask(new Deadline(task.substring(7, idx - 1),
                LocalDate.parse(task.substring(idx + 5, task.length() - 1),
                        DateTimeFormatter.ofPattern("MMM dd yyyy"))));
        markTask(list, task);
    }

    private void loadEvent(String task, TaskList list) {
        int idx1 = task.indexOf("(from:");
        int idx2 = task.indexOf("to:", idx1);

        list.addTask(new Event(task.substring(7, idx1 - 1),
                task.substring(idx1 + 7, idx2 - 1),
                task.substring(idx2 + 4, task.length() - 1)));
        markTask(list, task);
    }

    /**
     * Replaces the data on the hard disk with the data from the current task list.
     *
     * @param list TaskList whose data are used to replace the data on the hard disk.
     * @throws IOException If the file could not be written.
     */
    public void update(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            content.append(list.showTask(i + 1));
            if (i < list.size() - 1) {
                content.append('\n');
            }
        }

        fw.write(content.toString());
        fw.close();
    }
}




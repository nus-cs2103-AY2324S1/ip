package duke;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Storage {

    String filePath;
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        String[] splitFile = filePath.split("/",2);
        File dir = new File(splitFile[0]);
        boolean dirExists = dir.exists();
        if (!dirExists) {
            dir.mkdir();
        }
        try {
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("OOPS!! File cannot be created.");
        }
    }

    /**
     * Writes task into Local Storage.
     * @return The taskList.
     * @throws DukeException  Exception to be thrown when the input cannot be read.
     */
    public TaskList load() throws DukeException {
        try {
            TaskList returnList = new TaskList();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    return returnList;
                } else {
                    Character done = line.charAt(4);
                    boolean isDone = done.equals('X');

                    if (line.startsWith("[T]")) {
                        returnList.add(loadTodo(line, isDone), false);
                    } else if (line.startsWith("[D]")) {
                        returnList.add(loadDeadline(line, isDone), false);
                    } else {
                        returnList.add(loadEvent(line, isDone), false);
                    }
                }
            }
            return returnList;
        } catch (IOException e) {
            throw new DukeException("OOPS! File cannot be loaded.");
        }
    }

    private Task loadTodo(String line, boolean isDone) {
        Todo todo = new Todo(line.substring(7));
        if (isDone) {
            todo.mark(false);
        }
        return todo;
    }

    private Task loadDeadline(String line, boolean isDone) {
        int index = line.indexOf("(");
        int endIndex = line.indexOf(")");
        Deadline deadline = new Deadline(line.substring(7, index - 1),
                line.substring(index + 5, endIndex));
        if (isDone) {
            deadline.mark(false);
        }
        return deadline;
    }

    private Task loadEvent(String line, boolean isDone) {
        int index = line.indexOf("(");
        int midIndex = line.indexOf("to:");
        int endIndex = line.indexOf(")");
        Event event = new Event(line.substring(7, index - 1), line.substring(index + 7,
                midIndex - 1),
                line.substring(midIndex + 4, endIndex));
        if (isDone) {
            event.mark(false);
        }
        return event;
    }
}

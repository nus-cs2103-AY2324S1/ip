package duke;

import java.io.*;

public class Storage {
    public Storage(String filePath) throws DukeException {
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

    public TaskList load() throws DukeException {
        try {
            TaskList returnList = new TaskList();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./data/duke.txt")));
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    return returnList;
                } else {
                    Character done = line.charAt(4);
                    boolean isDone = done.equals('X');

                    if (line.startsWith("[T]")) {
                        Todo todo = new Todo(line.substring(7));
                        if (isDone) {
                            todo.mark(false);
                        }
                        returnList.add(todo, false);
                    } else if (line.startsWith("[D]")) {
                        int index = line.indexOf("(");
                        int endIndex = line.indexOf(")");
                        Deadline deadline = new Deadline(line.substring(7, index - 1),
                                line.substring(index + 5, endIndex));
                        if (isDone) {
                            deadline.mark(false);
                        }
                        returnList.add(deadline, false);
                    } else {
                        int index = line.indexOf("(");
                        int midIndex = line.indexOf("to:");
                        int endIndex = line.indexOf(")");
                        Event event = new Event(line.substring(7, index - 1), line.substring(index + 7, midIndex - 1),
                                line.substring(midIndex + 4, endIndex));
                        if (isDone) {
                            event.mark(false);
                        }
                        returnList.add(event, false);
                    }
                }
            }
            return returnList;
        } catch (IOException e) {
            throw new DukeException("OOPS! File cannot be loaded.");
        }
    }
}

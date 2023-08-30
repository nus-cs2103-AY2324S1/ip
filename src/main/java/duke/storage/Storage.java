package duke.storage;

import duke.exception.DukeException;
import duke.taskList.Task;
import duke.taskList.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private String home = System.getProperty("user.home");
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Collection<Task> load() throws DukeException {
        boolean directoryExists = new java.io.File(home + "\\Documents\\ip\\data").exists();
        if (!directoryExists) {
            java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "ip", "data");
            directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                throw new DukeException("There is no 'data' folder, please create one");
            }
        }
        File f = new File(filePath);

        try {
            ArrayList<Task> lst = new ArrayList<Task>();
            if (f.exists()) {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String temp = s.nextLine();
                    lst.add(existTasks(temp));
                }
                return lst;
            } else {
                f.createNewFile();
                return lst;
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    private Task existTasks(String s) {
        String[] temp = s.split(" \\| ");
        int n = temp.length;
        Task t;
        if (n == 2) {
            t = Task.of(temp[1]);
        } else if (n == 3) {
            t = Task.of(temp[1], LocalDate.parse(temp[2]));
        } else {
            t = Task.of(temp[1], LocalDate.parse(temp[2]), LocalDate.parse(temp[3]));
        }

        if (Objects.equals(temp[0], "1")) {
            t.mark();
        }
        return t;
    }



    public void changeFile(TaskList lst) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw = new FileWriter(filePath, true);
        for (Task curr : lst) {
            fw.write(curr.getText() + "\n");
        }
        fw.close();
    }

    public void addToFile(Task t) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(t.getText() + "\n");
            fw.close();
        } catch (IOException iE) {
            throw new DukeException(iE.getMessage());
        }
    }
}

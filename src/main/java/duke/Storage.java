package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store tasks.
 */
public class Storage {

    private File file;
    private String path;

    public Storage(String filePath) {
        file = new File(filePath);
        path = filePath;
        assert file.exists() : "File path is invalid";
    }

    /**
     * Loads data from the file in this storage to the task list.
     * @return the task list.
     * @throws IOException if there were errors reading and/or converting data from file.
     */
    public ArrayList<Task> load() throws IOException{
            if (file.createNewFile()) {
                return new ArrayList<>();
            }

            Scanner fScanner = new Scanner(file);

            ArrayList<Task> arrTask = new ArrayList<>();

            while (fScanner.hasNextLine()) {
                String s = fScanner.nextLine();
                String[] arr = s.split("--");
                if (arr[0].equals("T")) {
                    Todo t = new Todo(arr[2]);
                    if (arr[1].equals("1")) {
                        t.markAsDone();
                    }
                    arrTask.add(t);
                } else if (arr[0].equals("D")) {
                    Deadline dl = new Deadline(arr[2], LocalDate.parse(arr[3]));
                    if (arr[1].equals("1")) {
                        dl.markAsDone();
                    }
                    arrTask.add(dl);
                } else if (arr[0].equals("E")){
                    Event e = new Event(arr[2], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
                    if (arr[1].equals("1")) {
                        e.markAsDone();
                    }
                    arrTask.add(e);
                }
            }
            fScanner.close();
            return arrTask;
    }

    /**
     * Saves the data in task list to the file in storage.
     * @param list the task list.
     * @throws IOException if there were errors converting and/or storing data to file.
     */
    public void save(TaskList list) throws IOException {
        if (file.delete()) {
            file = new File(path);
            assert file.exists() : "File not created";
            for (int i = 1; i <= list.total(); i++) {
                Task t = list.get(i - 1);
                writeToFile(path, t.toFileString());
            }
        }
    }

    /**
     * Writes a line of text in the file in storage.
     * @param pathname path name of the file.
     * @param textToAdd line of text to write into file.
     * @throws IOException if there were errors reading and/or writing to file.
     */
    private void writeToFile(String pathname, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(pathname, true);
        assert fw != null : "File Writer should not be null";
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }
}

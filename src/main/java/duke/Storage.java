
package duke;

import duke.tasks.Deadline;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;


    /**
     * Init the storage at the current filePath.
     * @param filePath where the saved file is
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the saved text file into a list of tasks.
     * @return the arraylist with the tasks
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> res = new ArrayList<>();
        try {
            File f = new File(this.filePath);
            Scanner Reader = new Scanner(f);
            while (Reader.hasNextLine()) {
                String nxt = Reader.nextLine();
                res.add(restore(nxt));
            }
            Reader.close();
        } catch (FileNotFoundException e) {
            this.openfile();
        }
        return res;
    }

    /**
     * Process every line in the text file and convert to a Task.
     * @param s line in the file
     * @return the tasks after processing
     */
    public Task restore(String s) throws DukeException {
        String[] arr = s.split("\\|");
        Task t;
        switch (arr[0]) {
            case "E":
                LocalDateTime a = LocalDateTime.parse(arr[3].trim(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

                LocalDateTime a2 = LocalDateTime.parse(arr[4].trim(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                t = new Events(arr[2], a, a2);
                break;
            case "D":
                LocalDateTime a3 = LocalDateTime.parse(arr[3].trim(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
                t = new Deadline(arr[2], a3);
                break;
            case "T":
                t = new Todo(arr[2]);
                break;
            default:
                throw new DukeException("No such type of duke.tasks.Task");
        }
        if (arr[1].equals("1")) {
            t.mark();
        }
        return t;
    }

    private void openfile() {
        File db = new File(this.filePath);
        File directory = new File(db.getParent());
        directory.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            System.out.println("Error making the file");
        }
    }

    /**
     * Upon termination save the tasks into the textfile after formatting.
     * @param list the tasklist of tasks to be saved
     */
    public void save(TaskList list) throws DukeException {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(list.format());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save command to file");
        }
    }




}

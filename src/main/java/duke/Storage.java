package duke;

import duke.exceptions.DukeFileNotFoundException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Storage {

    /** File path of data file */
    private String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath path of data file from root.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads list of tasks from file.
     *
     * @return TaskList object.
     * @throws DukeFileNotFoundException If file is not found at filePath.
     */
    public TaskList readFromFile() throws DukeFileNotFoundException {
        TaskList list = new TaskList();
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String line = s.nextLine();
                if (line == null || line.equals("")) {
                    break;
                }
                String[] params = line.split(" \\| ");
                boolean isDone = params[1].equals("1");
                switch (params[0]) {
                case "T":
                    list.addToList(new Todo(params[2], isDone));
                    break;

                case "D":
                    list.addToList(new Deadline(params[2], isDone, LocalDateTime.parse(params[3], Duke.TIME_FORMAT)));
                    break;

                case "E":
                    list.addToList(new Event(params[2], isDone, LocalDateTime.parse(params[3], Duke.TIME_FORMAT),
                            LocalDateTime.parse(params[4], Duke.TIME_FORMAT)));
                    break;

                default:
                    isDone = params[0].equals("1");
                    list.addToList(new Task(params[1].trim(), isDone));
                    break;
                }
            }

            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n" + "OOPS!!! Could not find the file " + this.filePath);
        } finally {
            throw new DukeFileNotFoundException(filePath);
        }
    }

    /**
     * Writes list of tasks to file.
     *
     * @param list TaskList object having a list of Tasks.
     * @throws DukeFileNotFoundException If file is not found at filePath.
     */
    public void writeToFile(TaskList list) throws DukeFileNotFoundException {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");

            StringBuffer write = new StringBuffer("");
            for (int i = 0; i < list.getNumberOfTasks(); i++) {
                write.append(list.getTaskAt(i).getTextRepresentation() + "\n");
            }
            fw.write(write.toString());

            fw.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(filePath);
        } catch (IOException e) {
            System.out.println("\n" + "OOPS!!! " + e.getMessage());
        }
    }
}

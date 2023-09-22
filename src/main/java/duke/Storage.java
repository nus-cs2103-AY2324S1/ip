package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.DukeFileNotFoundException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Priority;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Implementation for the storage.
 */
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
            File f = getDataFile();
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
                    list.addToList(new Todo(params[3], isDone, Priority.valueOf(params[2])));
                    break;

                case "D":
                    list.addToList(new Deadline(params[3], isDone,
                            LocalDateTime.parse(params[4], Duke.TIME_FORMAT), Priority.valueOf(params[2])));
                    break;

                case "E":
                    list.addToList(new Event(params[3], isDone, LocalDateTime.parse(params[4], Duke.TIME_FORMAT),
                            LocalDateTime.parse(params[5], Duke.TIME_FORMAT), Priority.valueOf(params[2])));
                    break;

                default:
                    isDone = params[0].equals("1");
                    list.addToList(new Task(params[1].trim(), isDone));
                    break;
                }
            }

            s.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(this.filePath);
        }
        return list;
    }

    /**
     * Writes list of tasks to file.
     *
     * @param list TaskList object having a list of Tasks.
     * @throws DukeFileNotFoundException If file is not found at filePath.
     */
    public void writeToFile(TaskList list) throws DukeFileNotFoundException {
        try {
            FileWriter fw = new FileWriter(getDataFile());

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

    private File getDataDirectory() {
        File dataDirectory = new File("data");

        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
        return dataDirectory;
    }

    private File getDataFile() throws DukeException {
        File directory = this.getDataDirectory();
        File dataFile = new File(directory + "/" + this.filePath);

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                if (this.filePath.equals("duke.txt")) {
                    populateFile(dataFile);
                } else if (this.filePath.equals("testDuke.txt")) {
                    populateTestFile(dataFile);
                }
            } catch (IOException e) {
                throw new DukeException("OOPS!!! Could not create the file " + this.filePath);
            }
        }

        return dataFile;
    }

    private void populateFile(File dataFile) {
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write("T | 1 | LOW | read book\n"
                    + "D | 0 | MEDIUM | return book | 06 Jun 2023 - 16:00\n"
                    + "E | 0 | MEDIUM | project meeting | 06 Aug 2023 - 14:00 | 06 Aug 2023 - 16:00\n"
                    + "T | 1 | LOW | join sports club\n"
                    + "T | 0 | HIGH | borrow book\n"
                    + "D | 0 | MEDIUM | return another book | 02 Sep 2023 - 16:00\n"
                    + "E | 0 | LOW | another project meeting | 30 Aug 2023 - 21:00 | 30 Aug 2023 - 22:00");
            fw.close();
        } catch (IOException e) {
            System.out.println("\n" + "OOPS!!! " + e.getMessage());
        }
    }

    private void populateTestFile(File dataFile) {
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write("T | 1 | LOW | read book\n"
                    + "D | 0 | MEDIUM | return book | 06 Jun 2023 - 16:00\n"
                    + "E | 0 | HIGH | project meeting | 06 Aug 2023 - 14:00 | 06 Aug 2023 - 16:00");
            fw.close();
        } catch (IOException e) {
            System.out.println("\n" + "OOPS!!! " + e.getMessage());
        }
    }
}

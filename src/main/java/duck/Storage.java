package duck;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duck.exceptions.FileIoException;
import duck.task.Deadline;
import duck.task.Events;
import duck.task.Task;
import duck.task.TaskList;
import duck.task.ToDo;


/**
 * Acts as file handling class which is responsible for storing and reading tasks.
 */
public class Storage {
    private File file;

    /**
     * Constructs a storage object.
     * @param filePath Consists of a filepath for storing and reading tasks.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Constructs a Storage Object.
     */
    public Storage() {}

    /**
     * Creates a File if it is not there
     * @throws FileIoException If unsuccessful in creating directory.
     */
    private void create() throws FileIoException {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Couldn't create dir: "
                    + parent);
            }
            file.createNewFile();
        } catch (IOException e) {
            throw new FileIoException(e.getMessage());
        }
    }

    /**
     * Saves the tasks in the file from the list of tasks.
     * @param list List of tasks.
     * @throws FileIoException if unsuccessful in creating file.
     */
    public void saveInFile(TaskList list) throws FileIoException {
        try {
            if (!file.exists()) {
                create();
            }
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                switch (task.type()) {
                case "D":
                    Deadline deadline = (Deadline) task;
                    fileWriter.write(deadline.type()
                        + " | " + (deadline.getStatusIcon().isBlank() ? "0" : "1")
                        + " | " + deadline.getDescription() + " | " + deadline.getBy());
                    break;
                case "E":
                    Events events = (Events) task;
                    fileWriter.write(events.type() + " | " + (events.getStatusIcon().isBlank() ? "0" : "1")
                        + " | " + events.getDescription() + " | " + events.getDate());
                    break;
                case "T":
                    ToDo toDo = (ToDo) task;
                    fileWriter.write(toDo.type() + " | " + (toDo.getStatusIcon().isBlank() ? "0" : "1")
                        + " | " + toDo.getDescription());
                    break;
                default:
                    throw new FileIoException("Error in file loading");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileIoException(e.getMessage());
        }
    }

    /**
     * Loads tasks from a file and returns them as a list of Task objects.
     *
     * @return A List of Task objects loaded from the file.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            if (!file.exists()) {
                return list;
            }
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] split = line.split(" \\| ");
                switch (split[0]) {
                case "D":
                    Deadline tempDeadline = new Deadline(split[2],
                        LocalDateTime.parse(split[3], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
                    if (split[1].equals("1")) {
                        tempDeadline.markAsDone();
                    }
                    list.add(tempDeadline);
                    break;
                case "E":
                    String[] startEnd = split[3].split("-");
                    Events tempEvent =
                        new Events(LocalDateTime.parse(startEnd[0], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                            LocalDateTime.parse(startEnd[1], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                            split[2]);
                    if (split[1].equals("1")) {
                        tempEvent.markAsDone();
                    }
                    list.add(tempEvent);
                    break;
                case "T":
                    ToDo tempToDo = new ToDo(split[2]);
                    if (split[1].equals("1")) {
                        tempToDo.markAsDone();
                    }
                    list.add(tempToDo);
                    break;
                default:
                    throw new FileIoException("Unable to load");
                }
            }
        } catch (IOException | FileIoException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}

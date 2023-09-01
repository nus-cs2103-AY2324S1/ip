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



public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public Storage() {}

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
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileIoException(e.getMessage());
        }
    }

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
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}

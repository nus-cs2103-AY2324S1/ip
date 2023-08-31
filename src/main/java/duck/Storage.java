package duck;

import duck.task.*;
import duck.exceptions.FileIOException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File FILE;

    public Storage(String filePath) {
        this.FILE = new File(filePath);
    }

    private void create() throws FileIOException {
        try {
            File parent = FILE.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("Couldn't create dir: " + parent);
            }
            FILE.createNewFile();
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    public void saveInFile(TaskList list) throws FileIOException {
        try {
            if (!FILE.exists()) {
                create();
            }
            FileWriter fw = new FileWriter(FILE);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                switch (task.type()) {
                    case "D":
                        Deadline deadline = (Deadline) task;
                        fw.write(deadline.type() + " | " + (deadline.getStatusIcon().isBlank() ? "0" : "1") + " | " +
                                deadline.getDescription() + " | " +
                                deadline.getBy());
                        break;
                    case "E":
                        Events events = (Events) task;
                        fw.write(events.type() + " | " + (events.getStatusIcon().isBlank() ? "0" : "1") + " | " +
                                events.getDescription() + " | " +
                                events.getDate());
                        break;
                    case "T":
                        ToDo toDo = (ToDo) task;
                        fw.write(toDo.type() + " | " + (toDo.getStatusIcon().isBlank() ? "0" : "1") + " | " +
                                toDo.getDescription());
                        break;
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            if (!FILE.exists()) {
                return list;
            }
            FileReader fr = new FileReader(FILE);
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
                        String[] start_end = split[3].split("-");
                        Events tempEvent = new Events(
                                LocalDateTime.parse(start_end[0], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                                LocalDateTime.parse(start_end[1], DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
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
        }
        return list;
    }
}

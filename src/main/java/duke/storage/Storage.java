package duke.storage;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Storage {

    private ArrayList<Task> store = new ArrayList<Task>();
    private String filePath;
    private DateTimeFormatter storeTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm");
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public void saveData(String data) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
//            System.out.println(e.getMessage());
            System.out.println("Could not save data to storage!");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                // Process file line input
                String newLine = s.nextLine();
                String[] taskComponents = newLine.split(" \\| ");

                String type = taskComponents[0];
                String isCompleted = taskComponents[1];
                String desc = taskComponents[2];
                if (type.equals("T")) {

                    Task item = new ToDo(taskComponents[2]);
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                } else if (type.equals("D")) {

                    if (taskComponents.length < 3) {
                        throw new DukeException("Invalid Deadline Format!");
                    }
                    LocalDateTime buffer = LocalDateTime.parse(taskComponents[3], storeTimeFormatter);
                    Task item = new Deadline(desc, buffer.format(inputFormatter));
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                } else if (type.equals("E")) {

                    if (taskComponents.length < 3) {
                        throw new DukeException("Invalid Event Format!");
                    }
                    String[] timeComponents = taskComponents[3].split("-", 2);
                    if (taskComponents.length < 2) {
                        throw new DukeException("Invalid Event Format!");
                    }
                    LocalDateTime bufferStart = LocalDateTime.parse(timeComponents[0], storeTimeFormatter);
                    LocalDateTime bufferEnd = LocalDateTime.parse(timeComponents[1], storeTimeFormatter);
                    Task item = new Event(desc, bufferStart.format(inputFormatter), bufferEnd.format(inputFormatter));
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not Found!");
        }

        return store;
    }

}

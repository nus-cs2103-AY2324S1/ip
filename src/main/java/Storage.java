/**
 * deals with loading tasks from the file, and saving tasks in the file
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String path;
    public Storage(String path) {
        this.path = path;
    }
    public static LocalDateTime stringToDateTime(String str) throws DateTimeParseException {
        //check if dateTime has correct format: ie. YYYY-MM-DD 00:00
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.now();
        dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }
    public File createDataFile() {
        File dataFile = new File(this.path);
        try {
            Path dirPath = Paths.get("./data/");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file!!");
        }
        return dataFile;
    }

    public ArrayList<Task> loadTasks() throws IOException {
        File dataFile = new File(this.path);
        if (!dataFile.exists()) {
            dataFile = createDataFile();
        }
        Scanner sc = new Scanner(dataFile);
        ArrayList<Task> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            if (!task.isBlank()) {
                // | is a special symbol
                String[] taskDetails = task.split(" " + "\\|" + " ");
                String type = taskDetails[0];
                int status = Integer.parseInt(taskDetails[1]);
                String desc = taskDetails[2];
                switch (type) {
                case "T":
                    ToDo toDo = new ToDo(status, desc);
                    list.add(toDo);
                    break;
                case "D":
                    LocalDateTime date = stringToDateTime(taskDetails[3]);
                    Deadline deadline = new Deadline(status, desc, date);
                    list.add(deadline);
                    break;
                case "E":
                    LocalDateTime start = stringToDateTime(taskDetails[3]);
                    LocalDateTime end = stringToDateTime(taskDetails[4]);
                    Event event = new Event(status, desc, start, end);
                    list.add(event);
                    break;
                }
            }
        }
        sc.close();
        return list;
    }

    //this method should be called by the tasklist class ONLY!!! because we want to
    //keep the tasklist private
    public void updateFile(ArrayList<Task> list) throws IOException {
        try {
            //check if file exists, else create
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile = createDataFile();
            }

            //create a FileWriter object to write to file. Note that this overwrites the existing data!
            FileWriter file = new FileWriter("./data/duke.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskStr = task.convertTask();
                writer.write(taskStr);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

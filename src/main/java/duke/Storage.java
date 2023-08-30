package duke;

import duke.exceptions.DukeDatabaseException;
import duke.exceptions.DukeException;
import duke.task.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public Task[] loadData() throws DukeException {
        File db = new File(this.filePath.toUri());
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(db);
            while (fileReader.hasNextLine()) {
                taskList.add(readEntry(fileReader.nextLine()));
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        }
        return taskList.toArray(new Task[0]);
    }


    private void createDatabase() throws DukeException {
        File db = new File(this.filePath.toUri());
        File dir = new File(db.getParent());
        dir.mkdir();
        try {
            db.createNewFile();
        } catch (IOException e) {
            throw new DukeDatabaseException();
        }
    }


    private Task readEntry(String entry) throws DukeException {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        switch (fields[0]) {
            case "T":
                taskToAdd = new Todo(fields[2]);
                break;
            case "E":
                taskToAdd = new Event(fields[2],
                        LocalDateTime.parse(fields[3], formatter),
                        LocalDateTime.parse(fields[4], formatter));
                break;
            case "D":
                taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3], formatter));
                break;
            default:
                throw new DukeDatabaseException();
        }
        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.mark();
        }
        return taskToAdd;
    }

    public void saveFile(Tasklist todolist) throws IOException {
        if (!Files.exists(filePath.getParent())) {
            try {
                // Create the directory
                Files.createDirectories(filePath.getParent());
                System.out.println("Directory created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating directory.");
            }
        }
        if (!Files.exists(filePath)) {
            try {
                // Create the file
                Files.createFile(filePath);
                System.out.println("File created.");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error creating file.");
            }
        }
        FileWriter fw = new FileWriter(String.valueOf(filePath), false);
        BufferedWriter bw = new BufferedWriter(fw);
        todolist.savelist(bw);
        bw.close();
        fw.close();
    }

}
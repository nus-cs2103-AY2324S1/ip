package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File dataFile;
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.dataFile = new File(filepath);
        String directory = dataFile.getParent();
        findDirectory(directory);
        try {
            findFile(dataFile);
        } catch (IOException e) {
            throw new DukeException("Cannot find file.");
        }
    }

    public static void findDirectory(String directory) {
        File dataDirectory = new File(directory);
        if (dataDirectory.exists() && dataDirectory.isDirectory()) {
            System.out.println("Directory exists.");
        } else {
            if (dataDirectory.mkdir()) {
                System.out.println("Directory created.");
            }
        }
    }

    public static void findFile(File dataFile) throws IOException {
        if (dataFile.exists() && dataFile.isFile()) {
            System.out.println("Data file exists.");
        } else {
            if (dataFile.createNewFile()) {
                System.out.println("Data file created.");
            }
        }
    }

    public ArrayList<Task> load()  {
        try {
            ArrayList<Task> storedList = new ArrayList<>();
            Scanner scanner = new Scanner(this.dataFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] existingData = line.split("\\s*\\|\\s*");
                if (existingData[0].equals("T")) {
                    Todo todo = new Todo(existingData[2]);
                    if (existingData[1].equals("1")) todo.markAsDone();
                    storedList.add(todo);
                } else if (existingData[0].equals("D")) {
                    Deadline deadline = new Deadline(existingData[2], existingData[3]);
                    if (existingData[1].equals("1")) deadline.markAsDone();
                    storedList.add(deadline);
                } else {
                    Event event = new Event(existingData[2], existingData[3], existingData[4]);
                    if (existingData[1].equals("1")) event.markAsDone();
                    storedList.add(event);
                }
            }
            return storedList;
        } catch (FileNotFoundException e){
            throw new DukeException("Data File Not Found.");
        }
    }

    public String getFilePath() {
        return this.filepath;
    }

    public void writeTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw =  new FileWriter(this.filepath);
        for (Task task : tasks) {
            fw.write(task.writeToFile());
        }
        fw.close();
    }
}

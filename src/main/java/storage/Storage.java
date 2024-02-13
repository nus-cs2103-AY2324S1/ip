package storage;

import tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String FILE_PATH_NAME = "./data/chadBot.txt";

    /**
     * Creates a new directory for txt file if it does not exist
     */
    public void makeNewDirectory() {
        File newDir = new File("./data");
        if (newDir.mkdirs()) {
            System.out.println("Data directory has been created successfully!");
        } else {
            System.out.println("Data directory was not created! (There may already exists a data directory)");
        }
    }

    /**
     * Makes a new txt file if it does not exist
     */
    public void makeNewFile() {
        try {
            File newFile = new File(FILE_PATH_NAME);
            if (newFile.createNewFile()) {
                System.out.println("I have created this file for you: " + newFile.getName());
            } else {
                System.out.println("You already have the file... Stop wasting my time");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred when creating the file: " + e.getMessage());
        }
    }

    /**
     * Prints out tasks that are stored in txt file
     *
     * @throws FileNotFoundException if file is not found
     */
    public void printFile() throws FileNotFoundException {
        File chadFile = new File(FILE_PATH_NAME);
        Scanner s = new Scanner(chadFile);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    /**
     * Writes data from the ArrayList into the txt file
     *
     * @param t is the ArrayList that contains data of all the tasks
     */
    public void writeFile(ArrayList<Task> t) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH_NAME);
            for (Task task : t) {
                fw.write(task.taskToStringStore(task) + System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("There was an error writing the file: " + e);;
        }
    }

    /**
     * Takes a line of data from txt file and create a new Task from
     * the information
     *
     * @param data a line of data from txt file
     * @return a Task object with its respective data
     */
    public Task stringToTask(String data) {

        String[] parts = data.split("-", 0);

        String type = parts[0];
        String marked = parts[1].toString();

        boolean mark = marked.equals("X");

        switch (type) {
        case "T":
            Todo t = new Todo(parts[2]);
            t.isComplete = mark;

            System.out.println(t.toString());
            return t;

        case "D":
            Deadline d = new Deadline(parts[2], parts[3]);
            d.isComplete = mark;
            System.out.println(d.toString());
            return d;

        case "E":
            Event e = new Event(parts[2], parts[3], parts[4]);
            e.isComplete = mark;
            System.out.println(e.toString());
            return e;
        }
        return null;
    }

    /**
     * Loads up the txt file and adds the tasks to the ArrayList
     *
     * @param task an ArrayList for data to be stored in
     */
    public void loadFile(ArrayList<Task> task) {
        try {
            File chadFile = new File(FILE_PATH_NAME);
            Scanner s = new Scanner(chadFile);
            System.out.println("Here are the tasks from last time:");
            while (s.hasNext()) {
                String nextTask = s.nextLine();
                Task t = stringToTask(nextTask);
                if (t != null) {
                    task.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found... Unable to load tasks");
        }
    }
}

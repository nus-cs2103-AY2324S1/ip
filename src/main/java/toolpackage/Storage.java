package toolpackage;

import dukepackage.DukeException;

import taskpackage.Deadlines;
import taskpackage.Events;
import taskpackage.Task;
import taskpackage.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public boolean createStorage() throws DukeException {
        try {
            Files.createDirectories(Paths.get(this.file.getParent()));
            File newFile = new File(this.file.getPath());
            return newFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! There was an error saving data into storage.");
        }
    }

    public boolean saveStorage(TaskList listOfTasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            listOfTasks.saveStorage(fileWriter);
            fileWriter.close();
            return true;
        } catch (IOException | DukeException e) {
            throw new DukeException("☹ OOPS!!! There was an error saving data into storage.");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listOfTasks = new ArrayList<>();;
        Scanner data;
        try {
            data = new Scanner(this.file);
            String[] input;

            while (data.hasNextLine()) {
                input = data.nextLine().split("\\| ");
                if (input[0].equals("T ")) {
                    listOfTasks.add(new ToDos(input[2], input[1]));
                } else if (input[0].equals("D ")) {
                    listOfTasks.add(new Deadlines(input[2], input[3], input[1]));
                } else if (input[0].equals("E ")) {
                    listOfTasks.add(new Events(input[2], input[3], input[4], input[1]));
                }
            }
            
        } catch (FileNotFoundException | DukeException e) {
            throw new DukeException("☹ OOPS!!! There was an error loading data from the storage.");
        }
        data.close();
        return listOfTasks;
    }
}

package Utilities;

import Utilities.Exceptions.IncompleteDescriptionException;
import Utilities.Exceptions.InvalidTaskIndexException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void loadTaskFile(TaskList taskList) {
        try {
            if (!new File("data").isFile()) {
                new File("data").mkdir();
            }
            Scanner fileReader = new Scanner(this.file);
            while (fileReader.hasNext()) {
                taskList.addTask(Parser.dataToTask(fileReader.nextLine()));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No previous task list found");
        } catch (IncompleteDescriptionException ex) {
            System.out.println("Something went wrong!");
        }
    }

    public void writeTaskFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(this.file.getAbsoluteFile());
            String newTaskData = "";
            for (int i = 0; i < taskList.size(); i++) {
                newTaskData += taskList.getTask(i).compressData() + "\n";
            }
            writer.write(newTaskData);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Cannot update task file");
        } catch (InvalidTaskIndexException ex) {
            System.out.println("OOPS! Something went wrong!");
        }
    }

}

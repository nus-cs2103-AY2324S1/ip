package cheems;

import cheems.exceptions.InputOutputException;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Storage {
    private final File file;
    private static Storage instance;

    private Storage(String filePath) throws InputOutputException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            this.file = f;
        } catch (IOException e) {
            throw new InputOutputException("Failed to create a new data file!");
        }
    }

    public static Storage getInstance(String filepath) {
        if (instance != null) {
            return instance;
        } else {
            instance = new Storage(filepath);
            return instance;
        }
    }

    public static Storage getInstance() {
        return instance;
    }

    public void loadData() throws InputOutputException {
        try (Scanner s = new Scanner(this.file)){
            String input;
            while (s.hasNextLine()) {
                input = s.nextLine();
                String[] taskLine = input.split("\\|");
                Tasklist.addTask(taskLine);
            }
        } catch (FileNotFoundException e) {
            String msg = String.format("I cannot find %s! May be accidental deletion, " +
                    "try restart cheems.Cheems!", file.getName());
            throw new InputOutputException(msg);
        }
    }

    public void saveData(String taskLine) throws InputOutputException {
        try {
            FileWriter fw = new FileWriter(this.file.getPath(), true);
            fw.write(taskLine + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new InputOutputException("I cannot open the file for writing!");
        }
    }

    public void delete(int lineToDelete) throws InputOutputException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine != lineToDelete + 1) {
                    content += line + System.lineSeparator();
                }
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task deleted successfully from storage.");
        } catch (IOException e) {
            throw new InputOutputException("Sorry, I cannot update the text file!");
        }
    }

    public void mark(int lineToModify, boolean isDone) throws InputOutputException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String content = "";
            int currentLine = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineToModify + 1) {
                    if (!line.isEmpty()) {
                        line = (isDone ? "1" : "0")
                                        + line.substring(1);
                    }
                }
                content += line + System.lineSeparator();
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            writer.write(content);
            writer.close();

            System.out.println("Task udpated successfully from storage.");
        } catch (IOException e) {
            throw new InputOutputException("Sorry, I cannot update the text file!");
        }
    }
}
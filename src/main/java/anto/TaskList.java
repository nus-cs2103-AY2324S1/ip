package anto;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addToStorage(Task newTask) throws DukeException {
        this.taskList.add(newTask);

        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        try {
            FileWriter writer = new FileWriter(antoFile, true);
            if (newTask instanceof Todo) {
                writer.write(String.format("\nT | %s | %s", newTask.isDone ? "1" : "0", newTask.description));
            } else if (newTask instanceof Deadline) {
                writer.write(String.format("\nD | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description, (
                        (Deadline) newTask).by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            } else if (newTask instanceof Event) {
                writer.write(String.format("\nE | %s | %s | %s | %s", newTask.isDone ? "1" : "0", newTask.description, (
                        (Event) newTask).from.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), (
                        (Event) newTask).to.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! IOException");
        }
    }

    public ArrayList<Task> getStorage() {
        return this.taskList;
    }

    public int getLength() {
        return this.taskList.size();
    }

    public void markTaskAsDone(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 0 |", "| 1 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! IOException");
        }

        this.taskList.get(index).markAsDone();
    }

    public void unmarkTask(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line == index) {
                    writer.write(currLine.replace("| 1 |", "| 0 |")
                            + System.getProperty("line.separator"));
                } else {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! IOException");
        }

        this.taskList.get(index).unmark();
    }

    public Task deleteTask(int index) throws DukeException {
        String relativePath = "data/anto.txt";
        Path absolutePath = Paths.get(relativePath).toAbsolutePath();
        File antoFile = absolutePath.toFile();

        String tempRelativePath = "data/tempFile.txt";
        Path tempAbsolutePath = Paths.get(tempRelativePath).toAbsolutePath();
        File tempFile = tempAbsolutePath.toFile();

        try {
            Scanner sc = new Scanner(antoFile);
            FileWriter writer = new FileWriter(tempFile);

            int line = 0;

            while (sc.hasNextLine()) {
                String currLine = sc.nextLine();
                if (line != index) {
                    writer.write(currLine + System.getProperty("line.separator"));
                }
                line++;
            }

            writer.close();
            sc.close();
            tempFile.renameTo(antoFile);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! IOException");
        }
        return this.taskList.remove(index);
    }

}

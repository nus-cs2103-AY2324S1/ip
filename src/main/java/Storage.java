/** This file deals with all file reading from and writing to. */

import types.Deadlines;
import types.Party;
import types.Task;
import types.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Storage {
    protected static void addToList(Path path, String type, String desc) {
        String line = type + "," + 0 + "," + desc + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void addToList(Path path, String type, String desc, LocalDate deadline) {
        String line = type + "," + 0 + "," + desc + "," + deadline + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void addToList(Path path, String type, String desc, LocalDate from, LocalDate to) {
        String line = type + "," + 0 + "," + desc + "," + from + "," + to + "\n";
        try {
            Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    protected static ArrayList<Task> getLastList() {
        ArrayList<Task> finalList = new ArrayList<>();

        try {
            Path path = Paths.get("barbie.txt");

            if (!Files.exists(path)) {
                Files.createFile(path);
                System.out.println("[A new list created for current user]");
            } else {
                System.out.println("-------------------------------------------------");
                System.out.println("[A current list is being used for current user]");
                Files.readAllLines(path).forEach(x -> {

                    String[] taskParts = x.split(",");
                    String taskType = taskParts[0];
                    String taskStatus = taskParts[1];
                    String desc = taskParts[2];
                    Task task;

                    if (Objects.equals(taskType, "T")) {
                        task = new Todo(desc);
                    } else if (Objects.equals(taskType, "D")) {
                        task = new Deadlines(desc, LocalDate.parse(taskParts[3]));
                    } else if (Objects.equals(taskType, "P")) {
                        task = new Party(desc, LocalDate.parse(taskParts[3]), LocalDate.parse(taskParts[4]));
                    } else {
                        task = new Task(desc);
                    }
                    if (Integer.parseInt( taskStatus) == 1) { task.mark(); }
                    finalList.add(task);
                    System.out.println(finalList);
                });

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalList;

    }

    protected static void changeLineStatus(Path path, String status, int lineToChange) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToChange >= 0 && lineToChange < lines.size()) {
                String[] newContent = lines.get(lineToChange).split(",");
                newContent[1] = status;
                lines.set(lineToChange, Arrays.stream(newContent).reduce("", (x, acc) -> x + acc));
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to change.");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void deleteLine(Path path, int lineToDelete) {
        try {
            List<String> lines = Files.readAllLines(path);

            if (lineToDelete >= 0 && lineToDelete < lines.size()) {
                lines.remove(lineToDelete);
                Files.write(path, lines);
            } else {
                throw new IllegalArgumentException("Invalid line number to delete.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

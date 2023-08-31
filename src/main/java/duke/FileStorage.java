package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the class which handles reading and writing tasks to a file.
 */
public class FileStorage {
    private final String FILE_PATH;

    /**
     * Constructs a FileStorage object with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public FileStorage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Adds all items in the TaskList to the file in the specified file path.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void saveTasks (TaskList taskList) {
        File file = new File(FILE_PATH);
        file.mkdirs();

        if (file.exists()) {
            boolean isDeleteFileSuccess = file.delete();
            if (!isDeleteFileSuccess) {
                System.out.println("Failed to delete previous save file!");
            }
        }

        try {
            boolean isCreateNewFileSuccess = file.createNewFile();
            if (!isCreateNewFileSuccess) {
                System.out.println("Failed to create save file!");
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));
                for (Task task : taskList.getTask()) {
                    writer.append(task.toFileString()).append("\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to save file");
        }
    }

    /**
     * Returns true if the Task is completed, false otherwise.
     *
     * @param input The input representing the task's status.
     */
    public boolean isTaskDone(String input) {
        if (input.equals("T")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a LocalDateTime object by converting the String provided.
     *
     * @param date The date string to be converted.
     * @return A LocalDateTime object representing the date and time.
     */
    public LocalDateTime setDate(String date) {
        try {
            String[] dateParts = date.split("-");
            String[] timePart = dateParts[2].split(" ");

            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(timePart[0]);
            int timeHour = Integer.parseInt(timePart[1].substring(0, 2));
            int timeMin = Integer.parseInt(timePart[1].substring(2, 4));

            return LocalDateTime.of(year, month, day, timeHour, timeMin);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format for the date and/or time");
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong format for the date and/or time");
            return null;
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @return An ArrayList of Task objects creating from the file.
     * @throws FileLoadException If there is any error loading tasks from the file.
     */
    public ArrayList<Task> loadFiles() throws FileLoadException {
        File file = new File(FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            ArrayList<Task> tempList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(" \\| ");
                if (split.length == 1) {
                    throw new FileCorruptedException("Invalid record saved");
                }
                boolean isDone = isTaskDone(split[1]);
                switch (split[0]) {
                case "T":
                    if (split.length > 3) {
                        throw new FileCorruptedException("Invalid record saved");
                    }
                    ToDos temp = new ToDos(split[2].trim(), isDone);
                    tempList.add(temp);
                    break;
                case "D":
                    if (split.length > 4) {
                        throw new FileCorruptedException("Invalid record saved");
                    }
                    tempList.add(new Deadline(split[2], setDate(split[3]), isDone));
                    break;
                case "E":
                    if (split.length > 5) {
                        throw new FileCorruptedException("Invalid record saved");
                    }
                    tempList.add(new Event(split[2], setDate(split[3]), setDate(split[4]), isDone));
                    break;
                default:
                    System.out.println("Not a valid input: " + line);
                    break;
                }
            }
            System.out.println(tempList);
            return tempList;
        } catch (FileNotFoundException e) {
            System.out.println("There are no existing tasks, please use the commands to add new tasks!");
            File newFile = new File(FILE_PATH);
            newFile.mkdirs();
            return new ArrayList<>();
        }
    }
}
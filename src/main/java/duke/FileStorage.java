package duke;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class FileStorage {
    private final String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    // To save all the tasks into a file before the chatbot closes
    public void saveTasks (TaskList taskList) {
        File file = new File(filePath);
        file.mkdirs();

        if (file.exists()) {
            boolean deleteFileSuccess = file.delete();
            if (!deleteFileSuccess) {
                System.out.println("Failed to delete previous save file!");
            }
        }

        try {
            boolean createNewFileSuccess = file.createNewFile();
            if (!createNewFileSuccess) {
                System.out.println("Failed to create save file!");
            } else {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
                for (Task task : taskList.getTask()) {
                    writer.append(task.toFileString()).append("\n");
                }
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Unable to save file");
        }
    }

    // To read whether task is done
    public boolean isTaskDone(String input) {
        if (input.equals("T")) {
            return true;
        } else {
            return false;
        }
    }

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

    // To load all tasks in a file when the chatbot starts up
    public ArrayList<Task> loadFiles() throws FileLoadException {
        File file = new File(filePath);
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
            File newFile = new File(filePath);
            newFile.mkdirs();
            return new ArrayList<>();
        }
    }
}

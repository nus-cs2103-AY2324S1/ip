import java.io.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import task.*;

public class SaveData {
    private static final String SAVE_FILE_LOCATION = "./SaveFile.txt";
    private static final String DISCRIMINATOR = " || ";
    public static void saveData(Task taskData) {
        File f = new File(SAVE_FILE_LOCATION);
        try {
            if (f.createNewFile()) {
                FileWriter fw = new FileWriter(SAVE_FILE_LOCATION, true);
                char taskType = taskData.getType().getCharRep();
                Optional<String> timeDescriptor = Optional.ofNullable(taskData.getTimeDescriptor());
                SaveLine saveLine = new SaveLine(taskType, Boolean.compare(taskData.getStatus(), false), taskData.getName(), timeDescriptor);
                fw.write(saveLine + "\n");
            }
        } catch (IOException e) {
            System.out.println(String.format("CRITICAL ERROR!!! An unknown IO exception occurred: %s", e.getMessage()));
        }
    }

    public static ArrayList<Task> loadData() {
        File f = new File(SAVE_FILE_LOCATION);
        ArrayList<Task> tasks = new ArrayList();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                Optional<Task> optionalTask = parseSave(sc.nextLine());
                if (optionalTask.isEmpty()) {
                    System.out.println("Task is corrupted, it has been removed from the list");
                } else {
                    optionalTask.ifPresent(x -> tasks.add(x));
                }
            }
        } catch (IOException e) {
            System.out.println("Save File not found. Starting new instance...");
            return null;
        }
        return tasks;
    }

    public static void deleteData(int lineNum) {
        try {
            File currSave = new File(SAVE_FILE_LOCATION);
            File tempSave = new File("./tempSave.txt");

            BufferedReader reader = new BufferedReader(new FileReader(currSave));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempSave));
            int i = 1;
            String currLine;
            while((currLine = reader.readLine()) != null) {
                if (i == lineNum) {
                    continue;
                }
                writer.write(currLine + "\n");
            }

            writer.close();
            reader.close();

            if (currSave.delete()) {
                if (!tempSave.renameTo(currSave)) {
                    throw new IOException("Could not rename temporary save");
                }
            } else {
                throw new IOException("Could not delete current save");
            }

        } catch (IOException e) {
            System.out.println(String.format("CRITICAL ERROR!!! An unknown IO exception occurred: %s", e.getMessage()));
        }
    }

    private static Optional<Task> parseSave(String saveLine) {
        String[] splitArray = saveLine.split(DISCRIMINATOR);
        Optional<Task> ret = Optional.empty();
        switch (splitArray[0]) {
            case "T": {
                boolean status = Integer.parseInt(splitArray[1]) >= 1;
                ret = Optional.of(new Todo(splitArray[2], status));
            }
            case "D": {
                boolean status = Integer.parseInt(splitArray[1]) >= 1;
                ret = Optional.of(new Deadline(splitArray[2], status, splitArray[3]));
            }
            case "E": {
                boolean status = Integer.parseInt(splitArray[1]) >= 1;
                String[] timeDescriptor = splitArray[3].split(" to ");
                ret = Optional.of(new Event(splitArray[2], status, timeDescriptor[0], timeDescriptor[1]));
            }
        }
        return ret;
    }
    private static class SaveLine {
        private char taskType;
        private int status;
        private String name;
        private Optional<String> timeDescriptor;

        public SaveLine(char taskType, int status, String name, Optional<String> timeDescriptor) {
            this.taskType = taskType;
            this.status = status;
            this.name = name;
            this.timeDescriptor = timeDescriptor;
        }

        @Override
        public String toString() {
            if (timeDescriptor.isEmpty()) {
                return taskType + DISCRIMINATOR + status + DISCRIMINATOR + name;
            }
            return taskType + DISCRIMINATOR + status + DISCRIMINATOR + name + DISCRIMINATOR + timeDescriptor;
        }
    }
}

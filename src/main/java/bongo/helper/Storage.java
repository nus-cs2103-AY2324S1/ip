package bongo.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import bongo.task.Deadline;
import bongo.task.Event;
import bongo.task.Task;
import bongo.task.Todo;

/**
 * A class for a Storage.
 */
public class Storage {

    /**
     * Different types of actions we can take to modify the text file.
     */
    public enum FileAction {
        MARK_TASK,
        UNMARK_TASK,
        DELETE_TASK
    }

    private final String pathname;

    /**
     * A constructor for a Storage, with a pathname.
     *
     * @param pathname Pathname of file.
     */
    public Storage(String pathname) {
        this.pathname = pathname;
    }

    /**
     * Loads tasks into the TaskList, if there is an existing text file found.
     * If no file is found, throws a FileNotFound Exception.
     *
     * @return An ArrayList of preloaded tasks.
     * @throws FileNotFoundException If file is not found.
     * @throws BongoException        If datetime string has an invalid format.
     */
    public ArrayList<Task> load() throws FileNotFoundException, BongoException {
        File file = new File(this.pathname);
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        ArrayList<Integer> expiredTaskNumbers = new ArrayList<>();
        int numOfTasks = 0;
        while (fileScanner.hasNextLine()) {
            numOfTasks++;
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            boolean isTaskMarkedDone = arr[1].equals("1");
            LocalDateTime dateTime;
            switch (arr[0]) {
            case "T":
                loadedTasks.add(new Todo(arr[2], isTaskMarkedDone));
                break;
            case "D":
                dateTime = DateHelper.convertStringToDateTime(arr[3]);
                if (dateTime.isBefore(LocalDateTime.now())) {
                    expiredTaskNumbers.add(numOfTasks);
                } else {
                    loadedTasks.add(new Deadline(arr[2], isTaskMarkedDone, DateHelper.convertStringToDateTime(arr[3])));
                }
                break;
            case "E":
                LocalDateTime currentDateTime = LocalDateTime.now();
                LocalDateTime fromDateTime = DateHelper.convertStringToDateTime(arr[3]);
                LocalDateTime toDateTime = DateHelper.convertStringToDateTime(arr[4]);
                if (currentDateTime.isBefore(fromDateTime) || currentDateTime.isAfter(toDateTime)) {
                    expiredTaskNumbers.add(numOfTasks);
                } else {
                    loadedTasks.add(new Event(arr[2], isTaskMarkedDone, DateHelper.convertStringToDateTime(arr[3]),
                        DateHelper.convertStringToDateTime(arr[4])));
                }
                break;
            default:
                throw new BongoException("Error reading the text file.");
            }
        }
        deleteExpiredTasks(expiredTaskNumbers);
        fileScanner.close();
        return loadedTasks;
    }

    /**
     * Check if the data directory and text file is present.
     * If either is not present, create it.
     */
    public void checkIfFilesExist() {
        File file = new File(this.pathname);
        String directoryPath = file.getParent();
        File directory = new File(directoryPath);
        boolean isDirectoryInvalid = !directory.exists();
        boolean isFileInvalid = !file.exists();
        if (isDirectoryInvalid) {
            boolean isDirectoryCreated = directory.mkdirs();
            if (isDirectoryCreated) {
                System.out.println("Directory created: " + directoryPath);
            }
        }
        if (isFileInvalid) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + this.pathname);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    /**
     * Appends task on a new line in the text file.
     *
     * @param newTask New Task to add.
     */
    public void add(Task newTask) {
        try {
            File file = new File(this.pathname);
            FileWriter fw = new FileWriter(this.pathname, true);
            String newLine = newTask.generateStringForTextFile();
            boolean isBeyondFirstLine = file.length() != 0;
            if (isBeyondFirstLine) {
                fw.write(String.format("\n%s", newLine));
            } else {
                fw.write(newLine);
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        } catch (BongoException e) {
            throw new RuntimeException("There was an error adding the task.");
        }
    }

    /**
     * Deletes expired tasks from the text file.
     *
     * @param expiredTaskNumbers List of expired tasks.
     */
    public void deleteExpiredTasks(ArrayList<Integer> expiredTaskNumbers) {
        try {
            File file = new File(this.pathname);
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = fileReader.readLine()) != null) {
                if (expiredTaskNumbers.contains(currentLine)) {
                    currentLine++;
                    continue;
                } else {
                    stringBuilder.append(line).append("\n");
                }
                currentLine++;
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringBuilder.toString().trim());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Problem editing the file.");
            e.printStackTrace();
        }
    }

    /**
     * Edit the text that corresponds to a certain task in the text file.
     *
     * @param action Type of FileAction.
     * @param taskNumber Number of task to delete.
     */
    public void edit(FileAction action, int taskNumber) {
        try {
            File file = new File(this.pathname);
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = fileReader.readLine()) != null) {
                if (currentLine == taskNumber) {
                    String[] lineWordsArr = line.split("\\|");
                    for (int i = 0; i < lineWordsArr.length; i++) {
                        lineWordsArr[i] = lineWordsArr[i].trim();
                    }
                    switch (action) {
                    case MARK_TASK:
                        lineWordsArr[1] = "1";
                        stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                        break;
                    case UNMARK_TASK:
                        lineWordsArr[1] = "0";
                        stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                        break;
                    case DELETE_TASK:
                        currentLine++;
                        continue;
                    default:
                        throw new BongoException("Error modifying the text file.");
                    }
                } else {
                    stringBuilder.append(line).append("\n");
                }
                currentLine++;
            }
            fileReader.close();

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringBuilder.toString().trim());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Problem editing the file.");
            e.printStackTrace();
        }
    }
}

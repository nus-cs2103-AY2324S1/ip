package bongo.helper;

import bongo.task.Deadline;
import bongo.task.Event;
import bongo.task.Task;
import bongo.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public enum FileAction {
        MARK_TASK,
        UNMARK_TASK,
        DELETE_TASK
    }
    String pathname;

    /**
     * A constructor for a Storage, with a pathname.
     * @param pathname
     */
    public Storage(String pathname) {
        this.pathname = pathname;
    }

    /**
     * Loads tasks into the TaskList, if there is an existing text file found.
     * If no file is found, throws a FileNotFound Exception.
     * @return An ArrayList<Task> of preloaded tasks.
     * @throws FileNotFoundException
     * @throws BongoException
     */
    public ArrayList<Task> load() throws FileNotFoundException, BongoException {
        File file = new File(this.pathname);
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> loadedTasks = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            boolean isTaskMarkedDone = arr[1].equals("1");
            switch (arr[0]) {
                case "T":
                    loadedTasks.add(new Todo(arr[2], isTaskMarkedDone));
                    break;
                case "D":
                    loadedTasks.add(new Deadline(arr[2], isTaskMarkedDone, DateHelper.formatDateTime(arr[3]))) ;
                    break;
                case "E":
                    loadedTasks.add(new Event(arr[2], isTaskMarkedDone, DateHelper.formatDateTime(arr[3]), DateHelper.formatDateTime(arr[4])));
                    break;
            }
        }
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
        if (!directory.exists()) {
            boolean isDirectoryCreated = directory.mkdirs();
            if (isDirectoryCreated) {
                System.out.println("Directory created: " + directoryPath);
            }
        }
        if (!file.exists()) {
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
     * @param newTask
     */
    public void add(Task newTask) {
        try {
            File file = new File(this.pathname);
            FileWriter fw = new FileWriter(this.pathname, true);
            String newLine = newTask.generateStringForTextFile();
            if (file.length() != 0) {
                fw.write(String.format("\n%s", newLine));
            } else {
                fw.write(newLine);
            }

            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    /**
     * Edit the text that corresponds to a certain task in the text file.
     * @param action
     * @param taskNumber
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

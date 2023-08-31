package bongo.helper;

import bongo.command.Command;
import bongo.task.Deadline;
import bongo.task.Event;
import bongo.task.Task;
import bongo.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    public enum FileAction {
        MARK_TASK,
        UNMARK_TASK,
        DELETE_TASK
    }
    String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
    }

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
            System.out.println(Arrays.toString(arr));
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

    public void add(Task newTask) {
        try {
            File file = new File(this.pathname);
            FileWriter fw = new FileWriter(this.pathname, true);
            String newLine = newTask.generateStringForTextFile();
//            String isTaskMarkedDone = newTask.isDone ? "1" : "0";
//            if (newTask instanceof bongo.task.Todo) {
//                newLine = String.join(" | ", "T", isTaskMarkedDone, newTask.description);
//            } else if (newTask instanceof bongo.task.Deadline) {
//                bongo.task.Deadline newDeadline = (bongo.task.Deadline) newTask;
//                newLine = String.join(" | ", "D", isTaskMarkedDone, newDeadline.description, bongo.helper.DateHelper.formatter.format(newDeadline.deadline));
//            } else if (newTask instanceof bongo.task.Event) {
//                bongo.task.Event newEvent = (bongo.task.Event) newTask;
//                newLine = String.join(" | ", "E", isTaskMarkedDone, newEvent.description, bongo.helper.DateHelper.formatter.format(newEvent.from), bongo.helper.DateHelper.formatter.format(newEvent.to));
//            }
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

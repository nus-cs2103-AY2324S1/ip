package ari;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Storage class that deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {

    private String filePath;

    /**
     * Instantiates a Storage object. Creates directory and file if they aren't created yet
     * @param filePath a file path in String
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        assert filePath != null : "File path cannot be empty";

        String directoryPath = this.filePath.substring(0, this.filePath.indexOf("/"));
        String fileName = this.filePath.substring(this.filePath.indexOf("/"));

        try {
            File dir = new File(directoryPath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(directoryPath, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the file and reads it to store it into the ArrayList of tasks.
     * @return an ArrayList of task
     * @throws FileNotFoundException if the scanner object cannot find the file
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> lst = new ArrayList<>();

        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNext()) {
                lst.add(textToTask(scanner.nextLine()));
            }
        } catch (AriException e) {
            System.out.println(e.getMessage());
        }

        return lst;
    }

    /**
     * Converts text in the String to Task objects
     * @param text a line of text in text file
     * @return a Task object
     * @throws AriException if the text is not formatted correctly.
     */
    public static Task textToTask(String text) throws AriException {
        String[] arr = text.split(" [|] ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HHmm");

        String identifier = arr[0];
        String status = arr[1];
        boolean isDone;
        Task task = null;

        if (status.equals("[ ]")) {
            isDone = false;
        } else {
            isDone = true;
        }

        switch (identifier) {
        case "T":
            String todo = arr[2];
            task = new ToDo(todo, isDone);
            break;
        case "D":
            String deadline = arr[2];

            String by = arr[3];
            LocalDateTime dateTime = LocalDateTime.parse(by, formatter);

            task = new Deadline(deadline, dateTime, isDone);
            break;
        case "E":
            String event = arr[2];

            String from = arr[3].substring(0, arr[3].indexOf("-"));
            LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);

            String to = arr[3].substring(arr[3].indexOf("-") + 1);
            LocalTime dateTimeTo = LocalTime.parse(to, formatter2);

            task = new Event(event, dateTimeFrom, dateTimeTo, isDone);
            break;
        }
        if (task == null) {
            throw new AriException("Incorrect format of the file :(");
        } else {
            return task;
        }
    }

    /**
     * Clears the file.
     * @throws IOException if File cannot be opened / located
     */
    public void clearFile() throws IOException {
        File file = new File(this.filePath);
        file.delete();
        file.createNewFile();
    }

    /**
     * Appends a line of text to an existing file.
     * @param textToAppend text that will be appended.
     * @throws IOException if File cannot be opened / located
     */
    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Updates the file after a task has been marked as done.
     * @param lineNumber an integer representing which task has been marked as done
     * @throws IOException if File cannot be opened / located
     */
    public void updateFileAfterMark(int lineNumber) throws IOException {
        String updatedContent = "";
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[X]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }

    /**
     * Updates the file after a task has been unmarked from done.
     * @param lineNumber an integer representing which task has been unmarked
     * @throws IOException if File cannot be opened / located
     */
    public void updateFileAfterUnmark(int lineNumber) throws IOException {
        String updatedContent = "";
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                String updatedLine = scanner.nextLine();
                updatedContent += updatedLine.substring(0, updatedLine.indexOf("|") + 2) +
                        "[ ]" + updatedLine.substring(7) + System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }

    /**
     * Updates a file after a task has been deleted from TaskList
     * @param lineNumber an integer representing which task has been deleted
     * @throws IOException if File cannot be opened / located
     */
    public void updateFileAfterDelete(int lineNumber) throws IOException {
        String updatedContent = "";
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                scanner.nextLine();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }

    /**
     * Updates a file after a task's due date has been postponed.
     * @param postponedTask deadline task that has been postponed
     * @param lineNumber an integer representing which task has been postponed
     * @param newDeadline new deadline in String
     * @throws IOException if File cannot be opened / located
     */
    public void updateFileAfterPostpone(Task postponedTask, int lineNumber, String newDeadline) throws IOException {
        String updatedContent = "";
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                scanner.nextLine();
                updatedContent += "D | " + postponedTask.getStatusIcon() + " | " +
                        postponedTask.taskDescription + " | " + newDeadline +
                        System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }

    /**
     * Updates a file after an event has been rescheduled.
     * @param rescheduledTask event task that has been rescheduled
     * @param lineNumber an integer representing which task has been postponed
     * @param newFrom new date and time in which the event starts in String
     * @param newTo new time in which the event ends in String
     * @throws IOException if File cannot be opened / located
     */
    public void updateFileAfterReschedule(Task rescheduledTask, int lineNumber, String newFrom, String newTo)
            throws IOException{
        String updatedContent = "";
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        int i = 1;
        while (scanner.hasNext()) {
            if (i == lineNumber) {
                scanner.nextLine();
                updatedContent += "E | " + rescheduledTask.getStatusIcon() + " | " +
                        rescheduledTask.taskDescription + " | " + newFrom + "-" + newTo +
                        System.lineSeparator();
                i++;
                continue;
            }
            updatedContent += scanner.nextLine() + System.lineSeparator();
            i++;
        }
        clearFile();
        appendToFile(updatedContent);
    }
}

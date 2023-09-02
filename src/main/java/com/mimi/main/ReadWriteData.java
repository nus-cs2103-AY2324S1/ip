package com.mimi.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import com.mimi.tasks.Deadline;
import com.mimi.tasks.Event;
import com.mimi.tasks.Task;
import com.mimi.tasks.Todo;

/**
 * A class that reads from and writes data to the hard drive.
 * @author Yuheng
 */
public class ReadWriteData {
    private File dataFile;
    private Storage previousCommands;
    private Ui ui;

    /**
     * creates a new instance of ReadWriteData
     * @param datafile The file to be read/written into
     * @param previousCommands an instance of Storage that keeps track of what tasks have been recorded
     * @param ui the Ui responsible for displaying messages
     */
    public ReadWriteData(File datafile, Storage previousCommands, Ui ui) {
        this.dataFile = datafile;
        this.previousCommands = previousCommands;
        this.ui = ui;
    }

    /**
     * Initialises the storage by reading data previously stored in the hard drive.
     */
    public void initialise() {
        try {
            Scanner fileReader = new Scanner(dataFile);

            while (fileReader.hasNextLine()) {
                String task = fileReader.nextLine();
                if (task.equals("")) {
                    continue;
                }

                int i = task.indexOf('|');

                String taskCode = task.substring(0, i);
                String taskWithoutCode = task.substring(i + 1);
                int j = taskWithoutCode.indexOf('|');
                String isCompletedTask = taskWithoutCode.substring(0, j);

                String taskDescription = taskWithoutCode.substring(j + 1);

                switch(taskCode) {
                case "T":
                    initialiseTodo(isCompletedTask.equals("X"), taskDescription);
                    break;

                case "D":
                    initialiseDeadline(isCompletedTask.equals("X"), taskDescription);
                    break;

                case "E":
                    initialiseEvent(isCompletedTask.equals("X"), taskDescription);
                    break;

                default:
                }

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            ui.unableToLoadFromMemory();
        }

    }


    private void initialiseTodo(boolean isCompletedTask, String taskDescription) {

        Task newTask = new Todo(taskDescription);
        previousCommands.addWithoutPrinting(newTask);

        if (isCompletedTask) {
            newTask.toggleDone();
        }
    }

    private void initialiseDeadline(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');

        String taskName = taskDescription.substring(0, i - 1);
        String deadline = taskDescription.substring(i + 5, j);

        try {
            Task newTask = new Deadline(taskName, LocalDateTime.parse(deadline, Parser.FORMATTER));

            previousCommands.addWithoutPrinting(newTask);

            if (isCompletedTask) {
                newTask.toggleDone();
            }
        } catch (DateTimeParseException e) {
            ui.unableToLoadFromMemory();
        }
    }

    private void initialiseEvent(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');
        int k = taskDescription.indexOf("to:");

        String taskName = taskDescription.substring(0, i - 1);
        String startTime = taskDescription.substring(i + 7, k - 1);
        String endTime = taskDescription.substring(k + 4, j);

        try {
            Task newTask = new Event(taskName,
                    LocalDateTime.parse(startTime, Parser.FORMATTER),
                    LocalDateTime.parse(endTime, Parser.FORMATTER)
            );

            previousCommands.addWithoutPrinting(newTask);

            if (isCompletedTask) {
                newTask.toggleDone();
            }

        } catch (DateTimeParseException e) {
            ui.unableToLoadFromMemory();
        }

    }

    /**
     * Writes a given task into the hard drive.
     * @param task a Task instance that to be written into the hard drive
     */
    public void write(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(this.dataFile, true);
            String message = String.format("%s|%s|%s",
                    task.eventCode(), task.getStatusIcon(), task.writeFormat());

            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(message);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occured when writing to file: " + e.getMessage());
        }
    }

    /**
     * Updates the File to reflect any changes in the status of the tasks.
     */
    public void updateFile() {
        this.dataFile.delete();

        try {
            if (this.dataFile.createNewFile()) {
                this.previousCommands.updateAll(this);
            }
        } catch (IOException e) {
            ui.errorWhenUpdatingFile();
        }
    }
}

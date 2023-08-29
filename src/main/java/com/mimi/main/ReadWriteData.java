package com.mimi.main;

import com.mimi.commands.Command;
import com.mimi.tasks.Deadline;
import com.mimi.tasks.Event;
import com.mimi.tasks.Task;
import com.mimi.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReadWriteData {
    File dataFile;
    Storage previousCommands;

    Ui ui;


    public ReadWriteData(File datafile, Storage previousCommands, Ui ui) {
        this.dataFile = datafile;
        this.previousCommands = previousCommands;
        this.ui = ui;
    }



    public void onInitialise() {
        try {
            Scanner fileReader = new Scanner(dataFile);

            while (fileReader.hasNextLine()) {
                String task = fileReader.nextLine();
                if (task.equals("")) {
                    continue;
                }

                int i = task.indexOf('|');

                String taskCode = task.substring(0, i);
                String taskWithoutCode = task.substring(i+1);
                int j = taskWithoutCode.indexOf('|');
                String isCompletedTask = taskWithoutCode.substring(0, j);

                String taskDescription = taskWithoutCode.substring(j+1);

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
                }

            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            ui.unableToLoadFromMemory();
        }

    }


    public void initialiseTodo(boolean isCompletedTask, String taskDescription) {

        Task newTask = new Todo(taskDescription);
        previousCommands.addWithoutPrinting(newTask);

        if (isCompletedTask) {
            newTask.toggleDone();
        }
    }

    public void initialiseDeadline(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');

        String taskName = taskDescription.substring(0, i-1);
        String deadline = taskDescription.substring(i+5, j);

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

    public void initialiseEvent(boolean isCompletedTask, String taskDescription) {
        int i = taskDescription.indexOf('(');
        int j = taskDescription.indexOf(')');
        int k = taskDescription.indexOf("to:");

        String taskName = taskDescription.substring(0, i-1);
        String startTime = taskDescription.substring(i+7, k-1);
        String endTime = taskDescription.substring(k+4, j);

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

    public void write(Task task) {
        try {
            FileWriter fileWriter= new FileWriter(this.dataFile, true);
            String message = String.format("%s|%s|%s",
                    task.eventCode(), task.getStatusIcon(), task.writeFormat());

            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(message);

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error occured when writing to file: " + e.getMessage());
        }
    }

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

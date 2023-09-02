package storage;

import parsers.DateTimeParser;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    public static ArrayList<Task> readTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File myObj = new File("./src/main/storage/writtenStorage.txt");
            myObj.getParentFile().mkdirs();
            Scanner myReader = new Scanner(myObj);
            int taskIndex = 0;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                char taskType = data.charAt(1);
                boolean isComplete = data.charAt(4) == 'X';
                String taskDescription = data.substring(7);
                String[] arrTaskSplit;
                String constructorInput;

                switch (taskType) {
                case 'T':
                    Todo newTodo = new Todo(taskDescription);
                    tasks.add(newTodo);
                    break;
                case 'D':
                    arrTaskSplit = taskDescription.split(" DATETIME ");
                    LocalDateTime dateTime = DateTimeParser.readTasksParser(arrTaskSplit[1]);
                    Deadline newDeadline = new Deadline(dateTime, arrTaskSplit[0]);
                    tasks.add(newDeadline);
                    break;
                case 'E':
                    arrTaskSplit = taskDescription.split(" DATETIME ");
                    String[] dateTimeSplit = arrTaskSplit[1].split(" DATETIME_SPLIT ");
                    LocalDateTime startDateTime = DateTimeParser.readTasksParser(dateTimeSplit[0]);
                    LocalDateTime endDateTime = DateTimeParser.readTasksParser(dateTimeSplit[1]);
                    Event newEvent = new Event(startDateTime, endDateTime, arrTaskSplit[0]);
                    tasks.add(newEvent);
                    break;
                }
                if (isComplete) {
                    Task currentTask = tasks.get(taskIndex);
                    currentTask.markDone();
                }
                taskIndex += 1;
            }
            myReader.close();

        } catch (FileNotFoundException ignored) {
        }
        return tasks;
    }

    public static void storeTasks(ArrayList<Task> tasks) {
        try {
            File myObj = new File("./src/main/storage/writtenStorage.txt");
            myObj.getParentFile().mkdirs();
            myObj.createNewFile();

        } catch (IOException e) {

            Ui.print("Error creating file");
        }

        try {
            String toWrite = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                String currentItem = currentTask.toStringWithDateTime();
                toWrite += currentItem +"\n";
            }
            FileWriter myWriter = new FileWriter("./src/main/storage/writtenStorage.txt");
            myWriter.write(toWrite);
            myWriter.close();
        } catch (IOException e) {
            Ui.print("Error writing file");
        }
    }
}

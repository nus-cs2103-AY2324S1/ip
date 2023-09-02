package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : taskList.getTaskList()) {
                fw.write(t.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occured while writing the tasks into file: " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Task> readTasks(File file) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                char taskType = input.charAt(1);
                boolean isDone = input.charAt(4) == 'X';
                String description = input.split("] ")[1];
                if (taskType == 'T') {
                    Todo t = new Todo(description);
                    if (isDone) {
                        t.markDone();
                    }
                    tasks.add(t);
                } else if (taskType == 'D') {
                    int byIndex = description.indexOf("(by: ");
                    int lastByIndex = description.indexOf(")");
                    String deadlineDescription = description.substring(0, byIndex - 1);
                    String by = description.substring(byIndex + 5, lastByIndex);
                    String[] dateTimeArr = by.split(" ");
                    LocalDate byDate = LocalDate.parse(dateTimeArr[0]);
                    LocalTime byTime = LocalTime.parse(dateTimeArr[1]);
                    Deadline d = new Deadline(deadlineDescription, byDate, byTime);
                    if (isDone) {
                        d.markDone();
                    }
                    tasks.add(d);
                } else if (taskType == 'E') {
                    int fromIndex = description.indexOf("(from: ");
                    int toIndex = description.indexOf("to: ");
                    int lastToIndex = description.indexOf(")");
                    String eventDescription = description.substring(0, fromIndex - 1);
                    String start = description.substring(fromIndex + 7, toIndex - 1);
                    String end = description.substring(toIndex + 4, lastToIndex);
                    String[] startArr = start.split(" ");
                    LocalDate startDate = LocalDate.parse(startArr[0]);
                    LocalTime startTime = LocalTime.parse(startArr[1]);
                    String[] endArr = end.split(" ");
                    LocalDate endDate = LocalDate.parse(endArr[0]);
                    LocalTime endTime = LocalTime.parse(endArr[1]);
                    Event e = new Event(eventDescription, startDate, startTime, endDate, endTime);
                    if (isDone) {
                        e.markDone();
                    }
                    tasks.add(e);
                    // System.out.println("event added");
                } else {
                    System.out.println("An error has occurred: Unknown task type");
                }
            }
            sc.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
            System.out.println(exception);
        } finally {
            return tasks;
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String[] filePathArray = filePath.split("/");
            String fileDirectory = filePathArray[0] + "/" + filePathArray[1];
            File directory = new File(fileDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File taskFile = new File(filePath);
            if (taskFile.createNewFile()) {
                // creates new file
            } else {
                tasks = readTasks(taskFile);
            }
        } catch (IOException e) {
            System.out.println("An error occured when initialising tasks: " + e);
            e.printStackTrace();
        } finally {
            return tasks;
        }
    }
}

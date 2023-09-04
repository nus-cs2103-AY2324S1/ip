package noel.helper;

import noel.exceptions.NoelException;
import noel.tasks.Deadlines;
import noel.tasks.Events;
import noel.tasks.Task;
import noel.tasks.ToDos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tasklist {

    private final ArrayList<Task> taskList;
    static String addedMessageStart = "Got it. I've added this task:";

    public Tasklist(String listOfTasks) throws NoelException {
        if (listOfTasks == null) {
            throw new NoelException("No content in file!");
        } else {
            taskList = new ArrayList<>();
            updateTaskList(listOfTasks);
        }
    }

    public Tasklist() {
        taskList = new ArrayList<>();
    }

    public boolean checkFull() {
        int maxSize = 100;
        return taskList.size() == maxSize;
    }

    public boolean checkEmpty() {
        return taskList.size() == 0;
    }

    public void updateTaskList(String content) {
        String[] listOfStrings = content.split("\n");
        System.out.println(Arrays.toString(listOfStrings));
        for (String line : listOfStrings) {
            String[] values = line.split(" \\| ");

            if (values.length != 1) {

                if (Objects.equals(values[0], "E")) {
                    if (values.length == 4) {

                        // (from: 02 02 2023 06:00 to: 03 02 2023 06:00)
                        String[] dates = values[3].split("to:");
                        String[] startDateArray = dates[0].split("\\(from: ")[1].split(" ");
                        String startDate = startDateArray[0] + " " + startDateArray[1];
                        String[] endDateArray = dates[1].split("\\)")[0].split(" ");
                        String endDate = endDateArray[1] + " " + endDateArray[2];

                        if (dates.length == 2) {
                            addEvent(values[2], startDate, endDate);
                        } else {
                            System.out.println("Invalid line! Skipping line...");
                        }
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }

                } else if (Objects.equals(values[0], "T")) {
                    if (values.length == 3) {
                        addToDo(values[2]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }
                } else if (Objects.equals(values[0], "D")) {
                    if (values.length == 4) {
                        addDeadline(values[2], values[3]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }
                } else {
                    System.out.println("Invalid line! Skipping line...");
                }

                if (Objects.equals(values[1], "1")) {
                    this.taskList.get(this.taskList.size() - 1).markAsDone();
                }
            } else {
                System.out.println("No value in file");
            }
        }
    }

    public LocalDate dateFormat(String endDate) {
        LocalDate date;
        System.out.println(endDate);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            date = LocalDate.parse(endDate, formatter1);
            return date;
        } catch (DateTimeParseException e1) {
            try {
                date = LocalDate.parse(endDate, formatter2);
                return date;
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid date format");
            }
        }
        return null;
    }

    public LocalTime timeFormat(String date) {
        LocalTime time = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            time = LocalTime.parse(date, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                time = LocalTime.parse(date, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid Time Format");
            }
        }
        return time;
    }

    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public void addEvent(String task, String startDate, String endDate) {

        LocalDate startDateFormat = dateFormat(startDate);
        LocalDate endDateFormat = dateFormat(endDate);
        LocalTime startTimeFormat = timeFormat(startDate);
        LocalTime endTimeFormat = timeFormat(endDate);

        if (startDateFormat == null || endDateFormat == null) {
            System.out.println("Invalid format for start/end date!");
            return;
        }

        if (startTimeFormat == null || endTimeFormat == null) {
            System.out.println("Invalid format for start/end time!");
            return;
        }

        Task taskToAdd = new Events(task, startDateFormat, startTimeFormat, endDateFormat, endTimeFormat);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
    }

    public void addDeadline(String task, String endDate) {

        LocalDate date = dateFormat(endDate);
        if (date == null) {
            System.out.println("Invalid date!");
            return;
        }

        LocalTime time = timeFormat(endDate);
        if (time == null) {
            System.out.println("Invalid date!");
            return;
        }

        Task taskToAdd = new Deadlines(task, date, time);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
    }

    public void addToDo(String task) {

        Task taskToAdd = new ToDos(task);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
    }

    public void printTaskList(){
        if (checkEmpty()) {
            System.out.println("List is empty!");
        } else {
            String filler = "____________________________________________________________";
            System.out.println(filler);
            int maxLength = taskList.size();
            for (int i = 0; i < maxLength; i++) {
                System.out.println(i + 1 + ". " + taskList.get(i));
            }
            System.out.println(filler);
        }
    }

    public List<String> getTaskAsList() {
        List<String> linesToAppend = new ArrayList<>();
        for (Task t:taskList) {
            linesToAppend.add(t.toFileString());
        }
        return linesToAppend.subList(0, linesToAppend.size());
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void unMark(int index) {
        taskList.get(index).unMark();
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }
}
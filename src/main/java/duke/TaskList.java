package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TaskList {
    ArrayList<Task> list;
    File startFile;

    public TaskList(File startFile) {
        this.startFile = startFile;
        this.list = new ArrayList<Task>();
        Scanner scan = new Scanner(System.in);
        try {
            Scanner fileScan = new Scanner(startFile);
            while (fileScan.hasNext()) {
                String taskString = fileScan.nextLine();
                if (taskString.charAt(0) == 'T') {
                    Task task = new ToDo(taskString.substring(8));
                    if (taskString.charAt(4) == '1') {
                        task.setMark(true);
                    }
                    list.add(task);
                } else if (taskString.charAt(0) == 'D') {
                    String[] details = taskString.substring(8).split(Pattern.quote(" | "));
                    LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                    Task task = new Deadline(details[0], dateTime);
                    if (taskString.charAt(4) == '1') {
                        task.setMark(true);
                    }
                    list.add(task);
                } else if (taskString.charAt(0) == 'E') {
                    String[] details = taskString.substring(8).split(Pattern.quote(" | "));
                    String[] duration = details[1].split(" -> ");
                    LocalDateTime fromDateTime = LocalDateTime.parse(duration[0], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                    LocalDateTime toDateTime = LocalDateTime.parse(duration[1], DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
                    Task task = new Event(details[0], fromDateTime, toDateTime);
                    if (taskString.charAt(4) == '1') {
                        task.setMark(true);
                    }
                    list.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public String showList() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            Task task = list.get(i);
            listOfTasks.append("     ").append(i + 1).append(".").append(task.toString()).append("\n");
        }
        if (list.size() != 0) {
            Task task = list.get(list.size() - 1);
            listOfTasks.append("     ").append(list.size()).append(".").append(task.toString());
        }
        return listOfTasks.toString();
    }

    public String fileList() {
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            Task task = list.get(i);
            listOfTasks.append(task.writeToFile()).append("\r\n");
        }
        if (list.size() != 0) {
            Task task = list.get(list.size() - 1);
            listOfTasks.append(task.writeToFile());
        }
        return listOfTasks.toString();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int taskNumber) {
        return list.remove(taskNumber);
    }

    public Task get(int taskNumber) {
        return list.get(taskNumber);
    }

    public int size() {
        return list.size();
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;

public class Duke {
    public static final String PARTITION = "------------------------------------------------------------";
    public static final String NAME = "Duke Max";
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String FILEPATH = "./data/tasks.txt";

    public static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) throws DukeException{   
        load(FILEPATH);
        greet();     
        String input = SCANNER.next();
        while(!input.equals("bye")) {
            try {
                switch(input){
                    case "list":
                        print();
                        break;
                    case "print":
                        String time = SCANNER.nextLine();
                        if (time.isBlank() || time.isEmpty()) {
                            print();
                            break;
                        }

                        time = formatTime(time);
                        if (time == null) {
                            String messageSorry = "Sorry, you have entered the wrong format for time.";
                            String messageFormat = "Please enter in the format of yyyy-MM-dd HH:mm:ss or yyyy-MM-dd";
                            throw new DukeException(messageSorry + "\n" + messageFormat);
                        }

                        print(time);
                        break;
                    case "mark":
                        int markItem = SCANNER.nextInt();
                        taskList.get(markItem - 1).markItem(true);
                        save(FILEPATH);
                        break;
                    case "unmark":
                        int unmarkItem = SCANNER.nextInt();
                        taskList.get(unmarkItem - 1).markItem(false);
                        save(FILEPATH);
                        break;
                    case "delete":
                        int deleteItem = SCANNER.nextInt();
                        if (deleteItem >= taskList.size()) {
                            throw new DukeException("Sorry, seleted event is out of range.");
                        }
                        delete(deleteItem);
                        save(FILEPATH);
                        break;
                    case "todo":
                        String toDoEvent = SCANNER.nextLine();
                        if (toDoEvent.isBlank() || toDoEvent.isEmpty()) {
                            throw new DukeException("Sorry, the todo task must have a title.");
                        }
                        add(new ToDo(toDoEvent));
                        save(FILEPATH);
                        break;
                    case "event":
                        String eventCommand = SCANNER.nextLine();
                        //when user didn't provide title and start & end time
                        if (eventCommand.isBlank() || eventCommand.isEmpty() 
                        || !eventCommand.contains(" /from ") || !eventCommand.contains(" /to ")) {
                            throw new DukeException("Sorry, this event must have a title, start time, and end time.");
                        }
                        String[] event = new String[3];
                        try {
                            event[0] = eventCommand.substring(1, eventCommand.indexOf(" /"));
                            event[1] = eventCommand.substring(eventCommand.indexOf("/from") + 6, 
                            eventCommand.indexOf(" /to"));
                            event[2] = eventCommand.substring(eventCommand.indexOf("/to") + 4);
                        }
                        catch (StringIndexOutOfBoundsException e) {
                            System.out.println("Sorry, this event must have a title, start time, and end time.");
                        }
                        //when user provide empty title
                        if (event[0].isBlank() || event[0].isEmpty()) {
                             throw new DukeException("Sorry, the event must have a title.");
                        }
                        //when user didn't provide the starting time
                        if (event[1].isBlank() || event[1].isEmpty()) {
                            throw new DukeException("Sorry, the event must have a starting time");
                        }
                        //when user didn't provide the end time
                        if (event[2].isBlank() || event[2].isEmpty()) {
                            throw new DukeException("Sorry, the event must have an end time");
                        }
                        //when user provide wrong time format
                        String startTime = formatTime(event[1]);
                        String endTime = formatTime(event[2]);
                        if (startTime.isBlank() || startTime.isEmpty() || endTime.isBlank() || endTime.isEmpty()) {
                            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
                        }
                        add(new Event(event[0], startTime, endTime));
                        save(FILEPATH);
                        break;
                    case "deadline":
                        String ddlCommand = SCANNER.nextLine();
                        //when user didn't provide title & ddl
                        if (ddlCommand.isBlank() || ddlCommand.isEmpty() || !ddlCommand.contains(" /by ")) {
                            throw new DukeException("Sorry, the deadline task must have a title and a deadline.");
                        }
                        String[] ddl = new String[2];
                        try {
                            ddl = ddlCommand.split(" /by ");
                        }
                        catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Sorry, this deadline task must have a title and a deadline.");
                        }
                        //when user provide empty title
                        if (ddl[0].isBlank() || ddl[0].isEmpty()) {
                             throw new DukeException("Sorry, the deadline event must have a title.");
                        }
                        //when user didn't provide ddl
                        if (ddl[1].isBlank() || ddl[1].isEmpty()) {
                            throw new DukeException("Sorry, the deadline event must have a dealine");
                        }
                        String ddlTime = formatTime(ddl[1]);
                        //when user provide wrong time format
                        if (ddlTime.isBlank() || ddlTime.isEmpty()) {
                            throw new DukeException("Please enter the time with this format: yyyy-MM-dd HH:mm:ss");
                        }
                        add(new Deadline(ddl[0].substring(1), ddlTime));
                        save(FILEPATH);
                        break;
                    case "clear":
                        clear();
                        break;
                    default:
                        throw new DukeException("Sorry, I don't recognize this input. Please try again.");
                }
            } 

            catch (DukeException e) {
                System.out.println(e.getMessage());
                //help();
            }

            finally {
                System.out.println(PARTITION);
                input = SCANNER.next();
            }
        }
            
        exit();
    }

    public static void greet() {
        System.out.println(PARTITION);
        System.out.println("Hello! I'm " + NAME + ".");
        System.out.println("What can I do for you?");
        System.out.println(PARTITION);
    }

    public static void add(Task input) {
        taskList.add(input);
        System.out.println("Got it! This task has been added: ");
        System.out.println(input.getStatus());
        System.out.println("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    public static void delete(int num) {
        System.out.println("I've removed this task:");
        taskList.get(num-1).getStatus();
        taskList.remove(num - 1);
        System.out.println("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    public static void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
        System.out.println("Current # of " + plural(taskList.size(), "task") + ": " + taskList.size());
    }

    public static void print(String time) {
        int count = 0;
        for(Task task: taskList) {
            if(task.getTime().contains(time)) {
                System.out.println(task.getStatus());
                count += 1;
            }
        }
        System.out.println("Current # of " + plural(count, "task") + ": " + taskList.size());
    }

    public static void clear() {
        taskList.clear();
        System.out.println("Okay, I have cleared all tasks.");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(PARTITION);
    }

    private static String plural(int count, String word) {
        return (count <= 1) ? word : (word + "s");
    }

    private static void save(String path) {
        try (PrintWriter printwriter = new PrintWriter(new FileWriter(path))) {
            for (Task task : taskList) {
                printwriter.write(task.toFileString() +"\n");
            }
        } catch (IOException e) {
            System.out.println("There is an error saving this file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> load(String path) throws DukeException{
        handleMissing(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String text = reader.readLine();
            while (text != null) {
                Task task = Task.convertStringToTask(text);
                taskList.add(task);
                text = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("There is an error loading tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    private static void handleMissing(String testPath) {
        try{
            //if directory or path doesn't exist
            Path directoryPath = Paths.get(".", "data"); 
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            Path path = directoryPath.resolve("tasks.txt"); 
            if (!Files.exists(path)) {
                Files.createFile(path); 
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There is an error loading file: " + e.getMessage());
        }
    }

    private static String formatTime(String input) {
        try {
            // when input have a date and a time
            // convert string to LocalDateTime then convert to another format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } catch (DateTimeParseException notDateTime) {
            try {
                // input is only a date
                LocalDate date = LocalDate.parse(input);
                return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException notDate) {
                try {
                    // input is only a time
                    LocalTime time = LocalTime.parse(input);
                    return time.format(DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException nullError) {
                    return null;
                }
            }
        }
    }
}

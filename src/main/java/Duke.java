import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String PARTITION = "------------------------------------------------------------";
    public static final String NAME = "Duke Max";
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final String FILEPATH = "./data/tasks.txt";

    public static ArrayList<Task> taskList = new ArrayList<Task>();

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
                        delete(deleteItem);
                        save(FILEPATH);
                        break;
                    case "todo":
                        add(new ToDo(SCANNER.nextLine()));
                        save(FILEPATH);
                        break;
                    case "event":
                        String eventCommand = SCANNER.nextLine();
                        String[] event = new String[3];
                        event[0] = eventCommand.substring(1, eventCommand.indexOf(" /"));
                        event[1] = eventCommand.substring(eventCommand.indexOf("/from") + 6, 
                        eventCommand.indexOf(" /to"));
                        event[2] = eventCommand.substring(eventCommand.indexOf("/to") + 4);
                        add(new Event(event[0], event[1], event[2]));
                        save(FILEPATH);
                        break;
                    case "deadline":
                        String ddlCommand = SCANNER.nextLine();
                        String[] ddl = ddlCommand.split(" /by ");
                        add(new Deadline(ddl[0].substring(1), ddl[1]));
                        save(FILEPATH);
                        break;
                    default:
                        add(new Task(input));
                        save(FILEPATH);
                }
            } 

            catch (DukeException e) {
                System.out.println(e.getMessage());
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
        System.out.println("Current # of task: " + taskList.size());
    }

    public static void delete(int num) {
        System.out.println("I've removed this task:");
        taskList.get(num-1).getStatus();
        taskList.remove(num - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(PARTITION);
    }
}

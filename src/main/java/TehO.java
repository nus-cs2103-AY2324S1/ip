import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import static java.lang.Integer.parseInt;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class TehO {
    static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm TehO \nWhat can I do for you?");
        loadTasks();
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userCommand.equals("list")) {
                listTask(taskList);
            } else if (userCommand.startsWith("mark")) {
                markTask(userCommand);
            } else if (userCommand.startsWith("unmark")) {
                unmarkTask(userCommand);
            } else if (userCommand.startsWith("todo")) {
                addToDo(userCommand);
            } else if (userCommand.startsWith("deadline")) {
                addDeadline(userCommand);
            } else if (userCommand.startsWith("event")) {
                addEvent(userCommand);
            } else if (userCommand.startsWith("delete")) {
                delete(userCommand);
            } else {
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }
        sc.close();
        saveTasks();
    }

    public static void markTask(String userCommand) {
        //note that split returns a String[]
        //parseInt returns the integer value which is represented by the argument
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        task.markAsDone(taskNumber);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }
    public static void unmarkTask(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        task.markAsNotDone(taskNumber);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void addToDo(String userCommand) {
        try {
            if (userCommand.length() < 5) {
                throw new EmptyToDoDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String command = userCommand.substring(5); //"todo " 5 index
            Task task = new ToDo(command);
            taskList.add(task);
            System.out.println(task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyToDoDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void addDeadline(String userCommand) {
        try {
            if (userCommand.length() < 9) {
                throw new EmptyDeadlineDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String commandWithDate = userCommand.substring(9); //"deadline " 9 index
            String cDeadline = commandWithDate.split(" /by ")[0]; //just description
            LocalDate byDate = LocalDate.parse(commandWithDate.split(" /by ")[1]); //just byDate
            Task task = new Deadline(cDeadline, byDate);
            taskList.add(task);
            System.out.println(task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyDeadlineDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void addEvent(String userCommand) {
        try {
            if (userCommand.length() < 6) {
                throw new EmptyEventDescriptionException();
            }
            System.out.println("Got it. I've added this task:");
            String commandWithDate = userCommand.substring(6); //"todo " 6 index
            String cEvent = commandWithDate.split(" /from ")[0]; //just description
            String dates = commandWithDate.split(" /from ")[1]; //bothDates
            LocalDate fromDate = LocalDate.parse(dates.split(" /to ")[0]); //just fromDate
            LocalDate toDate = LocalDate.parse(dates.split(" /to ")[1]); //just toDate
            Task task = new Event(cEvent, fromDate, toDate);
            taskList.add(task);
            System.out.println(task.toString());
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        } catch (EmptyEventDescriptionException e) {
            System.out.println(e.toString());
        }
    }

    public static void listTask(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
    }

    public static void delete(String userCommand) {
        int taskNumber = parseInt(userCommand.split(" ")[1]) - 1; //counting from 0
        Task task = taskList.get(taskNumber);
        taskList.remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }


    public static void saveTasks() {
        try {
            //Solution below inspired by https://www.geeksforgeeks.org/io-bufferedwriter-class-methods-java/
            FileWriter file = new FileWriter("/Users/loomeilinzann/ip/text-ui-test/data/teho.txt");
            BufferedWriter writer = new BufferedWriter(file);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.fileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("☹ OOPS!!! Error when saving task(s).");
        }
    }

    public static void loadTasks() {
        try {
            File loadedFile = new File("/Users/loomeilinzann/ip/text-ui-test/data/teho.txt");
            Scanner sc = new Scanner(loadedFile);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                readLine(nextLine);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("☹ OOPS!!! Error when loading task(s).");
        }
    }

    public static void readLine(String nextLine) {
        Task taskToAddFirst;
        //use (\\|) to treat \ as a legit character
        // or else it will get split by any spaces
        String[] sections = nextLine.split("\\|");
        int taskNumber = taskList.size(); //counting from 0
        if (sections[0].equals("T")) {
            taskToAddFirst = new ToDo(sections[2]);
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        } else if (sections[0].equals("D")) {
            taskToAddFirst = new Deadline(sections[2], LocalDate.parse(sections[3]));
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        } else if (sections[0].equals("E")) {
            taskToAddFirst = new Event(sections[2], LocalDate.parse(sections[3]), LocalDate.parse(sections[4]));
            taskList.add(taskToAddFirst);
            if (sections[1].equals("1")) {
                taskToAddFirst.markAsDone(taskNumber);
            }
        }
    }
}


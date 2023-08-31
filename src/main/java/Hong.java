import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import parsers.DateTimeParser;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hong {
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    private static final String line = "---------------------------------------------------------";
    public static void main(String[] args) {
        sayHello();
        readTasks();
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myObj.close();
                break;
            } else if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.startsWith("mark")) {
                handleMark(userInput);
                storeTasks();
            } else if (userInput.startsWith("deadline")) {
                createDeadline(userInput);
                storeTasks();
            } else if (userInput.startsWith("event")) {
                createEvent(userInput);
                storeTasks();
            } else if (userInput.startsWith("todo")) {
                createTodo(userInput);
                storeTasks();
            } else if (userInput.startsWith("delete")) {
                deleteTask(userInput);
                storeTasks();
            } else {
                Task currentTask = new Task(userInput);
                String currentMessage = line + "\n" + "added: " + userInput + "\n" + line;
                System.out.println(currentMessage);
                tasks.add(currentTask);
                storeTasks();
            }
        }
        sayBye();
    }

    private static void readTasks() {
        try {
            File myObj = new File("./src/main/storage/writtenStorage.txt");
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
    }
    private static void storeTasks() {
        try {
            File myObj = new File("./src/main/storage/writtenStorage.txt");
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file");
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
            System.out.println("Error writing file");
        }
    }
    private static void sayHello() {
        String firstMessage = line + "\nHello! I'm Hong\nWhat can I do for you?\n" + line;
        System.out.println(firstMessage);
    }

    private static void sayBye() {
        String exitMessage = line + "\n" + "Bye. Hope to see you again soon!\n" + line;
        System.out.println(exitMessage);
    }

    private static void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentItem = (i + 1) + "." + currentTask.toString();
            System.out.println(currentItem);
        }
        System.out.println(line);
    }

    private static void handleMark(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {
            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            currentTask.markDone();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            String currentItem = currentTask.toString();
            System.out.println(currentItem);
            System.out.println(line);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }

    }

    private static void createDeadline(String userInput) {
        String newInput = userInput.substring(9);
        String[] arrInput = newInput.split("/by ");
        if (arrInput.length != 2) {
            System.out.println("Error! There is an issue with the format of your message. ");
        } else {
            LocalDateTime dateTime = DateTimeParser.parseDateTime(arrInput[1]);
            Deadline newDeadline = new Deadline(dateTime, arrInput[0]);
            tasks.add(newDeadline);
            addedMessage(newDeadline.toString());
        }

    }

    private static void createTodo(String userInput) {
        String newInput = userInput.substring(5);
        Todo newTodo = new Todo(newInput);
        tasks.add(newTodo);
        addedMessage(newTodo.toString());
    }

    private static void createEvent(String userInput) {
        String newInput = userInput.substring(6);
        String[] arrInput = newInput.split("/from ");
        String eventDetails = arrInput[0];
        String[] fromToArr = arrInput[1].split(" /to ");
        Event newEvent = new Event(DateTimeParser.parseDateTime(fromToArr[0]),
                DateTimeParser.parseDateTime(fromToArr[1]), eventDetails);
        tasks.add(newEvent);
        addedMessage(newEvent.toString());
    }

    private static void addedMessage(String taskMessage) {
        String message = line + "\nGot it. I've added this task:\n" + taskMessage + "\nNow you have " + tasks.size() +
                " tasks in the list.\n" + line;
        System.out.println(message);
    }

    private static void deleteTask(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {

            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            tasks.remove(Integer.valueOf(arrInput[1]) - 1);
            System.out.println(line);
            System.out.println("Noted. I've removed this task:");
            String currentItem = currentTask.toString();
            System.out.println(currentItem);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }
    }

}

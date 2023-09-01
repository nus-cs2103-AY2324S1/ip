import java.util.ArrayList;
import java.util.Scanner;

import exceptions.DukeException;
import parsers.DateTimeParser;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

import java.time.LocalDateTime;


public class Hong {
    private static ArrayList<Task> tasks;

    //private static final String LINE = "---------------------------------------------------------";
    public static void main(String[] args) {
        Ui.sayHello();
        TaskList taskList = new TaskList();
        Scanner myObj = new Scanner(System.in);
        while (true) {
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                myObj.close();
                break;
            } else if (userInput.equals("list")) {
                taskList.printTasks();
            } else if (userInput.startsWith("mark")) {
                taskList.handleMark(userInput);
                taskList.storeTasks();
            } else if (userInput.startsWith("deadline")) {
                taskList.createDeadline(userInput);
                taskList.storeTasks();
            } else if (userInput.startsWith("event")) {
                taskList.createEvent(userInput);
                taskList.storeTasks();
            } else if (userInput.startsWith("todo")) {
                taskList.createTodo(userInput);
                taskList.storeTasks();
            } else if (userInput.startsWith("delete")) {
                taskList.deleteTask(userInput);
                taskList.storeTasks();
            } else {
                Task currentTask = new Task(userInput);
                Ui.printLine();
                String currentMessage = "added: " + userInput + "\n";
                Ui.print(currentMessage);
                Ui.printLine();
                tasks.add(currentTask);
                taskList.storeTasks();
            }
        }
        Ui.sayBye();
    }

//    private static void readTasks() {
//        try {
//            File myObj = new File("./src/main/storage/writtenStorage.txt");
//            Scanner myReader = new Scanner(myObj);
//            int taskIndex = 0;
//            while (myReader.hasNextLine()) {
//                String data = myReader.nextLine();
//                char taskType = data.charAt(1);
//                boolean isComplete = data.charAt(4) == 'X';
//                String taskDescription = data.substring(7);
//                String[] arrTaskSplit;
//                String constructorInput;
//
//                switch (taskType) {
//                case 'T':
//                    Todo newTodo = new Todo(taskDescription);
//                    tasks.add(newTodo);
//                    break;
//                case 'D':
//                    arrTaskSplit = taskDescription.split(" DATETIME ");
//                    LocalDateTime dateTime = DateTimeParser.readTasksParser(arrTaskSplit[1]);
//                    Deadline newDeadline = new Deadline(dateTime, arrTaskSplit[0]);
//                    tasks.add(newDeadline);
//                    break;
//                case 'E':
//                    arrTaskSplit = taskDescription.split(" DATETIME ");
//                    String[] dateTimeSplit = arrTaskSplit[1].split(" DATETIME_SPLIT ");
//                    LocalDateTime startDateTime = DateTimeParser.readTasksParser(dateTimeSplit[0]);
//                    LocalDateTime endDateTime = DateTimeParser.readTasksParser(dateTimeSplit[1]);
//                    Event newEvent = new Event(startDateTime, endDateTime, arrTaskSplit[0]);
//                    tasks.add(newEvent);
//                    break;
//                }
//                if (isComplete) {
//                    Task currentTask = tasks.get(taskIndex);
//                    currentTask.markDone();
//                }
//                taskIndex += 1;
//            }
//            myReader.close();
//        } catch (FileNotFoundException ignored) {
//        }
//    }
//    private static void storeTasks() {
//        try {
//            File myObj = new File("./src/main/storage/writtenStorage.txt");
//            myObj.createNewFile();
//        } catch (IOException e) {
//            Ui.print("Error creating file");
//        }
//
//        try {
//            String toWrite = "";
//            for (int i = 0; i < tasks.size(); i++) {
//                Task currentTask = tasks.get(i);
//                String currentItem = currentTask.toStringWithDateTime();
//                toWrite += currentItem +"\n";
//            }
//            FileWriter myWriter = new FileWriter("./src/main/storage/writtenStorage.txt");
//            myWriter.write(toWrite);
//            myWriter.close();
//        } catch (IOException e) {
//            Ui.print("Error writing file");
//        }
//    }
//    private static void sayHello() {
//        String firstMessage = line + "\nHello! I'm Hong\nWhat can I do for you?\n" + line;
//        System.out.println(firstMessage);
//    }
//
//    private static void sayBye() {
//        String exitMessage = line + "\n" + "Bye. Hope to see you again soon!\n" + line;
//        System.out.println(exitMessage);
//    }

//    private static void printTasks() {
//        Ui.print(LINE);
//        Ui.print("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            Task currentTask = tasks.get(i);
//            String currentItem = (i + 1) + "." + currentTask.toString();
//            Ui.print(currentItem);
//        }
//        Ui.print(LINE);
//    }
//
//    private static void handleMark(String userInput) {
//        String[] arrInput = userInput.split(" ");
//        try {
//            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
//            currentTask.markDone();
//            Ui.print(LINE);
//            Ui.print("Nice! I've marked this task as done:");
//            String currentItem = currentTask.toString();
//            Ui.print(currentItem);
//            Ui.print(LINE);
//        } catch (IndexOutOfBoundsException err){
//            throw new exceptions.DukeException("This tasks.Task index does not exist!", err);
//        }
//
//    }
//
//    private static void createDeadline(String userInput) {
//        String newInput = userInput.substring(9);
//        String[] arrInput = newInput.split("/by ");
//        if (arrInput.length != 2) {
//            Ui.print("Error! There is an issue with the format of your message. ");
//        } else {
//            LocalDateTime dateTime = DateTimeParser.parseDateTime(arrInput[1]);
//            Deadline newDeadline = new Deadline(dateTime, arrInput[0]);
//            tasks.add(newDeadline);
//            addedMessage(newDeadline.toString());
//        }
//
//    }
//
//    private static void createTodo(String userInput) {
//        String newInput = userInput.substring(5);
//        Todo newTodo = new Todo(newInput);
//        tasks.add(newTodo);
//        addedMessage(newTodo.toString());
//    }
//
//    private static void createEvent(String userInput) {
//        String newInput = userInput.substring(6);
//        String[] arrInput = newInput.split("/from ");
//        String eventDetails = arrInput[0];
//        String[] fromToArr = arrInput[1].split(" /to ");
//        Event newEvent = new Event(DateTimeParser.parseDateTime(fromToArr[0]),
//                DateTimeParser.parseDateTime(fromToArr[1]), eventDetails);
//        tasks.add(newEvent);
//        addedMessage(newEvent.toString());
//    }
//
//    private static void addedMessage(String taskMessage) {
//        String message = LINE + "\nGot it. I've added this task:\n" + taskMessage + "\nNow you have " + tasks.size() +
//                " tasks in the list.\n" + LINE;
//        Ui.print(message);
//    }
//
//    private static void deleteTask(String userInput) {
//        String[] arrInput = userInput.split(" ");
//        try {
//
//            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
//            tasks.remove(Integer.valueOf(arrInput[1]) - 1);
//            Ui.print(LINE);
//            Ui.print("Noted. I've removed this task:");
//            String currentItem = currentTask.toString();
//            Ui.print(currentItem);
//            Ui.print("Now you have " + tasks.size() + " tasks in the list.");
//            Ui.print(LINE);
//        } catch (IndexOutOfBoundsException err){
//            throw new exceptions.DukeException("This tasks.Task index does not exist!", err);
//        }
//    }

}

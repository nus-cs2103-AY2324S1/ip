import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDateTime;

public class Blip {


    // File path for tasks
    private static String FILE_PATH = "./data/blip.txt";

    private BlipUI ui;
    private TaskList tasks;
    private BlipStorage storage;

    public Blip(String filePath) {
        this.ui = new BlipUI();
        this.storage = new BlipStorage(filePath);
              try {
        tasks = storage.loadFile();
        } catch (BlipException e) {
            ui.showLoadingErr();
            tasks = new TaskList();
        }
    }

    public void run() {
        //Implement whole while loop here
        Scanner scanIn = new Scanner(System.in);
        ui.showIntro();
        String userInput;
        userInput = scanIn.nextLine();

        while (true) {

        }

    }


//    private static void saveToFile(ArrayList<Task> tasks) throws IOException {
//        try {
//            File file = new File(FILE_PATH);
//            File fileDirectory = file.getParentFile();
//
//            if (!fileDirectory.exists()) {
//                fileDirectory.mkdirs();
//            }
//            FileWriter fileWriter = new FileWriter(file);
//
//            for (Task task : tasks) {
//                fileWriter.write(task.saveToFileString() + "\n");
//            }
//            fileWriter.close();
//        } catch (IOException e2) {
//            System.out.println("Error saving to file: " + e2.getMessage());
//        }
//    }
//
//
//    private static ArrayList<Task> loadFile()  {
//        ArrayList<Task> tasks = new ArrayList<>();
//        try {
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            return tasks;
//        }
//        FileReader fileReader = new FileReader(file);
//        BufferedReader finalReader = new BufferedReader(fileReader);
//        String lineToRead;
//        while ((lineToRead = finalReader.readLine()) != null) {
//            Task task = Task.loadTaskFromFile(lineToRead);
//            tasks.add(task);
//        }
//        finalReader.close();
//        } catch (IOException e2) {
//            System.out.println("Error reading line: " + e2.getMessage());
//        }
//        return tasks;
//    }

    public static void main(String[] args) {
        new Blip(FILE_PATH).run();
    }


//
//
//
//
//
//        // Intro message by Blip.
//        String intro = "Hello! I'm Blip\n"
//                + "What can I do for you?";
//
//        // Outro message by Blip.
//        String outro = "Bye. Hope to see you again soon!";
//
//        // Constant end trigger word to end the chat with outro.
//        String END_TRIGGER = "bye";
//
//        // Constant list trigger word to display back stored text.
//        String LIST_TRIGGER = "list";
//
//        // Constant mark trigger word to update status of task.
//        String MARK_TRIGGER = "mark";
//
//        // Constant unmark trigger word to update status of task.
//        String UNMARK_TRIGGER = "unmark";
//
//        // Constant deadline trigger word to create new task with deadline.
//        String DEADLINE_TRIGGER = "deadline";
//
//        // Constant event trigger word to create new task with deadline.
//        String EVENT_TRIGGER = "event";
//
//        // Constant to do trigger word to create new task with deadline.
//        String TODO_TRIGGER = "to do";
//
//        // Constant delete trigger to delete task from list.
//        String DELETE_TRIGGER = "delete";
//
//        // Constant empty string for exception handling.
//        String EMPTY = "";
//
//
//
//
//
//
//        ArrayList<Task> tasks = loadFile();
//
//        String userInput;
//        Scanner scanIn = new Scanner(System.in);
//        userInput = scanIn.nextLine();
//
//
//
//        while (!userInput.equals(END_TRIGGER)) {
//            try {
//                if (!userInput.equals(LIST_TRIGGER)) {
//
//                    // To mark a task.
//                    if (userInput.split(" ")[0].equals(MARK_TRIGGER)) {
//
//                        // Missing task number to mark.
//                        if (userInput.split("\\s+", 2).length < 2) {
//                            throw new EmptyTaskNumberException("!!! Missing Task Number Error !!!\n");
//                        }
//                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
//
//                        // Task number does not exist.
//                        if (taskNum >= tasks.size()) {
//                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
//                        }
//
//                        Task taskToUpdate = tasks.get(taskNum);
//                        taskToUpdate.markStatus();
//                        saveToFile(tasks);
//                        System.out.println("Nice! I've marked this task as done:\n" + taskToUpdate.toString());
//                        userInput = scanIn.nextLine();
//
//                    // To unmark a task.
//                    } else if (userInput.split(" ")[0].equals(UNMARK_TRIGGER)) {
//
//                        // Missing task number to unmark.
//                        if (userInput.split("\\s+", 2).length < 2) {
//                            throw new EmptyTaskNumberException("!!! Missing Task Number error !!!");
//                        }
//                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
//
//                        // Task number does not exist.
//                        if (taskNum >= tasks.size()) {
//                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
//                        }
//                        Task taskToUpdate = tasks.get(taskNum);
//                        taskToUpdate.unmarkStatus();
//                        saveToFile(tasks);
//                        System.out.println("Ok, I've marked this task as not done yet:\n" + taskToUpdate.toString());
//                        userInput = scanIn.nextLine();
//
//
//                    // To delete a task
//                    } else if (userInput.split(" ")[0].equals(DELETE_TRIGGER)) {
//
//                        // Missing task number to delete.
//                        if (userInput.split("\\s+", 2).length < 2) {
//                            throw new EmptyTaskNumberException("!!! Missing Task Number error !!!");
//                        }
//                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
//
//                        // Task number does not exist.
//                        if (taskNum >= tasks.size()) {
//                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
//                        }
//                        Task taskToDelete = tasks.get(taskNum);
//                        System.out.println("Ok, I've removed this task:\n" + taskToDelete.toString());
//                        tasks.remove(taskNum);
//                        saveToFile(tasks);
//                        userInput = scanIn.nextLine();
//
//
//                    // For a deadline task
//                    } else if (userInput.split(" ")[0].equals(DEADLINE_TRIGGER)) {
//                        String[] test = userInput.split("\\s+", 2);
//                        // Missing Deadline Description.
//
//                        if (test.length < 2 || test[1].equals(EMPTY)) {
//                            throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!\n");
//                        }
//
//                        String[] deadlineInfo = test[1].split("\\s*/by\\s*");
//                        LocalDateTime deadlineDateTime = DateConverter.convertToDateTime(deadlineInfo[1]);
//                        Deadline newDeadlineTask = new Deadline(deadlineInfo[0], deadlineDateTime, false);
//                        tasks.add(newDeadlineTask);
//                        saveToFile(tasks);
//                        System.out.println("Alright! I've added this task:\n " + newDeadlineTask.toString()
//                                + "\nNow you have " + tasks.size() + " tasks in the list.");
//                        userInput = scanIn.nextLine();
//
//
//                    // For an event task
//                    } else if (userInput.split(" ")[0].equals(EVENT_TRIGGER)) {
//                        String[] test = userInput.split("\\s+", 2);
//
//
//                        // Missing Event Description.
//                        if (test.length < 2 || test[1].equals(EMPTY)) {
//                            throw new EmptyDescriptionException("!!! Missing EVENT Description !!!\n");
//                        }
//
//                        String[] eventInfo = test[1].split(" /from | /to ");
//                        if (eventInfo.length < 3) {
//                            throw new InvalidCommandException("!!! Your command is incomplete !!!");
//                        }
//
//                        LocalDateTime eventFrom = DateConverter.convertToDateTime(eventInfo[1]);
//                        LocalDateTime eventTo = DateConverter.convertToDateTime(eventInfo[2]);
//                        Event newEventTask = new Event(eventInfo[0], eventFrom, eventTo, false);
//                        tasks.add(newEventTask);
//                        saveToFile(tasks);
//                        System.out.println("Alright! I've added this task:\n " + newEventTask.toString()
//                                + "\nNow you have " + tasks.size() + " tasks in the list.");
//                        userInput = scanIn.nextLine();
//
//
//                    // For to do task
//                    } else if (userInput.split(" ")[0].equals(TODO_TRIGGER)) {
//                        String[] test = userInput.split("\\s+", 2);
//
//                        // Missing TO DO Description.
//                        if (test.length < 2 || test[1].equals(EMPTY)) {
//                            throw new EmptyDescriptionException("!!! Missing TO DO Description !!!\n");
//                        }
//
//                        To Do newToDoTask = new To Do(test[1], false);
//                        tasks.add(newToDoTask);
//                        saveToFile(tasks);
//                        // if tasks = null then assign tasks = new ArrayList<Task>()
//                        System.out.println("Alright! I've added this task:\n " + newToDoTask.toString()
//                                + "\nNow you have " + tasks.size() + " tasks in the list.");
//                        userInput = scanIn.nextLine();
//                    } else {
//                        throw new InvalidCommandException("!!! Your command is invalid !!!\n");
//                    }
//
//                    // To list out tasks.
//                } else {
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 0; i < tasks.size(); i++) {
//                        System.out.println((i + 1) + "." + tasks.get(i).toString());
//                    }
//                    userInput = scanIn.nextLine();
//                }
//            } catch (EmptyTaskNumberException e1) {
//                System.out.println(e1.getMessage() + "\nOh no! The task number cannot be empty.\n" +
//                        "Please key in the task number you would like to mark/unmark.\n");
//                userInput = scanIn.nextLine();
//            } catch (WrongNumberException e2) {
//                System.out.println(e2.getMessage() + "Oh no! The task number does not exist.\n" +
//                        "You can find out the tasks and their numbers by typing list.\n" +
//                        "Please re-enter the correct task number to mark/unmark.");
//                userInput = scanIn.nextLine();
//            } catch (EmptyDescriptionException e3) {
//                System.out.println(e3.getMessage() + "Oh no! The task description cannot be empty.\n" +
//                        "Please key in the task description, with timings where applicable.\n");
//                userInput = scanIn.nextLine();
//            } catch (InvalidCommandException e4) {
//                System.out.println(e4.getMessage() + "Oh no! I don't understand what you mean. Please key in either\n" +
//                        "1. deadline [task description] /by [deadline datetime]\n" +
//                        "2. event [task description] /from [start datetime] /to [end datetime]\n" +
//                        "3. to do [task description].");
//                userInput = scanIn.nextLine();
//            } catch (IOException e5) {
//                System.out.println("Error loading file: " + e5.getMessage());
//                userInput = scanIn.nextLine();
//            } catch (DateTimeFormatException e6) {
//                System.out.println("Error with date time format: " + e6.getMessage());
//                userInput = scanIn.nextLine();
//            }
//        }
//
//        // If "bye" is triggered, exit while loop and print outro message.
//        System.out.println(outro);
//
//        }
}

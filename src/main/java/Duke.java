import exceptions.DukeException;
import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTodoException;
import exceptions.UnknownCommandException;

import extensions.TaskList;
import extensions.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;

public class Duke {

    /*
        file format:
        <tasktype>|<isMarked>|<desc>|<end>|<start>
     */

    private static final String HORIZONTAL_LINE = "_____________________________________________________\n";
    private static final String INTRO_MESSAGE = HORIZONTAL_LINE +
            " ____  _   _   ____  _____  ____   _     ____  _____\n" +
            "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
            "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n" +
            "Hello! I'm ChatALot.\n" +
            "What can I do for you?\n" +
            HORIZONTAL_LINE;
    private static final String OUTRO_MESSAGE = HORIZONTAL_LINE +
            "Bye. Hope to see you again soon!\n" +
            HORIZONTAL_LINE;

    private static TaskList list = new TaskList();

    private static void intro() {
        System.out.print(INTRO_MESSAGE);
    }

    private static void exit() {
        System.out.print(OUTRO_MESSAGE);
    }

    private static void retrieveSavedData() {
        try {
            ArrayList<String> arr = new ArrayList<>();
            File f = new File("data/duke.txt");
            if (!f.exists()) {
                return;
            }
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                String str = s.nextLine();
                arr.add(str);
            }

            populate(arr);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void populate(ArrayList<String> arr) {
        for (String str : arr) {
            String[] segmented = str.split("\\|");
            String taskType = segmented[0];
            int isMarked = Integer.parseInt(segmented[1]);
            String desc = segmented[2];
            String end;
            String start;

            switch (taskType) {
                case "T":
                    Duke.list.addTodo(desc, isMarked);
                    break;
                case "D":
                    end = segmented[3];
                    Duke.list.addDeadline(desc, end, isMarked);
                    break;
                case "E":
                    end = segmented[3];
                    start = segmented[4];
                    Duke.list.addEvent(desc, end, start, isMarked);
                    break;
            }

        }
    }

    public static void saveChanges() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter("data/duke.txt");
            fileWriter.write("");
            fileWriter.append(Duke.list.getTextFormattedString());
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String mark(String taskNumString) {
        if (Duke.list.getSize() < 1) {
            return "The task list is empty.";
        }

        if (!taskNumString.matches("[0-9]+")) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum < 1 || taskNum > Duke.list.getSize()) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        Task task = Duke.list.markTaskAsDone(taskNum);
        return "Nice! I've marked this task as done:\n" +
                "  " +
                task;
    }

    private static String unmark(String taskNumString) {
        if (Duke.list.getSize() < 1) {
            return "The task list is empty.";
        }

        if (!taskNumString.matches("[0-9]+")) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum < 1 || taskNum > Duke.list.getSize()) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        Task task = Duke.list.unmarkTask(taskNum);
        return "OK, I've marked this task as not done yet:\n" +
                "  " +
                task;
    }

    private static String deleteTask(String taskNumString) {
        if (Duke.list.getSize() < 1) {
            return "The task list is empty.";
        }

        if (!taskNumString.matches("[0-9]+")) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum < 1 || taskNum > Duke.list.getSize()) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        Task task = Duke.list.deleteTask(taskNum);
        return String.format("Noted. I've removed this task:\n" +
                "  " +
                task +
                "\nNow you have %d tasks in the list.", Duke.list.getSize());
    }

    private static String createTodo(String desc) {
        if (desc.equals("")) {
            throw new InvalidTodoException();
        }
        Task task = Duke.list.addTodo(desc);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String createDeadline(String action) {
        if (!action.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] arr = action.split("/by");
        if (arr.length < 2) {
            throw new InvalidDeadlineException();
        }

        String desc = arr[0].trim();
        String deadline = arr[1].trim();

        if (desc.equals("") || deadline.equals("")) {
            throw new InvalidDeadlineException();
        }
        Task task = Duke.list.addDeadline(desc, deadline);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String createEvent(String action) {
        if (!action.contains("/from") || !action.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] arr = action.split("/from|/to");
        if (arr.length < 3) {
            throw new InvalidEventException();
        }

        String desc = arr[0].trim();
        String start = arr[1].trim();
        String end = arr[2].trim();

        if (desc.equals("") || start.equals("") || end.equals("")) {
            throw new InvalidEventException();
        }

        Task task = Duke.list.addEvent(desc, start, end);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String processInput(String userInput) {
        String[] inputArr = userInput.trim().split(" ");
        String restOfInput;

        if (inputArr.length == 0) {
            throw new UnknownCommandException();
        }

        String command = inputArr[0];
        switch(command) {
        case "list":
            return Duke.list.toString();
        case "mark":
            restOfInput = userInput.trim().substring(4).trim();
            return mark(restOfInput);
        case "unmark":
            restOfInput = userInput.trim().substring(6).trim();
            return unmark(restOfInput);
        case "delete":
            restOfInput = userInput.trim().substring(6).trim();
            return deleteTask(restOfInput);
        case "todo":
            restOfInput = userInput.trim().substring(4).trim();
            return createTodo(restOfInput);
        case "deadline":
            restOfInput = userInput.trim().substring(8).trim();
            return createDeadline(restOfInput);
        case "event":
            restOfInput = userInput.trim().substring(5).trim();
            return createEvent(restOfInput);
        default:
            throw new UnknownCommandException();
        }
    }

    private static String displayOutput(String userInput) {
        String output = "";
        try {
            output = processInput(userInput);
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (RuntimeException e) {
            output = "Runtime: " + e.getMessage();
        } finally {
            String displayed = HORIZONTAL_LINE +
                    output +
                    "\n" +
                    HORIZONTAL_LINE;
            System.out.print(displayed);
            return displayed;
        }
    }

    public static void main(String[] args) {
        retrieveSavedData();
        intro();

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        String bye = "bye";

        while (!userInput.toLowerCase().equals(bye)) {
            displayOutput(userInput);
            userInput = myObj.nextLine();
        }

        saveChanges();
        exit();
    }

}

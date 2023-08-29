import Exceptions.*;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an archive of tasks in the Duke application.
 * This class provides methods to manage and manipulate tasks.
 */
public class Archive {
    protected List<Task> list;
    private final String filePath = "data/saved.txt";
    private final String dateFormat = "dd/MM/yyyy HH:mm";
    /**
     * Constructs a new Archive object with an empty task list.
     */
    public Archive(){
        try {
            File f = new File(filePath);
            Files.createDirectories(Paths.get("data"));
            f.createNewFile();
            Scanner s = new Scanner(f);
            list = new ArrayList<Task>();
            while (s.hasNext()) {
                loadTask(s.nextLine());
            }
        } catch (IOException e) {
            list = new ArrayList<Task>();
        }
    }


    /**
     * Prints the list of tasks in the archive along with their indices.
     */
    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
    }

    /**
     * Marks a task as completed based on user input.
     *
     * @param input The input string containing the task index to mark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void markTask(String input) throws OutOfIndexException {
        int item = input.charAt(5) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.get(item);
        curr.mark();
        System.out.println("I HAVE MARKED THIS TASK:");
        System.out.println(curr);
        save();
    }

    /**
     * Marks a completed task as incomplete based on user input.
     *
     * @param input The input string containing the task index to unmark.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void unmarkTask(String input) throws OutOfIndexException {
        int item = input.charAt(5) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.get(item);
        curr.unmark();
        System.out.println("I HAVE UNMARKED THIS TASK:");
        System.out.println(curr);
        save();
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input The input string containing the task index to delete.
     * @throws OutOfIndexException If the provided index is out of the valid range.
     */
    public void deleteTask(String input) throws OutOfIndexException {
        int item = input.charAt(7) - '0';
        if (item < 0 || item > list.size() - 1) {
            throw new OutOfIndexException();
        }
        Task curr = list.remove(item);
        save();
        System.out.println("I HAVE DELETED THE FOLLOWING TASK:");
        System.out.println(curr);
        System.out.println("NOW YOU HAVE " + list.size() + " TASKS LEFT");
    }

    /**
     * Adds a task to the archive based on user input.
     *
     * @param input The input string containing the task details.
     * @throws EmptyDeadlineException If the deadline task is missing required details.
     * @throws EmptyTodoException     If the todo task is missing a title.
     * @throws EmptyEventException    If the event task is missing required details.
     * @throws MissingByException     If the deadline task is missing the "by" date.
     * @throws MissingFromException   If the event task is missing the start date.
     * @throws MissingToException     If the event task is missing the end date.
     * @throws MissingTitleException  If a task is missing its title.
     * @throws InvalidInputException  If the input doesn't match any valid task format.
     */
    public void addTask(String input) throws InvalidDateFormatException, EmptyDeadlineException, EmptyTodoException, EmptyEventException,
            MissingByException, MissingFromException, MissingToException, MissingTitleException, InvalidInputException {
        Task added = null;
        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new EmptyTodoException();
            }
            String title = input.substring(5);
            added = new Todo(title, false);
        } else if (input.startsWith("deadline")) {
            if (input.length() < 10) {
                throw new EmptyDeadlineException();
            }
            int index = input.indexOf("/by");
            if (index == -1) {
                throw new MissingByException();
            }
            if (index < 10) {
                throw new MissingTitleException();
            }
            String title = input.substring(9, index - 1);
            String dueDate = input.substring(index + 4);
            added = new Deadline(title, parseDate(dueDate), false);
        } else if (input.startsWith("event")) {
            if (input.length() < 7) {
                throw new EmptyEventException();
            }
            int fromIndex = input.indexOf("/from");
            if (fromIndex == -1) {
                throw new MissingFromException();
            }
            if (fromIndex < 7) {
                throw new MissingTitleException();
            }
            String title = input.substring(6, fromIndex - 1);
            int toIndex = input.indexOf("/to");
            if (toIndex == -1) {
                throw new MissingToException();
            }
            String from = input.substring(fromIndex + 6, toIndex - 1);
            String to = input.substring(toIndex + 4);
            added = new Event(title, parseDate(from), parseDate(to), false);
        }
        if (added != null) {
            list.add(added);
            System.out.println("I'VE ADDED THIS TASK:");
            System.out.println(added);
            System.out.println("YOU HAVE " + list.size() + " TASKS IN THE LIST");
            save();
        } else {
            throw new InvalidInputException();
        }
    }

    public void loadTask(String input) {
        boolean isMarked;
        int markedIndex = input.indexOf("|");
        isMarked = input.charAt(markedIndex + 1) == 1;
        int titleIndex = input.indexOf("|", markedIndex + 1) + 2;
        try {
            if (input.startsWith("T")) {
                String title = input.substring(titleIndex);
                list.add(new Todo(title, isMarked));
            } else if (input.startsWith("D")) {
                int byIndex = input.indexOf("|", titleIndex);
                String title = input.substring(titleIndex, byIndex);
                String dueDateString = input.substring(byIndex + 2);
                list.add(new Deadline(title,parseDate(dueDateString),isMarked));
            } else {
                int fromIndex = input.indexOf("|", titleIndex);
                String title = input.substring(titleIndex, fromIndex);
                int toIndex = input.indexOf("|", fromIndex + 1);
                String from = input.substring(fromIndex + 2, toIndex);
                String to = input.substring( toIndex + 2);
                list.add(new Event(title,parseDate(from),parseDate(to),isMarked));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
    public void save() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                fw.write(list.get(i).toSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public LocalDateTime parseDate(String input) throws InvalidDateFormatException{
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (Exception e) {
            throw new InvalidDateFormatException();
        }
    }
}

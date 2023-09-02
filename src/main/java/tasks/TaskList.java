package tasks;

import parsers.DateTimeParser;
import storage.Storage;
import ui.Ui;
import exceptions.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = Storage.readTasks();
    }

    public void printTasks() {
        Ui.printLine();
        Ui.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentItem = (i + 1) + "." + currentTask.toString();
            Ui.print(currentItem);
        }
        Ui.printLine();
    }

    public void handleMark(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {
            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            currentTask.markDone();
            Ui.printLine();
            Ui.print("Nice! I've marked this task as done:");
            String currentItem = currentTask.toString();
            Ui.print(currentItem);
            Ui.printLine();
        } catch (IndexOutOfBoundsException err){
            throw new exceptions.DukeException("This Task index does not exist!", err);
        }

    }

    public void createDeadline(String userInput) {
        String newInput = userInput.substring(9);
        String[] arrInput = newInput.split("/by ");
        if (arrInput.length != 2) {
            Ui.print("Error! There is an issue with the format of your message. ");
        } else {
            LocalDateTime dateTime = DateTimeParser.parseDateTime(arrInput[1]);
            Deadline newDeadline = new Deadline(dateTime, arrInput[0]);
            tasks.add(newDeadline);
            addedMessage(newDeadline.toString());
        }

    }

    public void createTodo(String userInput) {
        String newInput = userInput.substring(5);
        Todo newTodo = new Todo(newInput);
        tasks.add(newTodo);
        addedMessage(newTodo.toString());
    }

    public void createEvent(String userInput) {
        String newInput = userInput.substring(6);
        String[] arrInput = newInput.split("/from ");
        String eventDetails = arrInput[0];
        String[] fromToArr = arrInput[1].split(" /to ");
        Event newEvent = new Event(DateTimeParser.parseDateTime(fromToArr[0]),
                DateTimeParser.parseDateTime(fromToArr[1]), eventDetails);
        tasks.add(newEvent);
        addedMessage(newEvent.toString());
    }

    public void addedMessage(String taskMessage) {
        Ui.printLine();
        String message = "Got it. I've added this task:\n" + taskMessage + "\nNow you have " + tasks.size() +
                " tasks in the list.\n";
        Ui.print(message);
        Ui.printLine();;
    }
    public void deleteTask(String userInput) {
        String[] arrInput = userInput.split(" ");
        try {

            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            tasks.remove(Integer.valueOf(arrInput[1]) - 1);
            Ui.printLine();
            Ui.print("Noted. I've removed this task:");
            String currentItem = currentTask.toString();
            Ui.print(currentItem);
            Ui.print("Now you have " + tasks.size() + " tasks in the list.");
            Ui.printLine();
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }
    }

    public void storeTasks() {
        Storage.storeTasks(tasks);
    }
}

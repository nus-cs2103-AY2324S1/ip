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

    public String printTasks() {
        String str = "";
        str += Ui.printLine();
        str += Ui.print("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            String currentItem = (i + 1) + "." + currentTask.toString();
            str += Ui.print(currentItem);
        }
        str += Ui.printLine();
        return str;
    }

    public String findTask(String userInput) {
        String str = "";
        str += Ui.printLine();
        String taskContent = userInput.substring(5);
        str += Ui.print("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.toString().contains(taskContent)) {
                str += Ui.print(count + "." + currentTask.toString());
                count += 1;
            }
        }
        str += Ui.printLine();
        return str;
    }

    public String handleMark(String userInput) {
        String str = "";
        String[] arrInput = userInput.split(" ");
        try {
            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            currentTask.markDone();
            str += Ui.printLine();
            str += Ui.print("Nice! I've marked this task as done:");
            String currentItem = currentTask.toString();
            str += Ui.print(currentItem);
            str += Ui.printLine();
        } catch (IndexOutOfBoundsException err){
            throw new exceptions.DukeException("This Task index does not exist!", err);
        }
        return str;

    }

    public String createDeadline(String userInput) {
        String str = "";
        String newInput = userInput.substring(9);
        String[] arrInput = newInput.split("/by ");
        if (arrInput.length != 2) {
            str += Ui.print("Error! There is an issue with the format of your message. ");
        } else {
            LocalDateTime dateTime = DateTimeParser.parseDateTime(arrInput[1]);
            Deadline newDeadline = new Deadline(dateTime, arrInput[0]);
            tasks.add(newDeadline);
            str += addedMessage(newDeadline.toString());
        }
        return str;
    }

    public String createTodo(String userInput) {
        String str = "";
        String newInput = userInput.substring(5);
        Todo newTodo = new Todo(newInput);
        tasks.add(newTodo);
        str += addedMessage(newTodo.toString());
        return str;
    }

    public String createEvent(String userInput) {
        String str = "";
        String newInput = userInput.substring(6);
        String[] arrInput = newInput.split("/from ");
        String eventDetails = arrInput[0];
        String[] fromToArr = arrInput[1].split(" /to ");
        Event newEvent = new Event(DateTimeParser.parseDateTime(fromToArr[0]),
                DateTimeParser.parseDateTime(fromToArr[1]), eventDetails);
        tasks.add(newEvent);
        str += addedMessage(newEvent.toString());
        return str;
    }

    public String addedMessage(String taskMessage) {
        String str = "";
        str += Ui.printLine();
        String message = "Got it. I've added this task:\n" + taskMessage + "\nNow you have " + tasks.size() +
                " tasks in the list.\n";
        str += Ui.print(message);
        str += Ui.printLine();;
        return str;
    }
    public String deleteTask(String userInput) {
        String str = "";
        String[] arrInput = userInput.split(" ");
        try {

            Task currentTask = tasks.get(Integer.valueOf(arrInput[1]) - 1);
            tasks.remove(Integer.valueOf(arrInput[1]) - 1);
            str += Ui.printLine();
            str += Ui.print("Noted. I've removed this task:");
            String currentItem = currentTask.toString();
            str += Ui.print(currentItem);
            str += Ui.print("Now you have " + tasks.size() + " tasks in the list.");
            str += Ui.printLine();
            return str;
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("This Task index does not exist!", err);
        }
    }

    public void storeTasks() {
        Storage.storeTasks(tasks);
    }
}

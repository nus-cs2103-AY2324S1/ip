package oreo.task;

import oreo.exception.IllegalCommandException;
import oreo.ui.Ui;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> taskList;

    public int numberOfCompletedTasks = 0;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task remove(int index) {
        Task removedTask = taskList.remove(index);
        if (removedTask.isComplete()) numberOfCompletedTasks--;
        return removedTask;
    }

    public void clearAll() {
        taskList.clear();
    }

    public int getNumberOfTask() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public boolean isAllComplete() {
        return taskList.size() == numberOfCompletedTasks;
    }

    public String list() {
        if (taskList.size() == 0) {
            return "list looks empty to me!";
        } else {
            StringBuilder displayList = new StringBuilder();
            if (isAllComplete()) {
                displayList.append("Wow! You are ALL COMPLETE!!!!\n")
                        .append("TIME TO PLAY WITH MEEEEE :DDDD\n");
            } else if (taskList.size() > 10) {
                displayList.append("Seems like you have a lot of things to do...\n")
                        .append("Remember to play with me after :D\n");
            } else {
                displayList.append("Here are the things you told me to keep track of:\n");
            }
            for (int i = 0; i < taskList.size(); i++) {
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    public String listResults(String keyword) {
        if (getNumberOfTask() == 0) {
            return "Unfortunately, I couldn't find any task matching \"" +
                    keyword +
                    "\" :(";
        } else {
            StringBuilder displayList = new StringBuilder();
            displayList.append("Here are task(s) matching \"" +
                    keyword +
                    "\" in your list: \n");
            for (int i = 0; i < taskList.size(); i++) {
                displayList.append(i + 1 + ".").append(taskList.get(i).toString());
            }
            return displayList.toString();
        }
    }

    public String changeMark(String command, Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > getNumberOfTask()) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                if (command.equals("mark")) {
                    String message = markDone(id - 1);
                    if (isAllComplete()) {
                        message += list();
                    }
                    return message;
                } else {
                    return markNotDone(id - 1);
                }
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    public String deleteTask(Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a task number");
        }
        String content = tokeniser.next();
        if (isInteger(content)) {
            int id = Integer.parseInt(content);
            if (id > getNumberOfTask()) {
                throw new IllegalCommandException("do that... this task does not exist :(");
            } else {
                Task removedTask = remove(id - 1);
                String message = "Happily scratched this off your list:\n" +
                        Ui.indentLineBy(removedTask.toString(), 2) +
                        "Now you have " +
                        getNumberOfTask() +
                        " tasks in the list!";
                if (isAllComplete()) {
                    message += list();
                }
                return message;
            }
        } else {
            throw new IllegalCommandException("do that... try a number instead");
        }
    }

    public String markDone(int index) {
        Task task = get(index);
        if (task.isComplete()) {
            return "That was done already...\n" +
                    "are you sure you wanted to mark that?\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks++;
            return "Yay! One step closer to playing with me!\n"
                    + task.toString();
        }
    }

    public String markNotDone(int index) {
        Task task = get(index);
        if (!task.isComplete()) {
            return "Don't worry it's still not done\n" +
                    "What are you doing? Let's get it done now!\n"
                    + task.toString();
        } else {
            task.switchMark();
            numberOfCompletedTasks--;
            return "Oh no... what happened :(\n"
                    + task.toString();
        }
    }

    public String findTasksWith(Scanner tokeniser) throws IllegalCommandException {
        if (!tokeniser.hasNext()) {
            throw new IllegalCommandException("do that without specifying a keyword");
        }
        String keyword = tokeniser.next();
        TaskList toPrint = new TaskList();
        for (int i = 0; i < getNumberOfTask(); i++) {
            Task ref = taskList.get(i);
            if (ref.contains(keyword)) {
                toPrint.add(ref);
            }
        }
        return toPrint.listResults(keyword);
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}

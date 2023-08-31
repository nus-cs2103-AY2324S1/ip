package duke;

import java.util.ArrayList;

public class TaskList {
    public static String line = "\t____________________________________________________________\n";
    protected ArrayList<Task> strList;

    private void printAddTask(Task t) {
        System.out.println(line + "\tGot it. I've added this task: ");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + strList.size() + " tasks in the list.");
        System.out.println(line);
    }
    public void toDoHandler(String description, boolean isDone, boolean readingFile) throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("todo");
        } else {
            Task newToDo = new ToDos(description, isDone);
            strList.add(newToDo);
            if (!readingFile) {
                printAddTask(newToDo);
            }
        }
    }

    public void deadlineHandler(String description, boolean isDone, boolean readingFile) throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            String[] parts = description.split("/by");  // Split the input string by the delimiter "/"
            String before = parts[0].trim();
            String after = parts[1].trim();
            Task newDeadline = new Deadline(before, after, isDone);
            strList.add(newDeadline);
            if (!readingFile) {
                printAddTask(newDeadline);
            }
        }
    }
    public void eventHandler(String description, boolean isDone, boolean readingFile) throws EmptyDescriptionException {
        if (description.equals("")) {
            throw new EmptyDescriptionException("event");
        } else {
            int fromIndex = description.indexOf("/from");  // Find the index of "/from"
            int toIndex = description.indexOf("/to");  // Find the index of "/to"
            String eventDescription = description.substring(0, fromIndex).trim();
            String from = description.substring(fromIndex + "/from".length(), toIndex).trim();
            String to = description.substring(toIndex + "/to".length()).trim();
            Task newEvent = new Event(eventDescription, from, to, isDone);
            strList.add(newEvent);
            if (!readingFile) {
                printAddTask(newEvent);
            }
        }
    }
    public void printTaskList() {
        for (Task element : strList) {
            System.out.println(element.toString());
        }
    }
    public void printListContent() {
        int arrLength = this.strList.size();
        System.out.println(line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < arrLength; i++) {
            int number = i + 1;
            Task t = strList.get(i);
            System.out.println("\t" + number + "." + t.toString());
        }
        System.out.println(line);
    }

    public void markTaskAsDone(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            Task t = strList.get(index);
            t.markTask();
            System.out.println(line);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t" + "\t" + t.toString());
            System.out.println(line);
        }
    }

    public void unmarkTask(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            Task t = strList.get(index);
            t.unmarkTask();
            System.out.println(line);
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t" + "\t" + t.toString());
            System.out.println(line);
        }
    }

    public void deleteTask(char number) throws NotANumberException {
        if (!Character.isDigit(number)) {
            throw new NotANumberException();
        } else {
            int index = Character.getNumericValue(number) - 1;
            Task t = strList.remove(index);
            System.out.println(line);
            System.out.println("\tNoted. I've removed this task: ");
            System.out.println("\t" + "\t" + t.toString());
            System.out.println("\tNow you have " + strList.size() + " tasks in the list.");
            System.out.println(line);
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.strList;
    }

    public TaskList() {
        this.strList = new ArrayList<>();
    }


}

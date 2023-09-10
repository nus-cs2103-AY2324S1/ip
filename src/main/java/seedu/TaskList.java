package seedu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {

    private static final String DELIMITER = "|";
    private static final String EMPTY = "";
    private List<Task> lists = new ArrayList<>();
    public TaskList(List<Task> t) {
        this.lists = t;
    }

    /**
     * Add a tasks in the lists of tasks
     *
     * @param t task the Task class that is to be added
     */
    public void add(Task t) throws Exception {
        try {
            this.lists.add(t);
        } catch(IllegalArgumentException e) {
            throw new Exception("Unfortunately, " + e.getMessage());
        } catch(Exception e) {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Returns the current task from the list
     *
     * @param index the position of the current task to be retrieved
     * @returns a Task object of interest
     */
    public Task get(int index) {
        return this.lists.get(index);
    }

    /**
     * Retrieves task lists from a list of string from a file
     *
     * @param from source of the lists of strings
     * @returns a TaskList of the current lists of tasks
     */
    public static TaskList getFromFile(List<String> from) throws Exception {
        try {
            List<Task> lists = new ArrayList<>();

            for (String l : from) {
                String[] chars = l.split("\\|");
                if (chars[0].equals("ToDo")) {
                    lists.add(new Task("todo" + " " + chars[2] ,"todo"));
                } else if (chars[0].equals("Event")){
                    String title = chars[2];
                    String fr = chars[4].trim();
                    String to = chars[3].trim();
                    Task temp = new Task(title + " /from " + fr + " /to " + to, "event");
                    if (chars[1].equals("true")) temp.isDone = true;
                    lists.add(temp);
                } else {
                    String title = chars[2].trim();
                    String to = chars[3].trim();
                    Task temp = new Task(title + " /by " + to,"deadline");
                    if (chars[1].equals("true")) temp.isDone = true;
                    lists.add(temp);
                }
            }
            return new TaskList(lists);
        } catch(Exception e) {
            throw new Error("Parsing error");
        }

    }

    /**
     * Encodes TaskList to a list of string to be used in a file
     *
     * @param tasks the list of Tasks objects
     * @returns a String List to be stored in a file
     */
    public static List<String> encode(TaskList tasks) {
        int curr = 1;
        List<String> arrays = new ArrayList<>();
        for (int i = 0; i < tasks.getLen(); i++) {
            if (tasks.get(i) != null) {
                Task t = tasks.get(i);
                String start = !t.start.isEmpty() ? t.start + DELIMITER : EMPTY;
                String end = !t.end.isEmpty()?  t.end + DELIMITER : EMPTY;
                arrays.add(t.category.toString() + DELIMITER + (t.isDone ? "true" : "false")
                        + DELIMITER + t.title + DELIMITER + end + start);
            }
            ;
        }
        return arrays;
    }

    /**
     * Returns the size of the TaskList
     *
     * @returns the length of the tasks number
     */
    public int getLen() {
        return this.lists.size();
    }

    /**
     * Marks a task in the lists of tasks
     *
     * @param index position of the task in the lists of tasks
     */
    public void mark(int index) throws Exception {
        try {
            this.lists.get(index).mark();
        } catch(Exception e) {
            throw new Exception("Sorry there is no such task");
        }

    }

    /**
     * Removes a task from lists of tasks
     *
     * @param index the position of the task to be removed
     * @returns the Task that was removed
     */
    public Task remove(int index) throws Exception {
        try {
            Task now = lists.get(index);
            this.lists.remove(now);
            return now;
        } catch(Exception e) {
            throw new Exception("Something wrong with the given input");
        }
    }

    public ArrayList<Task> specificRemove(ArrayList<Task> tasks) throws Exception {
        try {
            for(Task task : tasks) {
                this.lists.remove(task);
            }
            return tasks;
        } catch(Exception e) {
            throw new Exception("Something wrong with the given input");
        }
    }

    public ArrayList<Task> find(String keyword) throws Exception {
        ArrayList<Task> foundWords = new ArrayList<>();
        try {
            for(Task t : this.lists) {
                if(t.description.contains(keyword)) {
                    foundWords.add(t);
                }
            }
            return foundWords;
        } catch(Exception e) {
            throw new Exception("Keyword not found");
        }
    }
}

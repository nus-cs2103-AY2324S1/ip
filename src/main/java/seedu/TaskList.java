package seedu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {

    private List<Task> lists = new ArrayList<>();
    public TaskList(List<Task> t) {
        this.lists = t;
    }
    public void add(Task t) throws Exception {
        try {
            this.lists.add(t);
        } catch(IllegalArgumentException e) {
            throw new Exception("Unfortunately, " + e.getMessage());
        } catch(Exception e) {
            throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public Task get(int index) {
        return this.lists.get(index);
    }

    public static TaskList getFromFile(List<String> from) throws Exception {
        try {
            List<Task> lists = new ArrayList<>();

            for (String l : from) {
                String[] chars = l.split("\\|");
                if (chars[0].equals("ToDo")) {
                    lists.add(new Task("todo" + " " + chars[2] ,"todo"));
                } else if (chars[0].equals("Event")){
                    String title = chars[2];
                    String fr = chars[4];
                    String to = chars[3];
                    Task temp = new Task(title + " /from " + fr + " /to " + to, "event");
                    if (chars[1].equals("true")) temp.isDone = true;
                    lists.add(temp);
                } else {
                    String title = chars[2];
                    String to = chars[3];
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

    public static List<String> encode(TaskList tasks) {
        int curr = 1;
        List<String> arrays = new ArrayList<>();
        for (int i = 0; i < tasks.getLen(); i++) {
            if (tasks.get(i) != null) {
                Task t = tasks.get(i);
                String start = !t.start.isEmpty() ? t.start + "|" : "";
                String end = !t.end.isEmpty()?  t.end + "|" : "";
                arrays.add(t.category.toString() + "|" + (t.isDone ? "true" : "false") + "|" + t.title + "|" + end + start);

            }
            ;
        }
        return arrays;
    }

    public int getLen() {
        return this.lists.size();
    }

    public void mark(int index) throws Exception {
        try {
            this.lists.get(index).mark();
        } catch(Exception e) {
            throw new Exception("Sorry there is no such task");
        }

    }

    public Task remove(int index) throws Exception {
        try {
            Task now = lists.get(index);
            this.lists.remove(now);
            return now;
        } catch(Exception e) {
            throw new Exception("Something wrong with the given input");
        }

    }
}

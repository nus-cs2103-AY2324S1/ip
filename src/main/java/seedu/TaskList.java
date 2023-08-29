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
            String pattern = "\\(From : .+ To : .+\\)";
            Pattern regexPattern = Pattern.compile(pattern);

            for (String l : from) {
                Matcher matcher = regexPattern.matcher(l);
                if (!l.endsWith(")")) {
                    lists.add(new Task(l.split("]", 3)[2],"todo"));
                } else if (matcher.find()){
                    int startIndex = matcher.start();
                    int endIndex = matcher.end();
                    String title = l.split("]",3)[2].split("\\(")[0];
                    String fr = l.split("From :")[1].split("To")[0];
                    String to = l.substring(startIndex, endIndex).split("To :")[1];
                    lists.add(new Task(title + " /from " + fr + " /to " + to, "event"));
                } else {
                    String title =  l.split("\\(")[0].split("] ")[1];

                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("MMM dd yyyy");
                    String h = l.split("\\(")[1].substring(0, 10);

                    Date inputDate = inputDateFormat.parse(h);
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String outputDateStr = outputDateFormat.format(inputDate);
                    lists.add(new Task(title + " /by " + outputDateStr,"deadline"));
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
                arrays.add((curr++) + ". " + t.getStatus());
            }
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

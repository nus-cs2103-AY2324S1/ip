package Eddie.Tasks;

import java.util.ArrayList;

/**
 * Represents a task which can be created and listed.
 */
public class Task {
    private boolean done;
    private String name;

    private  ArrayList<String> tags = new ArrayList<>();

    public Task(){};
    public Task(String name) {
        this.done = false;
        this.name = name;

    }

    public void tag(String s) {
        tags.add(s);
    }

    public String printTags() {
        String tagsToPrint = "";
        for (int i = 0; i < tags.size(); i++) {
            tagsToPrint += "#" + tags.get(i) + " ";
        }

        return tagsToPrint;
    }

    public String getStatus(){
        return done ? "x" : " ";
    }

    public void taskIsDone() {
        this.done = true;
    }
    public void taskNotDone() {
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return "Eddie.Tasks.Task";
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]"
                + this.getName() + " " + this.printTags();
    }

    public String getDeadline() {return null;}

    public String getStartDate() {return null;}

    public String getEndDate() {return null;}

}

package adam.tasks;

import java.io.Serializable;

public class Task implements Serializable {
    protected String description;

    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
    }

    public void unmarkAsDone(){
        this.isDone = false;
        System.out.println(this.toString());
    }

    /**
     * Returns boolean depending on whether or not the param exists in the description.
     *
     * @param item String that is being checked.
     * @return Boolean.
     */
    public boolean search(String item) {
        boolean isIdentical = true;
        String[] tokens = item.split(" ");
        String[] descriptions = description.split(" ");
        for (String word : tokens) {
            for (String line : descriptions) {
                if (!word.equals(line)) {
                    isIdentical = false;
                }
            }
        }
        return isIdentical;
    }
}

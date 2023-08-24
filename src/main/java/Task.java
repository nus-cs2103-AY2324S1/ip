public class Task {
    //@@author iantsaii-reused
    //Source: https://nus-cs2103-ay2324s1.github.io/website/schedule/week2/project.html#:~:text=Partial-,solution,-Duke%20Level%2D4
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }
    //@@author
}

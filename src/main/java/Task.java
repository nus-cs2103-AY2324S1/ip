public abstract class Task {
//    static ArrayList<Task> allTasks = new ArrayList<Task>();
//    static int numIncompleteTasks = 0;

    String name;
    boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    void markAsDone() {
        this.isDone = true;
    }

    void markAsNotDone() {
        this.isDone = false;
    }

    boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }


    abstract String getTaskType();


    abstract String formatTaskForSaving();
    // String format to save the task to disk

}

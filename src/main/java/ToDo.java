public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName,TaskType.TODO);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Parse from string to a ToDo task
     *
     * @param line The String that is needed to parse into a ToDo Task
     */
    public static ToDo parseFromString(String line) {
        int firstBracketIndex = line.indexOf(']');
        String description = line.substring(firstBracketIndex + 5);
        String mark = line.substring(firstBracketIndex + 2, firstBracketIndex + 3);
        ToDo task = new ToDo(description);

        // if task is initially marked done, then mark the task as done
        if (mark.equals("X")) {
            task.markDone();
        }
        return task;
    }
}

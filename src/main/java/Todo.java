public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toData() {
        String done = String.valueOf(this.isDone ? 1 : 0);
        return "T | " + done + " | " + this.description;
    }

    public static Task dataToTask(String taskData) {
        int firstSplitIndex = taskData.indexOf("|");
        boolean isDone = taskData.substring(0, firstSplitIndex - 1).equals("1");
        String desc = taskData.substring(firstSplitIndex + 2);
        Todo newTodo = new Todo(desc);
        newTodo.isDone = isDone;
        return newTodo;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

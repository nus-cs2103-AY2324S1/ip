public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        char taskType = 'T';
        return taskType + " | " + super.toFileString();
    }

    public static Todo createTodoFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3 && taskParts[0].trim().equals("T")) {
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            Todo todo = new Todo(description);
            if (doneStatus.equals("1")) {
                todo.markDone();
            }
            return todo;
        }

        return null; // Incomplete data
    }

}

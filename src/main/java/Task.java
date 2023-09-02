public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description; // getter
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public void toggle() {
        this.isDone = !this.isDone;
    }

    public static Task fromString(String taskLine) {
        if (taskLine.charAt(0) == 'D') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            String deadline = parts[3].trim(); // Extract and trim the deadline
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newDeadline = new Deadline(taskDescription, isDone, deadline);
            return newDeadline;
        } else if (taskLine.charAt(0) == 'E') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            String from = parts[3].trim(); // Extract and trim the "from" time
            String to = parts[3].trim(); // Extract and trim the "to" time
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newEvent = new Event(taskDescription, isDone, from, to);
            return newEvent;
        } else if (taskLine.charAt(0) == 'T') {
            String[] parts = taskLine.split("\\|"); // Split the string by the | character

            String taskDescription = parts[2].trim(); // Extract and trim the task description
            int temp = Integer.parseInt(parts[1].trim()); // Extract the isDone status
            boolean isDone = (temp == 1); // Set as boolean

            Task newTodo = new Todo(taskDescription, isDone);
            return newTodo; 
        } else {
            return null;
        }
    }

}

/**
 * Represents a generic task with a description and completion status
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " (space) if not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return (this.description);
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

//    /**
//     * Parses the data string from the storage file and returns the corresponding task.
//     * example of data string:
//     * T | 1 | read book
//     * D | 0 | return book | June 6th
//     * E | 0 | project meeting | Aug 6th 2-4pm
//     * file corruption not handled
//     *
//     * @return The completion status of the task.
//     */
//    public static Task parseDataString(String taskData) {
//        String[] parts = taskData.split(" \\| ");
//        String type = parts[0].trim();
//        String isDone = parts[1].trim();
//        String description = parts[2].trim();
//
//        Task task = null;
//
//        switch (type) {
//        case "T":
//            task = new Todo(description);
//            break;
//        case "D":
//            String by = parts[3].trim();
//            task = new Deadline(description, by);
//            break;
//        case "E":
//            String from = parts[3].trim();
//            String to = parts[4].trim();
//            task = new Event(description, from, to);
//            break;
//        }
//
//        if (isDone.equals("1")) {
//            assert task != null;
//            task.markAsDone();
//        }
//
//        return task;
//    }

//    /**
//     * reads the task and returns the corresponding data string to be written to the storage file.
//     *
//     * @param task The task to be read.
//     * @return String to be written to the storage file.
//     */
//    public String readTaskToFile(Task task) {
//        StringBuilder data = new StringBuilder();
//
//        if (task instanceof Todo) {
//            data.append("T");
//            data.append(" | ").append(task.isDone() ? "1" : "0");
//            data.append(" | ").append(task.getDescription());
//        } else if (task instanceof Deadline) {
//            data.append("D");
//            data.append(" | ").append(task.isDone() ? "1" : "0");
//            data.append(" | ").append(task.getDescription());
//            data.append(" | ").append(((Deadline) task).getBy());
//        } else if (task instanceof Event) {
//            data.append("E");
//            data.append(" | ").append(task.isDone() ? "1" : "0");
//            data.append(" | ").append(task.getDescription());
//            data.append(" | ").append(((Event) task).getFrom()).append(" | ").append(((Event) task).getTo());
//        }
//
//        return data.toString();
//    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[" + this.getStatusIcon() + "] " + this.getDescription());
    }
}

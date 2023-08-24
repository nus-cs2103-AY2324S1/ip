public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markAsDone() {
        this.isDone = true;
    }
    public void markAsUndone() {
        this.isDone = false;
    }
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
    public static ToDo createToDo(String input) throws NoDescException {
            ToDo item = new ToDo(input.replace("todo", ""));
            if (item.description.isEmpty()) {
                throw new NoDescException("here's literally how to create a todo: todo [task name]");
            }
            return item;
    }
    public static Deadline createDeadline(String input) throws NoDescException, DeadlineNoEndException {
        String parts[] = input.split("/by");
        if (input.replace("deadline", "").isEmpty()) {
            throw new NoDescException("how am i suppose to know what is due...");
        }
        if (!input.contains("/by")) {
            throw new DeadlineNoEndException("here's literally how to create a deadline: deadline [task name] /by [date]");
        }
        Deadline item = new Deadline(parts[0].replace("deadline ", ""), parts[1]);
        return item;
    }

    public static Event createEvent(String input) throws NoDescException {
        String parts[] = input.split("/from");
        if (parts.length == 1) {
            throw new NoDescException("how am i suppose to know what is going on...");
        }
        String time[] = parts[1].split("/to");
        Event item = new Event(parts[0].replace("event", ""), time[0], time[1]);
        return item;
    }

//    public static Task createTask(String taskDetails) {
//        String taskType = taskDetails.substring(1, 2);
//        char status = taskDetails.charAt(4);
//        String description = taskDetails.substring(8);
//
//        Task task;
//
//        if (taskType.equals("T")) {
//
//        } else if (taskType.equals("D")) {
//
//        } else if (taskType.equals("E")) {
//
//        } else {
//            throw new IllegalArgumentException("Invalid task type.");
//        }
//
//        if (status == 'X') {
//            task.markAsDone();
//        }
//
//        return task;
//    }

}

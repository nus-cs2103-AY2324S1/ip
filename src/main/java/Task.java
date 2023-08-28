public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static Task createTask(String description) throws EmptyDetailsOfTaskError, UnknownCommandException {
        String[] splittedDescription = description.split(" ");
        String commandName = splittedDescription[0];
        if (description.startsWith(Commands.todo.name()) && splittedDescription.length != 1) {
            String task = description.replace("todo ", "");
            Task currentTask = new Todo(task);
            return currentTask;
        } else if (description.startsWith(Commands.deadline.name()) && splittedDescription.length != 1) {
            String task = description.replace("deadline ", "");
            String[] splittedTask = task.split(" /by ");
            if (splittedTask.length == 1) {
                throw new EmptyDetailsOfTaskError("The end of a deadline cannot be empty.");
            }
            String taskName = splittedTask[0];
            String end = splittedTask[1];
            Task currentTask = new Deadlines(taskName, end);
            return currentTask;
        } else if (description.startsWith(Commands.event.name()) && splittedDescription.length != 1) {
            String task = description.replace("event ", "");
            String[] splitStart = task.split(" /from ");
            if (splitStart.length == 1) {
                throw new EmptyDetailsOfTaskError("The start of a event cannot be empty.");
            }
            String[] splitEnd = splitStart[1].split(" /to ");
            if (splitEnd.length == 1) {
                throw new EmptyDetailsOfTaskError("The end of a event cannot be empty.");
            }
            String taskName = splitStart[0];
            String start = splitEnd[0];
            String end = splitEnd[1];
            Task currentTask = new Events(taskName, start, end);
            return currentTask;
        } else if (splittedDescription.length == 1 &&
                (commandName.equals(Commands.todo.name()) ||
                        commandName.equals(Commands.deadline.name()) ||
                        commandName.equals(Commands.event.name()))) {
            throw new EmptyDetailsOfTaskError("The description of a " + commandName + " cannot be empty.");
        } else {
            throw new UnknownCommandException("I'm sorry, but I don't know what that means :-C");
        }
    }

    @Override
    public String toString() {
        return String.format(" %s | %s", getStatusIcon(), description);
    }
}
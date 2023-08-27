public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static Task createTask(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String taskType = inputArray[0];
        String inputSplit[];
        switch (TaskType.valueOf(taskType.toUpperCase())) {
            case TODO:
                try {
                    return new Todo (inputArray[1]);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Description of todo cannot be empty.");
                }
            case DEADLINE:
                try {
                    inputSplit = input.split(" /by ");
                    return new Deadline(inputSplit[0].substring(9), inputSplit[1]);
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                    throw new DukeException("Deadline should follow the format deadline <description> /by <date and time>");
                }
            case EVENT:
                try {
                    inputSplit = input.split(" /");
                    return new Event(inputSplit[0].substring(6), inputSplit[1].substring(5), inputSplit[2].substring(3));
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("Event should follow the format event <description> /from <start date and time> /to <end date and time>");
                }
            default:
                throw new DukeException("Task type is not recognised. Please use todo, deadline or event.");
        }

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setStatusIcon(String statusIcon) {
        this.isDone = statusIcon.equals("X");
    }
    
    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        if (isDone) {
            System.out.println("Task is already done.");
        } else {
            isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        }
        System.out.println(this.toString());
    }

    public void markAsUndone() {
        if (!isDone) {
            System.out.println("Task is already not done.");
        } else {
            isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toFileString();

    public abstract void fromFileString(String fileString);
}

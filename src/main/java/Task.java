public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task createTask(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        String taskType = inputArray[0];
        String inputSplit[];
        switch (taskType) {
            case "todo":
                return new Todo(inputArray[1]);
            case "deadline":
                inputSplit = input.split(" /by ");
                return new Deadline(inputSplit[0].substring(9), inputSplit[1]);
            case "event":
                inputSplit = input.split(" /");
                return new Event(inputSplit[0].substring(6), inputSplit[1].substring(5), inputSplit[2].substring(3));
            default:
                throw new DukeException("Action is not recognised. Please use todo, deadline or event.");
        }

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void markAsUndone() {
        isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static Task taskCon(String userInput) {
        if (userInput.startsWith("todo")) {
            return new ToDo(userInput.substring(5));
        } else if (userInput.startsWith("deadline")) {

            String[] splitInput = userInput.split("/by");
            String taskDescription = splitInput[0].substring(9).trim();
            String deadline = splitInput[1].trim();

            return new Deadline(taskDescription, deadline);

        } else if (userInput.startsWith("event")) {

            String[] splitInput = userInput.split("/from");
            String taskDescription = splitInput[0].substring(6).trim();
            String[] eventDetails = splitInput[1].split("/to");
            String eventStartTime = eventDetails[0].trim();
            String eventEndTime = eventDetails[1].trim();

            return new Event(taskDescription, eventStartTime, eventEndTime);
        } else {
            return new Task(userInput);
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }
}

package duke.tasks;

import duke.exceptions.IncompleteInputException;
import duke.exceptions.InvalidInputException;

public class Task {
    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setAsDone(Task task) {
        this.isDone = true;
        System.out.println("Whoa... are you kidding me? You did that!?" + "\n" + task);
    }

    public void setAsUndone(Task task) {
        this.isDone = false;
        System.out.println("HAHHAA! I knew it! You won't be able to!" + "\n" + task);
    }

    public String getDescription() {
        return this.description;
    }

    public static Task createTask(String command) throws IncompleteInputException, InvalidInputException {
        String[] splittedCommand = command.split(" ", 2);
        String taskType = splittedCommand[0];
        if(splittedCommand.length == 1) {
            if(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event")) {
                throw new IncompleteInputException("The description of a " + taskType + " cannot be empty.");
            } else {
                throw new InvalidInputException("That is some garbage input you have there.");
            }
        } else {
            String taskDescription = splittedCommand[1];
            switch (taskType) {
            case "todo":
                return new Todo(taskDescription);
            case "deadline":
                String[] deadlineSplit = taskDescription.split(" /by ");
                if(deadlineSplit.length == 1) {
                    throw new IncompleteInputException("Deadline what ah? Why leave empty?");
                }
                String deadlineDescription = deadlineSplit[0];
                String deadlineBy = deadlineSplit[1];
                return new Deadline(deadlineDescription, deadlineBy);
            case "event":
                String[] eventSplit = taskDescription.split(" /from ");
                if(eventSplit.length == 1) {
                    throw new IncompleteInputException("Event what ah? Why leave empty?");
                }
                String eventDescription = eventSplit[0];
                String eventFrom = eventSplit[1].split(" /to ")[0];
                String eventTo = eventSplit[1].split(" /to ")[1];
                return new Event(eventDescription, eventFrom, eventTo);
            default:
                throw new InvalidInputException("That is some garbage input you have there.");
            }

        }
    }


    @Override
    public String toString() {
        return "| " + this.getStatusIcon() + " | " + this.getDescription();
    }
}
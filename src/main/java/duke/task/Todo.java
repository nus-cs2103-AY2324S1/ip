package duke.task;

import java.io.IOException;

import duke.exception.EmptyDescriptionException;
import duke.util.Storage;

/**
 * Represents a Todo task.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Todo extends Task {

    /**
     * Instantiates a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Handles the creation of a todo task based on user input.
     *
     * @param userInput A valid user input for a todo task.
     * @throws EmptyDescriptionException If the description of the todo task is empty.
     * @throws IOException If there is an issue with saving the task.
     */
    public static String handleTodoTask(String userInput) throws EmptyDescriptionException, IOException {
        StringBuilder message = new StringBuilder();

        String taskDescription = userInput.trim().replaceFirst("todo", "").trim();
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        } else {
            Todo todoTask = new Todo(taskDescription);
            Storage.saveTask(todoTask, true);
            Storage.listOfTasks.add(todoTask);

            message.append("Got it. I've added this task:\n");
            message.append(String.format(" %s\n", todoTask));
            message.append(String.format("Now you have %d task(s) in the list.\n", Storage.listOfTasks.size()));
        }
        return message.toString();
    }
}

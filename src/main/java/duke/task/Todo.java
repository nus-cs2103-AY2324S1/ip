package duke.task;

import duke.Duke;
import duke.exception.EmptyDescriptionException;
import duke.util.Storage;

import java.io.IOException;

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
     * Constructs a todo task with the given description.
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
    public static void handleTodoTask(String userInput) throws EmptyDescriptionException, IOException {
        // use \u2639 or U+2639 to insert the sad face icon

        String taskDescription = userInput.trim().replaceFirst("todo", "").trim();
        if (taskDescription.isEmpty()) {
            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
        } else {
            Todo todoTask = new Todo(taskDescription);
            Storage.saveTask(todoTask, true);
            Storage.listOfTasks.add(todoTask); //duke.task.Todo <: duke.task.Task

            //Print details in the console
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Got it. I've added this task:");
            System.out.printf("       %s\n", todoTask.toString());
            System.out.printf("     Now you have %d task(s) in the list.\n", Storage.listOfTasks.size());
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello from\n" + "Bloooooooop");
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BloopBot");
        System.out.println(" What can I do for you?");
        System.out.println("\n____________________________________________________________");
    }

    public void showError(DukeException e) {
        System.out.println("Error:" + e.getMessage());
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showCommands() {
        System.out.println(" List of Commands: Add, Deadline, Event, Todo, Echo, Mark, Unmark, Delete, Bye");
        System.out.println("1. Add - Add a task to the list");
        System.out.println("2. Deadline - Add a task with a deadline");
        System.out.println("3. Event - Add an event task");
        System.out.println("4. Todo - Add a todo task");
        System.out.println("5. Echo - Echo a message");
        System.out.println("6. Mark - Mark a task as done");
        System.out.println("7. Unmark - Unmark a task as done");
        System.out.println("8. Delete - Delete a task");
        System.out.println("9. Bye - Exit the program");
    }

    public void showTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        taskList.displayTasks();
    }

}

package duke;

import java.util.ArrayList;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static void setTodo(String userOutput, TaskList inputList) throws EmptyException {
        try {
            String todoCommand = userOutput.split("todo")[1].strip();
            if (todoCommand.equals("")) {
                throw new EmptyException("todo");
            }
            Todo newTodo = new Todo(todoCommand);
            inputList.add(newTodo);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTodo);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EmptyException("todo");
        }
    }

    public static void newTodo(String text, ArrayList<Task> tasks) {
        Todo updatedTodo = new Todo(text);
        tasks.add(updatedTodo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

import java.util.ArrayList;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public static void setTodo(String userOutput, ArrayList<Task> inputList) throws EmptyException {
        try {
            Todo newTodo = new Todo(userOutput.split("todo")[1].strip());
            inputList.add(newTodo);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTodo);
            System.out.println("Now you have " + inputList.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new EmptyException("todo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

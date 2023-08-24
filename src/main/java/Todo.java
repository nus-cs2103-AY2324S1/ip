import java.util.ArrayList;
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }


    public static void addTodo(ArrayList<Task> list, String command) {

        String splitCommand[] = command.split(" ");

        try {
            if (splitCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }


        printHorizontalLine();
        System.out.println("Got it, I've added this task.");
        String description = command.substring(5);
        Task todoTask = new Todo(description);
        list.add(todoTask);
        System.out.println(todoTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
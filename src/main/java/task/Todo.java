package task;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import dukeException.DukeException;
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
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write(  "T | 0 | " + description + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list.add(todoTask);
        System.out.println(todoTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }




    public static void readData(ArrayList<Task> list, String data) {
        String splitTodo[] = data.split(" \\| ");

        Task todoTask = new Todo(splitTodo[2]);
        list.add(todoTask);
        if (splitTodo[1].equals("1")) {
            list.get(list.size()-1).markDoneNoPrint();
        }
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
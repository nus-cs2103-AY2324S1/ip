import java.util.ArrayList;
import java.util.regex.Pattern;
public class Task {
    protected String description;
    protected boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public void markDone() {
        if (isDone) {
            return;
        } else {
            printHorizontalLine();
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println( "[" + this.getStatusIcon() + "] " + this.getDescription() );
            printHorizontalLine();
        }
    }

    public void markDoneNoPrint() {
        if (isDone) {
            return;
        } else {
            this.isDone = true;
        }
    }

    public void markUndone() {
        printHorizontalLine();
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println( "[" + this.getStatusIcon() + "] " + this.getDescription() );
        printHorizontalLine();
    }

    public static void printList(ArrayList<Task> list) {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int printIndex = i + 1;
            System.out.println(printIndex + "." + list.get(i).toString());
        }
        printHorizontalLine();
    }

    public static void deleteTask(ArrayList<Task> list, String command) {
        String splitCommand[] = command.split(" ");
        try {
            if (splitCommand.length != 2) {
                throw new DukeException("Please insert a numerical value to delete");
            }
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            if (!pattern.matcher(splitCommand[1]).matches()) {
                throw new DukeException("Please enter a numerical value");
            } else {
                if (Integer.parseInt(splitCommand[1]) > list.size()) {
                    throw new DukeException("The delete value is out of range");
                }
            }

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        int indexToRemove = Integer.parseInt(command.substring(7)) - 1;
        Task taskToRemove = list.get(indexToRemove);
        System.out.println(taskToRemove);
        list.remove(indexToRemove);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

}
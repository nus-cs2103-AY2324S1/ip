package mypackage;
import java.util.ArrayList;

public class CustomList extends ArrayList<Task> {
    public CustomList() {
        super();
    }

    public void printList() {
        System.out.println(Global.LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + ". " + this.get(i));
        }
        System.out.println(Global.LINE);
    }

    public void markAsDone(String command, Database database, boolean... isFromDatabase) throws DukeException{   

        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(Integer.valueOf(command.substring(5)) - 1).markAsDone();
            return;
        }

        try {
            Integer.valueOf(command.substring(5));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Mark command must be followed by a space and an integer. ERR: STRING INDEX OUT OF BOUNDS.");
        } catch (NumberFormatException e) {
            throw new DukeException("Mark command must be followed by a space and an integer. ERR: NOT AN INTEGER.");
        } 

        int index = Integer.valueOf(command.substring(5));

        if (index == 0) {
            throw new DukeException("Mark command must be followed by a space and an integer. ERR: NO INTEGER.");
        } 
            
        try {
            this.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("There is no task at that index.");
        }

        System.out.println(Global.LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.get(index - 1));
        System.out.println(Global.LINE);

        try {
            database.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            System.out.println(Global.LINE);
            System.out.println(e);
            System.out.println(Global.LINE);
        }
    }

    public void markAsUndone(int index, Database database, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.get(index - 1).markAsUndone();
            return;
        }

        System.out.println(Global.LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        this.get(index - 1).markAsUndone();
        try {
            database.replaceLine(index, this.get(index - 1).toString());
        } catch (DukeException e) {
            System.out.println(Global.LINE);
            System.out.println(e);
            System.out.println(Global.LINE);
        }
        System.out.println(Global.LINE);
    }

    public void deleteTask(int index, Database database, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.remove(index - 1);
            return;
        }

        System.out.println(Global.LINE);
        System.out.println("Noted. I've removed this task:");
        Task t = this.remove(index - 1);
        System.out.println(" " + t);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");

        try {
            database.deleteLine(index);
        } catch (DukeException e) {
            System.out.println(Global.LINE);
            System.out.println(e);
            System.out.println(Global.LINE);
        }
        System.out.println(Global.LINE);
    }

    public void addTask(Task task, Database database, boolean... isFromDatabase) {
        if (isFromDatabase.length > 0 && isFromDatabase[0]) {
            this.add(task);
            return;
        }

        System.out.println(Global.LINE);
        System.out.println("Got it. I've added this task:");
        this.add(task);
        System.out.println(" " + task);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");
        try {
            database.writeData(task.toString());
        } catch (DukeException e) {
            System.out.println(Global.LINE);
            System.out.println(e);
            System.out.println(Global.LINE);
        }
        System.out.println(Global.LINE);
    }
}

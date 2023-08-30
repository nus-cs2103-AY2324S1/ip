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

    public void markAsDone(String command) throws DukeException{
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
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("There is no task at that index.");
        }

        System.out.println(Global.LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.get(index - 1));
        System.out.println(Global.LINE);
    }

    public void markAsUndone(int index) {
        System.out.println(Global.LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        this.get(index - 1).markAsUndone();
        System.out.println(" " + this.get(index - 1));
        System.out.println(Global.LINE);
    }

    public void deleteTask(int index) {
        System.out.println(Global.LINE);
        System.out.println("Noted. I've removed this task:");
        Task t = this.remove(index - 1);
        System.out.println(" " + t);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");
        System.out.println(Global.LINE);
    }

    public void addTask(Task task) {
        System.out.println(Global.LINE);
        System.out.println("Got it. I've added this task:");
        this.add(task);
        System.out.println(" " + task);
        System.out.println("Now you have " + Integer.toString(this.size()) + " " + (this.size() == 1 ? "task" : "tasks") + " in the list.");
        System.out.println(Global.LINE);
    }
}

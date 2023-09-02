import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class Ui {
    public Ui() {

    }

    public void specify() {
        System.out.println("Please specify the content of the todo list");
    }
    public void remove(String task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }
    public void numExc() {
        System.out.println("to pick which task to do, please input an integer");
    }
    public void indexOut() {
        System.out.println("currently, you task list does not the task with the index you just inputted");
    }

    public void list(List<Task> list) {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        System.out.println("____________________________________________________________");
        for (Task thing: list) {
            System.out.println(index +". " + thing.toString());
            index++;
        }
        System.out.println("____________________________________________________________");

    }

    public void blank() {
        System.out.println("Don't just input blank space");
    }

    public void format() {
        System.out.println("Please input the correct format");
    }

    public void mark(int size, String onetwo, String description) {
        System.out.println("Noted. I've marked this task: ");
        System.out.println("    [X] " + description);
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    public void unmark(int size, String onetwo, String description) {
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println("    [ ] " + description);
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    public void currentlist(int size, String onetwo) {
        System.out.println("Now you have " + size + onetwo +  " in the list");
    }

    public void add(Task task, int size, String onetwo) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + onetwo +  " in the list");
        System.out.println("____________________________________________________________");
    }

    public void bye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm Zenith" );
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
}

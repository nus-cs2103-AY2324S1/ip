import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private final String name = "Duck";
    private final String exitMessage = "bye";

    protected ArrayList<Task> list = new ArrayList<>();
    private Task task = new Task();
    public void welcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon !");
        System.out.println("____________________________________________________________");
    }

    public boolean isBye(String str) {
        return str.equalsIgnoreCase(exitMessage);
    }

    public boolean checkList(String str) {
        return str.equalsIgnoreCase("list");
    }

    public boolean checkMark(String str) { return str.contains("mark");}

    public boolean checkUnMark(String str) { return str.contains("unmark");}



    public void typeMessage() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!isBye(str)){
            //echo(str);
            if (checkList(str)) {
                display();
            } else if (checkUnMark(str)) {
                setUndone(str);
            } else if (checkMark(str)) {
                setDone(str);
            } else {
                echo(str);
                list.add(new Task(str));
            }
            str = sc.nextLine();
        }
        if (isBye(str)) {
            exit();
        }
    }

    public void display() {
        int count = 0;
        int serial = 1;
        System.out.println("____________________________________________________________");
        while (count < list.size()) {
            System.out.println(serial + "." + list.get(count).Box() + " " + list.get(count));
            count++;
            serial++;
        }
        System.out.println("____________________________________________________________");
    }

    public void echo(String str) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + str);
        System.out.println("____________________________________________________________");
    }

    public void setDone(String str) {
        int index = task.getIndexOfMark(str);
        list.get(index).markAsDone();
    }

    public void setUndone(String str) {
        int index = task.getIndexOfUnmark(str);
        list.get(index).markUndone();
    }
    public static void main(String[] args) {
        Duke duck = new Duke();
        duck.welcomeMessage();
        duck.typeMessage();
    }
}
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    //        System.out.println("Hello from\n" + logo);
    public static String divider = "____________________________________________________________";
    public static void main(String[] args) {
        System.out.println(divider);
        System.out.println("Hello! I'm BanterBot");
        System.out.println("What can I do for you lmao?");
        System.out.println(divider);

        ToDoList toDoList = new ToDoList();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                toDoList.print();
            } else {
                toDoList.add(command);
            }
            command = scanner.nextLine();
        }
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon lol!");
        System.out.println(divider);
    }
}

class ToDoList {
    private List<String> list;
    public String divider = "____________________________________________________________";
    public ToDoList() {
        this.list = new ArrayList<String>();
    }

    void add(String todo) {
        list.add(todo);
        System.out.println(divider);
        System.out.println("added: " + todo);
        System.out.println(divider);
    }

    void print() {
        System.out.println(divider);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i));
        }
        System.out.println(divider);;
    }
}
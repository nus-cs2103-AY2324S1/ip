import java.util.*;

import javafx.concurrent.Task;

public class Duke {
    public static String partition = "------------------------------------------------------------";
    public static ArrayList<String> taskList = new ArrayList<String>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println(partition);
        System.out.println("Hello! I'm Edna-Duke.");
        System.out.println("What can I do for you?");
        System.out.println(partition);
        
        String input = sc.next();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                print();
            } else {
                add(input);
            }
            System.out.println(partition);
            input = sc.next();
        }
        exit();
    }

    public static void add(String input) {
        taskList.add(input);
        System.out.println("added: " + input);
    }

    public static void print() {
        int index = 1;
        for (String task: taskList) {
            System.out.println(index + ". " + task);
            index ++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }
}

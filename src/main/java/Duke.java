import java.util.*;

public class Duke {
    public static String partition = "------------------------------------------------------------";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println(partition);
        System.out.println("Hello! I'm Edna-Duke.");
        System.out.println("What can I do for you?");
        System.out.println(partition);
        
        String input = sc.next();
        while(!input.equals("bye")) {
            System.out.println(input);
            System.out.println(partition);
            input = sc.next();
        }
        exit();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }
}

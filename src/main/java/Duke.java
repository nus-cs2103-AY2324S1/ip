import javax.swing.*;
import java.util.Scanner;
public class Duke {
    boolean running = true;
    Scanner scaner = new Scanner(System.in);

    public void list(){
            while (running) {
                String li = scaner.next();
                if(li.equals("bye")){
                   bye();
                } else {
                    System.out.println(li);
                }
            }
    }
    public void bye() {
        running = false;
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        System.out.println("Hello! I am Adam\n" + "What can I do for you?\n");
        Duke test = new Duke();
        test.list();
    }
}

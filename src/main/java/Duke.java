import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    ArrayList<String> array = new ArrayList<>();
    boolean running = true;
    Scanner scaner = new Scanner(System.in);

    public void list(){
        int count = 1;
        for(String item: array){
            System.out.println(count + ". " + item);
            count++;
        }
    }

    public void start(){
            while (running) {
                String li = scaner.next();
                if(li.equals("bye")){
                   bye();
                } else if(li.equals("list")) {
                    list();
                } else{
                    array.add(li);
                        System.out.println("added: " + li);
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
        test.start();
    }
}

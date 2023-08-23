import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    ArrayList<Task> array = new ArrayList<>();
    boolean running = true;
    Scanner scaner = new Scanner(System.in);

    public void add(String text){
        array.add(new Task(text));
        System.out.println("added: " + text);
    }

    public void list(){
        System.out.println("Here are the task in your lists: ");
        int count = 1;
        for(Task item: array){
            System.out.println(count + ". " + item.toString());
            count++;
        }
    }

    public void start(){
            while (running) {
                String li = scaner.nextLine();
                String[] tokens = li.split(" ");
                //check if its divided by a space or not
                if(tokens.length == 2){
                    // check if its marked
                    if(tokens[0].equals("mark")){
                        //check if it only contains numbers
                        if(tokens[1].matches("[0-9]+")) {
                            int number = Integer.valueOf(tokens[1]);
                            if(number <= array.size()){
                                Task curr = array.get(number-1);
                                curr.markAsDone();
                            } else {
                                System.out.println("This task does not exist");
                            }
                        } else {
                            this.add(li);
                        }
                        // check if its unmarked
                    } else if(tokens[0].equals("unmark")){
                        // chcek if it contains numbers
                        if(tokens[1].matches("[0-9]+")) {
                            int number = Integer.valueOf(tokens[1]);
                            if(number <= array.size()){
                                Task curr = array.get(number-1);
                                curr.unmarkAsDone();
                            } else {
                                System.out.println("This task does not exist");
                            }
                        } else {
                            this.add(li);
                        }
                    } else{
                       this.add(li);
                    }
                } else {
                    switch (li) {
                        case "bye":
                            bye();
                            break;
                        case "list":
                            list();
                            break;
                        default:
                           this.add(li);
                    }
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

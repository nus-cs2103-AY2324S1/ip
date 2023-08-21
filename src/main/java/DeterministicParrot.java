import java.io.PrintWriter;
import java.util.*;

public class DeterministicParrot {
    private class Task{
        private String name;
        private boolean isDone;
        Task(String description){
            this.name = description;
            this.isDone = false;
        }
        public String getName(){
            return this.name;
        }
        public boolean getIsDone(){
            return this.isDone;
        }
        public void markAsDone(){
            this.isDone = true;
        }
        public void markAsUndone(){
            this.isDone = false;
        }
        @Override
        public String toString(){
            return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
        }

    }

    //init by setting input and output
    Scanner s;
    PrintWriter pw;
    List<Task> list;
    DeterministicParrot(){
        this.list = new LinkedList<>();
        s = new Scanner(System.in);
        pw = new PrintWriter(System.out, true);
    }
    DeterministicParrot(Scanner s, PrintWriter pw){
        this.list = new LinkedList<>();
        this.s = s;
        this.pw = pw;
    }
    /**
     * Prints a line
     */


    private void printDash() {
        this.pw.println("    ____________________________________________________________");
    }

    /**
     *  Hello! I'm [YOUR CHATBOT NAME]
     *  What can I do for you?
     */
    private void greet() {
        printDash();
        this.pw.println("     " + "Hello! I'm DeterministicParrot");
        this.pw.println("     " +"What can I do for you?");
        printDash();
    }

    private void echo(String s) {
        printDash();
        this.pw.println("     " + s);
        printDash();
    }
    private void bye() {
        printDash();
        this.pw.println("     " + "Bye. Hope to see you again soon!");
        printDash();
    }
    private void printList(){
        printDash();
        for(int i = 0; i < this.list.size(); i++){
            this.pw.println("     " + (i+1) + ". " + this.list.get(i));
        }
        printDash();
    }
    private void addToList(String s){
        //create task
        Task t = new Task(s);
        this.list.add(t);
        printDash();
        this.pw.println("    " + "added: " + s);
        printDash();

    }
    private void markAsDone(int i){
        //TODO: handle out of bounds
        this.list.get(i-1).markAsDone();
        printDash();
        this.pw.println("    " + "Nice! I've marked this task as done:");
        this.pw.println("       " + this.list.get(i-1));
        printDash();
    }
    private void markAsUndone(int i){
        //TODO: handle out of bounds
        this.list.get(i-1).markAsUndone();
        printDash();
        this.pw.println("    " + "OK, I've marked this task as not done yet:\n");
        this.pw.println("       " + this.list.get(i-1));
        printDash();
    }
    private void poll(){
        this.greet();
        while(true){
            String input = s.nextLine();
            if(input.equals("list")){
                printList();
            }
            else if(input.equals("bye")){
                break;
            }

            //TODO: what happens if empty input?
            else{
                String[] tok = input.split(" ");
                if(tok[0].equals("mark")){
                    int i = Integer.parseInt(tok[1]);
                    this.markAsDone(i);
                }
                else if(tok[0].equals("unmark")){
                    int i = Integer.parseInt(tok[1]);
                    this.markAsUndone(i);
                }
                else{
                    this.addToList(input);
                }
            }
        }
        this.bye();
    }

    public static void main(String[] args) {
        DeterministicParrot parrot = new DeterministicParrot();
        parrot.poll();
    }
}

import java.io.PrintWriter;
import java.util.*;

public class DeterministicParrot {

    //init by setting input and output
    Scanner s;
    PrintWriter pw;
    List<String> list;
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
                this.list.add(input);
                this.pw.println("     "+"added: " + input);
            }
        }
        this.bye();
    }

    public static void main(String[] args) {
        DeterministicParrot parrot = new DeterministicParrot();
        parrot.poll();
    }
}

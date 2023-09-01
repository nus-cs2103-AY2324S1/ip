import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Ui {
    private Scanner s;
    private PrintWriter pw;
    Ui(Scanner s, PrintWriter pw){
        this.s = s;
        this.pw = pw;
    }
    Ui(){
        this.s = new Scanner(System.in);
        this.pw = new PrintWriter(System.out, true);
    }
    public String readCommand(){
        return s.nextLine();
    }

    public void greet() {
        this.printDash();
        this.pw.println("     " + "Hello! I'm DeterministicParrot");
        this.pw.println("     " +"What can I do for you?");
        this.printDash();
    }
    public void bye(){
        this.pw.println("     " + "Bye. Hope to see you again soon!");
    }

    public String readLine(){
        return this.s.nextLine().trim();
    }
    public void println(String s){
        this.pw.println(s);
    }
    public void printError(Exception e){
        this.pw.println(e.getMessage());
    }
    public void printDash() {
        this.pw.println("    ____________________________________________________________");
    }


}

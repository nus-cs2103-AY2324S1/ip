import java.io.PrintWriter;
import java.util.*;
import java.util.function.Consumer;

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
    private Scanner s;
    private PrintWriter pw;
    private List<Task> list;
    private Map<String, Consumer<String[]>> commandHandlers = new HashMap<>();

    DeterministicParrot(){
        this.list = new LinkedList<>();
        s = new Scanner(System.in);
        pw = new PrintWriter(System.out, true);
        this.initCommandHandlers();
    }
    DeterministicParrot(Scanner s, PrintWriter pw){
        this.list = new LinkedList<>();
        this.s = s;
        this.pw = pw;
        this.initCommandHandlers();
    }
    private void initCommandHandlers() {
        commandHandlers.put("list", args -> printList());
        commandHandlers.put("bye", args -> bye());
        commandHandlers.put("mark", args -> {
            int i = Integer.parseInt(args[1]);
            markAsDone(i);
        });
        commandHandlers.put("unmark", args -> {
            int i = Integer.parseInt(args[1]);
            markAsUndone(i);
        });
    }



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
    private void handleCommand(String input) {
        String[] tokens = input.split(" ");
        Consumer<String[]> cmdHandler = this.commandHandlers.get(tokens[0]);
        if (cmdHandler != null) {
            cmdHandler.accept(tokens);
        } else {
            addToList(input);
        }
    }

    private void poll() {
        this.greet();
        while (true) {
            String input = this.s.nextLine().trim();
            if (input.isEmpty()) {
                continue; // Skip empty input
            }
            if(input.equals("bye")){
                bye();
                break;
            }
            handleCommand(input);
        }
    }


    public static void main(String[] args) {
        DeterministicParrot parrot = new DeterministicParrot();
        parrot.poll();
    }
}

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
    private class ToDo extends Task{
        ToDo(String s){
            super(s);
        }
        @Override
        public String toString(){
            return String.format("[T]%s", super.toString());
        }
    }
    private class Deadline extends Task{
        private String by;
        Deadline(String s, String by){
            super(s);
            this.by = by;
        }
        @Override
        public String toString(){
            return String.format("[D]%s (by: %s)", super.toString(), this.by);
        }
    }
    private class Event extends Task{
        private String timeStart;
        private String timeEnd;
        Event(String name, String timeStart, String timeEnd){
            super(name);
            this.timeStart = timeStart;
            this.timeEnd = timeEnd;
        }
        @Override
        public String toString(){
            return String.format("[E]%s (from: %s to: %s)", super.toString(), this.timeStart, this.timeEnd);
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
        commandHandlers.put("todo", args -> {
            addToDo(args);
        });
        commandHandlers.put("deadline", args -> {
            addDeadline(args);
        });
        commandHandlers.put("event", args -> {
            addEvent(args);
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
        this.pw.println("     " + "Here are the tasks in your list:");
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

    private void addToDo(String[] args) {
        printDash();
        String taskDescription = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ToDo t = new ToDo(taskDescription);
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
        printDash();
    }

    private void addDeadline(String[] args) {
        printDash();
        int byIndex = Arrays.asList(args).indexOf("/by");
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        Deadline t = new Deadline(taskName, deadline);
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
        printDash();
    }

    private void addEvent(String[] args) {
        printDash();
        int fromIndex = Arrays.asList(args).indexOf("/from");
        int toIndex = Arrays.asList(args).indexOf("/to");
        String eventName = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex));
        String startTime = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length));

        Event t = new Event(eventName, startTime, endTime);
        this.list.add(t);
        this.pw.println("    " + "Got it. I've added this task:");
        this.pw.println("       " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
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

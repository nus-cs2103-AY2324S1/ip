import java.io.PrintWriter;
import java.util.*;

class DeterministicParrotException extends Exception {
    public DeterministicParrotException(String message) {
        super(message);
    }
}

@FunctionalInterface
interface CheckedConsumer<T> {
    void accept(T t) throws DeterministicParrotException;
}

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
    private Map<String, CheckedConsumer<String[]>> commandHandlers = new HashMap<>();

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
            markAsDone(args);
        });
        commandHandlers.put("unmark", args -> {
            markAsUndone(args);
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
        commandHandlers.put("delete", args -> {
            deleteTask(args);
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
        this.pw.println("     " + "Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i++){
            this.pw.println("     " + (i+1) + ". " + this.list.get(i));
        }
    }
    private void addToList(String s){
        //create task
        Task t = new Task(s);
        this.list.add(t);
        this.pw.println("    " + "added: " + s);

    }
    private void markAsDone(String args[]) throws DeterministicParrotException {
        if(args.length < 2){
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        if (i <= 0 || i > list.size()) {
            throw new DeterministicParrotException("Invalid task number.");
        }
        this.list.get(i-1).markAsDone();
        this.pw.println("    " + "Nice! I've marked this task as done:");
        this.pw.println("       " + this.list.get(i-1));
    }
    private void markAsUndone(String toks[]) throws DeterministicParrotException {
        if(toks.length < 2){
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(toks[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        if (i <= 0 || i > list.size()) {
            throw new DeterministicParrotException("Invalid task number.");
        }
        this.list.get(i-1).markAsUndone();
        this.pw.println("    " + "OK, I've marked this task as not done yet:\n");
        this.pw.println("       " + this.list.get(i-1));
    }

    private void addToDo(String[] args) throws DeterministicParrotException {
        if (args.length < 2) {
            throw new DeterministicParrotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ToDo t = new ToDo(taskDescription);
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void addDeadline(String[] args) throws DeterministicParrotException {
        int byIndex = Arrays.asList(args).indexOf("/by");
        if (byIndex == -1 || byIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid deadline format. Use /by to specify deadline time.");
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        Deadline t = new Deadline(taskName, deadline);
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void addEvent(String[] args) throws DeterministicParrotException {
        int fromIndex = Arrays.asList(args).indexOf("/from");
        int toIndex = Arrays.asList(args).indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex || fromIndex == args.length - 1 || toIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid event format. Use /from and /to to specify event time.");
        }
        String eventName = String.join(" ", Arrays.copyOfRange(args, 1, fromIndex));
        String startTime = String.join(" ", Arrays.copyOfRange(args, fromIndex + 1, toIndex));
        String endTime = String.join(" ", Arrays.copyOfRange(args, toIndex + 1, args.length));

        Event t = new Event(eventName, startTime, endTime);
        this.list.add(t);
        this.pw.println("    " + "Got it. I've added this task:");
        this.pw.println("       " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
    }
    private void deleteTask(String args[]) throws DeterministicParrotException {
        if(args.length < 2){
            throw new DeterministicParrotException("Please provide a task number.");
        }
        int i;
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DeterministicParrotException("Please provide a valid task number.");
        }
        if (i <= 0 || i > list.size()) {
            throw new DeterministicParrotException("Invalid task number.");
        }
        Task t = this.list.remove(i-1);
        this.pw.println("    " + "Noted. I've removed this task:");
        this.pw.println("       " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void handleCommand(String input){
        String[] tokens = input.split(" ");
        CheckedConsumer<String[]> cmdHandler = this.commandHandlers.get(tokens[0]);
        this.printDash();
        try {
            if (cmdHandler != null) {
                cmdHandler.accept(tokens);
            } else {
                throw new DeterministicParrotException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DeterministicParrotException de) {
            this.pw.println("     " + de.getMessage());
        }
        finally{
            this.printDash();
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

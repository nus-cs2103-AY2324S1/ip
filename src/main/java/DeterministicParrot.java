import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


@FunctionalInterface
interface CheckedConsumer<T> {
    void accept(T t) throws DeterministicParrotException;
}

public class DeterministicParrot {
    //static variable storing the path to data file
    private static final String DATA_FILE_PATH = "./data/data.txt";

    //init by setting input and output
    private Scanner s;
    private PrintWriter pw;
    private List<Task> list;
    private Map<String, CheckedConsumer<String[]>> commandHandlers = new HashMap<>();
    DeterministicParrot(){
        this.list = new LinkedList<>();
        try{
            loadData();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
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
    //takes a datetime and prints it in a certain format
    private void loadData() throws FileNotFoundException, DateTimeParseException {
        File file = new File(DATA_FILE_PATH);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] data = line.split(" \\| ");
                    switch (data[0]) {
                        case "T":
                            ToDo todo = new ToDo(data[2]);
                            if (data[1].equals("1")) todo.markAsDone();
                            list.add(todo);
                            break;
                        case "D":
                            Deadline deadline = new Deadline(data[2], data[3]);
                            if (data[1].equals("1")) deadline.markAsDone();
                            list.add(deadline);
                            break;
                        case "E":
                            String[] time = data[3].split(" ");
                            Event event = new Event(data[2], time[0], time[1]);
                            if (data[1].equals("1")) event.markAsDone();
                            list.add(event);
                            break;
                    }
                }
                //TODO: might be redundant? might suffcie to just check if file exists.
            } catch (FileNotFoundException e) {
                this.pw.println("File not found. Creating new file...");
            }
        } else {
            file.getParentFile().mkdirs();
        }
    }

    private void saveData() {
        try (PrintWriter fileWriter = new PrintWriter(DATA_FILE_PATH)) {
            for (Task task : list) {
                if (task instanceof ToDo) {
                    fileWriter.println("T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getName());
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    fileWriter.println("D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getName() + " | " + DPUtils.saveFormatDateTime(deadline.by));
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    fileWriter.println("E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getName() + " | " + DPUtils.saveFormatDateTime(event.timeStart)+ " " + DPUtils.saveFormatDateTime(event.timeEnd));
                }
            }
        } catch (FileNotFoundException e) {
            // handle error
            System.out.println(e.getMessage());
        }
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
    private void addToList(Task t){
        this.list.add(t);
        saveData();
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("       " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
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
        saveData();
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
        saveData();
    }

    private void addToDo(String[] args) throws DeterministicParrotException {
        if (args.length < 2) {
            throw new DeterministicParrotException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String taskDescription = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        ToDo t = new ToDo(taskDescription);
        addToList(t);
        /*
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
         */
    }

    private void addDeadline(String[] args) throws DeterministicParrotException {
        int byIndex = Arrays.asList(args).indexOf("/by");
        if (byIndex == -1 || byIndex == args.length - 1) {
            throw new DeterministicParrotException("Invalid deadline format. Use /by to specify deadline time.");
        }
        String taskName = String.join(" ", Arrays.copyOfRange(args, 1, byIndex));
        String deadline = String.join(" ", Arrays.copyOfRange(args, byIndex + 1, args.length));

        Deadline t = new Deadline(taskName, deadline);
        /*
        this.list.add(t);
        this.pw.println("     " + "Got it. I've added this task:");
        this.pw.println("      " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
         */
        addToList(t);
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
        /*
        this.list.add(t);
        this.pw.println("    " + "Got it. I've added this task:");
        this.pw.println("       " + t);
        this.pw.println("     " + "Now you have " + this.list.size() + " tasks in the list.");
         */
        addToList(t);
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
        } catch (Exception de) {
            this.pw.println("     " + de.getMessage());
            this.pw.println("     " + de.getCause());
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
                saveData();
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

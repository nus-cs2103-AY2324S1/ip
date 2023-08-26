import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

abstract class TaskType {
    public abstract String getTaco(DateTimeFormatter formatter);
}
class Todo extends TaskType {
    public Todo(String x, ArrayList<DateTimeFormatter> formatters){

    }
    public String toString(){
        return "T";
    }
    public String getTaco(DateTimeFormatter formatter){
        return "";
    }

}
class Deadline extends TaskType {
    private LocalDateTime dl;
    public Deadline(String x, ArrayList<DateTimeFormatter> formatters)  throws DukeException{
        String[] s = x.split("\\s+");
        boolean ok = false;
        String a = Utils.getString(s, 1, s.length);
        if(a.isEmpty()) throw new DukeException("/by description cannot be empty");

        for(DateTimeFormatter fr : formatters){
            try{
                this.dl = LocalDateTime.parse(a, fr);
                break;
            }catch(DateTimeParseException e){}

        }
        if(dl == null) throw new DukeException("/by date format count not be recognized");

    }
    public String toString(){
        return "D";
    }
    public String getTaco(DateTimeFormatter formatter){
        return "(by: " +  dl.format(formatter) + ")";
    }
}
class Event extends TaskType {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String x, ArrayList<DateTimeFormatter> formatters)  throws DukeException{
        String[] s = x.split("\\s+");
        boolean ok = false;
        for(int i=1;i<s.length;i++){
            if(s[i].equals("to:")){
                String a = Utils.getString(s, 1, i);
                String b = Utils.getString(s, i+1, s.length);
                if(a.isEmpty()) throw new DukeException("/from description cannot be empty");
                if(b.isEmpty()) throw new DukeException("/to description cannot be empty");
                for(DateTimeFormatter fr : formatters){
                    try{
                        this.from = LocalDateTime.parse(a, fr);
                        break;
                    }catch(DateTimeParseException e){}

                }
                for(DateTimeFormatter fr : formatters){
                    try{
                        this.to = LocalDateTime.parse(b, fr);
                        break;
                    }catch(DateTimeParseException e){}
                }

                ok = true;
            }
        }
        if(from == null) throw new DukeException("/from date format count not be recognized");
        if(to == null) throw new DukeException("/to date format count not be recognized");
        if(!ok) throw new DukeException("keyword /to was not detected.");
    }
    public String toString(){
        return "E";
    }
    public String getTaco(DateTimeFormatter formatter){
        return "(from: " +  from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
class DukeException extends Exception {
    public DukeException(String a){
        super(a);
    }
}
class Item{
    private String task;
    private boolean completed;
    private TaskType tt;

    String dl;
    public Item(String task, boolean completed, TaskType tt, String dl){
        this.task = task;
        this.completed = completed;
        this.tt = tt;
        this.dl = dl;
    }
    public String toString(DateTimeFormatter fmt){
        String cBox = "[" + (completed ? "X" : " ") + "] ";
        String tBox = "[" + tt.toString() + "]";
        String taco = this.tt.getTaco(fmt);
        return  tBox + cBox + " " + task + " "  + taco;
    }
    public void setCompleted(boolean x){
        completed = x;
    }

    public String saveStringRep(){
        return this.task + " " + String.valueOf(completed) + " " + tt.toString() + " " + (dl == null ? "" : dl);
    }

}

class Utils {
    public static String getString(String[] a, int x, int y){
        String res = "";
        for(int i=x;i<y;i++){
            if(a[i].charAt(0) == '/') res += a[i].substring(1) + ":";
            else res += a[i];
            if(i!=y-1) res += " ";
        }
        return res;
    }
}



class Ui{
    private DTFormat dtf;
    public Ui(DTFormat dtf){
        this.dtf = dtf;
    }
    public void print(String msg){
        System.out.println(msg);
    }

    public void printItems(ArrayList<Item> items){
        for(int i=0;i<items.size();i++){
            print((i + 1) + "." + itemToString(items.get(i)));
        }
    }

    public void printIntro(){
        print("Hello I'm Robot!");
        print("What can I do for you?");
    }

    public void printExit(){
        print("Bye! Hope to see you again soon!");
    }

    public void showLoadingError(){
        print("An error occurred while loading data into your list, starting with a blank list...");
    }

    public String itemToString(Item a){
        return a.toString(dtf.getOutFormatter());
    }


}

class DTFormat{
    private ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter out_date_format;
    public DTFormat(){
        formatters = new ArrayList<>();
        DateTimeFormatter f1 = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-M-d[ H:m]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();

        DateTimeFormatter f2 = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy[ Hmm]")
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .toFormatter();


        formatters.add(f1);
        formatters.add(f2);
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy H"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        out_date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    }
    public ArrayList<DateTimeFormatter> getFormatters(){
        return formatters;
    }
    public void addReadFormat(String k){
        formatters.add(DateTimeFormatter.ofPattern(k));
    }
    public void removeReadFormat(int x){
        formatters.remove(x);
    }
    public DateTimeFormatter getOutFormatter(){
        return out_date_format;
    }
    public void setOutFormatter(String k){
        out_date_format = DateTimeFormatter.ofPattern(k);
    }

}

class TaskList{
    ArrayList<Item> items;
    private static TaskType parseTaskType(String x, String y, ArrayList<DateTimeFormatter> formatters)  throws DukeException{
        return x.equals("T") ? new Todo(y, formatters) : x.equals("D") ? new Deadline(y, formatters) : new Event(y, formatters);
    }
    public TaskList(){
        items = new ArrayList<>();
    }
    public TaskList(ArrayList<String> ss, DTFormat dtf) throws DukeException{
        items = new ArrayList<>();
        for(String s : ss){
            String[] d = s.split("\\s+");
            String r = (d.length > 3 ? Utils.getString(d, 3, d.length) : null);
            TaskType k = parseTaskType(d[2], r , dtf.getFormatters());
            items.add(new Item(d[0], Boolean.parseBoolean(d[1]), k,  r));
        }
    }

    public int getSize(){
        return items.size();
    }

    public Item getItem(int x){
        return items.get(x);
    }

    public void addItem(Item y){
        items.add(y);
    }
    public void removeItem(int x){
        items.remove(x);
    }

    public ArrayList<Item> getItems(){
        return items;
    }


}

class Storage{
    ArrayList<String> its;
    private String data_file;
    public Storage(){
        its = new ArrayList<>();
        data_file = "./src/main/data/data.txt";
    }
    public Storage(String data_file){
        File f = new File(data_file);
        this.data_file = data_file;
        its = new ArrayList<>();
        try{
            Scanner reader = new Scanner(f);
            while(reader.hasNextLine()){
                its.add(reader.nextLine());
            }
            reader.close();
        }catch(FileNotFoundException fe){
            System.out.println("Data file not found, attempting to create one...");
            try{
                f.createNewFile();
                System.out.println("Data file successfully created.");
            }catch(IOException e){
                System.out.println("Data storage could be created, any items added to the app will be deleted after program exit.");
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> load(){
        return its;
    }

    public void save( ArrayList<Item> items){
        try{
            FileWriter fileWriter = new FileWriter(data_file);
            for(int i=0;i<items.size();i++){
                fileWriter.write(items.get(i).saveStringRep() + "\n");
            }
            fileWriter.close();
        }catch(IOException e){
            System.out.println("Something went wrong while saving your list items.");
        }

    }


}

class Parser{
    DTFormat dtf;
    Ui ui;
    public Parser(DTFormat dtf, Ui ui){
        this.dtf = dtf;
        this.ui = ui;
    }
    public boolean parse(String userInput, TaskList tl) throws DukeException{
        String[] splitStr = userInput.split("\\s+");

            if(userInput.equals("bye")) return false;

            if(userInput.equals("list")){
                System.out.println("Here are the tasks in your list:");
                ui.printItems(tl.getItems());

            }else if(splitStr[0].equals("mark")){
                if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
                int x = Integer.parseInt(splitStr[1])-1;
                if(x < 0 || x+1 > tl.getSize())  throw new DukeException("Index is out of list range.");
                tl.getItem(x).setCompleted(true);
                ui.print("Nice! I've marked this task as done:");
                ui.print(ui.itemToString(tl.getItem(x)));

            }else if(splitStr[0].equals("unmark")){
                if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
                int x = Integer.parseInt(splitStr[1])-1;
                if(x < 0 || x+1 > tl.getSize())  throw new DukeException("Index is out of list range.");
                tl.getItem(x).setCompleted(false);
                ui.print("Ok, I've marked this task as not done yet:");
                ui.print(ui.itemToString(tl.getItem(x)));

            }else if(splitStr[0].equals("remove")){
                if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
                int x = Integer.parseInt(splitStr[1]) - 1;
                if(x < 0 || x+1 > tl.getSize())  throw new DukeException("Index is out of list range.");
                ui.print("Ok, the following item was removed:");
                ui.print(ui.itemToString(tl.getItem(x)));
                tl.removeItem(x);

            } else if(splitStr[0].equals("todo")){
                if(splitStr.length == 1){
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                tl.addItem(new Item(Utils.getString(splitStr, 1, splitStr.length), false,
                        new Todo(null, dtf.getFormatters()),  null));
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            }else if(splitStr[0].equals("deadline")){
                boolean x = false;
                for(int i=1;i<splitStr.length;i++){
                    if(splitStr[i].equals("/by")) {
                        String dl = Utils.getString(splitStr, i, splitStr.length);
                        TaskType tt = new Deadline(dl, dtf.getFormatters());
                        String task = Utils.getString(splitStr, 1, i);
                        if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                        if(dl.isEmpty()) throw new DukeException("Deadline cannot be empty.");
                        tl.addItem(new Item(task, false, tt, dl));
                        x = true;
                        break;
                    }
                }
                if(!x){
                    throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                }
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            }else if(splitStr[0].equals("event")){
                boolean x = false;
                for(int i=1;i<splitStr.length;i++){
                    if(splitStr[i].equals("/from")) {

                        String dl = Utils.getString(splitStr, i, splitStr.length);
                        TaskType tt = new Event(dl, dtf.getFormatters());
                        String task = Utils.getString(splitStr, 1, i);
                        if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                        if(dl.isEmpty()) throw new DukeException("Event dates cannot be empty.");
                        tl.addItem(new Item(task, false, tt,  dl));
                        x = true;
                        break;
                    }
                }
                if(!x){
                    throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                }
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");
            }else{
                throw new DukeException("Sorry, I don't understand that command");
            }

        return true;
    }

}

public class Duke {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private DTFormat dtf;
    static Scanner sc = new Scanner(System.in);





    public void run(){
        ui.printIntro();
        while(true){
            String userInput = sc.nextLine();
            try{
                if(!parser.parse(userInput, tasks)) break;
            }catch(DukeException e){
                ui.print(e.toString());
            }

        }
        storage.save(tasks.getItems());
        ui.printExit();
    }
    public Duke(String filepath){
        dtf = new DTFormat();
        ui = new Ui(dtf);
        storage = new Storage(filepath);
        parser = new Parser(dtf, ui);
        try{
            tasks = new TaskList(storage.load(), dtf);
        }catch(DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }




    public static void main(String[] args) {

        new Duke("./src/main/data/data.txt").run();

    }
}

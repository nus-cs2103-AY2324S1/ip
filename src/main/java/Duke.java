import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            if(s[i].equals("/to")){
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
    private DateTimeFormatter fmt;
    public Item(String task, boolean completed, TaskType tt, DateTimeFormatter fmt){
        this.task = task;
        this.completed = completed;
        this.tt = tt;
        this.fmt = fmt;
    }
    public String toString(){
        String cBox = "[" + (completed ? "X" : " ") + "] ";
        String tBox = "[" + tt.toString() + "]";
        String taco = this.tt.getTaco(fmt);
        return  tBox + cBox + " " + task + " "  + taco;
    }
    public void setCompleted(boolean x){
        completed = x;
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


public class Duke {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Item> items = new ArrayList<Item>();
    static ArrayList<DateTimeFormatter> formatters = new ArrayList<>();



    public static void main(String[] args) {


        System.out.println("Hello I'm Robot!");
        System.out.println("What can I do for you?");


        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d H"));
        formatters.add(DateTimeFormatter.ofPattern("yyyy-M-d"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy Hmm"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy H"));
        formatters.add(DateTimeFormatter.ofPattern("d/M/yyyy"));

        DateTimeFormatter out_date_format = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");

        while(true){
            String userInput = sc.nextLine();
            String[] splitStr = userInput.split("\\s+");
            try{
                if(userInput.equals("bye")) break;

                if(userInput.equals("list")){
                    System.out.println("Here are the tasks in your list:");
                    for(int i=0;i<items.size();i++){
                        System.out.println(Integer.toString(i+1)  + "." + items.get(i).toString());
                    }
                }else if(splitStr[0].equals("mark")){
                    if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
                    int x = Integer.parseInt(splitStr[1])-1;
                    if(x < 0 || x+1 > items.size())  throw new DukeException("Index is out of list range.");
                    items.get(x).setCompleted(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(items.get(x).toString());

                }else if(splitStr[0].equals("unmark")){
                    if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
                    int x = Integer.parseInt(splitStr[1])-1;
                    if(x < 0 || x+1 > items.size())  throw new DukeException("Index is out of list range.");
                    items.get(x).setCompleted(false);
                    System.out.println("Ok, I've marked this task as not done yet:");
                    System.out.println(items.get(x).toString());

                }else if(splitStr[0].equals("remove")){
                    if(splitStr.length != 2) throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
                    int x = Integer.parseInt(splitStr[1]) - 1;
                    if(x < 0 || x+1 > items.size())  throw new DukeException("Index is out of list range.");
                    System.out.println("Ok, the following item was removed:");
                    System.out.println(items.get(x).toString());
                    items.remove(x);


                } else if(splitStr[0].equals("todo")){
                    if(splitStr.length == 1){
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    items.add(new Item(Utils.getString(splitStr, 1, splitStr.length), false, new Todo(null, formatters), out_date_format));
                    System.out.println("Got it, I've added this task:");
                    System.out.println(items.get(items.size()-1).toString());
                    System.out.println("Now you have " + items.size() + " tasks in the list.");

                }else if(splitStr[0].equals("deadline")){
                    boolean x = false;
                    for(int i=1;i<splitStr.length;i++){
                        if(splitStr[i].equals("/by")) {
                            String dl = Utils.getString(splitStr, i, splitStr.length);
                            TaskType tt = new Deadline(dl, formatters);
                            String task = Utils.getString(splitStr, 1, i);
                            if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                            if(dl.isEmpty()) throw new DukeException("Deadline cannot be empty.");
                            items.add(new Item(task, false, tt,  out_date_format));
                            x = true;
                            break;
                        }
                    }
                    if(!x){
                        throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                    }
                    System.out.println(items.get(items.size()-1).toString());
                    System.out.println("Now you have " + items.size() + " tasks in the list.");

                }else if(splitStr[0].equals("event")){
                    boolean x = false;
                    for(int i=1;i<splitStr.length;i++){
                        if(splitStr[i].equals("/from")) {

                            String dl = Utils.getString(splitStr, i, splitStr.length);
                            TaskType tt = new Event(dl, formatters);
                            String task = Utils.getString(splitStr, 1, i);
                            if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                            if(dl.isEmpty()) throw new DukeException("Event dates cannot be empty.");
                            items.add(new Item(task, false, tt,  out_date_format));
                            x = true;
                            break;
                        }
                    }
                    if(!x){
                        throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                    }
                    System.out.println(items.get(items.size()-1).toString());
                    System.out.println("Now you have " + items.size() + " tasks in the list.");
                }else{
                    throw new DukeException("Sorry, I don't understand that command");
                }
            }catch(DukeException e){
                System.out.println(e);
            }finally{

            }



        }

        System.out.println("Bye! Hope to see you again soon!");

    }
}

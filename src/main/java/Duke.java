import java.util.Scanner;
import java.util.ArrayList;
class TaskType {

}
class Todo extends TaskType {
    public String toString(){
        return "T";
    }

}
class Deadline extends TaskType {
    public String toString(){
        return "D";
    }
}
class Event extends TaskType {
    public String toString(){
        return "E";
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
    private String dl;
    public Item(String task, boolean completed, TaskType tt, String dl){
        this.task = task;
        this.completed = completed;
        this.tt = tt;
        this.dl = dl;
    }
    public String toString(){
        String cBox = "[" + (completed ? "X" : " ") + "] ";
        String tBox = "[" + tt.toString() + "]";
        String taco = dl == null ? "" : " (" + dl + ")";
        return  tBox + cBox + " " + task + taco;
    }
    public void setCompleted(boolean x){
        completed = x;
    }

}


public class Duke {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Item> items = new ArrayList<Item>();

    public static String getString(String[] a, int x, int y){
        String res = "";
        for(int i=x;i<y;i++){
            if(a[i].charAt(0) == '/') res += a[i].substring(1) + ":";
            else res += a[i];
            if(i!=y-1) res += " ";
        }
        return res;
    }

    public static void main(String[] args) {


        System.out.println("Hello I'm Robot!");
        System.out.println("What can I do for you?");



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
                    items.add(new Item(getString(splitStr, 1, splitStr.length), false, new Todo(), null));
                    System.out.println("Got it, I've added this task:");
                    System.out.println(items.get(items.size()-1).toString());
                    System.out.println("Now you have " + items.size() + " tasks in the list.");

                }else if(splitStr[0].equals("deadline")){
                    boolean x = false;
                    for(int i=1;i<splitStr.length;i++){
                        if(splitStr[i].equals("/by")) {
                            TaskType tt = new Deadline();
                            String dl = getString(splitStr, i, splitStr.length);
                            String task = getString(splitStr, 1, i);
                            if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                            if(dl.isEmpty()) throw new DukeException("Deadline cannot be empty.");
                            items.add(new Item(task, false, tt, dl));
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
                            TaskType tt = new Deadline();
                            String dl = getString(splitStr, i, splitStr.length);
                            String task = getString(splitStr, 1, i);
                            if(task.isEmpty()) throw new DukeException("Description of task cannot be empty.");
                            if(dl.isEmpty()) throw new DukeException("Event dates cannot be empty.");
                            items.add(new Item(task, false, tt, dl));
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

package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Stores the list of tasks in ArrayList and carries out the functions.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Default constructor.
     */
    public TaskList(){
        list = new ArrayList<Task>();
    }

    /**
     * Constructor to load arraylist from another source.
     * @param list
     */
    public TaskList(ArrayList<Task> list){
        this.list = list;
    }

    /**
     * prints the tasks stored in list.
     * @param ui
     */
    public void list(Ui ui){
        ui.showLine();
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            ui.print(j + "." + list.get(i) + "");
        }
        ui.showLine();
    }

    /**
     * Marks a particular task as done or undone.
     * @param mark
     * @param str
     * @param ui
     * @param storage
     */
    public void mark(boolean mark,String str, Ui ui, Storage storage){
        ui.showLine();
        String val = str.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(val);
        if(mark){
            list.get(index-1).setDone();
            storage.updateFile(index,true);
            ui.print("Nice! I've marked this task as done:");
        }
        else{
            list.get(index-1).setUndone();
            storage.updateFile(index,false);
            ui.print("OK, I've marked this task as not done yet:");
        }
        ui.print("\t" + list.get(index-1) + "\n");
        ui.showLine();
    }

    /**
     * Creates a todo instance.
     * @param str
     * @param ui
     * @param storage
     */
    public void todo(String str, Ui ui, Storage storage){
        str = str.substring(5);
        if (str.isEmpty())
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "____________________________________________________________"));
        list.add(new Todo(str));
        storage.addToFile("T,0,"+str);
        ui.print("____________________________________________________________\n" +
                "Got it. I've added this task:");
        ui.print("\t" + list.get(list.size() - 1));
        ui.print("Now you have " + list.size() + " tasks in your list");
        ui.showLine();
    }

    /**
     * Converts date from string to LocalDate object.
     * @param input
     * @param ui
     * @return
     */
    public LocalDate changeDateFormat(String input,Ui ui){
        LocalDate date=null;
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(input,format);
        }catch (Exception e){
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! Improper date format.\n" +
                    "____________________________________________________________"));
        }
        return date;
    }

    /**
     * Creates new deadline instance.
     * @param str
     * @param ui
     * @param storage
     */
    public void deadline(String str, Ui ui, Storage storage){
        str = str.substring(9);
        if (str.isEmpty())
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "____________________________________________________________"));
        String[] arr = str.split(" /by ");
        if (arr.length<2)
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! Insufficient parameters passed to deadline.\n" +
                    "____________________________________________________________"));
        LocalDate date=changeDateFormat(arr[1],ui);
        arr[1]=date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        list.add(new Deadline(arr[0], arr[1]));
        storage.addToFile("D,0,"+arr[0]+","+arr[1]);
        ui.print("____________________________________________________________\n" +
                "Got it. I've added this task:");
        ui.print("\t" + list.get(list.size() - 1));
        ui.print("Now you have " + list.size() + " tasks in your list");
        ui.showLine();
    }

    /**
     * Creates new event instance.
     * @param str
     * @param ui
     * @param storage
     */
    public void event(String str, Ui ui, Storage storage){
        str = str.substring(6);
        if (str.isEmpty())
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! The description of a event cannot be empty.\n" +
                    "____________________________________________________________"));
        String[] arr = str.split(" /from ");
        if (arr.length<2)
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! Insufficient parameters passed to event.\n" +
                    "____________________________________________________________"));
        String[] time = arr[1].split(" /to ");
        if (time.length<2)
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! Insufficient parameters passed to event.\n" +
                    "____________________________________________________________"));
        LocalDate date=changeDateFormat(time[0],ui);
        time[0]=date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        date=changeDateFormat(time[1],ui);
        time[1]=date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        list.add(new Event(arr[0], time[0], time[1]));
        storage.addToFile("E,0,"+arr[0]+","+time[0]+","+time[1]);
        ui.showLine();
        ui.print( "Got it. I've added this task:");
        ui.print("\t" + list.get(list.size() - 1));
        ui.print("Now you have " + list.size() + " tasks in your list");
        ui.showLine();
    }

    /**
     * Deletes a particular task.
     * @param str
     * @param ui
     * @param storage
     */
    public void delete(String str, Ui ui, Storage storage){
        str = str.substring(7);
        String val = str.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(val);
        if (str.isEmpty())
            ui.handleError(new DukeException("\n____________________________________________________________\n" +
                    "☹ OOPS!!! The description of a delete cannot be empty.\n" +
                    "____________________________________________________________"));
        ui.showLine();
        ui.print("Noted. I've removed this task:");
        ui.print("\t" + list.get(index - 1));
        list.remove(index - 1);
        storage.deleteFromFile(index);
        ui.print("Now you have " + list.size() + " tasks in your list");
        ui.showLine();
    }
}

package duke;
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

/**
 * Contains utility functions to be used in several contexts.
 */

class Utils {
    /**
     * Takes an array of strings, a start and end index. Returns
     * a string with all tokens between start and end (not inclusive)
     * index separated by a space.
     *
     * @param a Array of strings
     * @param start  start index
     * @param end end index
     * @returns String with all tokens between start and end (not inclusive) index separated by space.
     */
    public static String splitStringBySpaces(String[] a, int start, int end){
        String res = "";
        for(int i = start; i < end; i++) {
            res += a[i];
            if (i != end-1) {
                res += " ";
            }
        }
        return res;
    }

    public static int getWordCount(String s) {
        return s.split("\\s+").length;
    }
}



/**
 * Represents a type of task to be done.
 */

class TaskType {
    private String task;
    private boolean isCompleted;
    private String dateString;

    /**
     * Constructor for the TaskType class.
     *
     * @param task Description of task
     * @param isCompleted Whether of not the task has been completed
     * @param dateString String containing only datetime-related information pf the task, if any.
     */

    public TaskType(String task, boolean isCompleted, String dateString) {
        this.task = task;
        this.isCompleted = isCompleted;
        this.dateString = dateString;
    }

    public String toStringWithDatetimeFormatter(DateTimeFormatter fmt) {
        String completedBox = "[" + (isCompleted ? "X" : " ") + "] ";
        String taskTypeBox = "[" + this.toShortString() + "]";
        String formattedDatetime = this.getFormattedDatetime(fmt);
        return taskTypeBox + completedBox + " " + task + " "  + formattedDatetime;
    }

    public void setIsCompleted(boolean x) {
        isCompleted = x;
    }

    public String saveStringRep() {
        return Utils.getWordCount(this.task) + " " + this.task + " " + String.valueOf(isCompleted) + " " + this.toShortString() +
                " " + (dateString == null ? "" : dateString);
    }

    public String toShortString() {
        return "";
    };

    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }


}


class Todo extends TaskType {

    public Todo(String task, boolean isCompleted) {
        super(task, isCompleted, "");
    }
    public String toShortString() {
        return "T";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "";
    }

}
class Deadline extends TaskType {
    private LocalDateTime dl;
    public Deadline(String task, boolean isCompleted, String dl, ArrayList<DateTimeFormatter> formatters) throws DukeException {
        super(task, isCompleted, dl);
        String[] s = dl.split("\\s+");
        String a = Utils.splitStringBySpaces(s, 1, s.length);
        if (a.isEmpty()) {
            throw new DukeException("/by description cannot be empty");
        }
        for(DateTimeFormatter fr : formatters) {
            try {
                this.dl = LocalDateTime.parse(a, fr);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }

        }
        if(dl == null) {
            throw new DukeException("/by date format count not be recognized");
        }

    }

    public String toShortString() {
        return "D";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter) {
        return "(by: " +  dl.format(formatter) + ")";
    }
}
class Event extends TaskType {
    private LocalDateTime from;
    private LocalDateTime to;
    public Event(String task, boolean isCompleted, String dl, ArrayList<DateTimeFormatter> formatters) throws DukeException {
        super(task, isCompleted, dl);
        String[] s = dl.split("\\s+");
        boolean wasToDetected = false;
        for(int i = 1; i < s.length; i++){
            if(s[i].equals("/to")) {
                String a = Utils.splitStringBySpaces(s, 1, i);
                String b = Utils.splitStringBySpaces(s, i+1, s.length);
                if(a.isEmpty()) {
                    throw new DukeException("/from description cannot be empty");
                }
                if(b.isEmpty()) {
                    throw new DukeException("/to description cannot be empty");
                }
                for(DateTimeFormatter fr : formatters) {
                    try{
                        this.from = LocalDateTime.parse(a, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }
                for (DateTimeFormatter fr : formatters) {
                    try {
                        this.to = LocalDateTime.parse(b, fr);
                        break;
                    } catch (DateTimeParseException e) {
                        continue;
                    }
                }

                wasToDetected  = true;
            }
        }
        if (from == null) {
            throw new DukeException("/from date format could not be recognized");
        }
        if (to == null) {
            throw new DukeException("/to date format could not be recognized");
        }
        if (!wasToDetected) {
            throw new DukeException("keyword /to was not detected.");
        }
    }

    public String toShortString() {
        return "E";
    }
    public String getFormattedDatetime(DateTimeFormatter formatter){
        return "(from: " +  from.format(formatter) + " to: " + to.format(formatter) + ")";
    }
}
class DukeException extends Exception {
    public DukeException(String a){
        super(a);
    }
}




class Ui{
    private DTFormat dtf;

    public Ui(DTFormat dtf) {
        this.dtf = dtf;
    }
    public void print(String msg) {
        System.out.println(msg);
    }

    public void printItems(ArrayList<TaskType> items) {
        for(int i = 0; i < items.size(); i++){
            print((i + 1) + "." + itemToString(items.get(i)));
        }
    }

    public void printIntro() {
        print("Hello I'm Robot!");
        print("What can I do for you?");
    }

    public void printExit() {
        print("Bye! Hope to see you again soon!");
    }

    public void showLoadingError() {
        print("An error occurred while loading data into your list, starting with a blank list...");
    }

    public String itemToString(TaskType a) {
        return a.toStringWithDatetimeFormatter(dtf.getOutFormatter());
    }


}

class DTFormat{
    private ArrayList<DateTimeFormatter> formatters;
    private DateTimeFormatter out_date_format;
    public DTFormat() {
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
    public void addReadFormat(String k) {
        formatters.add(DateTimeFormatter.ofPattern(k));
    }
    public void removeReadFormat(int x) {
        formatters.remove(x);
    }
    public DateTimeFormatter getOutFormatter() {
        return out_date_format;
    }
    public void setOutFormatter(String k) {
        out_date_format = DateTimeFormatter.ofPattern(k);
    }

}

class TaskList{
    ArrayList<TaskType> items;

    public TaskList(){
        items = new ArrayList<>();
    }
    public TaskList(ArrayList<String> ss, DTFormat dtf) throws DukeException {
        items = new ArrayList<>();
        for(String s : ss){
            String[] d = s.split("\\s+");
            int descLen = Integer.valueOf(d[0]);
            String desc = Utils.splitStringBySpaces(d, 1, descLen+1);
            boolean isCompleted = Boolean.valueOf(d[descLen+1]);
            String dateString = d.length > descLen+3 ? Utils.splitStringBySpaces(d, descLen+3, d.length) : null;
            TaskType task;
            if (d[descLen+2].equals("T")) {
                task = new Todo(desc, isCompleted);
            } else if (d[descLen+2].equals("D")) {
                task = new Deadline(desc, isCompleted, dateString, dtf.getFormatters());
            } else if (d[descLen+2].equals("E")) {
                task = new Event(desc, isCompleted, dateString, dtf.getFormatters());
            } else {
                throw new DukeException("Task type not recognized");
            }
            items.add(task);
        }
    }

    public int getSize() {
        return items.size();
    }

    public TaskType getItem(int x) {
        return items.get(x);
    }

    public void addItem(TaskType y) {
        items.add(y);
    }
    public void removeItem(int x) {
        items.remove(x);
    }

    public ArrayList<TaskType> getItems() {
        return items;
    }


}

class Storage{
    ArrayList<String> its;
    private String data_file;

    public Storage(String data_folder) {
        File g = new File(data_folder);
        if(!g.exists()) {
            g.mkdir();
        }
        data_file = data_folder + "data.txt";
        this.data_file = data_file;



    }

    public ArrayList<String> load() {
        File f = new File(data_file);
        its = new ArrayList<>();
        try {
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                its.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException fe) {
            System.out.println("Data file not found, attempting to create one...");
            try{
                f.createNewFile();
                System.out.println("Data file successfully created.");
            } catch (IOException e) {
                System.out.println("Data storage could be created, any items added to the app will be deleted after program exit.");
                e.printStackTrace();
            }
        }
        return its;
    }

    public void save(ArrayList<TaskType> items){
        try{
            FileWriter fileWriter = new FileWriter(data_file);
            for(int i = 0; i < items.size(); i++) {
                fileWriter.write(items.get(i).saveStringRep() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
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

            if (userInput.equals("bye")) {
                return false;
            }

            if (userInput.equals("list")){
                ui.print("Here are the tasks in your list:");
                ui.printItems(tl.getItems());

            } else if (splitStr[0].equals("mark")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'mark' command. Enter mark [item_no]");
                }
                int x = Integer.parseInt(splitStr[1])-1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                tl.getItem(x).setIsCompleted(true);
                ui.print("Nice! I've marked this task as done:");
                ui.print(ui.itemToString(tl.getItem(x)));

            } else if (splitStr[0].equals("unmark")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'unmark' command. Enter unmark [item_no]");
                }
                int x = Integer.parseInt(splitStr[1])-1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                tl.getItem(x).setIsCompleted(false);
                ui.print("Ok, I've marked this task as not done yet:");
                ui.print(ui.itemToString(tl.getItem(x)));

            } else if (splitStr[0].equals("remove")) {
                if(splitStr.length != 2) {
                    throw new DukeException("Invalid format detected for 'remove' command. Enter remove [item_no]");
                }
                int x = Integer.parseInt(splitStr[1]) - 1;
                if(x < 0 || x+1 > tl.getSize()) {
                    throw new DukeException("Index is out of list range.");
                }
                ui.print("Ok, the following item was removed:");
                ui.print(ui.itemToString(tl.getItem(x)));
                tl.removeItem(x);

            } else if (splitStr[0].equals("todo")) {
                if(splitStr.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                String desc = Utils.splitStringBySpaces(splitStr, 1, splitStr.length);
                tl.addItem(new Todo(desc, false));
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            } else if (splitStr[0].equals("deadline")) {
                boolean x = false;
                for(int i = 1; i < splitStr.length; i++) {
                    if(splitStr[i].equals("/by")) {
                        String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                        String desc = Utils.splitStringBySpaces(splitStr, 1, i);

                        if(desc.isEmpty()) {
                            throw new DukeException("Description of task cannot be empty.");
                        }
                        if(dateString.isEmpty()) {
                            throw new DukeException("Deadline cannot be empty.");
                        }
                        tl.addItem(new Deadline(desc, false, dateString, dtf.getFormatters()));
                        x = true;
                        break;
                    }
                }
                if(!x) {
                    throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                }
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");

            } else if (splitStr[0].equals("event")) {
                boolean x = false;
                for (int i = 1; i < splitStr.length; i++){
                    if (splitStr[i].equals("/from")) {
                        String dateString = Utils.splitStringBySpaces(splitStr, i, splitStr.length);
                        String desc = Utils.splitStringBySpaces(splitStr, 1, i);
                        if (desc.isEmpty()) {
                            throw new DukeException("Description of task cannot be empty.");
                        }
                        if(dateString.isEmpty()) {
                            throw new DukeException("Event dates cannot be empty.");
                        }
                        tl.addItem(new Event(desc, false, dateString, dtf.getFormatters()));
                        x = true;
                        break;
                    }
                }
                if (!x) {
                    throw new DukeException("/by keyword is necessary and not detected. Use /by to set a deadline.");
                }
                ui.print("Got it, I've added this task:");
                ui.print(ui.itemToString(tl.getItem(tl.getSize()-1)));
                ui.print("Now you have " + tl.getSize() + " tasks in the list.");
            } else {
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
    private static Scanner sc = new Scanner(System.in);

    public void run() {
        ui.printIntro();
        while(true) {
            String userInput = sc.nextLine();
            try {
                if (!parser.parse(userInput, tasks)) {
                    break;
                }
            } catch (DukeException e) {
                ui.print(e.toString());
            }

        }
        storage.save(tasks.getItems());
        ui.printExit();
    }
    public Duke(String filepath) {
        dtf = new DTFormat();
        ui = new Ui(dtf);
        storage = new Storage(filepath);
        parser = new Parser(dtf, ui);
        try {
            tasks = new TaskList(storage.load(), dtf);
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public static void main(String[] args) {
        new Duke("./data/").run();
    }
}

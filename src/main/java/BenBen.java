import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.io.IOException;

public class BenBen {
    //private static String[] arr;
    private static Task[] arr;
    private static ArrayList<Task> arrlst;


    private static String FILEPATH = "./src/main/java/tasks.txt";

    private static File FILE;

    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());
    //private static boolean[] bool;
    private static final String line ="_______________________________________\n";

    public static abstract class Task {
        protected String description;
        protected boolean isDone;
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void mark() {
            this.isDone = true;
        }

        public void unmark() {
            this.isDone = false;
        }

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }

        public String description() {
            return this.description;
        }

        public abstract String getLog() ;
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T] " + super.toString();
        }

        @Override
        public String getLog() {
            return "T | " + (isDone? "1" : "0")
                    + " | " + this.description + System.lineSeparator();
        }
    }

    public static class Deadline extends Task {
        protected LocalDate ddl;

        protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        public Deadline(String description, String ddl) {
            super(description);
                this.ddl = LocalDate.parse(ddl);
        }
        @Override
        public String toString() {
            return "[D] " + super.toString() + " (by: " + getDdl() + ")";
        }

        public String getDdl() {
            return this.ddl.getMonth().toString() + " " + this.ddl.getDayOfMonth() + " " + this.ddl.getYear();
        }

        public String getFormattedDdl() {
            return ddl.format(formatter);
        }

        @Override
        public String getLog() {
            return "D | " + (isDone? "1" : "0")
                    + " | " + this.description
                    + " | " + this.getFormattedDdl() + System.lineSeparator();
        }


    }

    public static class Event extends Task {
        protected LocalDateTime startTime;
        protected LocalDateTime endTime;

        protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.systemDefault());

        public Event(String description,String startTime, String endTime) throws BenBenException {
            super(description);
            try {
                this.startTime = LocalDateTime.parse(startTime, formatter);
                this.endTime = LocalDateTime.parse(endTime, formatter);
                if (this.startTime.isAfter(this.endTime)) {
                    throw new BenBenException("The end time should be later than the start time!");
                }
            } catch (DateTimeParseException e) {
                throw new BenBenException("The date and time is of the wrong format! Please use yyyy-MM-dd HH:mm");
            }
        }
        @Override
        public String toString() {
            return "[E] " + super.toString() + " (from: " + getStartTime() + " to: " + getEndTime() + ")";
        }

        public String getStartTime() {
            return startTime.getMonth().toString()
                    + " " + startTime.getDayOfMonth()
                    + " " + startTime.getYear()
                    + " " + startTime.getHour() + ":" + startTime.getMinute();
        }

        public String getEndTime() {
            return endTime.getMonth().toString()
                    + " " + endTime.getDayOfMonth()
                    + " " + endTime.getYear()
                    + " " + endTime.getHour() + ":" + endTime.getMinute();
        }

        public String getFormattedStart() {
            return startTime.format(formatter);
        }

        public String getFormattedEnd() {
            return endTime.format(formatter);
        }

        @Override
        public String getLog() {
            return "E | " + (isDone? "1" : "0")
                    + " | " + this.description
                    + " | " + getFormattedStart()
                    + " | " + getFormattedEnd() + System.lineSeparator();
        }
    }

    static class BenBenException extends RuntimeException {
        BenBenException(String message) {
            super(message);
        }

        @Override
        public String toString() {
            return "WOOF! " + getMessage();
        }
    }

    public static void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!\n" + line);
        System.exit(0);
    }

    public static void todo(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        for (int i =  1; i < strSplit.length; i++) {
            des = des + " " + strSplit[i];
        }
        des = des.trim();

        if (des.length() == 0) {
            throw new BenBenException("Please follow the format: todo todo details");
        }

        Task t = new Todo(des);
        //arr[counter] = t;
        arrlst.add(t);
        //counter++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + arrlst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void deadline(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String ddl = "";
        boolean isDes = true;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/by")) {
                isDes = false;
                continue;
            }

            if (isDes) {
                des = des + " " + strSplit[i];
            } else {
                ddl = ddl + " " + strSplit[i];
            }
        }
        des = des.trim();
        ddl = ddl.trim();

        if (des.length() == 0 || ddl.length() == 0 ) {
            throw new BenBenException("Please follow the format: deadline deadline details /by yyyy/mm/dd");
        }

        Task t = new Deadline(des, ddl);
        //arr[counter] = t;
        arrlst.add(t);
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + arrlst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void event (String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String start = "";
        String end = "";
        boolean isDes = true;
        boolean isStart = false;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/from")) {
                isDes = false;
                isStart = true;
                continue;
            }
            if (strSplit[i].equals("/to")) {
                isStart = false;
                continue;
            }


            if (isDes && !isStart) {
                des = des + " " + strSplit[i];
            } else if (!isDes && isStart) {
                start = start + " " + strSplit[i];
            } else {
                end = end + " " + strSplit[i];
            }
        }
        des = des.trim();
        start = start.trim();
        end = end.trim();

        if (des.length() == 0 || start.length() == 0 || end.length() == 0 ) {
            throw new BenBenException("Please follow the format: event event details /from yyyy-MM-dd HH-mm /to yyyy-MM-dd HH-mm");
        }

        Task t = new Event(des, start, end);
        //arr[counter] = t;
        arrlst.add(t);


        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + arrlst.size() + " tasks in the list.");
        System.out.println(line);
    }

    public static void iterList() {

        if (arrlst.isEmpty()) {
            System.out.println(line);
            System.out.println("Your list is currently empty! Add a new task now!");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < arrlst.size(); i++) {
                System.out.println((i + 1) + "." + arrlst.get(i).toString());
            }
            System.out.println(line);
        }
    }

    public static void mark(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to mark!");
        }

        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to mark!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //arr[x - 1].mark();
            arrlst.get(x - 1).mark();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n" +
                    "    " + arrlst.get(x - 1).toString());
            System.out.println(line);
        } catch(NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch(NullPointerException e) {
            throw new BenBenException("The task you are trying to mark does not exist!");
        }
    }

    public static void unmark(String str) throws BenBenException{

        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to unmark!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to unmark!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //[x - 1].unmark();
            arrlst.get(x - 1).unmark();
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    "    " + arrlst.get(x - 1).toString());
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to unmark does not exist!");
        }
    }

    public static void remove(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to remove!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to remove!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //[x - 1].unmark();
            Task temp = arrlst.get(x - 1);
            arrlst.remove(x - 1);
            //counter = counter - 1;
            System.out.println(line);
            System.out.println("Noted. I've removed this task:\n");
            System.out.println(temp.toString());
            System.out.println("Now you have " + arrlst.size() + " tasks in the list.");
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        } catch (IndexOutOfBoundsException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        }
    }

    public static void action(String next) throws BenBenException{
        boolean bool = false;

        if (!bool && next.equals("bye")) {
            exit();
            bool = true;
        }

        if (!bool && next.equals("list")) {
            iterList();
            bool = true;
        }

        if (!bool && next.startsWith("mark")) {
            mark(next);
            bool = true;
            writeFile();
        }

        if (!bool && next.startsWith("unmark")) {
            unmark(next);
            bool = true;
            writeFile();
        }

        if (!bool && next.startsWith("todo")) {
            todo(next);
            bool = true;
            writeFile();
        }

        if (!bool && next.startsWith("deadline")) {
            deadline(next);
            bool = true;
            writeFile();
        }

        if (!bool && next.startsWith("event")) {
            event(next);
            bool = true;
            writeFile();
        }

        if (!bool && next.startsWith("delete")) {
            remove(next);
            bool = true;
            writeFile();
        }

        if (!bool) {
            throw new BenBenException("BenBen does not understand your instruction:(");
        }

    }

    public static void writeFile() {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            String content = "";
            for (int i = 0; i < arrlst.size(); i++) {
                Task t = arrlst.get(i);
                content = content + t.getLog();
            }
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            throw new BenBenException("Failed to write to file!" + e.getMessage());
        }
    }


    public static void readFile() throws BenBenException {
        try {

            FILE = new File(FILEPATH);
            //System.out.println(FILE.exists());
           if (!FILE.exists()) {
                boolean isCreated = FILE.createNewFile();
                //System.out.println(isCreated + "new file created");
            }
            Scanner sc = new Scanner(FILE);
            //System.out.println("next line? : " + sc.hasNext());
            while (sc.hasNext()) {
                boolean canRead = false;
                String task = sc.nextLine();
                //System.out.println(task);
                String[] strSplit = task.split("\\|");

                for (int i = 0; i < strSplit.length; i++) {
                    strSplit[i] = strSplit[i].trim();
                    //System.out.println(strSplit[i]);
                }
                //System.out.println("length: " + strSplit.length);
                if (strSplit[0].startsWith("T") && strSplit.length == 3) {
                    Task nextTask = new Todo(strSplit[2]);
//                    System.out.println(strSplit[1]);
//                    System.out.println(strSplit[1].equals("1"));
                    if(strSplit[1].equals("1")) {
                        nextTask.mark();
                    }
                    arrlst.add(nextTask);
                    canRead = true;

                }
                if (strSplit[0].startsWith("D") && strSplit.length == 4) {
                    Task nextTask = new Deadline(strSplit[2], strSplit[3]);
                    if(strSplit[1].startsWith("1")) {
                        nextTask.mark();
                    }
                    arrlst.add(nextTask);
                    canRead = true;
                }
                if (strSplit[0].startsWith("E") && strSplit.length == 5) {
                    Task nextTask = new Event(strSplit[2], strSplit[3], strSplit[4]);
                    if (strSplit[1].startsWith("1")) {
                        nextTask.mark();
                    }
                    arrlst.add(nextTask);
                    canRead = true;
                }
                if (!canRead) {
                    throw new BenBenException("The file content is corrupted, please report this to admin");
                }
            }

        } catch (FileNotFoundException e) {
            throw new BenBenException("The local file is not found in the directory");
        } catch (IOException e) {
            throw new BenBenException("IOException found!" + e.getMessage());
        }

    }
    public static void main(String[] args) throws BenBenException{
            arrlst = new ArrayList<Task>();
            readFile();
            Scanner sc = new Scanner(System.in);
            //arr = new Task[100];

            System.out.println(line);
            System.out.println("Hello! I'm BenBen.\n" +
                    "What can I do for you?");
            System.out.println(line);
            ;

            while (sc.hasNext()) {
                try {
                    String next = sc.nextLine();
                    action(next);
                } catch (BenBenException e) {
                    System.out.println(line);
                    System.out.println(e.toString());
                    System.out.println(line);
                }
            }
    }
}

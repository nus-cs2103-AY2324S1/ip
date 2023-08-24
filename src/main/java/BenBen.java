import java.util.Scanner;

public class BenBen {
    //private static String[] arr;
    private static Task[] arr;
    //private static boolean[] bool;
    private static final String line ="_______________________________________\n";
    private static int counter = 0;

    public static class Task {
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
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T] " + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected String ddl;
        public Deadline(String description, String ddl) {
            super(description);
            this.ddl = ddl;
        }
        @Override
        public String toString() {
            return "[D] " + super.toString() + " (by: " + ddl + ")";
        }
    }

    public static class Event extends Task {
        protected String startTime;
        protected String endTime;
        public Event(String description, String startTime, String endTime) {
            super(description);
            this.startTime = startTime;
            this.endTime = endTime;
        }
        @Override
        public String toString() {
            return "[E] " + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
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
        arr[counter] = t;
        counter++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
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
            throw new BenBenException("Please follow the format: deadline deadline details /by deadline time");
        }

        Task t = new Deadline(des, ddl);
        arr[counter] = t;
        counter++;
        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
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
            throw new BenBenException("Please follow the format: event event details /from start time /to end time");
        }


        Task t = new Event(des, start, end);
        arr[counter] = t;
        counter++;


        System.out.println(line);
        System.out.println("Got it. I've added this task:\n");
        System.out.println(t.toString());
        System.out.println("Now you have " + counter + " tasks in the list.");
        System.out.println(line);
    }

    public static void iterList() {

        if (counter == 0) {
            System.out.println(line);
            System.out.println("Your list is currently empty! Add a new task now!");
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < counter; i++) {
                System.out.println((i + 1) + "." + arr[i].toString());
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
            arr[x - 1].mark();
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:\n" +
                    "    " + arr[x - 1].toString());
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
            arr[x - 1].unmark();
            System.out.println(line);
            System.out.println("OK, I've marked this task as not done yet:\n" +
                    "    " + arr[x - 1].toString());
            System.out.println(line);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to unmark does not exist!");
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
        }

        if (!bool && next.startsWith("unmark")) {
            unmark(next);
            bool = true;
        }

        if (!bool && next.startsWith("todo")) {
            todo(next);
            bool = true;
        }

        if (!bool && next.startsWith("deadline")) {
            deadline(next);
            bool = true;
        }

        if (!bool && next.startsWith("event")) {
            event(next);
            bool = true;
        }

        if (!bool) {
            throw new BenBenException("BenBen does not understand your instruction:(");
        }

    }
    public static void main(String[] args) throws BenBenException{
            Scanner sc = new Scanner(System.in);
            arr = new Task[100];
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

import java.util.ArrayList;

public class Kiera {
    protected static String name = "Kiera";
    protected static String line = "   ---------------------------------------------";
    protected static String hello = line
                + "\n"
                + "    " 
                + "hi, it's kiera.\n" 
                + "    " 
                + "what do you need?\n"
                + line;
    protected static String bye =  line
                + "\n"
                + "    " 
                + "muaks! <3\n"
                + line;
    
    protected static ArrayList<Task> store = new ArrayList<Task>();
    protected static int index = 0;


    public static String list() {
        String result = "";
        if (index == 0) {
            return line + "\n    nothing for you to do yet! \n" + result + line;
        }
        for (int i = 0; i < index; i++) {
            Task t = store.get(i);

            result = result +  "    " + (i + 1) + ". " + t.toString() +  "\n";

        }
        return line + "\n    you need to get these done today:\n" + result + line;
    }

    public static String mark(String input) {
        int unchecked = Integer.valueOf(input.replace("mark ", "")) - 1;
        Task t = store.get(unchecked);

        t.markAsDone();

        return line
            + "    yay, one task down: \n"
            + "    "
            + t.toString()
            + "\n"
            + line;
    }

    public static String unmark(String input) {
        int checked = Integer.valueOf(input.replace("mark ", "")) - 1;
        Task t = store.get(checked);

        t.markAsUndone();

       return line
            + "    ok, this task is not done yet: \n"
            + "    "
            + t.toString()
            + "\n"
            + line;
    }

    public static String addTask(String input, String type) {
        Task t;
        if (type == "todo") {
            if (input == "") {
                return "todo can't be empty!";
            }
            t = new Todo(input);
        } else if (type == "deadline") {
            if (input == "") {
                return "deadline can't be empty!";
            }
            String deadline = input.split("/")[1].replace("by ", "");
            String desc = input.split("/")[0];
            t = new Deadline(desc, deadline);
        } else {
            if (input == "") {
                return "event can't be empty!";
            }
            String end = input.split("/")[2].replace("to ", "");
            String start = input.split("/")[1].replace("from ", "");
            String desc = input.split("/")[0];
            t = new Event(desc, start, end);
        }
        
        store.add(t);
        index ++;
        String plural = index == 1 ? "task" : "tasks";
            
        return line 
            + "\n    " 
            + "alright, one more task: \n"
            + "      "
            + t.toString()
            + "\n    "
            + (index) 
            + " more "
            + plural
            + " to go! \n"
            + line;
    }

    public static String delete(String input) {
        int deleted = Integer.valueOf(input.replace("delete ", "")) - 1;
        Task t = store.get(deleted);

        store.remove(deleted);
        index --;

        String plural = index == 0 ? "task" : "tasks";

        return line 
            + "\n    " 
            + "alright, this task is gone: \n"
            + "      "
            + t.toString()
            + "\n    "
            + (index) 
            + " more "
            + plural
            + " left! \n"
            + line;

    }
    public static void main(String[] args) {
        

        System.out.println(hello);

        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            }

            if (input.startsWith("mark")) {
                System.out.println(mark(input));
                continue;
            } else if (input.startsWith("unmark")){
                System.out.println(unmark(input));
                continue;
            }

            if (input.equals("list")) {
                System.out.println(list());
                continue;
            }

            if (input.startsWith("delete")) {
                System.out.println(delete(input));
                continue;
            }
            
            if (input.startsWith("todo")) {
                String desc = input.replace("todo ", "");
                System.out.println(addTask(desc, "todo"));
            } else if (input.startsWith("deadline")) {
                String desc = input.replace("deadline ", "");
                System.out.println(addTask(desc, "deadline"));
            } else if (input.startsWith("event")) {
                String desc = input.replace("event ", "");
                System.out.println(addTask(desc, "event"));
            } else {
                System.out.println(line + "\n    sorry, i don't know what this means... \n" + line);
            }

            
        }
        System.out.println(bye);
     
    }
}


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
    protected static Task[] store = new Task[100];
    protected static Integer index = 0;


    public static String list() {
        String result = "";
            for (int i = 0; i < index; i++) {
                Task t = store[i];
                String status = t.getStatusIcon();
                String desc = t.getDescription();

                result = result +  "    " + (i + 1) + ". " + t.toString() +  "\n";

            }
        return line + "\n    you need to get these done today:\n" + result + line;
    }

    public static String mark(String input) {
        Integer unchecked = Integer.valueOf(input.replace("mark ", "")) - 1;
        Task t = store[unchecked];

        t.markAsDone();

        return line
            + "    yay, one task down: \n"
            + "    "
            + t.toString()
            + "\n"
            + line;
    }

    public static String unmark(String input) {
        Integer checked = Integer.valueOf(input.replace("mark ", "")) - 1;
        Task t = store[checked];

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
        
        store[index] = t;
        String plural = index == 0 ? "task" : "tasks";
            
        return line 
            + "\n    " 
            + "alright, one more task: \n"
            + "      "
            + t.toString()
            + "\n    "
            + (index + 1) 
            + " more "
            + plural
            + " to go! \n"
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
            
            if (input.startsWith("todo")) {
                String desc = input.replace("todo ", "");
                System.out.println(addTask(desc, "todo"));
                index ++;
            } else if (input.startsWith("deadline")) {
                String desc = input.replace("deadline ", "");
                System.out.println(addTask(desc, "deadline"));
                index ++;
            } else if (input.startsWith("event")) {
                String desc = input.replace("event ", "");
                System.out.println(addTask(desc, "event"));
                index ++;
            } else {
                System.out.println(line + "\n    sorry, i don't know what this means... \n" + line);
            }

            
        }
        System.out.println(bye);
     
    }
}

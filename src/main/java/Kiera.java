import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

public class Kiera {
    protected static String name = "kiera";
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
    
    protected static ArrayList<Task> store = new ArrayList<>();
    protected static String filePath = "./data/storage.txt";

    public static void readFile(String filePath) throws KieraException {
        File f = new File(filePath);
        String result = "";
        try {
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                Task t;
                String next = s.nextLine();
                String[] r = next.split(" // ");
                String type = r[0];
                String done = r[1];
                String desc = r[2];
                if (type.equals("T")) {
                    System.out.println(desc);
                    t = new Todo(desc);
                } else if (type.equals("D")) {
                    t = new Deadline(desc);
                } else {
                    t = new Event(desc);
                }
                if (done.equals("X")) {
                    t.markAsDone();
                } else {
                    t.markAsUndone();
                }
                store.add(t);

            }

        } catch (FileNotFoundException e) {
            throw new KieraException("no previous data found; starting with an empty list!");
        }
        System.out.println(result);
    }
    public static void writeToFile(String filePath, String text) throws KieraException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            throw new KieraException("data not saved..." + e);
        }
    }

    public static void list() {
        String result = "";
        if (store.isEmpty()) {
            System.out.println(line + "\n    nothing for you to do yet! \n" + result + line);
            return;
        }
        for (int i = 0; i < store.size(); i++) {
            Task t = store.get(i);

            result = result +  "    " + (i + 1) + ". " + t +  "\n";

        }
        System.out.println(line + "\n    you need to get these done today:\n" + result + line);
    }

    public static void mark(String input) {
        try {
            int unchecked = Integer.parseInt(input.replace("mark ", "")) - 1;
            Task t = store.get(unchecked);

            t.markAsDone();
            System.out.println(line
                    + "    \n    yay, one task down: \n"
                    + "     "
                    + t
                    + "\n"
                    + line);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }

    }

    public static void unmark(String input) {
        try {
            int checked = Integer.parseInt(input.replace("unmark ", "")) - 1;
            Task t = store.get(checked);

            t.markAsUndone();

            System.out.println(line
                    + "    \n    ok, this task is not done yet: \n"
                    + "     "
                    + t
                    + "\n"
                    + line);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }
        
    }


    public static void addTask(String input, String type) throws KieraException {
        Task t;
        if (type.equals("todo")) {
            if (input.isEmpty()) {
                throw new KieraException( "todo can't be empty! follow the format: todo (task).");
            }
            t = new Todo(input);
        } else if (type.equals("deadline")) {
            if (input.isEmpty()) {
                throw new KieraException("deadline can't be empty! follow the format: deadline (task) /by (date).");
            }
            t = new Deadline(input);
        } else {
            if (input.isEmpty()) {
                throw new KieraException( "event can't be empty! follow the format: event (task) /from (start) /to (end).");
            }
            t = new Event(input);
        }
        
        store.add(t);

        String plural = store.size() == 1 ? "task" : "tasks";

        System.out.println(line
                + "\n    "
                + "alright, one more task has been added: \n"
                + "      "
                + t
                + "\n    "
                + (store.size())
                + " more "
                + plural
                + " to go! \n"
                + line);
    }

    public static void delete(String input) {
        try {
            int deleted = Integer.parseInt(input.replace("delete ", "")) - 1;
            Task t = store.get(deleted);

            store.remove(deleted);


            String plural = store.size() == 0 ? "task" : "tasks";

            System.out.println(line
                    + "\n    "
                    + "alright, this task is gone: \n"
                    + "      "
                    + t.toString()
                    + "\n    "
                    + store.size()
                    + " more "
                    + plural
                    + " left! \n"
                    + line);

        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }
       
    }
    public static void main(String[] args) {
        try {
            readFile(filePath);
        } catch (KieraException e) {
            System.out.println(e);
        }

        Scanner in = new Scanner(System.in);
        System.out.println(hello);

        while (true) {
            String input = in.nextLine();
            if (input == null) {
                continue;
            }
            if (input.equals("bye")) {
                String text = store.stream().map(task -> task.toStorageString() + "\n").collect(Collectors.joining());
                writeToFile(filePath, text);
                break;
            }

            if (input.startsWith("mark")) {
                mark(input);
                continue;
            } else if (input.startsWith("unmark")){
                unmark(input);
                continue;
            }

            if (input.equals("list")) {
                list();
                continue;
            }

            if (input.startsWith("delete")) {
                delete(input);
                continue;
            }
            
            if (input.startsWith("todo")) {
                String desc = input.replace("todo ", "");
                addTask(desc, "todo");
            } else if (input.startsWith("deadline")) {
                String desc = input.replace("deadline ", "");
                addTask(desc, "deadline");
            } else if (input.startsWith("event")) {
                String desc = input.replace("event ", "");
                addTask(desc, "event");
            } else {
                System.out.println(line + "\n    sorry, i don't know what this means... \n" + line);
            }

            
        }
        System.out.println(bye);
     
    }
}

import java.util.*;

public class Duke {
    public static String partition = "------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
    
        System.out.println(partition);
        System.out.println("Hello! I'm Edna-Duke.");
        System.out.println("What can I do for you?");
        System.out.println(partition);
        
        String input = sc.next();
        while(!input.equals("bye")) {
            try {
                switch(input){
                    case "list":
                        print();
                        break;
                    case "mark":
                        int markItem = sc.nextInt();
                        taskList.get(markItem - 1).markDone();
                        break;
                    case "unmark":
                        int unmarkItem = sc.nextInt();
                        taskList.get(unmarkItem - 1).markUndone();
                        break;
                    case "delete":
                         int deleteItem = sc.nextInt();
                        delete(deleteItem);
                        break;
                    case "todo":
                        add(new ToDo(sc.nextLine()));
                        break;
                    case "event":
                        String eventCommand = sc.nextLine();
                        String[] event = new String[3];
                        event[0] = eventCommand.substring(1, eventCommand.indexOf(" /"));
                        event[1] = eventCommand.substring(eventCommand.indexOf("/from") + 6, 
                        eventCommand.indexOf(" /to"));
                        event[2] = eventCommand.substring(eventCommand.indexOf("/to") + 4);
                        add(new Event(event[0], event[1], event[2]));
                        break;
                    case "deadline":
                        String ddlCommand = sc.nextLine();
                        String[] ddl = ddlCommand.split(" /by ");
                        add(new Deadline(ddl[0].substring(1), ddl[1]));
                        break;
                    default:
                        add(new Task(input));
                }
            } 

            catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            finally {
                System.out.println(partition);
                input = sc.next();
            }
        }
            
        exit();
    }

    public static void add(Task input) {
        taskList.add(input);
        System.out.println("Got it! This task has been added: ");
        System.out.println(input.getStatus());
        System.out.println("Current # of task: " + taskList.size());
    }

    public static void delete(int num) {
        System.out.println("I've removed this task:");
        taskList.get(num-1).getStatus();
        taskList.remove(num - 1);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void print() {
        int index = 1;
        for (Task task: taskList) {
            System.out.println(index + ". " + task.getStatus());
            index ++;
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(partition);
    }
}

import java.util.Scanner;
public class Bot {
    Task[] tasks = new Task[100];
    int numOfTasks = 0;

    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";

    public static final String TODO_COMMAND = "todo";

    public static final String DEADLINE_COMMAND = "deadline";

    public static final String EVENT_COMMAND = "event";
    public static final String BYE_COMMAND = "bye";


    public void printWelcomeMessage(){
        String name = "LINUS";
        MessagePrint.print(
                "Hello! I'm " + name + "\n"
                        + "What can I do for you?");
    }
    public void printExitMessage() {
        MessagePrint.print( "Bye. Hope to see you again soon!");
    }

    public void list(){
        String listOfItems = "Here are the tasks in your list:\n";
        for(int i = 0; i < numOfTasks; ++i) {
            listOfItems += (i + 1) + "."
                    + tasks[i].toString() + "\n" ;
        }
        MessagePrint.print(listOfItems);
    }

    public void add(Task task){
        tasks[numOfTasks++] = task;
        MessagePrint.print("Got it. I've added this task:\n"
                +"  " + task + "\n"
                + "Now you have " + numOfTasks + " task" + (numOfTasks > 1 ? "s": "") +  " in the list.");
    }
    public void chat() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String[] items = null;
            String description = "";
            int index = 0;
            String input = sc.nextLine();
            String[] inputSplit = input.split(" ", 2);
            String command = inputSplit[0];
            String data = inputSplit.length == 2 ? inputSplit[1] : "";

            switch(command) {
                case BYE_COMMAND:
                    break;
                case LIST_COMMAND:
                    this.list();
                    continue;
                case MARK_COMMAND:
                    index = Integer.parseInt(input.split(" ")[1]);
                    tasks[index - 1].mark();
                    continue;
                case UNMARK_COMMAND:
                    index = Integer.parseInt(input.split(" ")[1]);
                    tasks[index - 1].unmark();
                    continue;
                case TODO_COMMAND:
                    description = data;
                    this.add(new ToDo(description));
                    continue;
                case DEADLINE_COMMAND:
                    items = data.split(" /by ");
                    description = items[0];
                    String by = items[1];

                    this.add(new Deadline(description, by));
                    continue;
                case EVENT_COMMAND:
                    items = data.split(" /from | /to ");
                    description = items[0];
                    String from = items[1];
                    String to = items[2];

                    this.add(new Event(description, from ,to));
                    continue;
                default:
                    continue;
            }
            break;
        }
    }
    public void start() {
        this.printWelcomeMessage();
        this.chat();
        this.printExitMessage();
    }
}
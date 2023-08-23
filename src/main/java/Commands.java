import Tasks.Deadlines;
import Tasks.Events;
import Tasks.Task;
import Tasks.ToDos;

public class Commands {
    static String name = "Nichbot";

    //    static String[] tasks = new String[100];
    static int count = 0;
    // Assuming there will not be more than 100 tasks
    static Task[] tasks = new Task[100];

    // Level-0, Function to say introduce the chatbot
    public static void sayHello() {
        String greet = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm %s\n" +
                        " I am a task planning bot that will help you record your tasks.\n"+
                        " I currently can record 3 types of tasks. tasks to do, tasks with deadlines and events\n" +
                        " Here is how you can record down your events.\n" +
                        " To record tasks to do, simply begin your command with \"todo\" followed by a space and the task you need to do. \n" +
                        " To record tasks with deadlines, simply begin your command with \"deadlines\" followed by the task and a / then the due date. \n" +
                        " To record events, simply begin your command with \"events\" followed by the event, / and start time , then / with the end time\n" +
                        "____________________________________________________________\n", name);
        System.out.println(greet);
    }

    // Level-0, Function to say goodbye
    public static void sayGoodBye() {
        String bye =  ("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
        System.out.println(bye);
    }

    //  Level-1, Echo user input
    public static void echoUserInput(Task task) {
        System.out.println(task);
        System.out.println("____________________________________________________________\n");
    }

    //    Level-2, Add, list
    public static void addList(String input) {
        Task newTask = null;

        // check if input begins with todo, deadline or event to categorise task type
        String[] wordSeparator = input.split(" ");
        String firstWord = wordSeparator[0];
        if (firstWord.toLowerCase().equals("todo")) newTask = new ToDos(input.substring(5,input.length()));
        if (firstWord.toLowerCase().equals("deadline")) {
            String[] parts = input.split("/");

            /*
            split input array into description and deadline.
            "deadline return book /by Sunday" -> parts[0] = deadline return book  and parts[1] = by Sunday
            using substring, deadline return book -> return book
            */

            String description = parts[0].substring(8, parts[0].length());
            String deadline = parts[1];
            newTask = new Deadlines(description, deadline);
        }

        if (firstWord.toLowerCase().equals("event")) {
            String[] parts = input.split("/");
            /*
            similar logic as above. additionally, splits input into start and end time
             */
            String description = parts[0].substring(6, parts[0].length());
            String startTime = parts[1];
            String endTime = parts[2];
            newTask = new Events(description, startTime, endTime);
        }

        if (newTask == null) newTask = new Task(input);

            tasks[count++] = newTask;
            System.out.println("____________________________________________________________\n" +
                    "I have added this task to your list\n" +
                    String.format("[%s]",newTask.getTaskType()) + "[ ]" + newTask.getDescription() + "\n" +
                    String.format("You now have %d tasks in your list.", count));
            System.out.println("____________________________________________________________\n");
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            // for each task in task[], prints out a simple line describing the task
            Character taskType = tasks[i].getTaskType();
            String current = String.format("%d: [%c][%s] %s ", i+1, taskType, tasks[i].getStatusIcon(),tasks[i]);

            if (tasks[i] instanceof Deadlines) {
                current += (String.format("(%s)", ((Deadlines) tasks[i]).getDeadline()));
            } else if (tasks[i] instanceof Events) {
                current += (String.format("(%s, %s)", ((Events) tasks[i]).getStartTime(), ((Events) tasks[i]).getEndTime()));
            }

            System.out.println(current);
        }
        System.out.print("\n____________________________________________________________\n");
    }

    // function to mark task as done or undone
    public static void markDoneOrUndone(int task, boolean done) {
        if (task < 1 || task > 100) {
            System.out.println("Invalid Input written.");
            return;
        }
        if (task > count + 1) {
            System.out.println(String.format("Tasks.Task %d has not been recorded",task));
            return;
        }
        Task current = tasks[task - 1];
        if (done) {
            current.setDone();
            System.out.println("____________________________________________________________\n");
            System.out.println(String.format("Nice! I've marked %s as completed", current));
            System.out.println("____________________________________________________________\n");
        } else {
            current.setNotDone();
            System.out.println("____________________________________________________________\n");
            System.out.println(String.format("Ok, I'll mark %s as not done", current));
            System.out.print("____________________________________________________________\n");
        }
    }

    // overall input handler which determines which function to run
    public static void handleInput(String userInput){
            if (userInput.toLowerCase().equals("bye")) return;
            if (userInput.toLowerCase().equals("list")) {
                printList();
            } else if (userInput.length() > 5 && userInput.substring(0,4).toLowerCase().equals("mark")) {
                markDoneOrUndone(Integer.parseInt(userInput.substring(5, userInput.length())), true);
            } else if (userInput.length() > 7 && userInput.substring(0,6).toLowerCase().equals("unmark")) {
                markDoneOrUndone(Integer.parseInt(userInput.substring(7, userInput.length())), false);
            } else {
                addList(userInput);
            }
    }


}

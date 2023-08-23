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
                        " What can I do for you?\n" +
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
        System.out.println(task + "\n____________________________________________________________");
    }

    //    Level-2, Add, list
    public static void addList(String input) {
        Task newTask = new Task(input);
        tasks[count++] = newTask;
        System.out.print("____________________________________________________________\n" + "added: ");
        echoUserInput(newTask);
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < count; i++) {
            String current = String.format("%d:[%s] %s",i + 1, tasks[i].getStatusIcon(),tasks[i]);
            System.out.println(current);
        }
        System.out.print("____________________________________________________________\n");
    }

    public static void markDoneOrUndone(int task, boolean done) {
        if (task < 1 || task > 100) {
            System.out.println("Invalid Input written.");
            return;
        }
        if (task > count + 1) {
            System.out.println(String.format("Task %d has not been recorded",task));
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

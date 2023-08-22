import java.util.Scanner;

public class Blip {

    public static void main(String[] args) {
        // Intro message by Blip.
        String intro = "Hello! I'm Blip\n"
                + "What can I do for you?";

        // Outro message by Blip.
        String outro = "Bye. Hope to see you again soon!";

        // Constant end trigger word to end the chat with outro.
        String END_TRIGGER = "bye";

        // Constant list trigger word to display back stored text.
        String LIST_TRIGGER = "list";

        // Constant mark trigger word to update status of task.
        String MARK_TRIGGER = "mark";

        // Constant unmark trigger word to update status of task.
        String UNMARK_TRIGGER = "unmark";

        // Constant deadline trigger word to create new task with deadline.
        String DEADLINE_TRIGGER = "deadline";

        // Constant event trigger word to create new task with deadline.
        String EVENT_TRIGGER = "event";

        // Constant to do trigger word to create new task with deadline.
        String TODO_TRIGGER = "todo";

        // Fixed-size array of tasks to do.
        Task[] tasks = new Task[100];
        int index = 0;


        // Bot starts here!
        System.out.println(intro);

        String userInput;
        Scanner scanIn = new Scanner(System.in);
        userInput = scanIn.nextLine();


        while (!userInput.equals(END_TRIGGER)) {
            if (!userInput.equals(LIST_TRIGGER)) {
                // To mark a task.
                if (userInput.split(" ")[0].equals(MARK_TRIGGER)) {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task taskToUpdate = tasks[taskNum];
                    taskToUpdate.markStatus();
                    System.out.println("Nice! I've marked this task as done:\n" + taskToUpdate.toString());
                    userInput = scanIn.nextLine();

                // To unmark a task.
                } else if (userInput.split(" ")[0].equals(UNMARK_TRIGGER)) {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task taskToUpdate = tasks[taskNum];
                    taskToUpdate.unmarkStatus();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + taskToUpdate.toString());
                    userInput = scanIn.nextLine();

                // For a deadline task.
                } else if (userInput.split(" ")[0].equals(DEADLINE_TRIGGER)) {
                    String[] deadlineInfo = userInput.split("/");
                    Deadline newDeadlineTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    tasks[index] = newDeadlineTask;
                    index++;
                    System.out.println("Alright! I've added this task: \n " + newDeadlineTask.toString()
                            + "\nNow you have " + (index) + " tasks in the list.");
                    userInput = scanIn.nextLine();

                // For an event task.
                } else if (userInput.split(" ")[0].equals(EVENT_TRIGGER)) {
                    String[] eventInfo = userInput.split("/");
                    Event newEventTask = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
                    tasks[index] = newEventTask;
                    index++;
                    System.out.println("Alright! I've added this task: \n " + newEventTask.toString()
                            + "\nNow you have " + (index) + " tasks in the list.");
                    userInput = scanIn.nextLine();

                // For to do task.
                } else if (userInput.split(" ")[0].equals(TODO_TRIGGER)) {
                    ToDo newToDoTask = new ToDo(userInput);
                    tasks[index] = newToDoTask;
                    index++;
                    System.out.println("Alright! I've added this task: \n " + newToDoTask.toString()
                            + "\nNow you have " + (index) + " tasks in the list.");
                    userInput = scanIn.nextLine();
                }

            // To list out tasks.
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
                userInput = scanIn.nextLine();
            }
        }

        // If "bye" is triggered, exit while loop and print outro message.
        System.out.println(outro);

    }
}

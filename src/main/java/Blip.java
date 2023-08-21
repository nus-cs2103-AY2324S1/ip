import java.util.Scanner;

public class Blip {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }

        public String getTaskNStatus() {
            return getStatusIcon() + " " + this.description;
        }
    }
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

        // Constant mark trigger word to update status of task.
        String UNMARK_TRIGGER = "unmark";

        // Fixed-size array of tasks to do.
        Task[] tasks = new Task[100];
        int index = 0;

        // Messages
        System.out.println(intro);

        String userInput;
        Scanner scanIn = new Scanner(System.in);
        userInput = scanIn.nextLine();


        while (!userInput.equals(END_TRIGGER)) {
            if (!userInput.equals(LIST_TRIGGER)) {
                if (userInput.split(" ")[0].equals(MARK_TRIGGER)) {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task taskToUpdate = tasks[taskNum];
                    taskToUpdate.markStatus();
                    System.out.println("Nice! I've marked this task as done:\n" + taskToUpdate.getTaskNStatus());
                    userInput = scanIn.nextLine();
                } else if (userInput.split(" ")[0].equals(UNMARK_TRIGGER)) {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task taskToUpdate = tasks[taskNum];
                    taskToUpdate.unmarkStatus();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + taskToUpdate.getTaskNStatus());
                    userInput = scanIn.nextLine();
                } else {
                    tasks[index] = new Task(userInput);
                    index++;
                    System.out.println("added: " + userInput);
                    userInput = scanIn.nextLine();
                }
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].getTaskNStatus());
                }
                userInput = scanIn.nextLine();
            }
        }

        System.out.println(outro);

    }
}

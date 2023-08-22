import java.util.Scanner;

public class Blip {
    public static class Task {
        protected String description;
        protected boolean isDone;

        protected String type;

        protected boolean isSplit;
        protected String TO_DO = "todo";
        protected String DEADLINE = "deadline";
        protected String EVENT = "event";


        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.type = "";
            this.isSplit = false;
        }

        public void splitDescription() {
            if (!isSplit) {
                String[] splitDesc = description.split("\\s+", 2);
                this.type = splitDesc[0];
                this.description = splitDesc[1];
                isSplit = true;
            }

        }
        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] ");
        }

        public String getEventType() {
            if (type.equals(TO_DO)) {
                return "[T]";
            } else if (type.equals(DEADLINE)) {
                return "[D]";
            } else if (type.equals(EVENT)) {
                return "[E]";
            } else {
                return "[ ]";
            }
        }


        public void convertStringDescription() {
            String[] taskParts = description.split("/");
            String taskName = taskParts[0];

            // If it is an event with 3 "groups" of text.
            if (taskParts.length == 3) {
                String taskStart = taskParts[1].replace("from", "(from:");
                String taskEnd = taskParts[2].replace("to", "to:");
                this.description = taskName + taskStart + taskEnd + ")";
            // If it is a deadline with 2 "groups" of text.
            } else if (taskParts.length == 2) {
                String taskDeadline = taskParts[1].replace("by", "(by:");
                this.description = taskName + taskDeadline + ")";
            } else {
                this.description = taskName;
            }
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }

        public String getTaskInfo() {
            splitDescription();
            convertStringDescription();
            return getEventType() + getStatusIcon() + this.description;
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
                    System.out.println("Nice! I've marked this task as done:\n" + taskToUpdate.getTaskInfo());
                    userInput = scanIn.nextLine();
                } else if (userInput.split(" ")[0].equals(UNMARK_TRIGGER)) {
                    int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    Task taskToUpdate = tasks[taskNum];
                    taskToUpdate.unmarkStatus();
                    System.out.println("Ok, I've marked this task as not done yet:\n" + taskToUpdate.getTaskInfo());
                    userInput = scanIn.nextLine();
                } else {
                    Task newTask = new Task(userInput);
                    tasks[index] = newTask;
                    index++;
                    System.out.println("Got it. I've added this task: \n " + newTask.getTaskInfo()
                            + "\nNow you have " + (index) + " tasks in the list.");
                    userInput = scanIn.nextLine();
                }
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + tasks[i].getTaskInfo());
                }
                userInput = scanIn.nextLine();
            }
        }

        System.out.println(outro);

    }
}

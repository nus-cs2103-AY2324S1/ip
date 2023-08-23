import java.util.Scanner;
public class Duke {
    public static class Task {
        public String name;
        public boolean isComplete;

        public Task(String name) {
            this.name = name;
            this.isComplete = false;
        }

        public String getMark() {
            return (isComplete ? "X" : " ");
        }

        public String toString() {
            return "[" + getMark() + "] " + name;
        }
    }

    public static class Todo extends Task {
        public Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        public String by;

        public Deadline(String name, String by) {
            super(name);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        public String from;
        public String to;

        public Event(String name, String from, String to) {
            super(name);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + " )";
        }
    }

    Task[] taskList = new Task[100];
    int taskCount = 0;

    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";
        public void chadGreet() {
            System.out.println(line);
            System.out.println("Yo! This is CHADbot\n");
            System.out.println("Need sum help?\n");
            System.out.println(line);
        }
        public void chadBye() {
            System.out.println(line);
            System.out.println("Cya l8r~\n");
            System.out.println(line);
        }

        public void chadOutput(String input) {
            System.out.println(line);
            System.out.println(input + "\n");
            System.out.println(line);
        }
        public void chadAddList(Task input) {
            taskList[taskCount] = input;
            taskCount++;
            System.out.println(line);
            System.out.println(input.toString() + " has been added to yo list!\n");
            System.out.println(line);
        }
        public void chadListTask() {
            if (taskCount == 0) {
                System.out.println("Your task list is EMPTY!");
            } else {
                System.out.println(line);
                System.out.println("Your outstanding tasks are...");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("Task " + (i + 1) + ") " + taskList[i].toString());
                }
                System.out.println("\n" + "Get to work NOW!\n");
                System.out.println(line);
            }
        }

        public void chadMarkTask(int index) {
            taskList[index - 1].isComplete = true;
            System.out.println(line);
            System.out.println("Good job! Task fulfilled!");
            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(line);
        }

        public void chadUnmarkTask(int index) {
            taskList[index - 1].isComplete = false;
            System.out.println(line);
            System.out.println("Boooo! Task is not done!");
            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(line);
        }


    public static void main(String[] args) {
        Duke chad = new Duke();
        chad.chadGreet();

        Scanner scanObj = new Scanner(System.in);

        while(true) {
            String input = scanObj.nextLine();
            String[] inputArray = input.split(" ", 2);
            if (inputArray[0].equals("bye")) {
                chad.chadBye();
                break;

            } else if (inputArray[0].equals("list")) {
                chad.chadListTask();

            } else if (inputArray[0].equals("mark")) {
                Integer index = Integer.valueOf(inputArray[1]);
                chad.chadMarkTask(index);

            } else if (inputArray[0].equals("unmark")) {
                Integer index = Integer.valueOf(inputArray[1]);
                chad.chadUnmarkTask(index);

            } else if (inputArray[0].equals("todo")) {
                chad.chadAddList(new Todo(inputArray[1]));

            } else if (inputArray[0].equals("deadline")) {
                String[] details = inputArray[1].split(" /by ", 2);
                chad.chadAddList(new Deadline(details[0],details[1]));

            } else if (inputArray[0].equals("event")) {
                String[] details = inputArray[1].split(" /from ", 2);
                String[] timings = details[1].split(" /to", 2);
                chad.chadAddList(new Event(details[0], timings[0], timings[1]));

            } else {

            }
        }
        scanObj.close();

    }
}

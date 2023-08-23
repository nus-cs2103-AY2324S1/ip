import java.util.ArrayList;
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

    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    Task[] taskList = new Task[100];
    ArrayList<Task> taskArrayList = new ArrayList<Task>();
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
            try {
                if (input == null) {
                    throw new DukeException("What are you on about? I do not understand...");
                }
//                taskList[taskCount] = input;
                taskArrayList.add(input);
                taskCount++;
                System.out.println(line);
                System.out.println(input.toString() + " has been added to yo list!\n");
                System.out.println(line);
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e.getMessage() + "\n");
                System.out.println(line);
            }
        }

        public void chadRemoveList(int index){
            try {
                Task removed = taskArrayList.remove(index - 1);
                taskCount--;
                System.out.println("Okay! I have removed this task :\n" + removed);

                if (taskArrayList.size() == 0) {
                    System.out.println("Your list is currently empty! Good job :)");
                } else {
                    System.out.println("Your list is currently " + taskArrayList.size() + " long... Get back to work!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid task index! Try again!");
            }

        }
        public void chadListTask() {
            if (taskCount == 0) {
                System.out.println("Your task list is EMPTY!");
            } else {
                System.out.println(line);
                System.out.println("Your outstanding tasks are...");
                for (int i = 0; i < taskCount; i++) {
//                    System.out.println("Task " + (i + 1) + ") " + taskList[i].toString());
                    System.out.println("Task " + (i + 1) + ") " + taskArrayList.get(i)); //<<<<<
                }
                System.out.println("\n" + "Get to work NOW!\n");
                System.out.println(line);
            }
        }

        public void chadMarkTask(int index) {
//            taskList[index - 1].isComplete = true;
            taskArrayList.get(index - 1).isComplete = true;
            System.out.println(line);
            System.out.println("Good job! Task fulfilled!");
//            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(taskArrayList.get(index - 1).name + " [" + taskArrayList.get(index - 1).getMark() + "]\n");
            System.out.println(line);
        }

        public void chadUnmarkTask(int index) {
//            taskList[index - 1].isComplete = false;
            taskArrayList.get(index - 1).isComplete = false;
            System.out.println(line);
            System.out.println("Boooo! Task is not done!");
//            System.out.println(taskList[index - 1].name + " [" + taskList[index - 1].getMark() + "]\n");
            System.out.println(taskArrayList.get(index - 1).name + " [" + taskArrayList.get(index - 1).getMark() + "]\n");
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
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadMarkTask(index);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");

                }
            } else if (inputArray[0].equals("unmark")) {
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadUnmarkTask(index);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");

                }

            } else if (inputArray[0].equals("todo")) {
                try {
                    if (inputArray.length == 1 || inputArray[1].isEmpty()) {
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    chad.chadAddList(new Todo(inputArray[1]));
                } catch (DukeException e) {
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (inputArray[0].equals("deadline")) {
                try {
                    if (inputArray.length < 2 || inputArray[1].isEmpty()){
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    String[] details = inputArray[1].split(" /by ", 2);

                    if (details.length < 2) {
                        throw new DukeException("Umm you forgot the deadline! Remember to use /by before the deadline!");
                    }
                    chad.chadAddList(new Deadline(details[0], details[1]));
                } catch (DukeException e){
                    System.out.println(e.getMessage() + "\n");
                }

            } else if (inputArray[0].equals("event")) {
                try {
                    if (inputArray.length < 2 || inputArray[1].isEmpty()) {
                        throw new DukeException("Hey! You forgot what you needed to do?");
                    }
                    String[] details = inputArray[1].split(" /from ", 2);
                    if (details.length < 2) {
                        throw new DukeException("Hey you are missing the start date! Remember to use /from before the deadline!");
                    }
                    String[] timings = details[1].split(" /to", 2);
                    if (timings.length < 2) {
                        throw new DukeException("The end date is missing! Do better! Use /to!");
                    }
                    chad.chadAddList(new Event(details[0], timings[0], timings[1]));
                } catch(DukeException e) {
                    System.out.println(e.getMessage() + "\n");
                }
            } else if (inputArray[0].equals("delete")) {
                try {
                    Integer index = Integer.valueOf(inputArray[1]);
                    chad.chadRemoveList(index);

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("The task index is invalid! Try again!");

                }


            } else {
                chad.chadOutput("Hmm? You are not making sense!");
            }
        }
        scanObj.close();

    }
}

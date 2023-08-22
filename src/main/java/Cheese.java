import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Cheese {
    private List<Task> CheeseStack = new ArrayList<Task>();

    enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    public void speak() {
        Scanner myObj = new Scanner(System.in);
        String echoedInput = "";
        System.out.println("\t-----------------------------------------");
        System.out.println("\tHello, I'm Cheese");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t------------------------------------------");
        while (true) {
            echoedInput = myObj.nextLine();
            commandResponse(echoedInput);
            if (echoedInput.equals("bye")) {
                break;
            }
        }
        System.out.println("\t-----------------------------------------");
    }

    private boolean isCommand(String input) {
        String[] inputSplit = input.split(" ");
        switch(inputSplit[0]) {
            case "mark":
            case "bye":
            case "list":
            case "todo":
            case "deadline":
            case "event":
            case "delete":
                return true;
            default:
                return false;
        }
    }

    private void addToList(String input) {
        String[] inputSplit = input.split(" ", 2);
        String command = inputSplit[0];

        try {
            if (inputSplit.length < 2 && isCommand(input))  {
                throw new IllegalArgumentException("The description of a " + command + " cannot be empty.");
            } else if (inputSplit.length < 2 && !isCommand(input)) {
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            String taskDescription = inputSplit[1];

            Task newTask = null;

            switch (command) {
                case "todo":
                    newTask = new Task('T', taskDescription);
                    break;
                case "deadline":
                    String[] deadlineInfo = taskDescription.split("/by", 2);
                    newTask = new Task('D', deadlineInfo[0].trim(), deadlineInfo[1].trim());
                    break;
                case "event":
                    String[] eventInfo = taskDescription.split("/from", 2);
                    String[] eventTime = eventInfo[1].split("/to", 2);
                    newTask = new Task('E', eventInfo[0].trim(), eventTime[0].trim(), eventTime[1].trim());
                    break;
                case "delete":
                    System.out.println("\t-----------------------------------------");
                    System.out.println("\tNoted. I've removed this task:");
                    System.out.println("\t  " + CheeseStack.get(Integer.parseInt(taskDescription) - 1));
                    deleteItem(Integer.parseInt(taskDescription) - 1);
                    System.out.println("\tNow you have " + CheeseStack.size() + " tasks in the list.");
                    System.out.println("\t-----------------------------------------");
                    break;
                default:
                    throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            if (newTask != null) {
                CheeseStack.add(newTask);
                System.out.println("\t-----------------------------------------");
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t  " + newTask.toString());
                System.out.println("\tNow you have " + CheeseStack.size() + " tasks in the list.");
                System.out.println("\t-----------------------------------------");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("\t-----------------------------------------");
            System.out.println("\t☹ " + e.getMessage());
            System.out.println("\t-----------------------------------------");
        }
    }
    private void commandResponse(String input) {
        String inputSplit[] = input.split(" ");
        switch(inputSplit[0]) {
            case "mark":
                markItem(Integer.parseInt(inputSplit[1]) - 1);
                System.out.println("\t-----------------------------------------");
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + CheeseStack.get(Integer.parseInt(inputSplit[1]) - 1));
                System.out.println("\t-----------------------------------------");
                break;
            case "bye":
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            case "list":
                System.out.println("\t-----------------------------------------");
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < CheeseStack.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + CheeseStack.get(i));
                }
                System.out.println("\t-----------------------------------------");
                break;
            
            default:
                addToList(input);
                break;
        }
    }

    private void markItem(int index) {
        if (index >= 0 && index < CheeseStack.size()) {
            Task task = CheeseStack.get(index);
            task.markAsDone();
        }
    }
    private void deleteItem(int index) {
        if (index >= 0 && index < CheeseStack.size()) {
            Task task = CheeseStack.get(index);
            CheeseStack.remove(index);
        }
    }

    public static void main(String[] args) {
        Cheese Cheese = new Cheese();
        Cheese.speak();
    }

    private class Task {
        private char type;
        private String description;
        private String dateTime1;
        private String dateTime2;
        private boolean isDone;

        public Task(char type, String description) {
            this.type = type;
            this.description = description;
            this.isDone = false;
        }

        public Task(char type, String description, String dateTime1) {
            this.type = type;
            this.description = description;
            this.dateTime1 = dateTime1;
            this.isDone = false;
        }

        public Task(char type, String description, String dateTime1, String dateTime2) {
            this.type = type;
            this.description = description;
            this.dateTime1 = dateTime1;
            this.dateTime2 = dateTime2;
            this.isDone = false;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(type).append("][").append(isDone ? "X" : " ").append("] ").append(description);
            if (dateTime1 != null) {
                sb.append(" (from: ").append(dateTime1);
                if (dateTime2 != null) {
                    sb.append(" to: ").append(dateTime2);
                }
                sb.append(")");
            }
            return sb.toString();
        }
    }
}

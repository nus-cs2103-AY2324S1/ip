import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //greeting
        System.out.println("Hello! I'm Sara");
        System.out.println("What can I do for you?");

        //create scanner to read user inputs
        Scanner scan = new Scanner(System.in);

        //create empty list to store stuff to do
        List<Task> toDoList = new ArrayList<>();

        //processing user commands
        while(true) {
            //read user input
            String userInput = scan.nextLine();

            //check for exit command first
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }

            try {
                if (userInput.equalsIgnoreCase("list")) {
                    //display list
                    System.out.println("Here are the tasks in your List:");
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(((i + 1) + ". " + toDoList.get(i)));
                    }

                } else if (userInput.startsWith("mark")) {
                    //mark task done
                    String[] splitInputs = userInput.split(" ");
                    if (splitInputs.length == 2) {
                        int taskIndex = Integer.parseInt(splitInputs[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
                            toDoList.get(taskIndex).markDone();
                            System.out.println("Nice! I've marked this task as done:\n" + toDoList.get(taskIndex));
                        } else {
                            System.out.println("Invalid task number");
                        }
                    }

                } else if (userInput.startsWith("unmark")) {
                    //unmark task
                    String[] splitInputs = userInput.split(" ");
                    if (splitInputs.length == 2) {
                        int taskIndex = Integer.parseInt(splitInputs[1]) - 1;
                        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
                            toDoList.get(taskIndex).markNotDone();
                            System.out.println("OK, I've marked this task as not done yet:\n" + toDoList.get(taskIndex));
                        } else {
                            System.out.println("Invalid task number");
                        }
                    }

                } else if (userInput.startsWith("todo")){
                    //add new to-do task
                    if (userInput.length() <= 5) {
                        throw new DukeEception("The description of a todo cannot be empty.");
                    } else {
                        String description = userInput.substring(5).trim();
                        Task newTask = new Todo(description);
                        toDoList.add(newTask);
                        System.out.println("Got it. I've added this task:\n " + newTask);
                        System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
                    }

                } else if (userInput.startsWith("deadline")) {
                    //add new deadline
                    if (userInput.length() <= 9) {
                        throw new DukeEception("The description of a deadline cannot be empty.");
                    } else {
                        String[] splitInput = userInput.split("/by");
                        if (splitInput.length == 2) {
                            String description = splitInput[0].substring(9).trim();
                            String by = splitInput[1].trim();
                            Task newTask = new Deadline(description, by);
                            toDoList.add(newTask);
                            System.out.println("Got it. I've added this task:\n " + newTask);
                            System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
                        } else { break;}
                    }

                } else if (userInput.startsWith("event")) {
                    //add new event
                    if (userInput.length() <= 6) {
                        throw new DukeEception("The description of a event cannot be empty.");
                    } else {
                        String[] splitInput = userInput.split("/from");
                        if (splitInput.length == 2) {
                            String description = splitInput[0];
                            String[] eventDetails = splitInput[1].split("/to");
                            if (eventDetails.length == 2 ) {
                                String from = eventDetails[0].trim();
                                String to = eventDetails[1].trim();
                                Task newTask = new Event(description, from, to);
                                toDoList.add(newTask);
                                System.out.println("Got it. I've added this task:\n " + newTask);
                                System.out.println("Now you have " + toDoList.size() + " tasks in the list.");
                            }
                        }
                    }

                } else {
                    //invalid input
                    throw new DukeEception("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeEception e) {
                System.out.println("☹ OOPS!!! " + e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

class Task {
    private String description;
    private Boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }
    public void markNotDone() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (isDone? "X" : " ") + "] " + this.description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString () {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString () {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}

class Event extends Task {
    private String from;
    private String to;
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString () {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
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
            } else {
                //add new task to to-do list
                Task newTask = new Task(userInput);
                toDoList.add(newTask);
                System.out.println("added: " + userInput);
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
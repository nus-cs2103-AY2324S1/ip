import java.util.*;

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

        // Constant delete trigger to delete task from list.
        String DELETE_TRIGGER = "delete";

        // Constant empty string for exception handling.
        String EMPTY = "";

        // Fixed-size array of tasks to do.
        ArrayList<Task> tasks = new ArrayList<>();
        int index = 0;


        // Bot starts here!
        System.out.println(intro);

        String userInput;
        Scanner scanIn = new Scanner(System.in);
        userInput = scanIn.nextLine();


        while (!userInput.equals(END_TRIGGER)) {
            try {
                if (!userInput.equals(LIST_TRIGGER)) {
                    // To mark a task.
                    if (userInput.split(" ")[0].equals(MARK_TRIGGER)) {

                        // Missing task number to mark.
                        if (userInput.split("\\s+", 2).length < 2) {
                            throw new EmptyTaskNumberException("!!! Missing Task Number Error !!!\n");
                        }
                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;

                        // Task number does not exist.
                        if (taskNum >= index) {
                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
                        }
                        Task taskToUpdate = tasks.get(taskNum);

                        taskToUpdate.markStatus();
                        System.out.println("Nice! I've marked this task as done:\n" + taskToUpdate.toString());
                        userInput = scanIn.nextLine();

                    // To unmark a task.
                    } else if (userInput.split(" ")[0].equals(UNMARK_TRIGGER)) {

                        // Missing task number to unmark.
                        if (userInput.split("\\s+", 2).length < 2) {
                            throw new EmptyTaskNumberException("!!! Missing Task Number error !!!");
                        }
                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;

                        // Task number does not exist.
                        if (taskNum >= index) {
                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
                        }
                        Task taskToUpdate = tasks.get(taskNum);
                        taskToUpdate.unmarkStatus();
                        System.out.println("Ok, I've marked this task as not done yet:\n" + taskToUpdate.toString());
                        userInput = scanIn.nextLine();
                        // To delete a task.
                    } else if (userInput.split(" ")[0].equals(DELETE_TRIGGER)) {

                        // Missing task number to delete.
                        if (userInput.split("\\s+", 2).length < 2) {
                            throw new EmptyTaskNumberException("!!! Missing Task Number error !!!");
                        }
                        int taskNum = Integer.parseInt(userInput.split(" ")[1]) - 1;

                        // Task number does not exist.
                        if (taskNum >= index) {
                            throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
                        }
                        Task taskToDelete = tasks.get(taskNum);
                        System.out.println("Ok, I've removed this task:\n" + taskToDelete.toString());
                        tasks.remove(taskNum);
                        index--;
                        userInput = scanIn.nextLine();
                        // For a deadline task.
                    } else if (userInput.split(" ")[0].equals(DEADLINE_TRIGGER)) {
                        String[] test = userInput.split("\\s+", 2);
                        // Missing Deadline Description.

                        if (test.length < 2 || test[1].equals(EMPTY)) {
                            throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!\n");
                        }

                        String[] deadlineInfo = userInput.split("/");
                        Deadline newDeadlineTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                        tasks.add(newDeadlineTask);
                        index++;
                        System.out.println("Alright! I've added this task:\n " + newDeadlineTask.toString()
                                + "\nNow you have " + (index) + " tasks in the list.");
                        userInput = scanIn.nextLine();

                        // For an event task.
                    } else if (userInput.split(" ")[0].equals(EVENT_TRIGGER)) {
                        String[] test = userInput.split("\\s+", 2);
                        // Missing Deadline Description.

                        // Missing Event Description.
                        if (test.length < 2 || test[1].equals(EMPTY)) {
                            throw new EmptyDescriptionException("!!! Missing EVENT Description !!!\n");
                        }

                        String[] eventInfo = userInput.split("/");
                        Event newEventTask = new Event(eventInfo[0], eventInfo[1], eventInfo[2]);
                        tasks.add(newEventTask);
                        index++;
                        System.out.println("Alright! I've added this task:\n " + newEventTask.toString()
                                + "\nNow you have " + (index) + " tasks in the list.");
                        userInput = scanIn.nextLine();

                        // For to do task.
                    } else if (userInput.split(" ")[0].equals(TODO_TRIGGER)) {
                        String[] test = userInput.split("\\s+", 2);

                        // Missing TO DO Description.
                        if (test.length < 2 || test[1].equals(EMPTY)) {
                            throw new EmptyDescriptionException("!!! Missing TO DO Description !!!\n");
                        }

                        ToDo newToDoTask = new ToDo(userInput);
                        tasks.add(newToDoTask);
                        index++;
                        System.out.println("Alright! I've added this task:\n " + newToDoTask.toString()
                                + "\nNow you have " + (index) + " tasks in the list.");
                        userInput = scanIn.nextLine();
                    } else {
                        throw new InvalidCommandException("!!! Your command is invalid !!!\n");
                    }

                    // To list out tasks.
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < index; i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    userInput = scanIn.nextLine();
                }
            } catch (EmptyTaskNumberException e1) {
                System.out.println(e1.getMessage() + "\nOh no! The task number cannot be empty.\n" +
                        "Please key in the task number you would like to mark/unmark.\n");
                userInput = scanIn.nextLine();
            } catch (WrongNumberException e2) {
                System.out.println(e2.getMessage() + "Oh no! The task number does not exist.\n" +
                        "You can find out the tasks and their numbers by typing list.\n" +
                        "Please re-enter the correct task number to mark/unmark.");
                userInput = scanIn.nextLine();
            } catch (EmptyDescriptionException e3) {
                System.out.println(e3.getMessage() + "Oh no! The task description cannot be empty.\n" +
                        "Please key in the task description, with timings where applicable.\n");
                userInput = scanIn.nextLine();
            } catch (InvalidCommandException e4) {
                System.out.println(e4.getMessage() + "Oh no! I don't understand what you mean. Please key in either\n" +
                        "1. deadline [task description] /by [deadline datetime]\n" +
                        "2. event [task description] /from [start datetime] /to [end datetime]\n" +
                        "3. todo [task description].");
                userInput = scanIn.nextLine();
            }
        }

            // If "bye" is triggered, exit while loop and print outro message.
            System.out.println(outro);


        }
    }

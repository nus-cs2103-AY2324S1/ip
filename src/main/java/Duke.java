import java.util.Scanner;

public class Duke {
    
    private static TaskList tasks = new TaskList();

    private static void greet() {
        System.out.println("Hello! I'm Siyuan");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                tasks.listAllTasks();  
            }  else {
                String[] inputSplit = input.split(" ");

                try {
                    switch (inputSplit[0]) {
                        case ("todo"):
                            try {
                                String todoDescription = input.substring(5);
                                TodoTask todoTask = new TodoTask(todoDescription);
                                tasks.addTask(todoTask);
                            } catch(StringIndexOutOfBoundsException e) {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            break;

                        case ("deadline"):
                            try {
                                String deadlineDescription = input.substring(9);
                                String[] deadlineSplit = deadlineDescription.split(" /by ");
                                DeadlineTask deadlineTask = new DeadlineTask(deadlineSplit[0], deadlineSplit[1]);
                                tasks.addTask(deadlineTask);
                            } catch(StringIndexOutOfBoundsException e) {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            } catch(ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("The deadline of a deadline cannot be empty.");
                            }
                            break;

                        case ("event"):
                            try {
                                String eventDescription = input.substring(6);
                                String[] eventSplit = eventDescription.split(" /from ");
                                String[] eventSplit2 = eventSplit[1].split(" /to ");
                                EventTask eventTask = new EventTask(eventSplit[0], eventSplit2[0], eventSplit2[1]);
                                tasks.addTask(eventTask);
                            } catch (StringIndexOutOfBoundsException e) {
                                throw new DukeException("The description of an event cannot be empty.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("The date of an event cannot be empty.");
                            }
                            break;
                        
                        case ("mark"):
                            try {    
                                int taskNumber = Integer.parseInt(inputSplit[1]);
                                tasks.markTaskAsDone(taskNumber);
                            } catch (NumberFormatException e) {
                                throw new DukeException("Please enter a valid task number.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Please enter a task number.");
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("Please enter a valid task number.");
                            } 
                            break;

                        case ("unmark"):
                            try {
                                int taskNumber2 = Integer.parseInt(inputSplit[1]);
                                tasks.markTaskAsUndone(taskNumber2);
                            } catch (NumberFormatException e) {
                                throw new DukeException("Please enter a valid task number.");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Please enter a task number.");
                            } catch (IndexOutOfBoundsException e) {
                                throw new DukeException("Please enter a valid task number.");
                            } 
                            break;  
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } 
                catch (DukeException e) {
                    System.out.println(e.toString());
                } 
                catch (Exception e) {
                    System.out.println("☹ OOPS!!! Unknown error occurred.");
                }
            }
            input = sc.nextLine();
        }

        sc.close();

        exit();
    }
}

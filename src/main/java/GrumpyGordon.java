import java.util.*;
public class GrumpyGordon {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Oi! I'm Grumpy Gordon.");
        System.out.println("     Why are you bothering me?");
        System.out.println("    ____________________________________________________________");

        int taskIndex = 0;
        String taskArgument;
        int taskCount = 0;
        ArrayList<Task> tasks = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        while (!str.equals("bye")) {
            try {
                switch (str.split(" ")[0]) {
                    case ("list"):
                        if (taskCount == 0) {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     The list is empty, you donkey!");
                            System.out.println("    ____________________________________________________________");
                        } else {
                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Stop wasting time, go get it done!");
                            for (int i = 0; i < taskCount; i++) {
                                System.out.println("     " + (i + 1) + "." + tasks.get(i).toString());
                            }
                            System.out.println("    ____________________________________________________________");
                        }
                        str = sc.nextLine();
                        break;
                    case ("mark"):
                        if (str.split(" ").length == 1) {
                            throw new TaskIndexMissingException("Missing task index.");
                        }
                        taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= taskCount) {
                            throw new TaskIndexOfOutBoundsException("Invalid task index.");
                        }
                        tasks.get(taskIndex).markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Took you long enough!");
                        System.out.println("       " + tasks.get(taskIndex).toString());
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                        break;
                    case ("unmark"):
                        if (str.split(" ").length == 1) {
                            throw new TaskIndexMissingException("Missing task index.");
                        }
                        taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= taskCount) {
                            throw new TaskIndexOfOutBoundsException("Invalid task index.");
                        }
                        tasks.get(taskIndex).markAsUndone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     My grandmother does it faster than you!");
                        System.out.println("       " + tasks.get(taskIndex).toString());
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                        break;
                    case ("delete"):
                        if (str.split(" ").length == 1) {
                            throw new TaskIndexMissingException("Missing task index.");
                        }
                        taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= taskCount) {
                            throw new TaskIndexOfOutBoundsException("Invalid task index.");
                        }
                        taskCount--;
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + tasks.get(taskIndex).toString());
                        System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("    ____________________________________________________________");
                        tasks.remove(taskIndex);
                        str = sc.nextLine();
                        break;
                    case ("todo"):
                        if (str.split(" ").length == 1) {
                            throw new DescriptionEmptyException("Invalid task description.");
                        }
                        taskArgument = str.split(" ", 2)[1];
                        String todoDescription = taskArgument;
                        tasks.add(new Todo(todoDescription));
                        taskCount++;
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(taskCount - 1).toString());
                        System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                        break;
                    case ("deadline"):
                        if (str.split(" ").length == 1) {
                            throw new DescriptionEmptyException("Invalid task description.");
                        }
                        taskArgument = str.split(" ", 2)[1];
                        if (taskArgument.split(" /by ").length <= 1) {
                            throw new DeadlineByMissingException("Deadline must have a /by argument.");
                        }
                        if (taskArgument.split(" /by ")[0] == "") {
                            throw new DescriptionEmptyException("Invalid task description.");
                        }
                        String deadlineDescription = taskArgument.split(" /by ")[0];
                        String deadlineBy = taskArgument.split(" /by ")[1];
                        tasks.add(new Deadline(deadlineDescription, deadlineBy));
                        taskCount++;
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(taskCount - 1).toString());
                        System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                        break;
                    case ("event"):
                        if (str.split(" ").length == 1) {
                            throw new DescriptionEmptyException("Invalid task description.");
                        }
                        taskArgument = str.split(" ", 2)[1];
                        if (taskArgument.split(" /from ").length == 1) {
                            throw new EventFromMissingException("Event must have a /from argument.");
                        }
                        String eventDescription = taskArgument.split(" /from ")[0];
                        if (taskArgument.split(" /to ").length == 1) {
                            throw new EventToMissingException("Event must have a /to argument.");
                        }
                        if (taskArgument.split(" /from ")[0].contains(" /to ")) {
                            throw new DescriptionEmptyException("Invalid task description.");
                        }
                        String eventFrom = taskArgument.split(" /from ")[0].split(" /to ")[0];
                        String eventTo = taskArgument.split(" /to ")[1];
                        tasks.add(new Event(eventDescription, eventFrom, eventTo));
                        taskCount++;
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + tasks.get(taskCount - 1).toString());
                        System.out.println("     Now you have " + taskCount + (taskCount > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                        break;
                    default:
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Can't you use simpler language?");
                        System.out.println("     I don't understand what this means: " + str);
                        System.out.println("     The only commands I understand are:");
                        System.out.println("       todo <description>");
                        System.out.println("       deadline <description> /by <datetime>");
                        System.out.println("       event <description> /from <datetime> /to <datetime>");
                        System.out.println("       mark <taskIndex>");
                        System.out.println("       unmark <taskIndex>");
                        System.out.println("       delete <taskIndex>");
                        System.out.println("       list");
                        System.out.println("       bye");
                        System.out.println("    ____________________________________________________________");
                        str = sc.nextLine();
                }
            } catch (TaskIndexOfOutBoundsException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     You think you're funny, don't you?");
                System.out.println("     THERE IS NO TASK " + (taskIndex + 1) + ", YOU DONKEY!");
                System.out.println("     Use the `list` command to see the task numbers.");
                System.out.println("     Try again using this format:");
                System.out.println("       mark <taskIndex>");
                System.out.println("       unmark <taskIndex>");
                System.out.println("       delete <taskIndex>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (TaskIndexMissingException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Are you an idiot sandwich?");
                System.out.println("     YOU NEED TO GIVE ME A TASK NUMBER!");
                System.out.println("     Use the `list` command to see the task numbers.");
                System.out.println("     Try again using this format:");
                System.out.println("       mark <taskIndex>");
                System.out.println("       unmark <taskIndex>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (DescriptionEmptyException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Are you an idiot sandwich?");
                System.out.println("     THE DESCRIPTION OF A TASK CANNOT BE EMPTY!");
                System.out.println("     Try again using this format:");
                System.out.println("       todo <description>");
                System.out.println("       deadline <description> /by <datetime>");
                System.out.println("       event <description> /from <datetime> /to <datetime>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     What? You're supposed to give me a task number!");
                System.out.println("     THAT'S NOT A NUMBER!");
                System.out.println("     Try again using this format:");
                System.out.println("       mark <taskIndex>");
                System.out.println("       unmark <taskIndex>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (DeadlineByMissingException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Are you an idiot sandwich?");
                System.out.println("     YOU MUST TELL ME WHEN IS THE DEADLINE!");
                System.out.println("     Try again using this format: deadline <description> /by <datetime>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (EventFromMissingException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Are you an idiot sandwich?");
                System.out.println("     YOU MUST TELL ME WHEN THIS EVENT STARTS!");
                System.out.println("     Try again using this format: event <description> /from <datetime> /to <datetime>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (EventToMissingException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Are you an idiot sandwich?");
                System.out.println("     YOU MUST TELL ME WHEN THIS EVENT ENDS!");
                System.out.println("     Try again using this format: event <description> /from <datetime> /to <datetime>");
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            } catch (Exception e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Some serious error occured!");
                System.out.println("     I hope you never see this error message!");
                System.out.println("     " + e.toString());
                System.out.println("    ____________________________________________________________");
                str = sc.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to never see you again.");
        System.out.println("    ____________________________________________________________");
    }
}

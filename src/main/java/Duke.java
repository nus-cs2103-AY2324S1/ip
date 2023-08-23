import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println(
                "____________________________________________________________\n" +
                "Hello! I'm Ding!\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            try {
                String command = str.split(" ")[0];
                switch (command) {
                    case "list":
                        System.out.println("Ding: These are your tasks... If I remember correctly:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                        }
                        break;
                    case "mark":
                        if (str.split(" ").length == 2) {
                            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                                System.out.println("Ding: I can't find that task :( I may have lost it...");
                                throw new InvalidTaskIndexException("Invalid Task Index.");
                            }
                            tasks.get(taskIndex).markAsDone();
                            System.out.println(String.format("Ding: Okay, I marked this task as done, but I have no idea what that means:\n %s", tasks.get(taskIndex)));
                        } else {
                            System.out.println("Ding: Which task do you want to mark as done?");
                            throw new MissingTaskIndexException("Task Index Missing.");
                        }
                        break;
                    case "unmark":
                        if (str.split(" ").length == 2) {
                            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                                System.out.println("Ding: I can't find that task :( I may have lost it...");
                                throw new InvalidTaskIndexException("Invalid Task Index.");
                            }
                            tasks.get(taskIndex).markAsUndone();
                            System.out.println(String.format("Ding: Okay, I marked this task as undone, but I have no idea what that means:\n %s", tasks.get(taskIndex)));
                        } else {
                            System.out.println("Ding: Which task do you want to mark as undone?");
                            throw new MissingTaskIndexException("Task Index Missing.");
                        }
                        break;
                    case "todo":
                        if (str.split(" ").length > 1) {
                            ToDo todo = new ToDo(str.split(" ")[1]);
                            tasks.add(todo);
                            System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
                        } else {
                            System.out.println("Ding: I seriously have no idea what I need to do here");
                            throw new InvalidDescriptionException("Invalid description.");
                        }
                        break;
                    case "deadline":
                        if (str.split(" ").length > 3) {
                            String fullTaskDescription = str.split(" ", 2)[1];
                            String description = fullTaskDescription.split(" /by ")[0];
                            String by = fullTaskDescription.split(" /by ")[1];

                            Deadline deadline = new Deadline(description, by);
                            tasks.add(deadline);
                            System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
                        } else {
                            System.out.println("Ding: I seriously have no idea what I need to do here");
                            throw new InvalidDescriptionException("Invalid description.");
                        }
                        break;
                    case "event":
                        if (str.split(" ").length > 4) {
                            String fullTaskDescription = str.split(" ", 2)[1];
                            String description = fullTaskDescription.split(" /from ")[0];
                            String from = String.join("", fullTaskDescription.split(" /from ")[1]).split(" /to ")[0];
                            String to = fullTaskDescription.split(" /to ")[1];

                            Event event = new Event(description, from, to);
                            tasks.add(event);
                            System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.\n You have like %d tasks now", str, tasks.size()));
                        } else {
                            System.out.println("Ding: I seriously have no idea what I need to do here");
                            throw new InvalidDescriptionException("Invalid description.");
                        }
                        break;
                    case "delete":
                        if (str.split(" ").length == 2) {
                            int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
                            if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
                                System.out.println("Ding: I can't find that task :( I may have lost it...");
                                throw new InvalidTaskIndexException("Invalid Task Index.");
                            }
                            Task toRemove = tasks.get(taskIndex);
                            tasks.remove(taskIndex);
                            System.out.println(String.format("Ding: Okay, I've forgotten this task, so don't expect me to remember it:\n %s", toRemove));
                            System.out.println(String.format("Ding: Right so now you have like %d tasks left", tasks.size()));
                        } else {
                            System.out.println("Ding: Which task do you want delete?");
                            throw new MissingTaskIndexException("Task Index Missing.");
                        }
                        break;
                    default:
                        System.out.println("Ding: I seriously have no idea what I need to do here");
                        throw new InvalidCommandException("No Command found.");

                }
            } catch (InvalidDescriptionException e) {
                System.err.println(e);
                System.out.println("Ding: I may be forgetful but you're so bad you even forgot the task description...");
                System.out.println("Ding: For ToDos, input 'todo (task)'");
                System.out.println("Ding: For Deadlines, input 'deadline (task) /by (date or time)");
                System.out.println("Ding: For Events, input 'event (task) /from (date or time) /to (date or time)");
            } catch (InvalidCommandException e) {
                System.out.println("Ding: No way you forgot to even input a proper command...");
                System.out.println("Ding: Available commands are 'todo', 'deadline', 'event', 'list', 'mark', 'unmark', 'bye'");

            } catch (InvalidTaskIndexException e) {
                System.out.println("Ding: Oh wait it's not lost, the task number you gave just doesn't exist in your list...");
                if (tasks.size() > 0) {
                    System.out.println(String.format("Ding: Please input a task number from 1 to %d", tasks.size()));
                } else {
                    System.out.println("Ding: You have nothing in your task list... What are you even trying to get me to do?");
                }
            } catch (MissingTaskIndexException e) {
                System.out.println("Ding: I don't quite understand what you want to do...");
                System.out.println("Ding: Please input '(command) (number)'...");
            } finally {
                System.out.println("\n____________________________________________________________\n");
                str = sc.nextLine();
            }
        }
        System.out.println(
                "____________________________________________________________\n" +
                "Ding: Bye. Hopefully I get to see you again soon!\n" +
                "____________________________________________________________");

    }

}

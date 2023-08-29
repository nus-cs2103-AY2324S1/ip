import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add
// the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start
and end, by moving
the statement outside the for loop
 */
// Solution below inspired by ChatGPT, regarding the method .contains("mark"), as it might be a bug if the input contains mark,
// without a corresponding integer.
// Solution below inspired by https://stackoverflow.com/questions/12973871/read-multiple-user-input-words-and-split-them
// Solution below inspired by ChatGPT, employed its help to solve my indexing problem. (ie mark 1 and unmark 1 refers to
// diff items)
/* Solution below inspired by ChatGPT, employed its help to solve the list not updating and showing the items with their
correct status icon, by creating a new task array of tasks instead of a string array.
 */
// Solution below inspired by https://stackoverflow.com/questions/10405789/regex-append-or-replace-only-the-first-letter-of-each-word
// Solution below inspired by https://www.programiz.com/java-programming/library/string/replacefirst
// Solution below adapted by ChatGPT, to solve the exception error when invoking last line of the loop. when there is no next line.
// Solution below inspired by https://stackoverflow.com/questions/32733084/pass-a-simple-enum-into-a-constructor-in-java
// Solution below inspired from ChatGPT, seeked clarification if the enums have to be passed into the child classes of parent class Task's constructor

public class Duke {

    public static void main(String[] args) throws DukeException.NoSuchItemException, DukeException.ToDoException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = Saving.loadTasks(); // Load tasks from file
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        String separators = "____________________________________________________________";
        String text1 = " Hello! I'm Novo\n"
                + " What can I do for you?\n" + separators
                + "\n";
        String text2 = " Bye. Hope to see you again soon!";
        System.out.println(separators + "\n" + text1);

        Scanner sc = new Scanner(System.in);
        int num_items = tasks.size();

        String user_text = sc.nextLine();

        while (!user_text.isEmpty()) {
            try {
                if (user_text.equals("todo")) {
                    throw new DukeException.ToDoException();
                }
                if (user_text.equals("blah")) {
                    throw new DukeException.NoSuchItemException();
                }
                if (user_text.equals("event")) {
                    throw new DukeException.EventException();
                }
                if (user_text.equals("deadline")) {
                    throw new DukeException.DeadlineException();
                }

                if (user_text.equals("bye")) {
                    System.out.println(separators + "\n" + text2 + "\n" + separators);
                    break;
                } else if (user_text.equals("list")) {
                    System.out.println(separators);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(separators);
                } else if (user_text.contains("mark")) {
                    String[] split_commands = user_text.split(" ");

                    if (split_commands.length == 2) {
                        int index_toChange = 1;
                        if (split_commands[0].equals("mark")) {
                            index_toChange = Integer.parseInt(split_commands[1]);
                            tasks.get(index_toChange - 1).markAsDone();
                            System.out.println(separators);
                            System.out.println("Nice! I've marked this task as done:" + "\n"
                                    + tasks.get(index_toChange - 1).toString());
                            System.out.println(separators);
                            Saving.saveTasks(tasks);
                        } else {
                            index_toChange = Integer.parseInt(split_commands[1]);
                            tasks.get(index_toChange - 1).markAsNotDone();
                            System.out.println(separators);
                            System.out.println("OK, I've marked this task as not done yet:" + "\n"
                                    + tasks.get(index_toChange - 1).toString());
                            System.out.println(separators);
                            Saving.saveTasks(tasks);
                        }
                    }

                } else if (user_text.contains("delete")) {
                    String[] split = user_text.split(" ");
                    int index_toChange = 1;

                    if (split[0].equals("delete")) {
                        index_toChange = Integer.parseInt(split[1]);
                        System.out.println(separators);
                        System.out.println("Noted. I've removed this task:" + "\n"
                                + tasks.get(index_toChange - 1).toString());
                        tasks.remove(index_toChange - 1);
                        num_items--;
                        System.out.println("Now you have " + num_items + " tasks in the list.");
                        System.out.println(separators);
                        Saving.saveTasks(tasks);
                    }

                } else if (user_text.contains("todo")) {
                    String[] split_command = user_text.split(" ");
                    String clean_text = user_text.replaceFirst("todo", "");
                    if (split_command[0].equals("todo")) {
                        tasks.add(new Todo(clean_text));
                        Saving.saveTasks(tasks);
                        num_items++;
                        System.out.println(separators);
                        System.out.println("Got it. I've added this task:" + "\n" + tasks.get(num_items-1).toString());
                        System.out.println("Now you have " + num_items + " tasks in the list.");
                        System.out.println(separators);

                    }

                } else if (user_text.contains("deadline")) {
                    String[] split_the_command = user_text.split(" ");
                    String[] clean_text = user_text.split("/", 2);
                    String the_description = clean_text[0].replaceFirst("deadline", "");
                    String the_by = clean_text[1];

                    if (split_the_command[0].equals("deadline")) {
                        tasks.add(new Deadline(the_description, the_by.replaceFirst("by", "by:")));
                        Saving.saveTasks(tasks);
                        num_items++;
                        System.out.println(separators);
                        System.out.println("Got it. I've added this task:" + "\n" + tasks.get(num_items - 1).toString());
                        System.out.println("Now you have " + num_items + " tasks in the list.");
                        System.out.println(separators);
                    }
                } else if (user_text.contains("event")) {
                    String[] split_the_command = user_text.split(" ");
                    String[] clean_text = user_text.split("/", 3);
                    String the_description = clean_text[0].replaceFirst("event", "");
                    String the_from = clean_text[1].replaceFirst("from", "from:");
                    String the_to = clean_text[2].replaceFirst("to", "to:");

                    if (split_the_command[0].equals("event")) {
                        tasks.add(new Event(the_description, the_from, the_to));
                        Saving.saveTasks(tasks);
                        num_items++;
                        System.out.println(separators);
                        System.out.println("Got it. I've added this task:" + "\n" + tasks.get(num_items - 1).toString());
                        System.out.println("Now you have " + num_items + " tasks in the list.");
                        System.out.println(separators);
                        Saving.saveTasks(tasks);
                    }
                } else {
                    tasks.add(new Task(user_text, Task.Type.OTHERS));
                    Saving.saveTasks(tasks);
                    num_items++;
                    System.out.println(separators + "\n" + "added: " + user_text + "\n" + separators);
                }
            } catch (DukeException.ToDoException e) {
                System.out.println("____________________________________________________________");
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                System.out.println("____________________________________________________________");
            } catch (DukeException.NoSuchItemException e) {
                System.out.println("____________________________________________________________");
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            } catch (DukeException.EventException e) {
                System.out.println("____________________________________________________________");
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } catch (DukeException.DeadlineException e) {
                System.out.println("____________________________________________________________");
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            }
            user_text = sc.hasNextLine() ? sc.nextLine() : "";
        }
        sc.close();
    }
}

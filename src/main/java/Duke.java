import java.util.Scanner;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add
// the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start
and end, by moving
the statement outside the for loop
 */
// Solution inspired by ChatGPT, regarding the method .contains("mark"), as it might be a bug if the input contains mark,
// without a corresponding integer.
// Solution below inspired by https://stackoverflow.com/questions/12973871/read-multiple-user-input-words-and-split-them
// Solution below inspired by ChatGPT, employed its help to solve my indexing problem. (ie mark 1 and unmark 1 refers to
// diff items)
/* Solution below inspired by ChatGPT, employed its help to solve the list not updating and showing the items with their
correct status icon, by creating a new task array of tasks instead of a string array.
 */

public class Duke {
    public static void main(String[] args) {
        String separators = "____________________________________________________________";
        String text1 = " Hello! I'm Novo\n"
                + " What can I do for you?\n" + separators
                + "\n";
        String text2 = " Bye. Hope to see you again soon!";
        System.out.println(separators + "\n" + text1);

        Scanner sc = new Scanner(System.in);
        String user_text = sc.nextLine();
        Task[] tasks = new Task[100];
        int num_items = 0;

        while (!user_text.isEmpty()) {
            if (user_text.equals("bye")) {
                System.out.println(separators + "\n" + text2 + "\n" + separators + "\n");
                break;
            } else if (user_text.equals("list")) {
                System.out.println(separators);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < num_items; i++) {
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
                }
                System.out.println(separators);
            } else if (user_text.contains("mark")) {
                String[] split_commands = user_text.split(" ");

                if (split_commands.length == 2) {
                    int index_toChange = 1;
                    if (split_commands[0].equals("mark")) {
                        index_toChange = Integer.parseInt(split_commands[1]);
                        tasks[index_toChange - 1].markAsDone();
                        System.out.println(separators);
                        System.out.println("Nice! I've marked this task as done:" + "\n" + "[" +
                                tasks[index_toChange - 1].getStatusIcon() + "] " + tasks[index_toChange - 1].description);
                        System.out.println(separators);
                    } else {
                        index_toChange = Integer.parseInt(split_commands[1]);
                        tasks[index_toChange - 1].markAsNotDone();
                        System.out.println(separators);
                        System.out.println("OK, I've marked this task as not done yet:" + "\n" + "[" +
                                tasks[index_toChange - 1].getStatusIcon() + "] " + tasks[index_toChange - 1].description);
                        System.out.println(separators);
                    }
                } else {
                    // only contains the word mark
                    tasks[num_items] = new Task(user_text);
                    num_items++;
                    System.out.println(separators + "\n" + "added: " + user_text + "\n" + separators + "\n");
                }
            } else {
                tasks[num_items] = new Task(user_text);
                num_items++;
                System.out.println(separators + "\n" + "added: " + user_text + "\n" + separators + "\n");
            }
            user_text = sc.nextLine();
        }
    }
}

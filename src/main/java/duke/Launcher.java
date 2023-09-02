package duke;

import java.util.ArrayList;

import javafx.application.Application;



//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(duke.Main.class, args);
    }

    public static class TaskList {
        private ArrayList<Task> tasks;

        public TaskList(ArrayList<Task> tasks) {
            this.tasks = tasks;
        }

        public TaskList() {
            tasks = null;
        }
        public ArrayList<Task> task() {
            return tasks;
        }

        public int num() {
            return tasks.size();
        }


        /**
         * Return the list of task descriptions
         *
         * @return an ArrayList with the String of description of each task in the file
         */
        public ArrayList<String> listTasks() {
            ArrayList<String> msg = new ArrayList<String>();
            if (tasks.isEmpty()) {
                msg.add("List is empty");
            }

            System.out.println("List:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                msg.add((i + 1) + ". " + task.toString());
            }
            return msg;
        }

        /**
         * Mark the task as done
         *
         * @param input string that user key in to dictate which task to mark
         * @param number number of tasks in the list
         */
        public String mark(String input, int number) {
            try {
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    Task item = tasks.get(taskIndex);
                    item.markAsDone();
                    return ("Nice! I've marked this task as done: \n" + item.toString());
                } else {
                    return ("You have chosen an invalid task");
                }
            } catch (NumberFormatException e) {
                return ("Invalid input. Please provide a valid task number.");
            }
        }

        /**
         * Mark task as undone
         *
         * @param input string that user key in to dictate which task to unmark
         * @param number number of tasks in the list
         */
        public String unmark(String input, int number) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    Task item = tasks.get(taskIndex);
                    item.markAsNotDone();
                    return ("OK, I've marked this task as not done yet: \n" + item.toString());
                } else {
                    return ("You have chosen an invalid task");
                }
            } catch (NumberFormatException e) {
                return ("Invalid input. Please provide a valid task number.");
            }
        }

        /**
         * Remove the task from the list
         *
         * @param input string that user key in to dictate which task to delete
         * @param number number of task in the list
         * @return the number of task in the list after deleting
         */
        public int delete(String input, int number, Gui gui) {
            try {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    Task item = tasks.get(taskIndex);
                    tasks.remove(taskIndex);
                    number--;
                    StringBuilder combinedMessage = new StringBuilder();
                    combinedMessage.append("Noted. I've removed this task:").append("\n");
                    combinedMessage.append(item.toString()).append("\n");
                    combinedMessage.append("Now you have " + number + " tasks in the list");
                    gui.print(combinedMessage.toString());
                    return number;
                } else {
                    gui.print("You have chosen an invalid task");
                    return number;
                }
            } catch (NumberFormatException e) {
                gui.print("Invalid input. Please provide a valid task number.");
                return number;
            }
        }

        /**
         * Add the given task to the ArrayList
         * @param task task to be added into the ArrayList
         */
        public void add(Task task) {
            tasks.add(task);
        }

        public ArrayList<String> find(String input) {
            String trimmedInput = input.substring(5).trim();
            ArrayList<String> matchingTasks = new ArrayList<>();
            int number = 1;
            for (Task task : tasks) {
                if (task.getDescription().contains(trimmedInput)) {
                    String string = task.toString();
                    matchingTasks.add(number + "." + string);
                    number++;
                }
            }
            if (!matchingTasks.isEmpty()) {
                matchingTasks.add(0, "Here are the matching tasks in your list:");
                return matchingTasks;
            } else {
                matchingTasks.add("There are no matching tasks in your list");
                return matchingTasks;
            }

        }
    }
}

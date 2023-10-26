package duke.helper;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class TaskList {
    private Task[] userList;
    private int userListPointer;




    public TaskList(Task[] userList, int userListPointer) {
        this.userList = userList;
        this.userListPointer = userListPointer;

    }

    public TaskList() {
        this.userList = new Task[100];
        this.userListPointer = 0;
    }

    /**
     * Adds a task into the task list.
     *
     * @param task The task to add.
     * @return A message of successfully adding the task.
     */
    public String addTask(Task task) {
        userList[userListPointer] = task;
        userListPointer++;
        String message = "Got it. I've added this task:\n" + task.display();
        message = message + "\nNow you have " + userListPointer + " tasks in the list.";
        return message;


    }

    /**
     * Deletes a task at given position.
     *
     * @param position The position of the task to be deleted.
     * @return A message of the result of the process.
     */
    public String deleteTask(int position) {

        if (position < 0 || position >= userListPointer) {
            return ("Invalid index.");
        } else {

            String message = "Noted. I've removed this task:\n" + userList[position].display();


            Task[] newUserList = new Task[100];

            for (int a = 0, k = 0; a < userListPointer; a++) {

                // if the index is
                // the removal element index
                if (a == position) {
                    continue;
                }

                // if the index is not
                // the removal element index
                newUserList[k++] = userList[a];
            }

            userListPointer--;

            userList = newUserList;

            message = message + "\nNow you have " + userListPointer + " tasks in the list.";
            return message;

        }
    }

    /**
     * Marks a task at given position as done.
     *
     * @param position The position of the task to be marked.
     * @return A message of the result of the process.
     */
    public String markTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].markDone();

            assert userList[position].getCompletionStatus(); //target task should be marked as done

            int index = position + 1;
            return "Following task is marked as done:\n" + index + ". "
                    + userList[position].display();

        }
    }

    /**
     * Marks a task at given position as undone.
     *
     * @param position The position of the task to be marked.
     * @return A message of the result of the process.
     */
    public String unmarkTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].unmarkDone();

            assert !userList[position].getCompletionStatus();//target task should be marked as undone

            int index = position + 1;
            return "Following task is marked as undone:\n" + index + ". "
                    + userList[position].display();

        }
    }

    public Task[] getUserList() {
        return userList;
    }

    public int getUserListPointer() {
        return userListPointer;
    }

    /**
     * Returns the String containing all the information in the list.
     * @return String representation of the content in the list.
     */
    public String displayList() {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < userListPointer; i++) {
                int num = i + 1;
                message.append(num).append(". ").append(userList[i].display()).append("\n");


            }
            return message.toString();
        }
    }

    /**
     * Finds all the tasks in the task list with given key word in their names.
     *
     * @param keyWords The key word for searching.
     * @return A string representation of all the tasks found.
     */
    public String findTask(String keyWords) {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            int num = 1;
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < userListPointer; i++) {
                if (userList[i].getTaskName().toLowerCase().contains(keyWords.toLowerCase())) {
                    message.append(num).append(". ").append(userList[i].display()).append("\n");
                    num++;
                }

            }
            if (num == 1) {
                return "No matches found";
            } else {
                return "Here are the matching tasks in your list:\n" + message;
            }

        }
    }


    /**
     * Shows statistics about the task list.
     */
    public String showTaskStatistics() {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            int numTodo = 0;
            int numDeadLine = 0;
            int numEvent = 0;

            for (int i = 0; i < userListPointer; i++) {
                int type = userList[i].getTypeOfTask();
                if (type == 1) {
                    numTodo++;
                }
                if (type == 2) {
                    numDeadLine++;
                }
                if (type == 3) {
                    numEvent++;
                }



            }

            assert numTodo <= userListPointer;//number of todo tasks should not exceed the total number of tasks
            assert numDeadLine <= userListPointer;//number of deadline tasks should not exceed the total number of tasks
            assert numEvent <= userListPointer;//number of event tasks should not exceed the total number of tasks

            return "You have:\n" + "~ " + numTodo + " todo.\n" + "~ " + numDeadLine + " deadline.\n"
                    + "~ " + numEvent + " event.\n";



        }
    }

    /**
     * Sorts the list based on start dates.
     */
    public void sortByStartDate() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < userListPointer; i++) {
            tasks.add(userList[i]);
        }
        tasks.sort(Comparator.comparing(Task::getStartDate));
        for (int i = 0; i < userListPointer; i++) {
            userList[i] = tasks.get(i);
        }
    }

    /**
     * Sorts the list based on end dates.
     */
    public void sortByEndDate() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < userListPointer; i++) {
            tasks.add(userList[i]);
        }
        tasks.sort(Comparator.comparing(Task::getEndDate));
        for (int i = 0; i < userListPointer; i++) {
            userList[i] = tasks.get(i);
        }
    }
}

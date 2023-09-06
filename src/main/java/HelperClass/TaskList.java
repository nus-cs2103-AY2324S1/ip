package HelperClass;

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

    public String addTask(Task task) {
        userList[userListPointer] = task;
        userListPointer++;
        String message = "Got it. I've added this task:\n" + task.display();
        message = message + "\nNow you have " + userListPointer + " tasks in the list.";
        return message;


    }

    public String deleteTask(int position) {

        if (position < 0 || position >= userListPointer) {
            return ("Invalid index.");
        } else {

            String message = "Noted. I've removed this task:" + userList[position].display();


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

    public String markTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].markDone();
            return "Following task is marked as done:\n" + userList[position].display();

        }
    }

    public String unmarkTask(int position) {
        if (position < 0 || position >= userListPointer) {
            return "Invalid index.";
        } else {

            userList[position].unmarkDone();
            return "Following task is marked as undone:\n" + userList[position].display();

        }
    }

    public Task[] getUserList() {
        return userList;
    }

    public int getUserListPointer() {
        return userListPointer;
    }

    public String displayList() {
        if (userListPointer < 1) {
            return "No items in the list yet";
        } else {
            StringBuilder message = new StringBuilder();
            for (int i = 0; i < userListPointer; i++) {
                int num = i + 1;
                message.append(num).append(userList[i].display()).append("\n");


            }
            return message.toString();
        }
    }
}

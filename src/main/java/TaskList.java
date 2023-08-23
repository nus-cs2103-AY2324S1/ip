public class TaskList {
    private Task[] tasksDescArray;
    private int counter;


    TaskList() {
        tasksDescArray = new Task[100];
        counter = 0;
    }

    void addTask(Task taskDesc) {
        if (counter < tasksDescArray.length) {
            tasksDescArray[counter] = taskDesc;
            counter++;
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + taskDesc);
            System.out.println(" Now you have " + counter + " tasks in the list.");
        } else {
            System.out.println(" 100/100 Task limit reached.");
            System.out.println(" Pay to upgrade your account.");
        }
    }

    public void displayTasks() {
        if (counter == 0) {
            System.out.println("No task present in list");
        } if (counter < 0) {
            System.out.println("Error: magician, you have negative task.");
        } else {
            for (int i = 0; i < counter; i++) {
                System.out.println(" " + (i + 1) + "." + tasksDescArray[i]);
            }
        }
    }

    public void doneAndDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < counter) {
            tasksDescArray[taskNum].isCompleted();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasksDescArray[taskNum]);
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public void notDoneNotDusted(int taskNum) {
        if (taskNum >= 0 && taskNum < counter) {
            tasksDescArray[taskNum].isNotCompleted();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasksDescArray[taskNum]);
        } else {
            System.out.println(" Error: No such Task Number");
        }
    }

    public int getCounter() {
        return counter;
    }

    public boolean isMarked(int taskNum) {
        if (taskNum >= 0 && taskNum < counter) {
            return tasksDescArray[taskNum].isDone;
        } else {
            System.out.println("Error: No such Task Number");
            return false;
        }
    }

}

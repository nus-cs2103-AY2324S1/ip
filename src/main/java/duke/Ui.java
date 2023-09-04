package duke;

import duke.tasks.Task;

/**
 * deals with interactions with the user
 */
public class Ui {
    public static String line = "--------------------------------------------------------------------";

    public void greeting() {
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        System.out.println(line + "\n" + intro + line);
    }

    public void bye() {
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";
        System.out.println(bye + line);
    }

    //get the size by calling storage.getListSize() later in duke.Duke
    public void showNumberOfTasks(int size) {
        System.out.println(size == 1
                ? "Now you have " + size + " task in the list!\n" + line
                : "Now you have " + size + " tasks in the list!\n" + line);
    }

    public void showNoTasks() {
        System.out.println("(o´ω`o)ﾉ You have no upcoming tasks!\n" + Ui.line);
    }

    public void showTasks(String tasks) {
        System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are your tasks for the day:");
        System.out.println(tasks + Ui.line);
    }

    public void showTaskAdded(Task task, int listSize) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
        this.showNumberOfTasks(listSize);
    }
    public void showDeleteTask(Task toRemove, int listSize) {
        System.out.println("ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:");
        System.out.println(toRemove);
        this.showNumberOfTasks(listSize);
    }

    public void showMarkTask(boolean isMarked, Task task) {
        if (!isMarked) {
            System.out.println("ଘ(੭ˊᵕˋ)੭ Yay! This task is completed:\n" + task.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as completed!");
            System.out.println(line);
        }
    }

    public void showUnmarkTask(boolean isMarked, Task task) {
        if (isMarked) {
            System.out.println("໒( ̿･ ᴥ ̿･ )ʋ All righty, I've marked this task as uncompleted:\n" + task.toString());
            System.out.println(line);
        } else {
            System.out.println("┐(´～｀)┌ This task is already marked as uncompleted!");
            System.out.println(line);
        }
    }
    public void showError(String message) {
        System.out.println(message);
        System.out.println(line);
    }

    public void showInvalidDateFormat() {
        System.out.println("(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format");
        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("(・´з`・) Uh oh... error loading BUTTER");
    }
}

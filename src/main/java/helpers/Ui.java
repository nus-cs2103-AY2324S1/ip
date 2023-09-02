package helpers;

import java.io.BufferedReader;
import java.io.IOException;

import exceptions.EmptyTasksException;
import exceptions.ErrorStorageException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidCommandException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTimeFormatException;
import tasks.Task;

/**
 * Represents Ui class that operations to print out messages for users
 */
public class Ui {
    final boolean isDebug = true;
    final String DIVIDER = "____________________________________________________________";

    /**
     * Method to print out the starting logo
     */
    public void showStartLogo() {
        String logo = isDebug ? ""
                : "      ＼ﾌﾌ 　　      ム｀ヽ\n"
                + "    / ノ)　  A　 A 　  ）  ヽ\n"
                + "   / ｜　　( ´・ω・`)ノ⌒ ゝ_ノ\n"
                + "  /　ﾉ⌒ 7  ⌒ヽーく　 ＼　／\n"
                + "  丶＿ ノ ｡　　  ノ､　　|/\n"
                + "　　  `ヽ   `ー-'_人`ーﾉ\n"
                + "　　　   丶   ￣ _人'彡ﾉ\n";
        System.out.println(logo);
    }

    /**
     * Method to print out the welcome message
     */
    public void showWelcome() {
        System.out.println(DIVIDER + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                + "\nWhat can I do for you today?\n" + DIVIDER);
    }

    /**
     * Method to print ending logo when user quits application
     */
    public void quit() {
        String endLogo = isDebug ? ""
                : "               ＿   ★★EVERYDAY★★\n"
                + "           ／     j     ★★ IS A  ★★\n"
                + "        ／     /ｰ'          ★★ MACHO  ★★\n"
                + "     〈       ヽ               ★★ DAY!!!  ★★\n"
                + "           ､       ヽ ﾍ⌒ ヽﾌ\n"
                + "             〉       ´ ･ω )        ,-､、\n"
                + "           / ノ         ￣⌒ヽ　「　   〉\n"
                + "          ﾉ       ' L          `ヽ.／   /\n"
                + "     ／    , '           .ノ＼    ´    /\n"
                + "    (                ∠_       ヽ､＿,.\n"
                + "     ＼   (            ヽ ";
        System.out.println(DIVIDER);
        System.out.println(endLogo);
    }

    /**
     * Method to get command from input
     *
     * @param br BufferedReader
     * @return String of input
     * @throws IOException Input exception
     */
    public String getCommand(BufferedReader br) throws IOException {
        return br.readLine();
    }

    /**
     * Method to show message that task has been marked
     *
     * @param task Task to mark as done
     */
    public void showMarkDoneMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have marked this task as done per your request, macho!\n" + task);
        System.out.println(DIVIDER);
    }

    /**
     * Method to show message that task has been unmarked
     *
     * @param task Task to unmarked as done
     */
    public void showUnmarkDoneMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have marked this task as undone yet, per your request, macho!\n" + task);
        System.out.println(DIVIDER);
    }

    /**
     * Method to show message that task has been deleted
     *
     * @param taskList List of tasks
     * @param task     Task to be deleted
     */
    public void showDeletedTaskMessage(TaskList taskList, Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have deleted this task as done per your request, macho!\n" + task.toString()
                + "\nYou now have " + taskList.getListLength() + " tasks in the list, macho!");
        System.out.println(DIVIDER);
    }

    /**
     * Method to show message that task has been added
     *
     * @param taskList List of tasks
     * @param task     Task to be added
     */
    public void showAddTaskMessage(TaskList taskList, Task task) {
        System.out.println(DIVIDER);
        System.out.println("Got it macho! I've added this task:\n" + task + "\n"
                + "You now have " + taskList.getListLength() + " tasks in the list, macho!");
        System.out.println(DIVIDER);
    }

    /**
     * Method to show message on the list of tasks
     *
     * @param taskList List of tasks
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.println(taskList.printTaskList());
        System.out.println(DIVIDER);
    }

    /**
     * Method to show error message if loading of storage fails
     *
     * @throws ErrorStorageException Exception for storage loading error
     */
    public void showLoadingError() throws ErrorStorageException {
        System.out.println(DIVIDER);
        throw new ErrorStorageException(DIVIDER);
    }

    /**
     * Method to show error message if there is invalid argument
     *
     * @param substring String with invalid argument
     * @param s         Command for correct argument
     * @throws InvalidArgumentException Exception for invalid argument
     */

    public void showArgumentErrorMessage(String substring, String s) throws InvalidArgumentException {
        System.out.println(DIVIDER);
        throw new InvalidArgumentException(substring, s, DIVIDER);
    }


    /**
     * Method to show error message if there is invalid datetime format
     *
     * @param part String containing the invalid format
     * @throws InvalidTimeFormatException Exception for invalid datetimeformat
     */
    public void showInvalidTimeFormatErrorMessage(String part) throws InvalidTimeFormatException {
        System.out.println(DIVIDER);
        throw new InvalidTimeFormatException(part, DIVIDER);
    }

    /**
     * Method to show error message if there is invalid index
     *
     * @param input String containing the invalid index
     * @throws InvalidIndexException Exception for invalid index
     */
    public void showInvalidIndexErrorMessage(String input) throws InvalidIndexException {
        System.out.println(DIVIDER);
        throw new InvalidIndexException(DIVIDER);
    }


    /**
     * Method to show error message if there is invalid command entered
     *
     * @param message String containing the invalid command
     * @throws InvalidCommandException Exception for invalid command given
     */
    public void showInvalidCommandErrorMessage(String message) throws InvalidCommandException {
        System.out.println(DIVIDER);
        throw new InvalidCommandException(DIVIDER);
    }

    /**
     * Method to show error message if user is performing actions on empty tasks
     *
     * @throws EmptyTasksException Exception for empty tasks
     */
    public void showEmptyTasksError(String input) throws EmptyTasksException {
        System.out.println(DIVIDER);
        throw new EmptyTasksException(DIVIDER);
    }

    /**
     * Method to get the string of divider
     *
     * @return DIVIDER
     */
    public String getDivider() {
        return DIVIDER;
    }
}

package Helpers;

import Exceptions.*;
import Tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;

public class Ui {
    final boolean isDebug = true;
    final String DIVIDER = "____________________________________________________________";

    public void showStartLogo() {
        String logo = isDebug ? "" :
                "      ＼ﾌﾌ 　　      ム｀ヽ\n" +
                        "    / ノ)　  A　 A 　  ）  ヽ\n" +
                        "   / ｜　　( ´・ω・`)ノ⌒ ゝ_ノ\n" +
                        "  /　ﾉ⌒ 7  ⌒ヽーく　 ＼　／\n" +
                        "  丶＿ ノ ｡　　  ノ､　　|/\n" +
                        "　　  `ヽ   `ー-'_人`ーﾉ\n" +
                        "　　　   丶   ￣ _人'彡ﾉ\n";
        System.out.println(logo);
    }

    public void showWelcome() {
        System.out.println(DIVIDER + "\nHello! I'm MACHO-CATTO! Your personal chat-bot to make your \nday macho!"
                + "\nWhat can I do for you today?\n" + DIVIDER);
    }

    public void quit() {
        String endLogo = isDebug ? "" :
                "               ＿   ★★EVERYDAY★★\n" +
                        "           ／     j     ★★ IS A  ★★\n" +
                        "        ／     /ｰ'          ★★ MACHO  ★★\n" +
                        "     〈       ヽ               ★★ DAY!!!  ★★\n" +
                        "           ､       ヽ ﾍ⌒ ヽﾌ\n" +
                        "             〉       ´ ･ω )        ,-､、\n" +
                        "           / ノ         ￣⌒ヽ　「　   〉\n" +
                        "          ﾉ       ' L          `ヽ.／   /\n" +
                        "     ／    , '           .ノ＼    ´    /\n" +
                        "    (                ∠_       ヽ､＿,.\n" +
                        "     ＼   (            ヽ ";
        System.out.println(DIVIDER);
        System.out.println(endLogo);
    }

    public String getCommand(BufferedReader br) throws IOException {
        return br.readLine();
    }

    public void showMarkDoneMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have marked this task as done per your request, macho!\n" + task);
        System.out.println(DIVIDER);
    }

    public void showUnmarkDoneMessage(Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have marked this task as undone yet, per your request, macho!\n" + task);
        System.out.println(DIVIDER);
    }

    public void showDeletedTaskMessage(TaskList taskList, Task task) {
        System.out.println(DIVIDER);
        System.out.println("I have deleted this task as done per your request, macho!\n" + task.toString()
                + "\nYou now have " + taskList.getListLength() + " tasks in the list, macho!");
        System.out.println(DIVIDER);
    }

    public void showAddTaskMessage(TaskList taskList, Task task) {
        System.out.println(DIVIDER);
        System.out.println("Got it macho! I've added this task:\n" + task + "\n" +
                "You now have " + taskList.getListLength() + " tasks in the list, macho!");
        System.out.println(DIVIDER);
    }

    public void showTaskList(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.println(taskList.printTaskList());
        System.out.println(DIVIDER);
    }

    public void showLoadingError() throws ErrorStorageException {
        System.out.println(DIVIDER);
        throw new ErrorStorageException(DIVIDER);
    }

    public void showArgumentErrorMessage(String substring, String s) throws InvalidArgumentException {
        System.out.println(DIVIDER);
        throw new InvalidArgumentException(substring, s, DIVIDER);
    }


    public void showInvalidTimeFormatErrorMessage(String part) throws InvalidTimeFormatException {
        System.out.println(DIVIDER);
        throw new InvalidTimeFormatException(part, DIVIDER);
    }

    public void showInvalidIndexErrorMessage(String input) throws InvalidIndexException {
        System.out.println(DIVIDER);
        throw new InvalidIndexException(DIVIDER);
    }


    public void showInvalidCommandErrorMessage(String message) throws InvalidCommandException {
        System.out.println(DIVIDER);
        throw new InvalidCommandException(DIVIDER);
    }

    public void showEmptyTasksError(String input) throws EmptyTasksException {
        System.out.println(DIVIDER);
        throw new EmptyTasksException(DIVIDER);
    }

    public String getDivider() {
        return DIVIDER;
    }
}

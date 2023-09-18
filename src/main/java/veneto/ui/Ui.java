package veneto.ui;

import veneto.command.*;
import veneto.exceptions.VenetoException;
import veneto.exceptions.VenetoOperateException;
import veneto.exceptions.VenetoStorageException;
import veneto.parser.Parser;
import veneto.task.TaskList;

import java.util.Arrays;
import java.util.Scanner;

public class Ui {
    /** Fields */
    private final static String GREETS = "\nVeneto: \n";
    private final static String[] COMMANDS = new String[] {
            "toDo [TASK]",
            "deadline [TASK] /by [DEADLINE(YYYY-MM-DD)]",
            "event [TASK] /from [START_TIME(YYYY-MM-DD)] /to [END_TIME(YYYY-MM-DD)]",
            "mark [TASK_ID]", "unmark [TASK_ID]",
            "list", "bye"
    };

    /** Methods */
    public static void showError(VenetoException e) {
        if (e instanceof VenetoOperateException) {
            switch (e.getMessage()) {
                case "Unmarked":
                    System.out.println(GREETS + " 这个没标记过哦！\n");
                    return;
                case "Marked":
                    System.out.println(GREETS + " 这个已经做完了哦！\n");
                    return;
                case "Add":
                    System.out.println(GREETS + " 输入格式不对！");
                    System.out.println(" 你可以跟我说：\n" + Arrays.toString(COMMANDS) + "\n");
            }
        } else if (e instanceof VenetoStorageException) {
            System.out.println(GREETS + " 没找到内存哦 现在重新创建一个！\n");
        } else if (e.getMessage().equals("Invalid Command")) {
            System.out.println(GREETS + " 输入格式不对！");
            System.out.println(" 你可以跟我说：\n" + Arrays.toString(COMMANDS) + "\n");
        } else {
            System.out.println(GREETS + "???\n");
        }
    }

    public void afterCommand(Command command, TaskList tasks) {
        switch (command.getType()) {
            case "exit":
                goodbye();
                break;
            case "add":
                System.out.println(
                        GREETS + " 记下来了：\n " + command +
                                "!\n 现在有" + tasks.size() + "项任务要做\n"
                );
                break;
            case "list":
                System.out.println(
                        GREETS + " 还有这些要做哦！\n" +
                                tasks.toString() + "\n"
                );
                break;
            case "mark":
                System.out.println(
                        GREETS + " 我这就帮您标记好！\n " + command + "\n"
                );
                break;
            case "unmark":
                System.out.println(
                        GREETS + " 啊 没关系 帮您擦掉标记了哦" + "\n " + command + "\n"
                );
                break;
            case "delete":
                System.out.println(
                        GREETS +
                                " 好啦，帮你擦掉了一条任务哦：\n " + command +
                                "\n 现在还剩下" + tasks.size() + "项任务\n"
                );
                break;
        }
    }

    public Command getCommand() {
        String text = new Scanner(System.in).nextLine();
        return Parser.translateCommand(text);
    }

    public void hello() {
        System.out.println(
                GREETS +
                        " VV为您服务\n" +
                        " 有什么可以要帮忙可以跟我说！\n"
        );
    }

    public void goodbye() {
        System.out.println(
                GREETS +
                        " 拜拜啦 下次见\n"
        );
    }
}

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
    /* Fields */
    private final static String GREETS = "\nVeneto: \n";
    private final static String[] COMMANDS = new String[] {
            "toDo [TASK]",
            "deadline [TASK] /by [DEADLINE(YYYY-MM-DD)]",
            "event [TASK] /from [START_TIME(YYYY-MM-DD)] /to [END_TIME(YYYY-MM-DD)]",
            "mark [TASK_ID]", "unmark [TASK_ID]",
            "list", "bye"
    };
    private final static String LOGO =
            " ___      ___ __________  ___    ___  __________  __________  __________\n" +
            " \\  \\    /  /|   _______||   \\  |   ||   _______||___    ___||   ____   |\n" +
            "  \\  \\  /  / |  |_______ |     \\|   ||  |_______     |  |    |  |    |  |\n" +
            "   \\  \\/  /  |   _______||          ||   _______|    |  |    |  |    |  |\n" +
            "    \\    /   |  |_______ |   |\\     ||  |_______     |  |    |  |____|  |\n" +
            "     \\__/    |__________||___|  \\___||__________|    |__|    |__________|    ...starts\n\n";

    /* Methods */
    /**
     * shows which exception occurs and give hint to the user
     * @param e the DanException
     */
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
                    return;
                case "Not Found":
                    System.out.println(GREETS + " 好像没有这样的任务要做哦\n");
                    return;
            }
        } else if (e instanceof VenetoStorageException) {
            if (e.getMessage().equals("Storage File Destroyed")) {
                System.out.println(GREETS + " 没找到内存哦 现在重新创建一个！\n");
            } else {
                System.out.println(GREETS + " 不该发生的发生了…");
            }
        } else if (e.getMessage().equals("Invalid Command")) {
            System.out.println(GREETS + " 输入格式不对！");
            System.out.println(" 你可以跟我说：\n" + Arrays.toString(COMMANDS) + "\n");
        } else {
            System.out.println(GREETS + " ???\n");
        }
    }

    /**
     * give responses to user after they call commands
     * @param command the Command operated just now
     * @param tasks the TaskList of Veneto
     */
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
            case "find":
                System.out.println(
                        GREETS + " 相关的任务都在这里了哦：\n" + command + "\n"
                );
                break;
        }
    }

    /**
     * get input from user
     * @return the command translated from user input
     */
    public Command getCommand() {
        String text = new Scanner(System.in).nextLine();
        return Parser.translateCommand(text);
    }

    /**
     * greets when Veneto runs
     */
    public void hello() {
        System.out.println(
                LOGO + GREETS +
                    " VV为您服务\n" +
                    " 有什么可以要帮忙可以跟我说！\n"
        );
    }

    /**
     * says goodbye when session ends
     */
    public void goodbye() {
        System.out.println(
                GREETS +
                        " 拜拜啦 下次见\n"
        );
    }
}

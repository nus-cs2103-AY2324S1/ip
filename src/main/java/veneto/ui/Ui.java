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
    public final static String EXIT_SUFFIX = "0";
    private final static String GREETS = "\nVeneto: \n";
    private final static String COMMANDS =
            "  toDo [TASK],\n" +
            "  deadline [TASK] /by [DEADLINE(YYYY-MM-DD)],\n" +
            "  event [TASK] /from [START_TIME(YYYY-MM-DD)] /to [END_TIME(YYYY-MM-DD)],\n" +
            "  mark [TASK_ID], " + "unmark [TASK_ID],\n" +
            "  find [KEYWORDS],\n" +
            "  list, " + "bye";
    private final static String LOGO =
            " ___      ___ __________  ___    ___  __________  __________  __________\n" +
            " \\  \\    /  /|   _______||   \\  |   ||   _______||___    ___||   ____   |\n" +
            "  \\  \\  /  / |  |_______ |     \\|   ||  |_______     |  |    |  |    |  |\n" +
            "   \\  \\/  /  |   _______||          ||   _______|    |  |    |  |    |  |\n" +
            "    \\    /   |  |_______ |   |\\     ||  |_______     |  |    |  |____|  |\n" +
            "     \\__/    |__________||___|  \\___||__________|    |__|    |__________|    ...starts\n\n";
    private final static String INCORRECT_ERROR = GREETS + " ???\n";

    /* Methods */
    /**
     * shows which exception occurs and give hint to the user
     * @param e the DanException
     */
    public String showError(VenetoException e) {
        if (e instanceof VenetoOperateException) {
            return showOperationError(e);
        } else if (e instanceof VenetoStorageException) {
            return showStorageError(e);
        } else if (e.getMessage().equals("Invalid Command")) {
            return showInvalidCommandError();
        } else {
            assert false : "Invalid Error";
        }
        return INCORRECT_ERROR;
    }

    private String showStorageError(VenetoException e) {
        if (e.getMessage().equals("Storage File Destroyed")) {
            return GREETS + " 没找到内存哦 现在重新创建一个！\n";
        } else {
            assert false : "Invalid type of exception";
        }
        return INCORRECT_ERROR;
    }

    private String showOperationError(VenetoException e) {
        switch (e.getMessage()) {
            case "Unmarked":
                return GREETS + " 这个没标记过哦！\n";
            case "Marked":
                return GREETS + " 这个已经做完了哦！\n";
            case "Add":
                return showInvalidCommandError();
            case "Not Found":
                return GREETS + " 好像没有这样的任务要做哦\n";
            case "Duplicate":
                return GREETS + " 已经记录过这个任务啦\n";
            default:
                assert false : "Invalid type of exception";
        }
        return INCORRECT_ERROR;
    }

    private String showInvalidCommandError() {
        return GREETS + " 输入格式不对！" + " 你可以跟我说：\n" + COMMANDS + "\n";
    }

    /**
     * give responses to user after they call commands
     * @param command the Command operated just now
     * @param tasks the TaskList of Veneto
     */
    public String afterCommand(Command command, TaskList tasks) {
        switch (command.getType()) {
            case "exit":
                return goodbye();
            case "add":
                return GREETS + " 记下来了：\n " + command +
                        "!\n 现在有" + tasks.size() + "项任务要做\n";
            case "list":
                return GREETS + " 还有这些要做哦！\n" + tasks.toString();
            case "mark":
                return GREETS + " 我这就帮您标记好！\n " + command + "\n";
            case "unmark":
                return GREETS + " 啊 没关系 帮您擦掉标记了哦" + "\n " + command + "\n";
            case "delete":
                return GREETS + " 好啦，帮你擦掉了一条任务哦：\n " + command +
                                "\n 现在还剩下" + tasks.size() + "项任务\n";
            case "find":
                return GREETS + " 相关的任务都在这里了哦：\n" + command;
            default:
                assert false : "Invalid type of command";
        }
        return "Parser error";
    }

    /**
     * get input from user
     * @return the command translated from user input
     */
    public Command getCommand(String input) throws VenetoException {
        return Parser.translateCommand(input);
    }

    /**
     * greets when Veneto runs
     */
    public String hello() {
        return LOGO + GREETS +
                " Veneto为您服务哦\n" +
                " 有什么可以要帮忙可以跟我说！\n";
    }

    /**
     * says goodbye when session ends
     */
    public String goodbye() {
        return GREETS + " 拜拜啦 下次见\n" + EXIT_SUFFIX;
    }
}

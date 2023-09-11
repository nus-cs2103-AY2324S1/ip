//package grumpygordon.ui;
//
//import java.util.Scanner;
//
//import grumpygordon.storage.Storage;
//import grumpygordon.tasks.TaskList;
//
//
//
///**
// * Represents the user interface of the chatbot.
// */
//public class Ui {
//
//    /**
//     * Separator for responses.
//     */
//    public static final String SEPARATOR = "    ____________________________________________________________\n";
//
//    /**
//     * Indentation for responses.
//     */
//    private static final String INDENTATION = "     ";
//
//    /**
//     * Intro string when chatbot starts.
//     */
//    private static final String INTRO = INDENTATION + "Oi! I'm Grumpy Gordon.\n"
//            + INDENTATION + "Why are you bothering me?\n";
//
//    /**
//     * Outro string when chatbot ends.
//     */
//    private static final String OUTRO = INDENTATION + "Bye. Hope to never see you again.\n";
//
//    /**
//     * Scanner to read user input.
//     */
//    private final Scanner scanner;
//
//    /**
//     * List of tasks by the user.
//     */
//    private final TaskList tasks;
//
//    /**
//     * Storage of tasks.
//     */
//    private final Storage storage;
//
//    /**
//     * Constructor of Ui.
//     *
//     * @param tasks List of tasks by user.
//     * @param storage Storage of tasks.
//     */
//    public Ui(TaskList tasks, Storage storage) {
//        this.scanner = new Scanner(System.in);
//        this.tasks = tasks;
//        this.storage = storage;
//    }
//
//
//
//}

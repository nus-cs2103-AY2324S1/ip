package catbot.bot;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import catbot.CatBotCommandPatterns;
import catbot.internal.CommandMap;
import catbot.internal.CommandPattern;
import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;
import catbot.io.UserIo;
import catbot.task.Deadline;
import catbot.task.Event;
import catbot.task.Task;
import catbot.task.TaskList;
import catbot.task.Todo;

/**
 * Main runnable class for the CatBot assistant.
 */
public class CatBot implements Bot {

    //region Fields

    private UserIo io;
    private CommandMap commands;
    private final TaskList taskList;

    //endregion

    //region Constructor

    public CatBot(TaskList taskList) {
        this.taskList = taskList;
    }

    //endregion

    //region Bot

    @Override
    public void initialize(UserIo userIo) {
        initializeFields(userIo);
        addSupportedCommandsToCommandMap();
    }

    public void run(CommandArgumentStruct commandArgumentStruct) {
        commands.run(commandArgumentStruct.getCommand(), commandArgumentStruct.getArgument());
    }

    //endregion

    //region Internal Helpers

    private void initializeFields(UserIo userIo) {
        this.io = userIo;
        this.commands = new CommandMap();
        assert taskList != null;
    }

    private void addSupportedCommandsToCommandMap() {
        CommandPattern<Integer> integerPattern = CatBotCommandPatterns.getIntegerPatternGenerator()
                .generateUsingDefault(io::indicateInvalidInteger);
        CommandPattern<NamedParameterMap> slashPattern = CatBotCommandPatterns.getSlashPatternGenerator()
                .generateUsingDefault(CatBotCommandPatterns.NO_DEFAULT);
        CommandPattern<String> stringPattern = CatBotCommandPatterns.getStringPatternGenerator()
                .generateUsingDefault(CatBotCommandPatterns.NO_DEFAULT);

        commands.setDefaultCommand(io::indicateInvalidCommand)
                .addCommand("bye", args -> io.cleanup())
                .addCommand("list", args -> io.displayTaskList(taskList));

        // User modifying existing tasks (through IntegerPattern, and Index)
        // noinspection FunctionalExpressionCanBeFolded for better readability
        BiConsumer<String, Consumer<Integer>> runIfValidIndexElseIndicateError = (args, lambda) ->
                integerPattern.ifParsableElseDefault(args,
                        integer -> taskList.ifValidIndexElse(integer,
                                validIndex -> lambda.accept(validIndex),
                                invalidIndex -> io.indicateInvalidIndex(invalidIndex, taskList.getIndexBounds())
                        ));

        //noinspection SpellCheckingInspection for "unmark"
        commands.addCommand("mark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.markTask(validIndex);
                                    io.displayTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("unmark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.unmarkTask(validIndex);
                                    io.displayTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("delete",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> io.displayTaskDeleted(taskList.removeTask(validIndex))
                        )
                );

        // User creating new tasks (with SlashPattern)

        BiConsumer<String, BiFunction<
                NamedParameterMap, BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap>,
                Optional<Task>>>
                createTaskIfValidElseWarn = (args, bifunction) -> slashPattern.ifParsableElseDefault(args,
                namedParameterMap -> bifunction.apply(
                        namedParameterMap,
                        io::indicateArgumentInvalid
                ).ifPresent(task -> {
                    taskList.addTask(task);
                    io.displayTaskAdded(taskList);
                })
        );

        commands.addCommand("todo",
                        args -> createTaskIfValidElseWarn.accept(args, Todo::createIfValidElse)
                )
                .addCommand("event",
                        args -> createTaskIfValidElseWarn.accept(args, Event::createIfValidElse)
                )
                .addCommand("deadline",
                        args -> createTaskIfValidElseWarn.accept(args, Deadline::createIfValidElse)
            );

        // User filtering for tasks

        commands.addCommand("find",
                args -> stringPattern.ifParsableElseDefault(args,
                        str -> io.displayTaskList(taskList.findInDescriptions(str)))
        );

    }

    //endregion

}

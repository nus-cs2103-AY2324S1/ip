package catbot;

import catbot.internal.CommandMap;
import catbot.internal.CommandPattern;
import catbot.internal.NamedParameterMap;
import catbot.io.CatbotConsoleIO;
import catbot.io.ErrorIndicatorIo;
import catbot.io.UserIo;
import catbot.task.Task;
import catbot.task.TaskList;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CatBot {

    private static final CommandMap commands = new CommandMap();
    static final UserIo io = new CatbotConsoleIO();
    private static final TaskList taskList = new TaskList("Tasks.txt");
    static {
        addSupportedCommandsToCommandMap();
    }

    private static void addSupportedCommandsToCommandMap() {
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
        BiConsumer<String, Consumer<Integer>> runIfValidIndexElseIndicateError =
                (args, lambda) -> integerPattern.ifParsableElseDefault(args,
                        integer -> taskList.ifValidIndexElse(integer,
                                lambda,
                                invalidIndex -> io.indicateInvalidIndex(invalidIndex, taskList.getIndexBounds())
                        ));

                //noinspection SpellCheckingInspection
        commands.addCommand("mark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.markTask(validIndex-1);
                                    io.displayTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("unmark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.unmarkTask(validIndex-1);
                                    io.displayTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("delete",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> io.displayTaskDeleted(taskList.removeTask(validIndex-1))
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
                        args -> createTaskIfValidElseWarn.accept(args, Task.Todo::createIfValidElse)
                )
                .addCommand("event",
                        args -> createTaskIfValidElseWarn.accept(args, Task.Event::createIfValidElse)
                )
                .addCommand("deadline",
                        args -> createTaskIfValidElseWarn.accept(args, Task.Deadline::createIfValidElse)
                )
        ;

        // User filtering for tasks

        commands.addCommand("find",
                args -> stringPattern.ifParsableElseDefault(args,
                        str -> io.displayTaskList(taskList.find(str)))
        );

    }

    public static void main(String[] args) {
        UserIo.CommandArgumentStruct commandArgumentStruct;
        io.initialize();
        do {
            commandArgumentStruct = io.getNextCommand();
            commands.run(commandArgumentStruct.getCommand(), commandArgumentStruct.getArgument());
        } while (!Objects.equals(commandArgumentStruct.getCommand(), "bye"));
    }
}

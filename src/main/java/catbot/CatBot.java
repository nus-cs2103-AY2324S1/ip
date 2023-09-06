package catbot;

import catbot.internal.CommandMap;
import catbot.internal.CommandPattern;
import catbot.internal.NamedParameterMap;
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
                .generate(io::indicateInvalidInteger);
        CommandPattern<NamedParameterMap> slashPattern = CatBotCommandPatterns.getSlashPatternGenerator()
                .generate(CatBotCommandPatterns.NO_DEFAULT);

        commands.setDefaultCommand(io::indicateInvalidCommand)
                .addCommand("bye", args -> io.cleanup())
                .addCommand("list", args -> io.printTaskList(taskList));

        // User modifying existing tasks (through IntegerPattern, and Index)
        BiConsumer<String, Consumer<Integer>> runIfValidIndexElseIndicateError =
                (args, lambda) -> integerPattern.ifParsableElseDefault(args,
                        integer -> taskList.ifValidIndexElse(integer,
                                lambda,
                                invalidIndex -> io.indicateInvalidIndex(invalidIndex, taskList.getIndexBounds())
                        ));

        commands.addCommand("mark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.markTask(validIndex-1);
                                    io.printTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("unmark",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> {
                                    taskList.unmarkTask(validIndex-1);
                                    io.printTaskModified(taskList, validIndex);
                                }
                        )
                )
                .addCommand("delete",
                        string -> runIfValidIndexElseIndicateError.accept(string,
                                validIndex -> io.printTaskDeleted(taskList.removeTask(validIndex-1))
                        )
                );

        // User creating new tasks (with SlashPattern)

        BiConsumer<String, BiFunction<NamedParameterMap, BiConsumer<ErrorIndicatorIo.InvalidState, NamedParameterMap>, Optional<Task>>>
                createTaskIfValidElseWarn = (args, bifunction) -> {
            slashPattern.ifParsableElseDefault(args,
                    namedParameterMap -> bifunction.apply(
                            namedParameterMap,
                            io::indicateArgumentInvalid
                    ).ifPresent(task -> {
                        taskList.addTask(task);
                        io.printTaskAdded(taskList);
                    })
            );
        };

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

    }

    public static void main(String[] args) {
        UserIo.CommandArgument commandArgument;
        io.initialize();
        do {
            commandArgument = io.getNextCommand();
            commands.run(commandArgument.getCommand(), commandArgument.getArgument());
        } while (!Objects.equals(commandArgument.getCommand(), "bye"));
    }
}

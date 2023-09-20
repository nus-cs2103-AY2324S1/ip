package catbot.task;

import java.util.Optional;
import java.util.function.BiConsumer;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

/**
 * The most basic task.
 */
public class Todo extends Task {
    private Todo(String desc) {
        setDescription(desc);
    }

    /**
     * Optionally creates a Deadline, if the given NamedParameterMap has valid arguments.
     *
     * @param map                 map of parameters and arguments to attempt to create a Deadline.
     * @param invalidStateHandler consumer to accept information about the error in case of argument invalidity.
     * @return an Optional Task if arguments are valid, otherwise an empty Optional.
     */
    public static Optional<Task> createIfValidElse(
            NamedParameterMap map,
            BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
    ) {

        Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        map,
                        ""
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey("", "description");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        return Optional.of(new Todo(
                map.get("")
        ));
    }

    @Override
    public void edit(NamedParameterMap map) {
        map.moveToNewKey("desc", "description");
        if (!map.containsKey("description")) {
            return;
        }
        this.setDescription(map.get("description"));
    }
}

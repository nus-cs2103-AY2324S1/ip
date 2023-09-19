package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.util.Optional;
import java.util.function.BiConsumer;

public class Todo extends Task {
    private Todo(String desc) {
        setDescription(desc);
    }

    public static Optional<Task> createIfValidElse(
            NamedParameterMap namedParameterMap,
            BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
    ) {

        Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        namedParameterMap,
                        ""
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey("", "description");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        return Optional.of(new Todo(
                namedParameterMap.get("")
        ));
    }
}
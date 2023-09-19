package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Deadline extends Task {

    private LocalDate dueDate;

    private Deadline(String desc, LocalDate dateTime) {
        setDescription(desc);
        setDueDate(dateTime);
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public static Optional<Task> createIfValidElse(
            NamedParameterMap map,
            BiConsumer<ErrorIndicatorIo.InvalidArgumentState, NamedParameterMap> invalidStateHandler
    ) {

        Optional<InvalidArgumentStruct> optionalInvalidParameterState =
                Task.invalidStateIfTaskParametersMissingOrBlank(
                        map,
                        "", "by"
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey("", "description");
            invalidArgumentStruct.parameters.moveToNewKey("by", "due date");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        String description = map.get("");
        NamedParameterMap invalidArgs = new NamedParameterMap();
        Optional<LocalDate> optionalDueDate = Task.parseOptionalDateElseMap(map, invalidArgs, "by");
        if (optionalDueDate.isPresent()) {
            return Optional.of(new Deadline(description, optionalDueDate.get()));
        } else {
            invalidArgs.moveToNewKey("by", "due date");
            invalidStateHandler.accept(ErrorIndicatorIo.InvalidArgumentState.NOT_A_DATE, invalidArgs);
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [due: " + Task.formatDate(this.dueDate) + "]";
    }
}
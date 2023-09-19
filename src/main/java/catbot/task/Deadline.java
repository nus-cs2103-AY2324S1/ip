package catbot.task;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Deadline extends Task {


    private static final String DESC_KEY = "";
    private static final String DUE_DATE_KEY = "by";

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
                        DESC_KEY, DUE_DATE_KEY
                );
        if (optionalInvalidParameterState.isPresent()) {
            InvalidArgumentStruct invalidArgumentStruct = optionalInvalidParameterState.get();
            invalidArgumentStruct.parameters.moveToNewKey(DESC_KEY, "description");
            invalidArgumentStruct.parameters.moveToNewKey(DUE_DATE_KEY, "due date");
            invalidStateHandler.accept(invalidArgumentStruct.state, invalidArgumentStruct.parameters);
            return Optional.empty();
        }

        String description = map.get(DESC_KEY);
        NamedParameterMap invalidArgs = new NamedParameterMap();
        Optional<LocalDate> optionalDueDate = Task.parseOptionalDateElseMap(map, invalidArgs, DUE_DATE_KEY);
        if (optionalDueDate.isPresent()) {
            return Optional.of(new Deadline(description, optionalDueDate.get()));
        } else {
            invalidArgs.moveToNewKey(DUE_DATE_KEY, "due date");
            invalidStateHandler.accept(ErrorIndicatorIo.InvalidArgumentState.NOT_A_DATE, invalidArgs);
            return Optional.empty();
        }
    }

    @Override
    public String toString() {
        return super.toString() + " [due: " + Task.formatDate(this.dueDate) + "]";
    }

    @Override
    public void edit(NamedParameterMap map) {
        map.moveToNewKey("desc", "description");
        if (map.containsKey("description")) {
            this.setDescription(map.get("description"));
        }
        if (map.containsKey(DUE_DATE_KEY)) {
            Optional<LocalDate> optDate = Task.parseOptionalDateElseMap(map, null, DUE_DATE_KEY);
            optDate.ifPresent(this::setDueDate);
        }
    }
}
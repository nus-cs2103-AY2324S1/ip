package catbot.task;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import catbot.internal.NamedParameterMap;
import catbot.io.ErrorIndicatorIo;

public abstract class Task implements Serializable {

    //region Fields

    private String description;
    private boolean isDone = false;

    //endregion

    //region Getter/setter

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }
    public void setUndone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "[" + (isDone() ? "X" : " ") + "] " + getDescription();
    }

    //endregion

    //region Internal Helpers

    private static Optional<NamedParameterMap> mapIfDescriptionEmpty(NamedParameterMap map) {
        String desc = map.get("");
        if (desc == null || desc.isEmpty() || desc.isBlank()) {
            NamedParameterMap newMap = new NamedParameterMap();
            newMap.addNamedParameter("description", "");
            return Optional.of(newMap);
        } else {
            return Optional.empty();
        }
    }
    private static Optional<NamedParameterMap> mapIfArgumentsMissing(
            NamedParameterMap map,
            String... arguments
    ) {
        NamedParameterMap newMap = new NamedParameterMap();
        boolean missing = false;
        for (String arg : arguments) {
            if (!map.containsKey(arg)) {
                missing = true;
                newMap.addNamedParameter(arg, "");
            }
        }
        if (missing) {
            return Optional.of(newMap);
        } else {
            return Optional.empty();
        }
    }

    protected static class InvalidArgumentStruct {
        protected final ErrorIndicatorIo.InvalidArgumentState state;
        protected final NamedParameterMap parameters;

        private InvalidArgumentStruct(ErrorIndicatorIo.InvalidArgumentState state, NamedParameterMap map) {
            this.state = state;
            this.parameters = map;
        }
    }

    protected static Optional<InvalidArgumentStruct> invalidStateIfTaskParametersMissingOrBlank(
            NamedParameterMap namedParameterMap, String... arguments
    ) {
        // parameters cannot be missing
        Optional<NamedParameterMap> optionalNamedParameterMap =
                Task.mapIfArgumentsMissing(namedParameterMap, arguments);
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidArgumentStruct(
                    ErrorIndicatorIo.InvalidArgumentState.PARAMETER_MISSING,
                    optionalNamedParameterMap.get())
            );
        }

        // description cannot be empty
        optionalNamedParameterMap = Task.mapIfDescriptionEmpty(namedParameterMap);
        //noinspection OptionalIsPresent for readability, to distinguish default return value
        if (optionalNamedParameterMap.isPresent()) {
            return Optional.of(new InvalidArgumentStruct(
                    ErrorIndicatorIo.InvalidArgumentState.PARAMETER_EMPTY,
                    optionalNamedParameterMap.get())
            );
        }

        // if all ok
        return Optional.empty();
    }

    protected static Optional<LocalDate> parseOptionalDateElseMap(
            NamedParameterMap map, NamedParameterMap elseMap, String arg
    ) {
        String val = map.get(arg);
        try {
            return Optional.of(LocalDate.parse(val));
        } catch (DateTimeParseException ignored) {
            elseMap.addNamedParameter(arg, val);
            return Optional.empty();
        }
    }

    protected static String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(
                        date.getYear() == Year.now().getValue()
                                ? "MMM d"
                                : "MMM d yyyy"
                )
        );
    }

    //endregion

}

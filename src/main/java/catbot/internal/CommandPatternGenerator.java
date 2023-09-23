package catbot.internal;

import java.util.function.Consumer;

/**
 * Object that is used to generate a CommandPattern.
 * Meant to allow a lambda to be passed to the pattern on creation, while allowing it to be a functional interface.
 *
 * @param <T> return type of the generated CommandPattern.
 * @see CommandPattern type of object generated.
 */
public interface CommandPatternGenerator<T> {

    /**
     * Returns a CommandPattern created with the provided Consumer as a fallthrough option.
     * The default consumer will be run if the pattern cannot be applied to the provided string.
     *
     * @param invalidInput the default Consumer that accepts the provided command, if the pattern cannot be applied.
     * @return CommandPattern generated.
     * @see CommandPattern
     */
    CommandPattern<T> generateUsingDefault(Consumer<String> invalidInput);
}

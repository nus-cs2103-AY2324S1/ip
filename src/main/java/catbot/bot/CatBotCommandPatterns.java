package catbot.bot;

import java.util.function.Consumer;

import catbot.internal.CommandPattern;
import catbot.internal.CommandPatternGenerator;
import catbot.internal.NamedParameterMap;
import catbot.internal.Parser;

/**
 * Class containing {@link CommandPattern CommandPatterns} and {@link CommandPatternGenerator CommandPatternGenerators}
 * used by the CatBot Assistant.
 */
public abstract class CatBotCommandPatterns {

    //region Fields

    /**
     * A consumer that throws an error when called.
     * Intended to assert that a CommandPattern does not result in fallthrough behaviour;
     * ie all possible inputs are handled.
     */
    public static final Consumer<String> NO_DEFAULT = s -> {
        assert false; //SHOULD NOT BE CALLED
    };
    private static final IntegerPatternGenerator integerPatternGenerator = new IntegerPatternGenerator();
    private static final SlashArgumentPatternGenerator slashPatternGenerator = new SlashArgumentPatternGenerator();
    private static final StringPatternGenerator stringPatternGenerator = new StringPatternGenerator();

    //endregion

    //region Integer Pattern

    /**
     * Gets a singleton instance of {@link IntegerPatternGenerator}.
     * @return the generator.
     */
    public static CommandPatternGenerator<Integer> getIntegerPatternGenerator() {
        return integerPatternGenerator;
    }
    private static class IntegerPatternGenerator implements CommandPatternGenerator<Integer> {

        @Override
        public CommandPattern<Integer> generateUsingDefault(Consumer<String> invalidInput) {
            return (args, consumer) -> {
                try {
                    consumer.accept(Integer.parseInt(args));
                } catch (NumberFormatException nfe) {
                    invalidInput.accept(args);
                }
            };
        }
    }
    //endregion

    //region Slash Arguments Pattern

    /**
     * Gets a singleton instance of {@link SlashArgumentPatternGenerator}.
     * @return the generator.
     */
    public static CommandPatternGenerator<NamedParameterMap> getSlashPatternGenerator() {
        return slashPatternGenerator;
    }

    private static class SlashArgumentPatternGenerator implements CommandPatternGenerator<NamedParameterMap> {

        @Override
        public CommandPattern<NamedParameterMap> generateUsingDefault(Consumer<String> ignored) {
            return new CommandPattern<>() {

                private final Parser slashParser = Parser.with("/", true);

                @Override
                public void ifParsableElseDefault(String args, Consumer<NamedParameterMap> consumer) {
                    consumer.accept(slashParser.parse(args));
                }
            };
        }
    }

    //endregion

    //region String Pattern

    public static CommandPatternGenerator<String> getStringPatternGenerator() {
        return stringPatternGenerator;
    }

    private static class StringPatternGenerator implements CommandPatternGenerator<String> {

        @Override
        public CommandPattern<String> generateUsingDefault(Consumer<String> ignored) {
            return (args, consumer) -> consumer.accept(args);
        }
    }

    //endregion
}

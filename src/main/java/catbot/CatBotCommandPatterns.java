package catbot;

import catbot.internal.CommandPattern;
import catbot.internal.CommandPatternGenerator;
import catbot.internal.NamedParameterMap;

import java.util.function.Consumer;

public class CatBotCommandPatterns {

    public static final Consumer<String> NO_DEFAULT = new Consumer<String>() {
        @Override
        public void accept(String s) {
            assert false; //SHOULD NOT BE CALLED
        }
    };

    //region Integer Pattern
    public static CommandPatternGenerator<Integer> getIntegerPatternGenerator() {
        return integerPatternGenerator;
    }
    private static final IntegerPatternGenerator integerPatternGenerator = new IntegerPatternGenerator();
    private static class IntegerPatternGenerator implements CommandPatternGenerator<Integer> {

        @Override
        public CommandPattern<Integer> generateUsingDefault(Consumer<String> invalidInput) {
            return new CommandPattern<>() {
                @Override
                public void ifParsableElseDefault(String args, Consumer<Integer> consumer) {
                    try {
                        consumer.accept(Integer.parseInt(args));
                    } catch (NumberFormatException nfe) {
                        invalidInput.accept(args);
                    }
                }
            };
        }
    }
    //endregion

    //region Slash Arguments Pattern
    private static final SlashArgumentPatternGenerator slashPatternGenerator = new SlashArgumentPatternGenerator();

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
    private static final StringPatternGenerator stringPatternGenerator = new StringPatternGenerator();

    public static CommandPatternGenerator<String> getStringPatternGenerator() {
        return stringPatternGenerator;
    }

    private static class StringPatternGenerator implements CommandPatternGenerator<String> {

        @Override
        public CommandPattern<String> generateUsingDefault(Consumer<String> ignored) {
            return new CommandPattern<>() {
                @Override
                public void ifParsableElseDefault(String args, Consumer<String> consumer) {
                    consumer.accept(args);
                }
            };
        }
    }
    //endregion
}

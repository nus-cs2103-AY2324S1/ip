package catbot;


import catbot.bot.CatBotCommandPatterns;
import catbot.internal.CommandPattern;
import catbot.internal.CommandPatternGenerator;
import catbot.internal.NamedParameterMap;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CatBotCommandPatternsTest {

    @Nested
    class IntegerPatternTest {

        @Test
        public void integerPatternTest() {
            CommandPatternGenerator<Integer> generator = CatBotCommandPatterns.getIntegerPatternGenerator();
            LambdaOutput<String> defaultOutput = new LambdaOutput<>();
            Consumer<String> defaultConsumer = defaultOutput::setOutput;
            CommandPattern<Integer> integerPattern = generator.generateUsingDefault(defaultConsumer);

            LambdaOutput<Integer> output = new LambdaOutput<>();
            integerPattern.ifParsableElseDefault("5", output::setOutput);
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), 5);

            integerPattern.ifParsableElseDefault("100", output::setOutput);
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), 100);

            output.setOutput(0);
            integerPattern.ifParsableElseDefault("not integer", output::setOutput);
            assertEquals(defaultOutput.getOutput(), "not integer");
            assertEquals(output.getOutput(), 0);
        }

    }

    @Nested
    class SlashPatternTest {

        @Test
        public void slashPatternTest() {
            CommandPatternGenerator<NamedParameterMap> generator = CatBotCommandPatterns.getSlashPatternGenerator();
            LambdaOutput<String> defaultOutput = new LambdaOutput<>();
            Consumer<String> defaultConsumer = defaultOutput::setOutput;
            CommandPattern<NamedParameterMap> slashPattern = generator.generateUsingDefault(defaultConsumer);

            LambdaOutput<NamedParameterMap> output = new LambdaOutput<>();

            slashPattern.ifParsableElseDefault("placeholder text", output::setOutput);
            NamedParameterMap expectedOutput = new NamedParameterMap();
            expectedOutput.addNamedParameter("", "placeholder text");
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), expectedOutput);

            slashPattern.ifParsableElseDefault("slash delimited /text test with /123 multiple slash",
                    output::setOutput);
            expectedOutput = new NamedParameterMap()
                    .addNamedParameter("", "slash delimited")
                    .addNamedParameter("text", "test with")
                    .addNamedParameter("123", "multiple slash");
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), expectedOutput);

            slashPattern.ifParsableElseDefault("overriding / slash /slash commands /slash test", output::setOutput);
            expectedOutput = new NamedParameterMap()
                    .addNamedParameter("", "slash")
                    .addNamedParameter("slash", "test");
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), expectedOutput);

            slashPattern.ifParsableElseDefault("whitespace  /arg  te  st/text  ", output::setOutput);
            expectedOutput = new NamedParameterMap()
                    .addNamedParameter("", "whitespace")
                    .addNamedParameter("arg", "te  st")
                    .addNamedParameter("text", "");
            assertNull(defaultOutput.getOutput());
            assertEquals(output.getOutput(), expectedOutput);
        }

    }
}

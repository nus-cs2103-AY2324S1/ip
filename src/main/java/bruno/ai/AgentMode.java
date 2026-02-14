package bruno.ai;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.request.ChatRequest;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.List;

import bruno.Parser;
import bruno.TaskList;
import bruno.exceptions.BrunoException;

public class AgentMode {

    private final ChatModel model;
    private final Parser parser;
    private final TaskList taskList;

    public AgentMode(Parser parser, TaskList taskList, String apiKey) {
        this.parser = parser;
        this.taskList = taskList;
        this.model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .build();
    }

    public String executeFromNaturalLanguage(String naturalLanguageCommand) throws BrunoException {
        String systemPrompt = buildSystemPrompt();
        String userPrompt = buildUserPrompt(naturalLanguageCommand);

        ChatRequest req = ChatRequest.builder()
                .messages(List.of(
                        SystemMessage.from(systemPrompt),
                        UserMessage.from(userPrompt)
                ))
                .build();

        ChatResponse res = model.chat(req);
        String aiResponse = res.aiMessage().text();

        String command = parseAIResponse(aiResponse);

        return executeCommand(command);
    }

    private String buildSystemPrompt() {
        return """
                TASK: Convert natural language requests into EXACT task management commands.
                
                CRITICAL RULES:
                1. Return ONLY the command - NO explanation, NO extra text, NO periods
                2. Return the command on ONE line
                3. Do not add punctuation or periods at the end
                4. Match the exact format shown in examples
                
                Available commands (exact format required):
                - TODO <description>: Create a simple to-do task
                - DEADLINE <description> /by <date>: Create a deadline task (date format: YYYY-MM-DD or MMM dd yyyy)
                - EVENT <description> /from <date> /to <date>: Create an event
                - MARK <task_number>: Mark a task as done
                - UNMARK <task_number>: Mark a task as not done
                - DELETE <task_number>: Delete a task
                - LIST: Show all tasks
                - FIND <keyword>: Search for tasks
                - SCHEDULE <date>: Show tasks for a specific date
                - NOTE <task_number> /n <note>: Add a note to a task
                
                IMPORTANT EXAMPLES - Return EXACTLY like this:
                
                Input: "Add a task to buy groceries"
                Output: TODO buy groceries
                
                Input: "Add a new task to buy groceries"
                Output: TODO buy groceries
                
                Input: "Create a deadline to submit project by next Friday"
                Output: DEADLINE submit project /by 2026-02-06
                
                Input: "Show me all my tasks"
                Output: LIST
                
                Input: "Mark task 3 as done"
                Output: MARK 3
                
                Input: "Find homework tasks"
                Output: FIND homework
                
                DO NOT output:
                ❌ "Add a new task to buy groceries."
                ❌ "Here's the command: TODO buy groceries"
                ❌ "TODO buy groceries. This will..."
                ❌ Any explanation or period at the end
                
                RETURN ONLY THE COMMAND, NOTHING ELSE.
                """;
    }

    private String buildUserPrompt(String naturalLanguageCommand) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Current tasks:\n");
        prompt.append(taskList.displayListForAgent());
        prompt.append("\n\nUser request: ").append(naturalLanguageCommand);
        return prompt.toString();
    }

    private String parseAIResponse(String aiResponse) {
        String cleaned = aiResponse.trim();
        
        if (cleaned.startsWith("```")) {
            cleaned = cleaned.replaceAll("```[a-z]*\\n", "").replaceAll("```", "").trim();
        }
        
        cleaned = cleaned.replaceAll("(?i)^(output|command|response|result):\\s*", "").trim();
        
        String[] lines = cleaned.split("\n");
        String firstLine = lines[0].trim();
        
        if (firstLine.endsWith(".") && !firstLine.matches("^[A-Z]+\\s+.*")) {
            firstLine = firstLine.replaceAll("\\.$", "").trim();
        }
        
        if (firstLine.length() > 0 && !isValidCommandStart(firstLine)) {
            String[] words = cleaned.split("\\s+");
            for (String word : words) {
                if (isCommandKeyword(word.toUpperCase())) {
                    int idx = cleaned.indexOf(word);
                    String possibleCommand = cleaned.substring(idx).split("\n")[0].trim();
                    if (possibleCommand.endsWith(".")) {
                        possibleCommand = possibleCommand.replaceAll("\\.$", "").trim();
                    }
                    return possibleCommand;
                }
            }
        }
        
        return firstLine;
    }
    
    private boolean isValidCommandStart(String str) {
        String upper = str.toUpperCase();
        return upper.startsWith("TODO") || 
               upper.startsWith("DEADLINE") ||
               upper.startsWith("EVENT") ||
               upper.startsWith("MARK") ||
               upper.startsWith("UNMARK") ||
               upper.startsWith("DELETE") ||
               upper.startsWith("LIST") ||
               upper.startsWith("FIND") ||
               upper.startsWith("SCHEDULE") ||
               upper.startsWith("NOTE");
    }
    
    private boolean isCommandKeyword(String word) {
        return word.equals("TODO") || 
               word.equals("DEADLINE") ||
               word.equals("EVENT") ||
               word.equals("MARK") ||
               word.equals("UNMARK") ||
               word.equals("DELETE") ||
               word.equals("LIST") ||
               word.equals("FIND") ||
               word.equals("SCHEDULE") ||
               word.equals("NOTE");
    }

    private String executeCommand(String command) throws BrunoException {
        try {
            return parser.parseInput(command);
        } catch (Exception e) {
            throw new BrunoException("Failed to execute command: " + command + "\nError: " + e.getMessage());
        }
    }
}

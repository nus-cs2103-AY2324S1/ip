# Duke project
## Hello from
```
      ██╗██╗███╗   ██╗ ██████╗     ███████╗██╗  ██╗███████╗███╗   ██╗ ██████╗ 
      ██║██║████╗  ██║██╔════╝     ██╔════╝██║  ██║██╔════╝████╗  ██║██╔════╝ 
      ██║██║██╔██╗ ██║██║  ███╗    ███████╗███████║█████╗  ██╔██╗ ██║██║  ███╗
 ██   ██║██║██║╚██╗██║██║   ██║    ╚════██║██╔══██║██╔══╝  ██║╚██╗██║██║   ██║
 ╚█████╔╝██║██║ ╚████║╚██████╔╝    ███████║██║  ██║███████╗██║ ╚████║╚██████╔╝
  ╚════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝     ╚══════╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝
```
## Project Description
This is my project, created from a template for a greenfield Java project.
It's named after the Java mascot _Duke_.
This project is a Java application that aims to provide a user-friendly task management system. It allows users to manage their tasks efficiently and keep track of their schedules.

## Features

- **Task Creation**: Users can add various types of tasks, including todo, deadline, and event tasks.
- **Task List**: View a list of all tasks, including their descriptions and completion status.
- **Task Completion**: Mark tasks as complete when they are done.
- **Task Deletion**: Remove tasks that are no longer needed.
- **Task Searching**: Search for tasks based on keywords.
- **Data Storage**: Data is automatically saved and loaded for seamless usage.

## Installation Option 1

1. Go to tjingsheng ip [releases](https://github.com/tjingsheng/ip/releases)
2. **Get Java**: Ensure you have a functioning version of Java.
3. **Copy the JAR File**: Copy the JAR file you want to run into an empty folder.
4. **Open a Command Window**: Open a command prompt (Windows) or terminal (macOS/Linux) in the same folder where you copied the JAR file.
5. **Run the Command**: Use the following command to run the JAR file:
6. **Enjoy**: Duke is very fun.

   ```shell
   java -jar duke.jar

## Installation Option 2

1. Clone the tjingsheng ip [repository](https://github.com/tjingshengg/ip) to your local machine.
2. Open the project in your preferred Java development environment.
3. Build and run the application.
4. Enjoy

## Todo List

- [x] Task 1 : Get duke
- [ ] Task 2 : Experiment with it
- [ ] Task 3 : Profit ???
- [ ] Task 4 : ~~Cry~~

## Project Sample
```
    /**
     * Runs the Duke application, allowing the user to interact with it via the command line.
     */
    public static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        Ui.helloWorld();
        boolean isConversing = true;
        while (isConversing) {
            String rawCommand = Ui.getUserInput(scanner);
            TaskList taskList = TaskFileHandler.readFromFile();
            Ui.getBotMessage();
            Command command = Parser.parse(rawCommand);
            command.execute(taskList);
            TaskFileHandler.saveToFile(taskList);
            isConversing = !command.isByeCommand();
        }
    }
```

>>>>> "Duke hehe Duke hehe" - Jing Sheng
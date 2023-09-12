# Simon Chatbot

**Simon** is an innovative chatbot designed to assist users in task management and provide an intuitive user interface. It's not just a chatbot, but a tool that understands the value of your time.

> "Simplicity is the soul of efficiency." - [Austin Freeman](https://www.pinterest.com/pin/simplicity-is-the-soul-of-efficiency-austin-freeman-motivational-quote-of-the-day-august-15-2019-ave-mateiu--692850723914823746/#:~:text=Simplicity%20is%20the%20soul%20of,Austin%20Freeman%20Do%20you%20agree%3F)

## Features

- **Interactive UI**: Provides a clear and responsive chat-like interface.
- **Task Management**: Keep track of your to-dos, deadlines, and events.
- **Persistent Storage**: Tasks are saved in a file for future reference.
- **Dark Mode**: A pleasing and eye-friendly dark mode to work at any time of the day.

## Getting Started

1. **Installation**
   - Clone the repository.
   - Compile and run the `Simon` class.

2. **Usage**
   - Type your tasks or commands in the text field.
   - Click on the 'Send' button or press 'Enter' to submit.

3. [Download Simon](https://github.com/LimJH2002/ip/releases) from our official repository.
4. Double-click the downloaded file to run.
5. Add your tasks using simple commands.
6. Let `Simon` manage everything for you! :smile:

## Commands

- `list`: Lists all tasks.
- `todo <task>`: Adds a new to-do.
- `deadline <task> /by <date>`: Adds a new deadline.
- `event <task> /at <date>`: Adds a new event.
- `mark <task_number>`: Marks a task as done.
- `unmark <task_number>`: Marks a task as not done.
- `delete <task_number>`: Deletes a task.
- `find <keyword>`: Finds tasks that match the keyword.
- `bye`: Exits the chatbot.

## User Interface

The chatbot features a modern design with a chat interface. Users can see Simon's responses as well as their messages. The chatbot also supports a sleek dark mode.

## For Java Programmers

If you're a Java programmer looking for a project to work on or understand, Simon is perfect for you. Here's the main method to get you started:

```java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
# BauBauBot

BauBauBot is a Java-based chatbot that helps you keep track of your to-dos, deadlines, and events. With BauBauBot, you can easily manage your tasks and stay organized. 🤖

## Getting Started

To use BauBauBot, follow these simple steps:

1. **Download the JAR File**: You can download the JAR file for BauBauBot from the latest releases on our [GitHub page](https://github.com/et-irl/ip/releases/tag/A-Jar).

2. **Run the JAR File**: After downloading the JAR file, open your command terminal and navigate to the directory where the JAR file is located. Use the following command to run BauBauBot:

   ```
   java -jar taskbot.jar
   ```

   This will launch BauBauBot, and you can start interacting with it right away. 🚀

## Commands

BauBauBot understands a variety of commands to help you manage your tasks. Here are some of the key commands:

- 📅 **Add a Deadline**: You can add a deadline task using the following command or its alias:
  
  ```
  deadline [NAME] /by [TIME]
  ```

  Alias: `d` 

- 📆 **Add an Event**: To add an event task, use the following command or its alias:
  
  ```
  event [NAME] /from [START TIME] /to [END TIME]
  ```

  Alias: `e`

- ✅ **Add a To-Do**: Add a simple to-do task with this command or its alias:
  
  ```
  todo [NAME]
  ```

  Alias: `t`

- 🗑️ **Delete a Task**: Remove a task from your list using this command or its alias:
  
  ```
  delete [TASK NUMBER]
  ```

  Alias: `rm`

- 🔍 **Find Tasks**: To search for tasks that match a partial search term in their names, use this command or its alias:
  
  ```
  find [PARTIAL SEARCH TERM]
  ```

  Alias: `f`

- 📋 **List Tasks**: View all your tasks with this command or its alias:
  
  ```
  list
  ```

  Alias: `ls`

- ✅ **Mark Task as Done**: Mark a task as completed with this command or its alias:
  
  ```
  mark [TASK NUMBER]
  ```

  Alias: `m`

- ❌ **Mark Task as Undone**: Reverse a completed task to its undone status with this command or its alias:
  
  ```
  unmark [TASK NUMBER]
  ```

  Alias: `um`

## Development

If you're interested in contributing to BauBauBot or exploring its code, here's how you can get started:

- **Clone the Repository**: You can clone the BauBauBot repository from GitHub using the following command:

  ```
  git clone https://github.com/et-irl/ip.git
  ```

- **Install Java 11**: Ensure that you have Java 11 or higher installed on your system. You can download Java 11 for Windows or Linux from the official Oracle website or use your preferred package manager. ☕

- **Build and Run**: Use the following Gradle commands to build, run, clean, and test the project:

  - To build the project:

    ```
    ./gradlew build
    ```

  - To run the project:

    ```
    ./gradlew run
    ```

  - To clean the project:

    ```
    ./gradlew clean
    ```

  - To run tests:

    ```
    ./gradlew test
    ```

We welcome pull requests and issue reports from the community. Feel free to contribute and help improve BauBauBot. 🙌

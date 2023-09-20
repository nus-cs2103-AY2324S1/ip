# RemindMe User Guide

## Introduction

Welcome to RemindMe, your friendly task management chatbot! RemindMe is designed to help you organize your tasks efficiently. Whether it's creating to-dos, tracking deadlines, or managing events, RemindMe has you covered. This user guide will provide you with all the information you need to make the most of this chatbot.

## Table of Contents

- [Getting Started](#getting-started)
- [Basic Commands](#basic-commands)
- [Advanced Features](#advanced-features)
  - [Finding Tasks](#finding-tasks)
- [Saving Your Data](#saving-your-data)
- [Running via JAR File](#running-via-jar-file)
- [Conclusion](#conclusion)

## Getting Started

Before we dive into the details, here's how you can start using RemindMe:

1. **Run the Chatbot**: To use RemindMe, you can run it in two ways:
   
   a. **Running from Source Code**: If you have the source code, execute the main class `Duke.java` to start the chatbot.

   b. **Running from JAR File**: If you have the JAR file, follow the instructions in the [Running via JAR File](#running-via-jar-file) section.

2. **Interact with RemindMe**: Once the chatbot is running, you can start interacting with it through your console or terminal.

3. **Enter Commands**: Simply type in your commands to create, manage, and track tasks. RemindMe will respond to your inputs accordingly.

## Basic Commands

### 1. Adding Tasks

- **To-Do**: Create a to-do task by entering `todo [task description]`.

- **Deadline**: Set a task with a deadline using `deadline [task description] /by [date and time]`.

- **Event**: Plan an event task with `event [task description] /from [start date and time] /to [end date and time]`.

### 2. Listing Tasks

- **List**: See all your tasks with `list`.

### 3. Managing Tasks

- **Mark as Done**: Mark a task as done by typing `mark [task number]`.

- **Unmark**: Undo a task's completion with `unmark [task number]`.

- **Delete**: Remove a task from your list with `delete [task number]`.

### 4. Exiting

- **Exit**: To exit the chatbot, type `bye`. Don't worry; your tasks will be saved for your next session!

## Advanced Features

### Finding Tasks

You can search for specific tasks using the `find [keyword]` command. RemindMe will display all tasks that contain the provided keyword.

Example:
```
find important
```

This will list all tasks with "important" in their descriptions.

## Saving Your Data

RemindMe automatically saves your tasks to a file (`duke.txt`) when you exit the chatbot. When you relaunch the chatbot, your tasks will be loaded and ready for you to manage.

## Running via JAR File

If you have the RemindMe JAR file, follow these steps to run the chatbot:

1. **Open Terminal (or Command Prompt)**: Launch your terminal or command prompt.

2. **Navigate to the JAR File Directory**: Use the `cd` command to navigate to the directory where the RemindMe JAR file is located (`build/libs`).

3. **Run the JAR File**: Enter the following command to run the chatbot:

   ```shell
   java -jar RemindMe.jar
   ```

4. **Interact with RemindMe**: Once the chatbot starts, follow the same interaction steps as described in [Getting Started](#getting-started).

## Conclusion

RemindMe is here to simplify your task management. Whether it's daily to-dos, important deadlines, or exciting events, RemindMe ensures you stay organized. Enjoy using RemindMe and stay on top of your tasks!
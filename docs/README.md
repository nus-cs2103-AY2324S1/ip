# Jarvis Chatbot User Guide

## Table of Contents

1. [Getting Started](#getting-started)
2. [Features](#features)
3. [Commands](#commands)
4. [Contributing and Feedback](#contributing-and-feedback)
5. [License](#license)

## Getting Started

1. **Installation**: Download and set up Jarvis on your system.
2. **Initiate Jarvis**: Navigate to the Jarvis directory and run the start command.
3. **Chat away!**: Enter your commands to interact with Jarvis.

**Sample Interface**

![screenshot of the product](https://github.com/StevenLiudw/ip/blob/master/docs/Ui.png)

## Features

- Quick task management: Add, delete, and mark tasks as done.
- Multiple task types: Handle to-dos, events, and deadlines.
- Search functionality: Easily find tasks with keywords.
- Easy tagging: Prioritize and categorize with tags.
- Intuitive interface: Minimalist design for maximum productivity.

## Commands

### General

- `bye`: Exits Jarvis.  
  **Usage**: `bye`
- `list`: Displays all your tasks.  
  **Usage**: `list`

### Task Management

- `todo TASK_DESCRIPTION`: Adds a todo task.  
  **Usage**: `todo Read book`
- `event TASK_DESCRIPTION /from START_TIME /to END_TIME`: Adds an event.  
  **Usage**: `event Team meeting /from 2pm /to 4pm`
- `deadline TASK_DESCRIPTION /by DEADLINE_TIME`: Adds a deadline.  
  **Usage**: `deadline Submit report /by 5pm`
- `delete INDEX`: Deletes the task at the specified index.  
  **Usage**: `delete 2`
- `mark INDEX`: Marks the task at the specified index as done.  
  **Usage**: `mark 2`
- `unmark INDEX`: Unmarks the task at the specified index.  
  **Usage**: `unmark 2`

### Search and Tagging

- `find KEYWORD`: Finds tasks with the specified keyword.  
  **Usage**: `find book`
- `tag INDEX TAG_NAME`: Tags a task with a tag name.  
  **Usage**: `tag 3 urgent`

## Contributing and Feedback

If you'd like to contribute to Jarvis or have some feedback, please [open a new issue](https://github.com/StevenLiudw/ip/issues)on our GitHub page.


## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
          _      _ _               _   
     (_) ___| (_)_ __ ___   __| |  
     | |/ _ \\ | | '_ ` _ \\ / _` |  
     | |  __/ | | | | | | | (_| |  
    _/ |\\___|_|_|_| |_| |_|\\__,_|  
   |__/                            
   ```

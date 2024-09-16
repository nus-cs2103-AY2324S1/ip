# User Guide

Welcome to **A.R.O.N.A**, your personal chatbot assistant designed to simplify your life and help with **task tracking**. This user guide provides an overview of A.R.O.N.A's features and instructions on how to use them.

## 1. Getting Started

### 1.1 System Requirements

Before you begin using A.R.O.N.A, ensure that your computer meets the following system requirements:

- You have **Java 11** or above installed on your computer.

### 1.2 Installation

To install A.R.O.N.A, follow these steps:

- [Installation Link](https://github.com/kanna-1/ip/releases)

### 1.3 Launching A.R.O.N.A

To run A.R.O.N.A, open your terminal and navigate to the directory where the downloaded jar file is and enter the following command.

```sh
java -jar arona.jar
```

or right-click the executable file.

## 2. Features

A.R.O.N.A offers a range of features to assist you in various tasks. Here are some of the key features:


>**Notes about the command format:**
>
>1. Words in UPPER_CASE are the parameters to be supplied by the user.
>2. Commands are case-insensitive, and all tasks stored will be lowercased as well.

### 2.1 Adding a task: `todo, deadline, event`

Adds the corresponding tasks to the task list.

**Usage:**
- `todo TASK`
- `deadline TASK /by DEADLINE (in YYYY-MM-DD)`
- `event TASK /from FROM /to TO`


### 2.2 Managing a task: `mark, unmark`
Marks/unmarks a task at the specified index.

**Usage:** `unmark/mark INDEX`

- The index refers to the index number shown in the displayed task list.
- The index must be a **positive integer**.

### 2.3 Deleting a task: `delete`
Deletes a task at the specified index.

**Usage:** `delete INDEX`

- The index refers to the index number shown in the displayed task list.
- The index must be a **positive integer**.

### 2.4 Listing all tasks: `list`
Shows a list of all tasks.

**Usage:** `list`

### 2.5 Undoing a command: `undo`
Undoes the last command (if it is undoable).

**Usage:** `undo`

- Commands that can be undone are: `todo`, `deadline`, `event`, `mark`, `unmark` and `delete`.
- Commands that cannot be undone are: `list`, `undo`, `bye` and `find`.

### 2.6 Finding a task: `find`

Shows all tasks that match the TASK keyword.

**Usage:** `find TASK`

- Only full words will be matched.
- Only one keyword can be used.
- Only the task name is searched.

### 2.7 Exiting the program: `bye`

Exits the program.

**Usage:** `bye`

### 2.8 Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 2.9 Editing the data file

Tasks data are saved automatically as a txt file at `[JAR file location]/data/arona.txt`. Advanced users are welcome to update data directly by editing that data file.

## 3. Known Issues

1. Enlarging the window may cause UI problems.
2. Resetting the application will also reset the history of commands, potentially affecting the `undo` command.

## 4. Command Summary

Here's a summary of available actions:

| Action      | Format                         | Examples                                             |
|-------------|--------------------------------|------------------------------------------------------|
| Add         | `todo TASK`                    | `todo buy groceries`                                 |
|             | `deadline TASK /by DEADLINE`   | `deadline submit report /by 2023-12-31`              |
|             | `event TASK /from FROM /to TO` | `event team meeting /from 2023-10-15 /to 2023-10-16` |
| Mark/Unmark | `unmark/mark INDEX`            | `mark 1`                                             |
| Delete      | `delete INDEX`                 | `delete 3`                                           |
| List        | `list`                         |                                                      |
| Undo        | `undo`                         |                                                      |
| Find        | `find TASK`                    | `find meeting`                                       |
| Bye         | `bye`                          |                                                      |


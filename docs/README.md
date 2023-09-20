# User Guide

Welcome to **A.R.O.N.A**, your personal chatbot assistant designed to simplify your life and keep you organized. This user guide provides an overview of A.R.O.N.A's features and instructions on how to use them.

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

**Notes:**

1. Words in UPPER_CASE are the parameters to be supplied by the user.
2. Commands are case-insensitive, and all tasks stored will be lowercased as well.

### 2.1 Adding a task: `todo, deadline, event`

**Usage:**
- `todo TASK`
- `deadline TASK /by DEADLINE (in YYYY-MM-DD)`
- `event TASK /from FROM /to TO`

Adds the corresponding tasks.

### 2.2 Managing a task: `mark, unmark`

**Usage:**
- `unmark/mark INDEX`

Unmark/mark a task at the specified index.

### 2.3 Deleting a task: `delete`

**Usage:**
- `delete INDEX`

Deletes a task at the specified index.

### 2.4 Listing all tasks: `list`

**Usage:**
- `list`

List all tasks.

### 2.5 Undoing a command: `undo`

**Usage:**
- `undo`

Undo the last command (if it is undoable).

### 2.6 Finding a task: `find`

**Usage:**
- `find TASK`

Show all tasks that match the TASK keyword.

### 2.7 Saving the data

Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 2.8 Editing the data file

Tasks data are saved automatically as a txt file at `[JAR file location]/data/arona.txt`. Advanced users are welcome to update data directly by editing that data file.

## Known Issues

1. Enlarging the window may cause UI problems.
2. Resetting the application will also reset the history of commands, potentially affecting the `undo` command.

## Command Summary

Here's a summary of available actions:

| Action  | Format                       | Examples                                             |
| ------- |------------------------------|------------------------------------------------------|
| Add     | `todo TASK`                  | `todo buy groceries`                                 |
|         | `deadline TASK /by DEADLINE` | `deadline submit report /by 2023-12-31`              |
|         | `event TASK /from FROM /to TO` | `event team meeting /from 2023-10-15 /to 2023-10-16` |
| Mark/Unmark | `unmark/mark INDEX`          | `mark 1`                                             |
| Delete  | `delete INDEX`               | `delete 3`                                           |
| List    | `list`                       |                                                      |
| Undo    | `undo`                       |                                                      |
| Find    | `find TASK`                  | `find meeting`                                       |
| Bye     | `bye`                        |                                                      |


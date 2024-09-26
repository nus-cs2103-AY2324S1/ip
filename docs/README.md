# User Guide

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)

## Getting Started

1. Ensure that you are using java 11.
2. Download the jar file from the version on github.
3. Navigate to the directory containing the jar file.
4. Run java -jar duke.jar to begin.
5. A chatbot should appear.

## Features

### Adding a task: `todo`, `event`, `deadline`

Add a new task of the choosen type into the task list.

Format:

- `todo [DESCRIPTION]`
- `event [DESCRIPTION] /from [yyyy-MM-dd] /to [yyyy-MM-dd]`
- `deadline [DESCRIPTION] /by [yyyy-MM-dd]`

Example of usage:

- `todo watch lecture videos`
- `event hackathon /from 2023-09-29 /to 2023-10-02`
- `deadline 2100 assignment /by 2023-10-23`

### Deleting a task: `delete`

Delete the task from the task list.

Format:

`delete [TASKNUMBER]`

Example of usage:

`delete 1`

### Listing all the tasks: `list`

List down all the tasks.

Format:

`list`

### Getting help: `help`

List down all the commands.

Format:

`help`

### Find specific tasks: `find`

Find all tasks that contains the keyword.

Format:

`find [KEYWORD]`

Example of usage:

`find do`

### Marking each task: `mark`

Mark the task as done with an X.

Format:

`mark [TASKNUMBER]`

Example of usage:

`mark 1`

### Unmarking marked task: `unmark`

Remove the X in marked task.

Format:

`unmark [TASKNUMBER]`

Example of usage:

`unmark 1`

### Exiting the application: `bye`

Exit the application.

Format:

`bye`

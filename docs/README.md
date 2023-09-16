# RoeBot

RoeBot is a CLI-based application that allows you to manage and track simple
tasks such as to-dos and deadlines.

## Features 

### To-dos, deadlines and events

RoeBot features 3 different types of tasks.

### List tasks

List out tasks in chronological order.

### Mark tasks

Mark a task as done or undone.

## Quick Start

Run the jar file with:

```
java -jar RoeBot.jar
```

## Caution

All date time must be in typed in the format `dd-MM-yyyy HH:mm`.

Example:
```
05-09-2023 13:15
```

## Usage

### `todo {taskDescription}`

### `deadline {taskDescription} /by {dateTime}`

### `event {taskDescription} /from {fromDateTime} /to {toDateTime}`

### `list`

List out all the tasks in the list.

### `sort`

Sort the tasks in chronological order. The ordering is preserved thereafter.

### `mark {index}`

Mark the `index`th task as done.

### `unmark {index}`

Mark the `index`th task as undone.

### `delete {index}`

Delete the `index`th task.

### `find {keyWord}`

List out all tasks containing `keyWord`.

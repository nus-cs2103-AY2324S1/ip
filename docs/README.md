# GigaChadbot

The **GigaChadbot** is a command-line utility for managing tasks. It allows you to create, organize, and manage your tasks efficiently. This README provides an overview of the application and its features.

## Table of Contents

- [Getting Started](#getting-started)
- [Features](#features)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

To get started with the Task Manager Application, follow these steps:

```shell
# Clone the repository to your local machine:
git clone https://github.com/lululwtv/ip.git

# Navigate to the project directory:
cd /build/libs

# Compile and run the application:
java -jar GigaChadbot.jar
```

## Features

### Task Management

- Create various types of tasks: Todo, Deadline, and Event.
- Mark tasks as done when completed.
- Archive tasks for historical reference.

### Interactive Interface

- The application offers an interactive command-line interface (CLI) for easy task management.
- Use commands like `todo`, `deadline`, `event`, `list`, `mark`, `unmark`, `delete`, `find`, and `archive` to interact with your tasks.

### Persistent Storage

- Tasks are stored in the `OUTPUT.txt` file for data persistence.
- Archived tasks are saved in the `ARCHIVE.txt` file for reference.

## Usage

### Adding Tasks

- To add a Todo task, use the `todo` command followed by the task description:

```shell
todo Buy groceries
```

- To add a Deadline task, use the `deadline` command with the task description and the deadline date in the format `yyyy-mm-dd`:

```shell
deadline Finish project /by 2023-12-31
```

- To add an Event task, use the `event` command with the event description and the event dates in the format `yyyy-mm-dd`:

```shell
event Team meeting /from 2023-09-20 /to 2023-09-21
```

### Listing Tasks

- To list all your tasks, use the `list` command:

```shell
list
```

### Marking Tasks

- To mark a task as done, use the `mark` command followed by the task number:

```shell
mark 1
```

### Unmarking Tasks

- To unmark a completed task, use the `unmark` command followed by the task number:

```shell
unmark 1
```

### Deleting Tasks

- To delete a task, use the `delete` command followed by the task number:

```shell
delete 1
```

### Finding Tasks

- To find tasks containing a specific keyword, use the `find` command followed by the keyword:

```shell
find important
```

### Archiving Tasks

- To archive a task, use the `archive` command followed by the task number:

```shell
archive 1
```

## Contributing

Contributions are welcome! If you have any suggestions, feature requests, or bug reports, please open an issue or create a pull request.

# Chatbot Genos

This is a project for a greenfield Java project, with a focus on command-line interface to help
users manage tasks in the form of todo list. The chat bot name is Genos! It has a GUI to show
the chat history, rather than just the CLI in the terminal.

## Getting Started

1. Download the jar file in the release page of this repository.
2. Go to your favourite terminal, navigate to the directory containing the jar file.
3. Compile and run the application with this command: `java -jar GigaChadBot.jar`

## Features

### Task Management

- Add/delete tasks of your chosen types: Todo, Event, Deadline
- Mark/Unmark the tasks as done/not done.
- Find tasks according to date/description/loation

### Storage

- Tasks are stored in a local file on your own machine at `./data/tasks.txt`
- Tasks are also loaded from the previously saved file if it exists.

### GUI

- GUI for the app that looks like a chat bot.

## Usage

_(arguments specificed in <> are required, in [] are optional)_

### Add Tasks

- Note that `/at <place>` parameter is all optional when adding tasks
- Todo: `Todo <description> [/at <place>]`
- Event: `Event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd> [/at <place>]`
- Deadline: `Deadline <description> /from `

### Delete Tasks

- `delete <number>`

### List Tasks

- Equivalent to a read operation
- `list [yyyy-mm-dd]`
- Optional argument to list tasks that is on that date. Todo will not be included since it does
  not have any date.

### Find Tasks

- Find tasks according to the description
- `find <description>`

### Exit

- `bye`

### Mark/Unmark Task

- To mark as done: `mark <task number>`
- To unmark task as not done: `unmark <task number>`

### Location

- Find tasks based on the location specified by the `/at` tag
- `location <place>`
# Duke User Guide

Welcome to the Duke the Chatbot! This chatbot is designed to help you store and display information about your upcoming events using a Command Line Interface (CLI). Below is a list of available commands and their descriptions:

## Available Commands

### 1. List

- Command: `list`
- Parameters: None
- Description: List all upcoming events.
- Usage: `list`

### 2. Mark

- Command: `mark (i)`
- Parameters:
  - `i`: The index of the event to mark (must be an integer).
- Description: Mark the specified event in the list of events.
- Usage: `mark 1` (to mark the first event)

### 3. Unmark

- Command: `unmark (i)`
- Parameters:
  - `i`: The index of the event to unmark (must be an integer).
- Description: Unmark the specified event.
- Usage: `unmark 1` (to unmark the first event)

### 4. Todo

- Command: `todo (name)`
- Parameters:
  - `name`: The name of the todo event (represented by a string).
- Description: Add a todo event with the provided name.
- Usage: `todo Buy groceries`

### 5. Event

- Command: `event (name) /from (datetime) /to (datetime)`
- Parameters:
  - `name`: The name of the event.
  - `datetime`: The start and end date and time of the event.
- Description: Add an event with a name, start date, and end date.
- Usage: `event Birthday Party /from 2023-10-15 15:00 /to 2023-10-15 18:00`

### 6. Deadline

- Command: `deadline (name) /by (datetime)`
- Parameters:
  - `name`: The name of the deadline event.
  - `datetime`: The deadline date and time.
- Description: Add a deadline event with a name and a specified deadline.
- Usage: `deadline Submit Report /by 2023-11-01 23:59`

### 7. Find

- Command: `find (name)`
- Parameters:
  - `name`: The keyword to search for within event names.
- Description: Find event names that match the provided string.
- Usage: `find Party`

### 8. Upcoming

- Command: `upcoming`
- Parameters: None
- Description: List all events in ascending order of their datetime.
- Usage: `upcoming`

### 9. Bye

- Command: `bye`
- Parameters: None
- Description: Exit the program.
- Usage: `bye`

## Example Usage

- To list all upcoming events: `list`
- To mark the first event: `mark 1`
- To add a todo event: `todo Buy groceries`
- To add an event with a specific date and time: `event Birthday Party /from 2023-10-15 15:00 /to 2023-10-15 18:00`
- To find events with the keyword "Party" in their names: `find Party`
- To exit the program: `bye`

Feel free to use these commands to manage your upcoming events effectively with the chatbot. Enjoy using Duke!
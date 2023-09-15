# Horo App User Guide

The Horo application is a simple Java application to manage tasks and basic expense management, optimized for use via a Command Line Interface (CLI).

## Features

### Task Management

There are 3 kinds of tasks you can add.

**Todo**
`todo <description>`
**Deadline**
`deadline <description> /by yyyy/mm/dd HH:mm`
**Event**
`event <description> /from yyyy/mm/dd HH:mm /to yyyy/mm/dd HH:mm`

List tasks (Also shows the number associated with the task)
`list task`

Mark task as done
`mark <number>`

Mark task as not done
`mark <number>`

Delete task
`delete task <number>`

Find task
`find <query>`

### Expense Management

Add expense
`expense <amount> <description>`

List expenses (Also shows the number associated with the expense)
`list expense`

Delete expense
`delete expense <number>`

### Others

Exit the application
`bye`

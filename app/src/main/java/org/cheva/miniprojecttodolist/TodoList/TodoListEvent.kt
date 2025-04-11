package org.cheva.miniprojecttodolist.TodoList

sealed class TodoListEvent {
    data class AddTask(val task: String) : TodoListEvent()
    data class DeleteTask(val task: String) : TodoListEvent()
    data class UpdateTask(val oldTask: String, val newTask: String) : TodoListEvent()
}
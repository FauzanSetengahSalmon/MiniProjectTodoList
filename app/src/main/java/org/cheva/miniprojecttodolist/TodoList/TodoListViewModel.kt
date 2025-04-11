package org.cheva.miniprojecttodolist.Todolist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.cheva.miniprojecttodolist.TodoList.TodoListEvent
import org.cheva.miniprojecttodolist.TodoList.TodoListState

class TodoListViewModel : ViewModel() {
    private val _state = MutableStateFlow(TodoListState())
    val state: StateFlow<TodoListState> = _state.asStateFlow()

    fun onEvent(event: TodoListEvent) {
        when (event) {
            is TodoListEvent.AddTask -> {
                if (event.task.isNotBlank()) {
                    _state.value = _state.value.copy(
                        tasks = _state.value.tasks + event.task,
                        message = "Task added"
                    )
                }
            }

            is TodoListEvent.DeleteTask -> {
                _state.value = _state.value.copy(
                    tasks = _state.value.tasks - event.task,
                    message = "Task deleted"
                )
            }

            is TodoListEvent.UpdateTask -> {
                if (event.newTask.isNotBlank()) {
                    _state.value = _state.value.copy(
                        tasks = _state.value.tasks.map { task ->
                            if (task == event.oldTask) event.newTask else task
                        },
                        message = "Task updated"
                    )
                }
            }
        }
    }
}
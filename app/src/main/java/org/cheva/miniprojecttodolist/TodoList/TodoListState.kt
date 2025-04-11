package org.cheva.miniprojecttodolist.TodoList

data class TodoListState(
    val tasks: List<String> = emptyList(), // Daftar tugas
    val isEditing: Boolean = false, // Menandakan apakah sedang dalam mode pengeditan
    val taskToEdit: String? = null, // Tugas yang sedang diedit
    val message: String = "" // Pesan untuk ditampilkan (jika ada)
)

package org.cheva.miniprojecttodolist.navigation

import kotlinx.serialization.Serializable

@Serializable
object RegisterScreen {
    const val route = "register"
}

@Serializable
object LoginScreen {
    const val route = "login"
}

@Serializable
object TodoListScreen {
    const val route = "TodoList"
}
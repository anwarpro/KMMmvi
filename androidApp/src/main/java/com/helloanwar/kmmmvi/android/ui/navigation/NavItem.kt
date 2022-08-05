package com.helloanwar.kmmmvi.android.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    private val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Characters : NavItem("characters")
    object Favorites : NavItem("favorites")
    object Detail : NavItem("detail")

//    object Detail : NavItem(
//        "detail",
//        listOf(
//            NavArg.CharacterId
//        )
//    ) {
//        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
//    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    IdCharacter("id", NavType.IntType)

}

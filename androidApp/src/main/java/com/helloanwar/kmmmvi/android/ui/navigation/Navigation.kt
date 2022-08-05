package com.helloanwar.kmmmvi.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.helloanwar.kmmmvi.android.ui.features.characters.CharactersScreen
import com.helloanwar.kmmmvi.android.ui.features.detail.CharacterDetailScreen
import com.helloanwar.kmmmvi.android.ui.features.features.CharactersFavoriteScreen
import com.helloanwar.kmmmvi.features.characters.mvi.CharactersViewModel
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailContract
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailViewModel
import com.helloanwar.kmmmvi.features.favorites.mvi.CharactersFavoritesViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Navigation(
    vmCharacters: CharactersViewModel,
    vmCharacterDetail: CharacterDetailViewModel,
    vmCharactersFavorites: CharactersFavoritesViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Characters.route
    ) {
        composable(NavItem.Characters) {
            CharactersScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.route)
                    vmCharacterDetail.setEvent(
                        CharacterDetailContract.Event.GetCharacter(
                            idCharacter = idCharacter
                        )
                    )
                },
                navigateToFavorite = {
                    navController.navigate(route = NavItem.Favorites.route)
                },
                viewModel = vmCharacters
            )
        }
        composable(NavItem.Detail) {
//            backStackEntry.findArg(NavArg.CharacterId.key)
            CharacterDetailScreen(
                onBackPressed = { navController.popBackStack() },
                viewModel = vmCharacterDetail
            )
        }
        composable(NavItem.Favorites) {
            CharactersFavoriteScreen(
                onCharacterClick = { idCharacter ->
                    navController.navigate(route = NavItem.Detail.route)
                    vmCharacterDetail.setEvent(
                        CharacterDetailContract.Event.GetCharacter(
                            idCharacter = idCharacter
                        )
                    )
                },
                onBackPressed = { navController.popBackStack() },
                viewModel = vmCharactersFavorites
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(key: String): T {
    val value = arguments?.get(key)
    requireNotNull(value)
    return value as T
}
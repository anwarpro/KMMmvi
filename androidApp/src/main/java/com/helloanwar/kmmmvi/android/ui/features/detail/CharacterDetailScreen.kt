package com.helloanwar.kmmmvi.android.ui.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.helloanwar.kmmmvi.android.ui.components.state.ManagementResourceState
import com.helloanwar.kmmmvi.domain.model.Character
import com.helloanwar.kmmmvi.domain.model.Status
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailContract
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailViewModel
import kotlinx.coroutines.flow.collectLatest


@ExperimentalCoilApi
@Composable
fun CharacterDetailScreen(
    onBackPressed: () -> Unit,
    viewModel: CharacterDetailViewModel
) {
    var name by remember { mutableStateOf("") }
    val state by viewModel.uiState.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = when (effect) {
                    CharacterDetailContract.Effect.CharacterAdded -> "Character added to favorites"
                    CharacterDetailContract.Effect.CharacterRemoved -> "Character removed from favorites"
                }
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ActionBar(
                name = name,
                isFavorite = state.isFavorite,
                actionAddFavorite = { viewModel.setEvent(CharacterDetailContract.Event.AddCharacterToFavorite) },
                actionRemoveFavorite = { viewModel.setEvent(CharacterDetailContract.Event.RemoveCharacterToFavorite) },
                onBackPressed = onBackPressed
            )
        }
    ) { padding ->
        ManagementResourceState(
            resourceState = state.character,
            successView = { character ->
                requireNotNull(character)
                CharacterDetail(character)
                name = character.name
            },
            modifier = Modifier.padding(padding),
            onTryAgain = { viewModel.setEvent(CharacterDetailContract.Event.Retry) },
            onCheckAgain = { viewModel.setEvent(CharacterDetailContract.Event.Retry) },
        )
    }
}

@ExperimentalCoilApi
@Composable
fun CharacterDetail(character: Character) {
    val status = character.status
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = character.name,
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = rememberImagePainter(character.image),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "${character.origin}, ${character.species}",
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = status.name, color = when (status) {
                Status.ALIVE -> Color.Green
                Status.DEAD -> Color.Red
                Status.UNKNOWN -> Color.Gray
            },
            fontWeight = FontWeight.Bold
        )
    }
}
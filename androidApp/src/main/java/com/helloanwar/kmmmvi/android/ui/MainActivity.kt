package com.helloanwar.kmmmvi.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.helloanwar.kmmmvi.android.base.BaseActivity
import com.helloanwar.kmmmvi.android.ui.navigation.Navigation
import com.helloanwar.kmmmvi.android.ui.theme.KmmMviTheme
import com.helloanwar.kmmmvi.base.executor.IExecutorScope
import com.helloanwar.kmmmvi.features.characters.mvi.CharactersViewModel
import com.helloanwar.kmmmvi.features.detail.mvi.CharacterDetailViewModel
import com.helloanwar.kmmmvi.features.favorites.mvi.CharactersFavoritesViewModel
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val vmCharacters: CharactersViewModel by inject()
    private val vmCharacterDetail: CharacterDetailViewModel by inject()
    private val vmCharactersFavorites: CharactersFavoritesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KmmMviTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(
                        vmCharacters = vmCharacters,
                        vmCharacterDetail = vmCharacterDetail,
                        vmCharactersFavorites = vmCharactersFavorites
                    )
                }
            }
        }
    }

    override val vm: Array<IExecutorScope>
        get() = arrayOf(vmCharacters, vmCharacterDetail, vmCharactersFavorites)
}

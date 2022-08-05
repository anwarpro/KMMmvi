package com.helloanwar.kmmmvi.android.base

import androidx.activity.ComponentActivity
import com.helloanwar.kmmmvi.base.executor.IExecutorScope

abstract class BaseActivity : ComponentActivity() {
    protected abstract val vm: Array<IExecutorScope>

    override fun onDestroy() {
        vm.forEach { it.cancel() }
        super.onDestroy()
    }
}
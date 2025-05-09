package su.pank.ecards.ui.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import su.pank.ecards.ui.auth.AuthViewModel
import su.pank.ecards.ui.start.StartViewModel

val uiModule = module {
    viewModel {
        StartViewModel(get())
    }
    viewModel {
        AuthViewModel(it[0], get())
    }

}
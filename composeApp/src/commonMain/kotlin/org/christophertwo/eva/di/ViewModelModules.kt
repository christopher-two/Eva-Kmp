package org.christophertwo.eva.di

import org.christophertwo.eva.ui.screens.eva.EvaViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val ViewModelModules: Module
    get() = module {
        viewModelOf(::EvaViewModel)
    }
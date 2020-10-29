package org.sochidrive.keep.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.sochidrive.keep.data.NotesRepository
import org.sochidrive.keep.data.provider.DataProvider
import org.sochidrive.keep.data.provider.FirestoreDataProvider
import org.sochidrive.keep.ui.main.MainViewModel
import org.sochidrive.keep.ui.note.NoteViewModel
import org.sochidrive.keep.ui.splash.SplashViewModel

val appModule = module {
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }
    single { FirestoreDataProvider(get(), get()) } bind DataProvider::class
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}
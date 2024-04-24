package com.rbppl.testfoodiesapp.main
import android.app.Application
import com.rbppl.testfoodiesapp.main.domain.usecase.UpdateMenuUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
@HiltAndroidApp
class App: Application() {
    @Inject
    lateinit var updateMenuUseCase: UpdateMenuUseCase
}
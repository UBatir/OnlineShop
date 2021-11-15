package uz.example.onlineshop

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import uz.example.onlineshop.di.adapterModule
import uz.example.onlineshop.di.remoteModule
import uz.example.onlineshop.di.viewModelModule

class App : Application() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val modules = listOf(remoteModule, viewModelModule, adapterModule)
        startKoin { // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(this@App)

            // load properties from assets/koin.properties file
            androidFileProperties()

            // module list
            modules(modules)
        }
    }
}
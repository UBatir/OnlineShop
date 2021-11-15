package uz.example.onlineshop.di

import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.example.onlineshop.data.remote.ApiInterface
import uz.example.onlineshop.ui.main.category.CategoryFragment
import uz.example.onlineshop.ui.main.popular.PopularAdapter
import java.util.concurrent.TimeUnit

object C {
    const val baseUrl: String = ""
}

/*val localModule = module {
    single {
        Room.databaseBuilder(androidContext(), CheckListDatabase::class.java, "base.db")
            .build()
    }
    single {
        get<CheckListDatabase>().daoCheckList()
    }
    single {
        androidApplication().applicationContext.getSharedPreferences(
            androidContext().packageName,
            Context.MODE_PRIVATE
        )
    }
    single { Settings(androidApplication().applicationContext) }
}*/
val remoteModule = module {
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

    }
    single {
        Retrofit.Builder()
            .baseUrl(C.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get()).build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java) }
}

val adapterModule = module {
    single { PopularAdapter() }
    single { CategoryFragment() }
}

val viewModelModule = module {
}
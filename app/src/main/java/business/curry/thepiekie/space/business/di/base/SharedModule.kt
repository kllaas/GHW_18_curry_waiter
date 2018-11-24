/*
 * MIT License
 *
 * Copyright (c) 2018 Artem Vasylenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package business.curry.thepiekie.space.business.di.base

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
open class SharedModule {

    @Singleton
    @Provides
    @Named("postsApiClient")
    fun provideSharedApiClient(context: Context): OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheSize = 2L * 1024 * 1024 // 2 MiB
        val cacheDir = context.getDir("posts_cache", Context.MODE_PRIVATE)
        val cache = okhttp3.Cache(cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(loggingInterceptor)
            .build()
    }

//    @Singleton
//    @Provides
//    @Named("postsRemoteDataSource")
//    open fun providePostsRemoteDataSource(postsApi: CurryApi): PostsDataSource {
//        return PostsRemoteDataSource(postsApi)
//    }
//
//    @Singleton
//    @Provides
//    @Named("postsPagedDataSourceFactory")
//    fun providePostsPagedDataSourceFactory(
//        @Named("postsRemoteDataSource") postsRemoteDataSource: PostsDataSource
//    ): PostsPagedDataSourceFactory {
//        return PostsPagedDataSourceFactory(postsRemoteDataSource)
//    }
}
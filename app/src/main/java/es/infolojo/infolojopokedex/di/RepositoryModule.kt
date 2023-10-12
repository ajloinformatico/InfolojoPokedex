package es.infolojo.infolojopokedex.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.infolojo.infolojopokedex.data.repository.RemoteRepository
import es.infolojo.infolojopokedex.data.repository.RemoteRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    fun provideRemoteRepository(repositoryImpl: RemoteRepositoryImpl): RemoteRepository
}
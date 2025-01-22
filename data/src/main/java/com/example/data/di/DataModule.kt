package com.example.data.di

import com.example.data.api.IncidentApi
import com.example.data.api.ZoneApi
import com.example.data.repository.IncidentRepositoryImpl
import com.example.data.repository.ZoneRepositoryImpl
import com.example.domain.repository.IncidentRepository
import com.example.domain.repository.ZoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideIncidentRepository(incidentApi: IncidentApi): IncidentRepository {
        return IncidentRepositoryImpl(incidentApi)
    }

    @Provides
    @Singleton
    fun provideZoneRepository(zoneApi: ZoneApi): ZoneRepository {
        return ZoneRepositoryImpl(zoneApi)
    }
}
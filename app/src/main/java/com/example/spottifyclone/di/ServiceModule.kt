package com.example.spottifyclone.di

import android.content.Context
import android.media.AudioAttributes
import com.bumptech.glide.util.Util
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped


@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideAudioAttributes()  = com.google.android.exoplayer2.audio.AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @ServiceScoped
    @Provides
    fun provideExoplayer(
        @ApplicationContext context: Context,
        audioAttributes: com.google.android.exoplayer2.audio.AudioAttributes
    ) = SimpleExoPlayer.Builder(context).build().apply {
        setAudioAttributes(audioAttributes,true)
        setHandleAudioBecomingNoisy(true)
    }


    @ServiceScoped
    @Provides
    fun provideDataSourceFactory(
        @ApplicationContext context: Context
    ) = DefaultDataSourceFactory(context,
        com.google.android.exoplayer2.util
        .Util.getUserAgent(context, "Spotify App"))

}
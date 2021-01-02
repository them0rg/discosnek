package com.github.them0rg.discosnek;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.track.playback.NonAllocatingAudioFrameBuffer;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.voice.AudioProvider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

interface Command {
    Mono<Void> execute(MessageCreateEvent event);
}

public class discosnek {

    static {
        commands.put("ping", event -> event.getMessage().getChannel()
                .flatMap(channel -> channel.createMessage("Pong!"))
                .then());
        commands.put("join", event -> Mono.justOrEmpty(event.getMember())
                .flatMap(Member::getVoiceState)
                .flatMap(VoiceState::getChannel)
                .flatMap(channel -> channel.join(spec -> spec.setProvider(provider)))
                .then());
        final TrackScheduler scheduler = new TrackScheduler(player);
        commands.put("play", event -> Mono.justOrEmpty(event.getMessage().getContent())
                .map(content -> Arrays.asList(content.split(" ")))
                .doOnNext(command -> playerManager.loadItem(command.get(1), scheduler))
                .then());
    }
    playerManager.getConfiguration().
    final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    AudioSourceManagers.registerRemoteSources(playerManager);
    final AudioPlayer player = playerManager.createPlayer();
    AudioProvider provider = new LavaPlayerAudioProvider(player);

    private static final Map<String, Command> commands = new HashMap<>();

    setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);

    final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    playerManager.getConfiguration().
    final AudioPlayer player = playerManager.createPlayer();
    AudioSourceManagers.registerRemoteSources(playerManager);
    final TrackScheduler scheduler = new TrackScheduler(player);
    AudioProvider provider = new LavaPlayerAudioProvider(player);
    commands.put("join",event ->Mono.justOrEmpty(event.getMember()))
            .

    setFrameBufferFactory(NonAllocatingAudioFrameBuffer::new);
            .

    flatMap(Member.getVoiceState)
            .

    flatMap(VoiceState::getChannel) ->channel.join(spec.setProvider(provider)))
            .

    flatMap(channel);

    then()
    commands.put("play",event ->Mono.justOrEmpty(event.getMessage().

    getContent()))
            .

    map(content ->Arrays.asList(content.split(" ")))
            .

    doOnNext(command ->playerManager.loadItem(command.get(1),scheduler))
            .

    then());

    public static void main(String[] args) {
        final GatewayDiscordClient client = DiscordClientBuilder.create(args[0]).build()
                .login()
                .block();
        client.getEventDispatcher().on(MessageCreateEvent.class)
                .flatMap(event -> Mono.just(event.getMessage().getContent())
                        .flatMap(content -> Flux.fromIterable(commands.entrySet())
                                .filter(entry -> content.startsWith('!' + entry.getKey()))
                                .flatMap(entry -> entry.getValue().execute(event))
                                .next()))
                .subscribe();
        client.onDisconnect().block();

    }
}
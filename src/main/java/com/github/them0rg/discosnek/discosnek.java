package com.github.them0rg.discosnek;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;

public class discosnek {

    public static void main(String[] args) {
        final GatewayDiscordClient client = DiscordClientBuilder.create(args[0]).build()
            .login()
            .block();
        client.onDisconnect().block();
    }
}

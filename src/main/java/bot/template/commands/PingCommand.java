package bot.template.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand implements SlashCommands {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Replies with Pong and shows the latency.";
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        long time = System.currentTimeMillis();
        event.reply("Calculating...").setEphemeral(true).queue(response -> {
            long latency = System.currentTimeMillis() - time;
            response.editOriginalFormat("Pong! 🏓 Latency: %d ms", latency).queue();
        });
    }
}

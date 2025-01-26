package bot.template.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface SlashCommands {
    String getName();
    String getDescription();


    void execute(SlashCommandInteractionEvent event);
}

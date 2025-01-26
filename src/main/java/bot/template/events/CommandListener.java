package bot.template.events;

import bot.template.commands.SlashCommands;
import bot.template.utils.CommandScanner;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class CommandListener extends ListenerAdapter {
    private final Map<String, SlashCommands> commands = new HashMap<>();

    public CommandListener() {
        loadCommands();
    }

    private void loadCommands() {
        try {
            Set<Class<? extends SlashCommands>> commandClasses = CommandScanner.findCommands("bot.template.commands");
            for (Class<? extends SlashCommands> clazz : commandClasses) {
                SlashCommands command = clazz.getDeclaredConstructor().newInstance();
                commands.put(command.getName(), command);
            }
        } catch (Exception e) {
            System.err.println("Error at loading the commands: " + e.getMessage());
        }
    }

    public Collection<SlashCommands> getCommands() {
        return commands.values();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String commandName = event.getName();
        SlashCommands command = commands.get(commandName);

        if (command != null) {
            command.execute(event);
        } else {
            event.reply("Error at executing this command.").setEphemeral(true).queue();
        }
    }
}

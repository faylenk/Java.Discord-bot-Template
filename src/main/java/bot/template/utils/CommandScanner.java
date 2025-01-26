package bot.template.utils;

import bot.template.commands.SlashCommands;

import org.reflections.Reflections;

import java.util.Set;

public class CommandScanner {

    public static Set<Class<? extends SlashCommands>> findCommands(String packageName) {
        Reflections reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(SlashCommands.class);
    }
}

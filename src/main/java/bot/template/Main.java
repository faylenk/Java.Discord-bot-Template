package bot.template;

import bot.template.events.CommandListener;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        Dotenv dotenv = Dotenv.configure().load();
        String token = dotenv.get("TOKEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        CommandListener commandListener = new CommandListener();
        jdaBuilder.addEventListeners(commandListener);

        JDA jda = jdaBuilder.build();

        jda.updateCommands().addCommands(
                commandListener.getCommands().stream()
                        .map(command -> Commands.slash(command.getName(), command.getDescription()))
                        .toList()
        ).queue();
    }
}

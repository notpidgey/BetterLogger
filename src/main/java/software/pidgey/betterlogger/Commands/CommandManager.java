package software.pidgey.betterlogger.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import software.pidgey.betterlogger.Commands.SubCommands.GetHistory;
import software.pidgey.betterlogger.Commands.SubCommands.QueryRaw;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    public static String prefix = "§l[§6BetterLogger§f§l]§r ";

    public CommandManager(){
        subCommands.add(new QueryRaw());
        subCommands.add(new GetHistory());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;

            if(args.length > 0){
                for(int i = 0; i < subCommands.size(); i++){
                    if(args[0].equalsIgnoreCase(getSubCommands().get(i).getName())){
                        getSubCommands().get(i).perform(p, args);
                    }
                }
            }
        }

        return true;
    }

    public static void improperCommand(Player player, String usage){
        player.sendMessage(prefix + "Improper Usage\n"+ prefix + usage);
    }

    public static String encloseText(String text){
        return "§l[§6" + text + "§f§l]§r ";
    }

    public ArrayList<SubCommand> getSubCommands(){
        return subCommands;
    }
}

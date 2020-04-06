package software.pidgey.betterlogger.Commands.SubCommands;

import org.bukkit.entity.Player;
import software.pidgey.betterlogger.Commands.SubCommand;

import java.sql.ResultSet;
import java.sql.SQLException;

import static software.pidgey.betterlogger.Commands.CommandManager.improperCommand;
import static software.pidgey.betterlogger.Commands.CommandManager.prefix;
import static software.pidgey.betterlogger.DataBase.SQLUtilities.statement;

public class QueryRaw extends SubCommand {
    @Override
    public String getName() {
        return "query";
    }

    @Override
    public String getDescription() {
        return "Create a raw SQL query for stored BetterLogger data.";
    }

    @Override
    public String getSyntax() {
        return "/BetterLogger query 'PostgreSQL Query'";
    }

    @Override
    public void perform(Player player, String[] args) {
        String sqlQuery = "";

        if(args.length < 2)
            improperCommand(player, getSyntax());
        else{
            for(int i = 1; i<args.length; i++){
                sqlQuery += " " + args[i];
            }
            try {
                ResultSet rs = statement.executeQuery(sqlQuery);
                while(rs.next()){
                    player.sendMessage(prefix + rs.getString("Time") + rs.getString("X") + rs.getString("Y")+ rs.getString("Z"));
                }
            } catch (SQLException e) {
                player.sendMessage(prefix + "ERROR: " + e.toString());
                e.printStackTrace();
            }
        }
    }
}

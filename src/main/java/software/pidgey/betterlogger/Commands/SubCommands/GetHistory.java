package software.pidgey.betterlogger.Commands.SubCommands;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import software.pidgey.betterlogger.Commands.SubCommand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static software.pidgey.betterlogger.Commands.CommandManager.encloseText;
import static software.pidgey.betterlogger.Commands.CommandManager.prefix;
import static software.pidgey.betterlogger.DataBase.BlockData.BlockInteractionData.dateFormat;
import static software.pidgey.betterlogger.DataBase.SQLUtilities.*;
import static software.pidgey.betterlogger.Utilities.getTargetBlock;

public class GetHistory extends SubCommand {
    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String getDescription() {
        return "Query history of block changes of given block.";
    }

    @Override
    public String getSyntax() {
        return "/BetterLogger history";
    }

    @Override
    public void perform(Player player, String[] args) {
        Block block = getTargetBlock(player, 5);
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM \"Block Interaction\" " +
                    "WHERE \"X\" =?" +
                    "AND \"Y\" =?" +
                    "AND \"Z\" =?", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, block.getLocation().getBlockX());
            ps.setInt(2, block.getLocation().getBlockY());
            ps.setInt(3, block.getLocation().getBlockZ());
            
            ResultSet rs = ps.executeQuery();

            if(getRowCount(rs) == 0)
                player.sendMessage(prefix + "This coordinate contains no history");
            else {
                while(rs.next()){
                    player.sendMessage(encloseText(rs.getString("Time")));
                    player.sendMessage(encloseText("Player") +rs.getString("Username"));
                    player.sendMessage(encloseText("Event") + rs.getString("Interaction Type"));
                    player.sendMessage(encloseText("Block") + rs.getString("Block"));
                    player.sendMessage(encloseText("--------------------------------------------------"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

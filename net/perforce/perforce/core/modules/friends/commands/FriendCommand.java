package net.perforce.perforce.core.modules.friends.commands;

import net.perforce.perforce.Main;
import net.perforce.perforce.storage.data_manager.Friends_Manager;
import net.perforce.perforce.users.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class FriendCommand implements CommandExecutor {

    private final Main plugin = Main.getInstance;
    private final Map<User, User> requests = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("friend")) {
            Player player = (Player)sender;
            User user = new User(player);
            if (args.length < 1) {

                for (User friend : Friends_Manager.getFriends(user)) {
                    player.sendMessage(friend.getDispayName());
                }
                return true;

            }
            if (args[0].equalsIgnoreCase("add")) {

                if (args.length != 2) {
                    // TODO specify user error message
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    // TODO Could not find player error message
                    return true;
                }
                // TODO Send request to target
                // TODO make request clickable
                requests.put(user, new User(target));

            } else if (args[0].equalsIgnoreCase("remove")) {

                if (args.length != 2) {
                    // TODO specify user error message
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    // TODO Could not find player error message
                    return true;
                }
                User remove = new User(target);
                Friends_Manager.removeFriend(user, remove);
                // TODO Success message

            } else if (args[0].equalsIgnoreCase("list")) {

                for (User friend : Friends_Manager.getFriends(user)) {
                    player.sendMessage(friend.getDispayName());
                }

            } else if (args[0].equalsIgnoreCase("accept")) {

                if (requests.containsValue(user)) {
                    User target = requests.get(user);
                    Friends_Manager.addFriend(user, target);
                }
                // TODO Success message

            } else if (args[0].equalsIgnoreCase("deny")) {

                if (requests.containsValue(user)) {
                    // TODO Success message
                }

            }

        }
        return false;
    }

}

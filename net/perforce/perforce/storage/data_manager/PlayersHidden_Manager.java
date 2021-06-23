package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

public class PlayersHidden_Manager {

    public static boolean getHidden(User user) {
        return Data_Lists.Player_Stats_PlayersHidden.get(user.getUuid()).getHidden();
    }

    public static void isHidden(User user, boolean hidden) {
        Data_Lists.Player_Stats_PlayersHidden.get(user.getUuid()).setHidden(hidden);
    }

}

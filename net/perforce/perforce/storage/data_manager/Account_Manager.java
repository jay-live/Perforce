package net.perforce.perforce.storage.data_manager;

import net.perforce.perforce.storage.Data_Lists;
import net.perforce.perforce.users.User;

public class Account_Manager {

    public static double getCoins(User user) {
        return Data_Lists.Player_Stats_Coins.get(user.getUuid()).getCoins();
    }

    public static boolean hasEnoughCoins(User user, double coins) {
        return Data_Lists.Player_Stats_Coins.get(user.getUuid()).getCoins() >= coins;
    }

    public static void setCoins(User user, double coins) {
        Data_Lists.Player_Stats_Coins.get(user.getUuid()).setCoins(coins);
    }

    public static void addCoins(User user, double coins) {
        double balance = Data_Lists.Player_Stats_Coins.get(user.getUuid()).getCoins();
        Data_Lists.Player_Stats_Coins.get(user.getUuid()).setCoins(balance + coins);
    }

    public static void removeCoins(User user, double coins) {
        double balance = Data_Lists.Player_Stats_Coins.get(user.getUuid()).getCoins();
        if (balance >= coins) {
            Data_Lists.Player_Stats_Coins.get(user.getUuid()).setCoins(balance - coins);
        }
    }

}

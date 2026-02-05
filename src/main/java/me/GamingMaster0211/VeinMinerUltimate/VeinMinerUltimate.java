package me.GamingMaster0211.VeinMinerUltimate;

import org.bukkit.plugin.java.JavaPlugin;

public class VeinMinerUltimate extends JavaPlugin {

    private static VeinMinerUltimate instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(
                new VeinMinerUltimateListener(this), this);

        getCommand("vmu").setExecutor(new VeinMinerUltimateToggleCommand());

        getLogger().info("VeinMinerUltimate enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("VeinMinerUltimate disabled!");
    }

    public static VeinMinerUltimate getInstance() {
        return instance;
    }
}

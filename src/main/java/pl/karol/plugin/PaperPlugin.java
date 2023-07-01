package pl.karol.plugin;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.json.gson.JsonGsonConfigurer;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.karol.plugin.command.GetMagicBottleCommand;
import pl.karol.plugin.drop.DropCommand;
import pl.karol.plugin.drop.DropConfig;
import pl.karol.plugin.drop.DropItemSerdes;
import pl.karol.plugin.listener.PlayerDeathListener;
import pl.karol.plugin.listener.PlayerInteractListener;
import pl.karol.plugin.listener.PlayerPreCommandListener;

import java.io.File;

public final class PaperPlugin extends JavaPlugin {
    private DropConfig dropConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        dropConfig = ConfigManager.create(DropConfig.class, (config) -> {
            config.withConfigurer(new JsonGsonConfigurer(), new SerdesCommons(), new SerdesBukkit(), new DropItemSerdes());
            config.withBindFile(new File(this.getDataFolder(), "drops.json"));
            config.saveDefaults();
            config.load();
        });

        this.getServer().getPluginManager().registerEvents(new PlayerPreCommandListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getCommand("bottle").setExecutor(new GetMagicBottleCommand());
        getCommand("adrop").setExecutor(new DropCommand(this));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DropConfig getDropConfig() {
        return dropConfig;
    }
}

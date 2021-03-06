package com.github.yannicklamprecht.worldborder.plugin;

import com.github.yannicklamprecht.worldborder.api.BorderAPI;
import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by ysl3000
 */
public class WorldBorderPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        String version = Bukkit.getServer().getClass().getPackage().getName().replace('.', ',').split(",")[3];
        System.out.println("Version: " + version);
        WorldBorderApi worldBorderApi;
        switch (version) {
            case "v1_14_R1":
                worldBorderApi = new com.github.yannicklamprecht.worldborder.v1_14_R1.Impl();
                break;
            case "v1_15_R1":
                worldBorderApi = new com.github.yannicklamprecht.worldborder.v1_15_R1.Impl();
                break;
            case "v1_16_R1":
                worldBorderApi = new com.github.yannicklamprecht.worldborder.v1_16_R1.Impl();
                break;
            default: {
                getLogger().info("Unsupported version of Minecraft");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
        }

        worldBorderApi = new PersistenceWrapper(this, worldBorderApi);

        BorderAPI.setWorldBorderApi(worldBorderApi);
    }

}

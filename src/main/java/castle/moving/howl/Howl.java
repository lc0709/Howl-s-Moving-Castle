package castle.moving.howl;


import org.bukkit.plugin.java.JavaPlugin;

public final class Howl extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("get").setExecutor(new GetCommand(this));
        getCommand("set").setExecutor(new SetCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

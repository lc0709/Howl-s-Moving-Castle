package castle.moving.howl;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.*;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SetCommand implements CommandExecutor {
    private final Howl plugin;
    public SetCommand(Howl plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = null;
        if(sender instanceof Player) player = (Player) sender;

        Clipboard clipboard = null;
        File file = new File("C:\\Users\\CHAN.LEE\\aaaa\\test.schem");
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
        } catch (IOException e) {
            plugin.getLogger().warning("setting err");
        }
        try (EditSession editSession = WorldEdit.getInstance().newEditSession((World) player.getWorld())) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(v(args[0], args[2], args[2]))
                    // configure here
                    .build();
            Operations.complete(operation);
        }
        return true;
    }
    private BlockVector3 v(String x, String y, String z){
        return new BlockVector3(){
            @Override
            public int getX() {
                return Integer.parseInt(x);
            }
            @Override
            public int getY() {
                return Integer.parseInt(y);
            }
            @Override
            public int getZ() {
                return Integer.parseInt(z);
            }
        };
    }
}

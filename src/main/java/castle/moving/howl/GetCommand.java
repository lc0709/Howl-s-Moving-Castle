package castle.moving.howl;

import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardWriter;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GetCommand implements CommandExecutor {
    private final Howl plugin;
    public GetCommand(Howl plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = null;
        if(sender instanceof Player) player = (Player) sender;

        CuboidRegion region = new CuboidRegion( v(args[0], args[1], args[2]), v(args[3], args[4], args[5]));
        BlockArrayClipboard clipboard = new BlockArrayClipboard(region);
        assert player != null;
        ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy((Extent) player.getWorld(), region, clipboard, region.getMinimumPoint());
        // configure here
        Operations.complete(forwardExtentCopy);
        player.sendMessage("[ " + args[0] + ", "+ args[1] + ", " + args[2] + " ] , [ " +args[3] + ", " + args[4] + ", " + args[5]+" ]");

        File file = new File("C:\\Users\\CHAN.LEE\\aaaa\\test.schem");
        try (ClipboardWriter writer = BuiltInClipboardFormat.FAST.getWriter(new FileOutputStream(file))) {
            writer.write(clipboard);
        } catch (IOException e) {
            plugin.getLogger().warning("getting err");;
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

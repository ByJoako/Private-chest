package net.helydev.com.command;

import net.helydev.com.EnderChest;
import net.helydev.com.listener.SaveInventory;
import net.helydev.com.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderchestCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Use command in-game!");
            return true;
        }
        Player player= (Player) sender;
        if(args.length==0){
                if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.6.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9*6, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.6.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.5.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9*5, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.5.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        if(EnderChest.menu.get(player.getName()).length>=46){
                            sender.sendMessage(CC.translate("&4&lError!"));
                            return true;
                        }
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.4.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9*4, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.4.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        if(EnderChest.menu.get(player.getName()).length>=37){
                            sender.sendMessage(CC.translate("&4&lError!"));
                            return true;
                        }
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.3.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9*3, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.3.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        if(EnderChest.menu.get(player.getName()).length>=28){
                            sender.sendMessage(CC.translate("&4&lError!"));
                            return true;
                        }
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.2.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9*2, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.2.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        if(EnderChest.menu.get(player.getName()).length>=19){
                            sender.sendMessage(CC.translate("&4&lError!"));
                            return true;
                        }
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else if(player.hasPermission(EnderChest.getInstance().getConfig().getString("Enderchest.1.perm"))){
                    Inventory inv= Bukkit.createInventory(player, 9, CC.translate(EnderChest.getInstance().getConfig().getString("Enderchest.1.displayname")));
                    if(EnderChest.menu.containsKey(player.getName())){
                        if(EnderChest.menu.get(player.getName()).length>=10){
                            sender.sendMessage(CC.translate("&4&lError!"));
                            return true;
                        }
                        inv.setContents(EnderChest.menu.get(player.getName()));
                    }
                    player.openInventory(inv);
                    return true;
                }else{
                    sender.sendMessage(CC.translate(EnderChest.getInstance().getConfig().getString("No_permission")));
                }
        }else if(player.hasPermission("enderchest.command.other")){
            if(EnderChest.menu.containsKey(args[0])){
                Inventory inv=Bukkit.createInventory(null, EnderChest.menu.get(args[0]).length, CC.translate("&ePrivate enderchest &7"+args[0]));
                inv.setContents(EnderChest.menu.get(args[0]));
                player.openInventory(inv);
                SaveInventory.EditChest.put(player, args[0]);
            }else{
                player.sendMessage(CC.translate("&ePlayer has no ender chest"));
            }

            return true;
        }else{
            sender.sendMessage(CC.translate("&eUse /enderchest"));
        }
        return false;
    }
}

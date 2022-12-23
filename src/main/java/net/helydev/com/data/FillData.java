package net.helydev.com.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Joako (21/12/2022 | 23:25)
 **/

@AllArgsConstructor
@Getter
@Setter
public class FillData {

    private String permission;
    private Integer slotStart;
    private Integer slotAmount;
    private ItemStack item;
}

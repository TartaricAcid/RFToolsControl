package mcjty.rftoolscontrol.items;

import mcjty.rftoolscontrol.RFToolsControl;
import mcjty.rftoolscontrol.api.parameters.Parameter;
import mcjty.rftoolscontrol.logic.ParameterTools;
import mcjty.rftoolscontrol.logic.registry.ParameterTypeTools;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class TokenItem extends GenericRFToolsItem {

    public TokenItem() {
        super("token");
        setMaxStackSize(64);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> list, boolean advanced) {
        super.addInformation(stack, playerIn, list, advanced);

        boolean hasContents = false;
        if (stack.hasTagCompound()) {
            NBTTagCompound parameter = stack.getTagCompound().getCompoundTag("parameter");
            if (!parameter.hasNoTags()) {
                Parameter par = ParameterTools.readFromNBT(parameter);
                hasContents = true;
                list.add(TextFormatting.BLUE + "Type: " + par.getParameterType().getName());
                list.add(TextFormatting.BLUE + "Value: " + ParameterTypeTools.stringRepresentation(par.getParameterType(), par.getParameterValue()));
            }
        }
        if (!hasContents) {
            list.add(TextFormatting.BLUE + "This token is empty");
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            list.add(TextFormatting.WHITE + "This item is a simple token. It does");
            list.add(TextFormatting.WHITE + "not do anything but it can store");
            list.add(TextFormatting.WHITE + "information");
        } else {
            list.add(TextFormatting.WHITE + RFToolsControl.SHIFT_MESSAGE);
        }
    }
}

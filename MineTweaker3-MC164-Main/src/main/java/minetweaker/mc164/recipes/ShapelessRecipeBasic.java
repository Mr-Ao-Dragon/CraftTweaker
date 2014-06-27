/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minetweaker.mc164.recipes;

import minetweaker.api.recipes.ShapelessRecipe;
import java.util.Arrays;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

/**
 *
 * @author Stan
 */
public class ShapelessRecipeBasic extends ShapelessRecipes {
	private final ShapelessRecipe recipe;
	
	public ShapelessRecipeBasic(ItemStack[] ingredients, ShapelessRecipe recipe) {
		super((ItemStack) recipe.getOutput().getInternal(), Arrays.asList(ingredients));
		
		this.recipe = recipe;
	}
	
	@Override
	public boolean matches(InventoryCrafting inventory, World world) {
		return recipe.matches(MCCraftingInventory.get(inventory));
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inventory) {
		return ((ItemStack) recipe.getCraftingResult(MCCraftingInventory.get(inventory)).getInternal()).copy();
	}
}

package com.blamejared.crafttweaker.natives.util;

import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker_annotations.annotations.NativeMethod;
import com.blamejared.crafttweaker_annotations.annotations.NativeTypeRegistration;
import net.minecraftforge.registries.IForgeRegistryEntry;

@ZenRegister
@NativeMethod(name = "getRegistryName", parameters = {}, getterName = "registryName")
@NativeTypeRegistration(value = IForgeRegistryEntry.class, zenCodeName = "crafttweaker.api.util.IForgeRegistryEntry")
public class ExpandIForgeRegistryEntry {
}

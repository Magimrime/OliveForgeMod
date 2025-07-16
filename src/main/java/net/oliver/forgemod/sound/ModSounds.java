package net.oliver.forgemod.sound;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.oliver.forgemod.ForgeMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ForgeMod.MOD_ID);

    public static RegistryObject<SoundEvent> CHISEL_USE = registerSoundEvent("chisel_use");

    public static final RegistryObject<SoundEvent> BAR_BRAWL = registerSoundEvent("bar_brawl");
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "bar_brawl"));

    public static final RegistryObject<SoundEvent> ROLL = registerSoundEvent("roll");
    public static final ResourceKey<JukeboxSong> ROLL_KEY = ResourceKey.create(Registries.JUKEBOX_SONG,
            ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, "roll"));



    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(ForgeMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

package net.kuko.ish.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.kuko.ish.Ish;
import net.kuko.ish.data.entry.DataEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.Map;
import java.util.function.Consumer;

public class DataRegistry extends SimpleJsonResourceReloadListener implements IdentifiableResourceReloadListener {
    private static final Gson GSON = new GsonBuilder().create();

    public DataRegistry() {
        super(GSON, Ish.MOD_ID + "/data");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profilerFiller) {
        // use handle() here when you have actual codecs to parse
        handle(object, DataEntry.CODEC, data -> {
            // Do something with the parsed data
            Ish.LOGGER.info("Loaded data: {}", data);
        });
    }

    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(Ish.MOD_ID, "data_listener");
    }

    public static <T> void handle(Map<ResourceLocation, JsonElement> object, Codec<T> codec, Consumer<T> onSuccess) {
        object.forEach((rl, element) ->
                codec.parse(JsonOps.INSTANCE, element)
                        .resultOrPartial(error -> Ish.LOGGER.error("Failed to parse {}: {}", rl, error))
                        .ifPresent(onSuccess)
        );
    }

    public static void register() {
        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new DataRegistry());
    }
}
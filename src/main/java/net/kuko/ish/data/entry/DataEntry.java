package net.kuko.ish.data.entry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record DataEntry(
        String name,
        Integer age,
        Double rating
) {
    public static final Codec<DataEntry> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(DataEntry::name),
                    Codec.INT.fieldOf("age").forGetter(DataEntry::age),
                    Codec.DOUBLE.fieldOf("rating").forGetter(DataEntry::rating)
            ).apply(instance, DataEntry::new));
}


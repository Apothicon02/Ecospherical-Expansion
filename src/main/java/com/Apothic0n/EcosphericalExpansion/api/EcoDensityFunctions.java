package com.Apothic0n.EcosphericalExpansion.api;

import com.Apothic0n.EcosphericalExpansion.EcosphericalExpansion;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ConcurrentHashMap;

import static com.Apothic0n.EcosphericalExpansion.api.EcoMath.progressBetweenInts;

public final class EcoDensityFunctions {
    public static final DeferredRegister<Codec<? extends DensityFunction>> DENSITY_FUNCTION_TYPES = DeferredRegister.create(Registries.DENSITY_FUNCTION_TYPE, EcosphericalExpansion.MODID);

    public static final RegistryObject<Codec<? extends DensityFunction>> TO_HEIGHTMAP_DENSITY_FUNCTION_TYPE = DENSITY_FUNCTION_TYPES.register("to_heightmap", ToHeightmap.CODEC::codec);
    public static final RegistryObject<Codec<? extends DensityFunction>> SHIFT_DENSITY_FUNCTION_TYPE = DENSITY_FUNCTION_TYPES.register("shift", Shift.CODEC::codec);
    public static final RegistryObject<Codec<? extends DensityFunction>> FLATTEN_DENSITY_FUNCTION_TYPE = DENSITY_FUNCTION_TYPES.register("flatten", Flatten.CODEC::codec);
    public static final RegistryObject<Codec<? extends DensityFunction>> STORE_TEMPERATURE_DENSITY_FUNCTION_TYPE = DENSITY_FUNCTION_TYPES.register("store_temperature", StoreTemperature.CODEC::codec);
    public static final RegistryObject<Codec<? extends DensityFunction>> STORE_HUMIDITY_DENSITY_FUNCTION_TYPE = DENSITY_FUNCTION_TYPES.register("store_humidity", StoreHumidity.CODEC::codec);

    public static void register(IEventBus eventBus) {
        DENSITY_FUNCTION_TYPES.register(eventBus);
    }

    public static ConcurrentHashMap<String, Double> heightmap = new ConcurrentHashMap<>();
    public static DensityFunction temperature;
    public static DensityFunction humidity;

    protected record Shift(DensityFunction input, int shiftX, int shiftY, int shiftZ) implements DensityFunction {
        private static final MapCodec<Shift> DATA_CODEC = RecordCodecBuilder.mapCodec((data) -> {
            return data.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(Shift::input), Codec.INT.fieldOf("shift_x").forGetter(Shift::shiftX), Codec.INT.fieldOf("shift_y").forGetter(Shift::shiftY), Codec.INT.fieldOf("shift_z").forGetter(Shift::shiftZ)).apply(data, Shift::new);
        });
        public static final KeyDispatchDataCodec<Shift> CODEC = EcoDensityFunctions.makeCodec(DATA_CODEC);

        @Override
        public double compute(@NotNull FunctionContext context) {
            return input.compute(new DensityFunction.SinglePointContext(context.blockX()+shiftX(), context.blockY()+shiftY(), context.blockZ()+shiftZ()));
        }

        @Override
        public void fillArray(double @NotNull [] densities, ContextProvider context) {
            context.fillAllDirectly(densities, this);
        }

        @Override
        public @NotNull DensityFunction mapAll(Visitor visitor) {
            return visitor.apply(new StoreTemperature(this.input().mapAll(visitor)));
        }

        @Override
        public double minValue() {
            return -1875000d;
        }

        @Override
        public double maxValue() {
            return 1875000d;
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }

    }

    protected record Flatten(DensityFunction input, int atY) implements DensityFunction {
        private static final MapCodec<Flatten> DATA_CODEC = RecordCodecBuilder.mapCodec((data) -> {
            return data.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(Flatten::input), Codec.INT.fieldOf("at_y").forGetter(Flatten::atY)).apply(data, Flatten::new);
        });
        public static final KeyDispatchDataCodec<Flatten> CODEC = EcoDensityFunctions.makeCodec(DATA_CODEC);

        @Override
        public double compute(@NotNull FunctionContext context) {
            return input.compute(new DensityFunction.SinglePointContext(context.blockX(), atY(), context.blockZ()));
        }

        @Override
        public void fillArray(double @NotNull [] densities, ContextProvider context) {
            context.fillAllDirectly(densities, this);
        }

        @Override
        public @NotNull DensityFunction mapAll(Visitor visitor) {
            return visitor.apply(new StoreTemperature(this.input().mapAll(visitor)));
        }

        @Override
        public double minValue() {
            return -1875000d;
        }

        @Override
        public double maxValue() {
            return 1875000d;
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }

    }

    protected record StoreTemperature(DensityFunction input) implements DensityFunction {
        private static final MapCodec<StoreTemperature> DATA_CODEC = RecordCodecBuilder.mapCodec((data) -> {
            return data.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(StoreTemperature::input)).apply(data, StoreTemperature::new);
        });
        public static final KeyDispatchDataCodec<StoreTemperature> CODEC = EcoDensityFunctions.makeCodec(DATA_CODEC);

        @Override
        public double compute(@NotNull FunctionContext context) {
            if (temperature == null) {
                temperature = input();
            }
            return input.compute(context);
        }

        @Override
        public void fillArray(double @NotNull [] densities, ContextProvider context) {
            context.fillAllDirectly(densities, this);
        }

        @Override
        public @NotNull DensityFunction mapAll(Visitor visitor) {
            return visitor.apply(new StoreTemperature(this.input().mapAll(visitor)));
        }

        @Override
        public double minValue() {
            return -1875000d;
        }

        @Override
        public double maxValue() {
            return 1875000d;
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }

    }
    protected record StoreHumidity(DensityFunction input) implements DensityFunction {
        private static final MapCodec<StoreHumidity> DATA_CODEC = RecordCodecBuilder.mapCodec((data) -> {
            return data.group(DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(StoreHumidity::input)).apply(data, StoreHumidity::new);
        });
        public static final KeyDispatchDataCodec<StoreHumidity> CODEC = EcoDensityFunctions.makeCodec(DATA_CODEC);

        @Override
        public double compute(@NotNull FunctionContext context) {
            if (humidity == null) {
                humidity = input();
            }
            return input.compute(context);
        }

        @Override
        public void fillArray(double @NotNull [] densities, ContextProvider context) {
            context.fillAllDirectly(densities, this);
        }

        @Override
        public @NotNull DensityFunction mapAll(Visitor visitor) {
            return visitor.apply(new StoreHumidity(this.input().mapAll(visitor)));
        }

        @Override
        public double minValue() {
            return -1875000d;
        }

        @Override
        public double maxValue() {
            return 1875000d;
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }

    }
    protected record ToHeightmap(int minY, int maxY, DensityFunction input) implements DensityFunction {
        private static final MapCodec<ToHeightmap> DATA_CODEC = RecordCodecBuilder.mapCodec((data) -> {
            return data.group(Codec.INT.fieldOf("min_y").forGetter(ToHeightmap::minY), Codec.INT.fieldOf("max_y").forGetter(ToHeightmap::maxY), DensityFunction.HOLDER_HELPER_CODEC.fieldOf("input").forGetter(ToHeightmap::input)).apply(data, ToHeightmap::new);
        });
        public static final KeyDispatchDataCodec<ToHeightmap> CODEC = EcoDensityFunctions.makeCodec(DATA_CODEC);

        @Override
        public double compute(@NotNull FunctionContext context) {
            int x = context.blockX();
            int z = context.blockZ();
            String id = x+"/"+z;
            Double storedValue = heightmap.get(id);
            if (storedValue != null) {
                return storedValue;
            } else {
                for (int newY = maxY(); newY > minY(); newY--) {
                    double value = input().compute(new SinglePointContext(x, newY, z));
                    if (value > 0) {
                        double returnValue = progressBetweenInts(minY(), maxY(), newY);
                        heightmap.put(id, returnValue);
                        return returnValue;
                    }
                }
            }
            heightmap.put(id, 0D);
            return 0;
        }

        @Override
        public void fillArray(double @NotNull [] densities, ContextProvider context) {
            context.fillAllDirectly(densities, this);
        }

        @Override
        public @NotNull DensityFunction mapAll(Visitor visitor) {
            return visitor.apply(new ToHeightmap(minY(), maxY(), this.input().mapAll(visitor)));
        }

        @Override
        public double minValue() {
            return -1875000d;
        }

        @Override
        public double maxValue() {
            return 1875000d;
        }

        @Override
        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            return CODEC;
        }
    }

    static <O> KeyDispatchDataCodec<O> makeCodec(MapCodec<O> codec) {
        return KeyDispatchDataCodec.of(codec);
    }
}

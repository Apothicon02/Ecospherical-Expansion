package com.Apothic0n.EcosphericalExpansion.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class EcoJsonReader {
    public static boolean serverSidedOnlyMode = false;

    public static void main() throws Exception {
        makeCustomBlocks(Path.of(FMLPaths.CONFIGDIR.get().toString() + "/eco-common.json"));
    }

    private static void makeCustomBlocks(Path path) throws IOException {
        Gson gson = new Gson();
        if (!Files.exists(path)) {
            JsonWriter writer = new JsonWriter(new FileWriter(path.toString()));
            JsonObject defaultData = gson.fromJson("{\"serverSidedOnlyMode\":\"false\"}", JsonObject.class);
            gson.toJson(defaultData, writer);
            writer.close();
        }
        JsonReader reader = new JsonReader(new FileReader(path.toString()));
        JsonObject data = gson.fromJson(reader, JsonObject.class);
        serverSidedOnlyMode = data.get("serverSidedOnlyMode").getAsBoolean();
    }
}

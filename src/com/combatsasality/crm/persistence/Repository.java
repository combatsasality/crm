package com.combatsasality.crm.persistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public abstract class Repository<E extends Entity> {
    private final Path path;
    protected final Set<E> entities;
    private final Gson gson;
    private final Type type;

    protected Repository(String fileName, Type type) {
        this.path = Path.of("data", fileName);
        this.type = type;
        this.gson = new Gson();
        this.entities = loadAll();
    }


    private Set<E> loadAll() {
        try {
            fileNotFound();
            var json = Files.readString(path);
            return isValidJson(json) ? gson.fromJson(json, type) : new HashSet<>();
        } catch (IOException e) {
            return new HashSet<>();
        }
    }

    private boolean isValidJson(String input) {
        try (JsonReader reader = new JsonReader(new StringReader(input))) {
            reader.skipValue();
            return reader.peek() == JsonToken.END_DOCUMENT;
        } catch (IOException e) {
            return false;
        }
    }


    private void fileNotFound() throws IOException {
        if (Files.notExists(Path.of("data/"))) {
            Files.createDirectory(Paths.get("data/"));
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }
    }

    public void add(E ent) {
        if (this.entities.stream().anyMatch(e -> e.getId().equals(ent.getId()))) {
            return;
        }
        entities.add(ent);
    }

    public boolean remove(E ent) {
        return entities.remove(ent);
    }

    public Set<E> findAll() {
        return entities;
    }

    public void save() {
        try (Writer writer = new FileWriter(path.toFile())) {
            gson.toJson(entities, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

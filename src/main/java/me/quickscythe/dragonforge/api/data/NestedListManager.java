package me.quickscythe.dragonforge.api.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedListManager<T extends TypeAdapter<E>, E extends Enum<E>> {

    private final List<T> objects = new ArrayList<>();

    @SafeVarargs
    public NestedListManager(T... objects) {
        this.objects.addAll(Arrays.asList(objects));
    }

    public NestedListManager<T, E> ofType(E type) {
        return new NestedListManager<>(objects.stream().filter(object -> object.type().equals(type)).toArray(TypeAdapter[]::new));
//        return new ObjectManager(objects.stream().filter(object -> object.type().equals(type)).toArray(T[]::new));
    }

    public T get(E type) {
        return objects.stream().filter(object -> object.type().equals(type)).findFirst().orElse(null);
    }

    public List<T> all(){
        return objects;
    }

    public T random() {
        return objects.get((int) (Math.random() * objects.size()));
    }

}

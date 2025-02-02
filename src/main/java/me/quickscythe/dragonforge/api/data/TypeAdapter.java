package me.quickscythe.dragonforge.api.data;

public class TypeAdapter<E extends Enum<E>> {
    private final E enumValue;

    public TypeAdapter(E enumValue) {
        this.enumValue = enumValue;
    }

    public E type() {
        return enumValue;

    }
}

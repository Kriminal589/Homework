package models;

import org.jetbrains.annotations.NotNull;

public record Product(int id, @NotNull String name, int code) {}

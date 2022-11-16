package controller.DTO;

import org.jetbrains.annotations.NotNull;

public class AverageCostDTO {
    private final @NotNull String name;
    private final Integer code;
    private final Float averageCost;

    public AverageCostDTO(@NotNull String name, Integer code, Float averageCost) {
        this.name = name;
        this.code = code;
        this.averageCost = averageCost;
    }
}

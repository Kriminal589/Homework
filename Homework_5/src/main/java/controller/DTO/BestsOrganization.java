package controller.DTO;

import models.Organization;
import org.jetbrains.annotations.NotNull;

public final class BestsOrganization {
    public final @NotNull Organization organization;
    public final Integer count;

    public BestsOrganization(@NotNull Organization organization, Integer count) {
        this.organization = organization;
        this.count = count;
    }
}

package controller.DTO;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class ProductStatistics {
    private final @NotNull String name;
    private final @NotNull Date date;
    private Integer count;
    private final Integer code;

    private String result = "За данный период было продано товара: ";
    private Integer cost;

    public ProductStatistics(@NotNull String name, @NotNull Date date, Integer count, Integer code, Integer cost) {
        this.name = name;
        this.date = date;
        this.count = count;
        this.code = code;
        this.cost = cost;
    }

    public void setResult() {
        result += count;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public void setCount(Integer count) {
        this.count += count;
    }

    public void setCost(Integer cost) {
        this.cost += cost;
    }

    public Integer getCost() {
        return cost;
    }
}

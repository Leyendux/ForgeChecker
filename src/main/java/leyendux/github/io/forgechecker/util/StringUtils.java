package leyendux.github.io.forgechecker.util;

public enum StringUtils {
    //Easy method to get constant values, values that are not going to change in the whole code
    PERMISSION("uhc.mod"),
    PREFIX("§5§lVenomUHC §8» "),
    CHANNEL_NAME("FML|HS");

    private String name;

    StringUtils(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

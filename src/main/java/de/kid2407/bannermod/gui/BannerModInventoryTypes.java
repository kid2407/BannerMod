package de.kid2407.bannermod.gui;

/**
 * User: Tobias Franz
 * Date: 01.05.2020
 * Time: 10:13
 */
public enum BannerModInventoryTypes {
    DEFAULT("default"),
    TEXT_COLOR("textColor"),
    BASE_COLOR("baseColor");

    private final String name;

    BannerModInventoryTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

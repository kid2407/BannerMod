package de.kid2407.bannermod.util;

import de.kid2407.bannermod.BannerMod;

/**
 * User: Tobias Franz
 * Date: 28.04.2020
 * Time: 22:31
 */
public enum CONFIG_TYPES {
    CHARACTERS(BannerMod.charactersFilename),
    SPECIAL(BannerMod.specialsFilename);

    private final String filename;

    CONFIG_TYPES(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
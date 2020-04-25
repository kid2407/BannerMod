# Banner mod

A simple mod making it easier to create your own banners.

## Installation

Simply drop the jar from the [releases page](https://github.com/kid2407/BannerMod/releases) into your mods folder to start using the mod.
If you want to run this on a server, you only need to install the mod there, people who join your server don't need to install the mod themselves.

## Configuration

You can choose the language the mod runs in by setting the appropiate language in bannermod.cfg. Currently, the following languages are supported:
* English (en_us)
* German (de_de)

If you want to add support for another language, take a look at TranslationHelper#init() to see how translations are added and create a pull request.

### Permission

This mod uses one permission, `bannermod.use`. This is granted to OPs by default and required to run the `/banner` command and all of its subcommands.

### Usage

There are three sub-commands for this mod, two of which will give you banners:
```
/banner help [subcommand] // Displays a help text
/banner word <word or character> [textColor] [baseColor] // Gives you banners based on the word or character you provided
/banner special <name> [textColor] [baseColor] // Gives you a special banner as configured in special.json, see 
```

#### Single character

```
/banner word A
```
This will give you a banner for the letter **A** with the default colors, white for the banner and black for the text on it.

#### Whole word

```
/banner word Wallmart
```
This will give you banners in the default colors for the characters **W**, **A**, **L**, **M**, **R** and **T**.

#### Different colors

```
/banner word Wallmart 14 15
```
This will give you the same banners as with the last command, except now the base color is black and the text color is red. For a list of color codes, see the relevant entry in the [Minecraft wiki](https://minecraft.gamepedia.com/Banner#Pattern_color).

### Special

```
/banner special <name> [textColor] [baseColor]
```

This will give you special preconfigured Banners, which are defined in the special.json, which you can edit yourself, see the characters.json file for reference about the JSON structure.
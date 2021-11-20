package world;

import java.awt.Color;

public class Item extends Thing{
    public Item(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        super(world, glyph, color, maxHP, attack, defense, visionRadius);
    }

}
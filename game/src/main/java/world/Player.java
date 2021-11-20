package world;

import java.awt.Color;

public class Player extends Creature {

    public Player(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        super(world, glyph, color, maxHP, attack, defense, visionRadius);
    }
    @Override
    public void moveBy(int mx, int my) {
        super.moveBy(mx, my);
        //
        //super
        //Creature other = world.creature(x + mx, y + my);

        //if (other == null) {
        //    ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        //} else {
        //    attack(other);
        //}

    }
    public void fire(){
    
    }
}
package world;

import java.awt.Color;

import screen.PlayScreen;

public class Player extends Creature {
    public Player(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius) {
        super(world, glyph, color, maxHP, attack, defense, visionRadius);
        
    }
    @Override
    public void moveBy(int mx, int my,boolean isAttack) {
        super.moveBy(mx, my,isAttack);
        //
        //super
        //Creature other = world.creature(x + mx, y + my);

        //if (other == null) {
        //    ai.onEnter(x + mx, y + my, world.tile(x + mx, y + my));
        //} else {
        //    attack(other);
        //}
    }
    @Override
    public void update() {
        if(hp() <= 0){
            ai.onNotify("输！");
        }
    }
}
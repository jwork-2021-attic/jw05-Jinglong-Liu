package world;

import java.awt.Color;

import asciiPanel.AsciiPanel;

public class Wall extends Item {

    public Wall(World world,int x,int y) {
        //super(world, glyph, color, maxHP, attack, defense, visionRadius);
        // TODO Auto-generated constructor stub
        super(world,(char)177,AsciiPanel.brightBlack,1,0,0,0);
        setX(x);
        setY(y);
    }
    //WALL((char) 177, AsciiPanel.brightBlack),
    @Override
    public void modifyHP(int amount) {
        // TODO Auto-generated method stub
        super.modifyHP(amount);
        //this.hp += amount;

        //if (this.hp < 1) {
        //    world.remove(this);
        //}
        if(this.hp() < 1){
            world.setFloor(this.x(), this.y());
        }
    }
}
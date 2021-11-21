package world;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import asciiPanel.AsciiPanel;

public class Bullet extends world.Item{
    private Creature order;
    public Creature getOrder() {
        return order;
    }
    public Bullet(World world, Creature order) {
        //(World world, char glyph, Color color, int maxHP, int attack, int defense, int visionRadius)
        super(world, (char)250, AsciiPanel.brightWhite,1,order.attackValue(),0,1);
        this.order = order;
        setX(order.x());
        setY(order.y());
        setDirection(order.getDirection());
    }
    @Override
    public void attack(Thing other) {
        // 
        if(other == this.order){
            this.die();
        }
        else if(other instanceof Bullet && ((Bullet)other).getOrder() == getOrder()){
            this.die();
        }
        else if(other instanceof Enemy && this.order instanceof Enemy){
            this.die();
        }
        else{
            int damage = Math.max(0, this.attackValue() - other.defenseValue());
            if(other instanceof Player){
                String str = String.format("The '%s' attacks you for %d damage.", glyph, damage);
                world.nodifyMessage(str);
            }
            else if(other instanceof Enemy){
                String str = String.format("The '%s' attacks enemy for %d damage.", glyph, damage);
                world.nodifyMessage(str);
            }
            super.attack(other);
            this.die();
        }
    }
    @Override
    public void update() {
       // step();
    }
    @Override
    public void dig(int wx, int wy) {
        // TODO Auto-generated method stub
        super.dig(wx, wy);
        this.die();
    }
}
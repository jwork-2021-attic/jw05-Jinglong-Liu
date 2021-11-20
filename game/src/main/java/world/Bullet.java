package world;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import asciiPanel.AsciiPanel;

public class Bullet extends world.Item implements Runnable{
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
        else{
            super.attack(other);
            this.die();
        }
    }
    @Override
    public void run() {
        while(this.hp() > 0){
            try{
                step();

                //System.out.println(order.x() + " " + order.y()+ " "+x() + " " + y());
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void update() {
        //step();
    }
    @Override
    public void dig(int wx, int wy) {
        // TODO Auto-generated method stub
        super.dig(wx, wy);
        this.die();
    }
}
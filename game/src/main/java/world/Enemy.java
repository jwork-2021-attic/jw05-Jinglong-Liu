package world;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Enemy extends Creature{
    public static int enemyNum = 2;
    private CreatureFactory factory;
    public Enemy(World world, char glyph, Color color, int maxHP, int attack, int defense,CreatureFactory factory) {
        //super(world, glyph, color, maxHP, attack, defense, visionRadius);
        super(world, glyph, color, maxHP, attack, defense, 0);
        this.factory = factory;
        // TODO Auto-generated constructor stub
    }
    @Override
    public void run() {
        while(hp() > 0){
            try{
                TimeUnit.MILLISECONDS.sleep(400);
                randomStep();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    void randomStep(){
        Random rand = new Random();
        int turn = rand.nextInt(6);
        if(turn == 0){
            randomTurn();
        }
        else if(turn <2){
            //fire(factory.newBullet(this));
            factory.newBullet(this);
        }
        else{
            this.stepAndAttack(false);
            //System.out.println(x() + " " +  y());
        }
    }
    @Override
    public void modifyHP(int amount) {
        // TODO Auto-generated method stub
        super.modifyHP(amount);
        if(hp() <= 0){
            Enemy.enemyNum--;
            //System.out.println(Enemy.enemyNum);
            if(Enemy.enemyNum == 0){
                notify("赢！");
                world.acceptWin();
            }           
        }
    }
    @Override
    public void addAtEmptyLocation(World world) {
        // TODO Auto-generated method stub
        int x;
        int y;

        do {
            x = (int) (Math.random() * world.width());
            y = (int) (Math.random() * world.height()/3);
        } while (!tile(x, y).isGround() || world.creature(x, y) != null);

        setX(x);
        setY(y);
        world.add(this);
    }
}
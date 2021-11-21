package world;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Enemy extends Creature{
    public static int enemyNum = 8;
    private CreatureFactory factory;
    public Enemy(World world, char glyph, Color color, int maxHP, int attack, int defense,CreatureFactory factory) {
        //super(world, glyph, color, maxHP, attack, defense, visionRadius);
        super(world, glyph, color, maxHP, attack, defense, 0);
        this.factory = factory;
        // TODO Auto-generated constructor stub
        this.setDirection(Direction.DOWN);
    }
    @Override
    public void run() {
        while(hp() > 0 && world.isPlay()){
            try{
                TimeUnit.MILLISECONDS.sleep(400);
                randomStep();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void randomStep(){
        Random rand = new Random();
        int turn = rand.nextInt(6);
        if(turn == 0){
            randomTurn();
        }
        
        else{
            this.stepAndAttack(false);
        }
        
        if(turn < 4){
            //fire(factory.newBullet(this));
            factory.newBullet(this);
        }
    }
    public void step(){
        Random rand = new Random();
        int r1 = rand.nextInt(100);
        if(r1<=50){
            factory.newBullet(this);
        }else if(Math.abs(x() - world.getMx()) <= 1){
            turn(Direction.DOWN);
            super.step();
        }
        else if(y() == world.height() - 1){
            if(x() < world.getMx() - 1){
                turn(Direction.RIGHT);
            }
            else if(x() > world.getMy() + 1){
                turn(Direction.LEFT);
            }
        }
        else if(r1 < 60){

            if(x() < world.getMx() - 1){
                turn(Direction.RIGHT);
                super.step();
            }
            else if(x() > world.getMy() + 1){
                turn(Direction.LEFT);
                super.step();
            }
            else{
                super.step();
            }
        }
        else if(r1 < 80){
            turn(Direction.DOWN);
            super.step();
        }
        else{
            super.step();
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
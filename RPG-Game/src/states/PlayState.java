
package states;

import entity.EvilMage;
import entity.Warrior;
import graphics.Font;
import graphics.Sprite;
import util.KeyHandler;
import util.Vector2f;
import java.awt.Graphics2D;

import util.StopWatch;


public class PlayState extends GameState{
    
    
    private String text1, text2;
    private Warrior warrior;
    private EvilMage eMage;
    
    
    public static boolean playerTurn = true;
    public static boolean enemyTurn = false;
    
    public PlayState(GameStateManager gsm) {
        super(gsm);
        warrior = new Warrior(new Sprite("resources/sprites/warrior/Idle.png"), new Vector2f(10,100), 256);
        warrior.setLV(30);
        eMage = new EvilMage(new Sprite("resources/sprites/inimigos/evilmage/Idle.png"), new Vector2f(780,100), 256);
        eMage.setLV(1);
    }
    
    @Override
    public void update() {
        warrior.update();
        eMage.update();
        stateChange();
        if(warrior.getCalculateDamage() == true) {
            warrior.calculateDamage(eMage, 5);
            eMage.receiveDamage();
        }
        if(eMage.getCalculateDamage() == true) {
            eMage.calculateDamage(warrior, 5);
            warrior.receiveDamage();
        }
    }

    @Override
    public void input(KeyHandler key) {
        warrior.input(key);
    }
    
    @Override
    public void render(Graphics2D g) {
        if(playerTurn) {
            text1 = "Seu Turno";
            text2 = "Aperte (Z) para Atacar";
        }
        else {
            text1 = " ";
            text2 = " ";
        }
            Font.drawArray(g, blackFont, text1, new Vector2f(50, 690), 32, 32, 32, 0);
            Font.drawArray(g, blackFont, text2, new Vector2f(50, 730), 32, 32, 28, 0);
        
        //Apenas troca a ordem de render para as animações aparecerem de maneira correta. (Um sobre o outro)
        if(playerTurn) {
            eMage.render(g);
            warrior.render(g);
        }
        else {
            warrior.render(g);
            eMage.render(g);
        }
        
    }
    
    @Override
    public void stateChange() {
        if(warrior.getIsDead()) {
            getGSM().changeState(GameStateManager.GAMEOVER);
        }
        else if(eMage.getIsDead()) {
            getGSM().changeState(GameStateManager.VICTORY);
        }
        else
            return;
    }
    
    public static Font getFont() {
        return whiteFont;
    }
    
    public static Font getFont2() {
        return blackFont;
    }
    
    public static void changeTurn() throws InterruptedException {
        if(playerTurn) {
            StopWatch.elapseTime(5);
            playerTurn = false;
            enemyTurn = true;
        }
        else if(enemyTurn) {
            StopWatch.elapseTime(5);
            playerTurn = true;
            enemyTurn = false;
        }
    }
}

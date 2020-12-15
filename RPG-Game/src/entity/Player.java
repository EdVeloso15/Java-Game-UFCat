
package entity;

import graphics.Font;
import graphics.Sprite;
import java.awt.Graphics2D;
import states.PlayState;
import util.KeyHandler;
import util.StopWatch;
import util.Vector2f;


public abstract class Player extends Entity {
    
    /* Atributos Pai
    protected Animation ani;
    protected HashMap<String, Sprite> hpAnim;
    protected Sprite sprite;
    protected Vector2f pos;
    protected float initPosX;
    
    //Vida Máxima da Entidade
    protected float entityMHP;
    //Vida Atual da Entidade
    protected float hp;
    //Booleano para determinar se a entidade morreu ou não
    protected boolean isDead = false;
    //Booleano para determinar se deve haver o cálculo de dano ou não
    protected boolean calculateDamage = false;
    */
    
    //Estado de Iniciar o ataque
    private boolean initAttack = false;
    
    private int expLV = 100;
    private int exp = 0;
    
    public Player(Sprite sprite, Vector2f origin, int size) {
        super(sprite, origin, size);
    }
    
    public void addExp(int value) {
        exp += value;
        if(exp >= expLV) {
            entityLV++;
            exp = 0;
            expLV += (entityLV * expLV) - (entityLV*90);
            entityMHP += 10;
        }
    }
    
    public int getPlayerLV() { return entityLV; }
    
    @Override
    public void animate() {
        if(receiveDamage == false && !isDead){
            //Estado de Corrida        
            if(movement == 0 && attacking == false) {
                if(initAttack && PlayState.playerTurn) {
                    //Distância pecorrida na tela
                    movement = 675;
                    attacking = true;
                    if(ani.getNumFrames() != 8) {
                        ani.setNumFrames(8);
                        if(ani.getFrame()>=8) {
                            ani.setFrame(0);
                        }
                    }
                    ani.resetTimesPlayed();
                    ani.setAnimation(hSprite.get("run").getSpriteArray(0), 10);
                    flag1=true;
                }
            }
            if(attacking && movement==0 && flag1) {
                if(ani.getNumFrames() != 4) {
                    ani.setNumFrames(4);
                }
                if(ani.getFrame()>= 4) {
                        ani.setFrame(0);
                }
                ani.setAnimation(hSprite.get("attack").getSpriteArray(0), 10);

                //Iniciar animação de ataque no frame 0
                if(!flag2){
                 ani.setFrame(0);
                 flag2 = true;
                }
                if(ani.getFrame()==3 && flag2) {
                    //System.out.println("Animação finalizada");        Debug
                    attacking = false;
                    flag2=false;
                    flag1=false;
                    StopWatch.elapseTime(200);
                }
            }
            if(movement == 0 && attacking == false) {
                if(initPosX < pos.x) {
                    pos.x = initPosX;
                    //Se quisermos ligar o moonwalk
                    /*movement = (-700);
                    if(ani.getNumFrames() != 8)
                        ani.setNumFrames(8);
                    if(ani.getFrame()>=8)
                        ani.setFrame(0);
                    ani.setAnimation(run.getSpriteArray(0), 10); */
                    try{
                        calculateDamage = true;
                        PlayState.changeTurn();
                        //System.out.println("Trocou de turno");            //Debug
                    }
                    catch(InterruptedException e){
                        //System.out.println("Erro ao trocar de turno");    //Debug
                    }
                }
                else {   
                if(ani.getNumFrames() != 8)
                    ani.setNumFrames(8);
                if(ani.getFrame()>=8)
                    ani.setFrame(0);
                ani.setAnimation(hSprite.get("idle").getSpriteArray(0), 10);
                }
            }
        }
        else if(!isDead && receiveDamage == true){
        if(ani.getNumFrames() != 4) {
                ani.setNumFrames(4);
                if(ani.getFrame()>=4) {
                    ani.setFrame(0);
                }
            }
            ani.setAnimation(hSprite.get("hit").getSpriteArray(0), 10);
            movement = 0;
            if(!flag3){
                ani.setFrame(0);
                flag3 = true;
            }
            if(ani.getFrame()==3 && flag3) {
                receiveDamage = false;
                flag3 = false;
                attacking = false;
            }
        }
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void input(KeyHandler key) {
        if(PlayState.playerTurn) {
            if(key.confirm.down) {
            initAttack = true;
            } else {
            initAttack = false;
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if(movement > 0) {
            pos.x += speed;
            movement-= speed;
            if(movement < 0)
                movement = 0;
        }
        if(movement < 0) {
            pos.x -= speed;
            movement += speed;
        }
        Font.drawArray(g, PlayState.getFont(), "LV: " + entityLV, new Vector2f(initPosX, pos.y-50), 32, 32, 24, 0);
        g.drawImage(ani.getImage(), (int) (pos.x), (int) (pos.y), size, size, null);
    }    
}

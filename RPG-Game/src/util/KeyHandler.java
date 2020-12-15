package util;

import game.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener{

    //Lista de Teclas do Teclado usadas no Jogo
    public static List<Key> keys = new ArrayList<Key>();
    
    public class Key {
        public int presses, absorbs;
        public boolean down, clicked;
        
        public Key() {
            keys.add(this);
        }
        
        public void toggle(boolean pressed) {
            if(pressed != down) {
                down = pressed;
            }
            if(pressed) {
                presses++;
            }
        }
        
        public void tick() {
            if(absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }
    
    public Key up = new Key();
    public Key down = new Key();
    public Key right = new Key();
    public Key left = new Key();
    public Key confirm = new Key();
    public Key cancel = new Key();
    public Key menu = new Key();
    public Key pause = new Key();
    
    private boolean fired = true;
    
    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);
    }
    
    public void releaseAll() {
        int i = 0;
        for(Key key: keys) {
            keys.get(i).down = false;
            i++;
        }
    }
    
    public void tick() {
        int i = 0;
        for(Key key: keys) {
            keys.get(i).tick();
            i++;
        }
    }
    
    public void toggle(KeyEvent e, boolean pressed) {
        if(e.getKeyCode() == KeyEvent.VK_UP) up.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_DOWN) down.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_Z) confirm.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_X) cancel.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) pause.toggle(pressed);
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e, false);
    }
    
}

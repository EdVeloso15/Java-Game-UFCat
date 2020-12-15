package states;

import game.GamePanel;
import util.KeyHandler;
import util.Vector2f;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameStateManager implements State {

    public ArrayList<GameState> states;
    
    public static final int PLAY = 0;
    public static final int MENU = 1;
    public static final int PAUSE = 2;
    public static final int GAMEOVER = 3;
    public static final int VICTORY = 4;
    
    private PlayState ps;
    private GameOverState gso;
    private VictoryState vs;
    
    public GameStateManager() {
        states = new ArrayList<GameState>();
        ps = new PlayState(this);
        vs = new VictoryState(this);
        gso = new GameOverState(this);
        states.add(ps);
    }
    
    public void changeState(int state) {
        switch(state) {
            case PLAY:
                //Adiciona o estado e o coloca como atual
                int i = states.indexOf(ps);
                states.add(0, null);
                ps = (PlayState)states.get(i);
                states.set(0, ps);
                states.remove(i);
            break;
            /*
            case MENU:
                states.add(new MenuState(this));
                break;
            case PAUSE:
                states.add(new PauseState(this));
                break;
            */
            case GAMEOVER:
                if(!states.contains(gso))
                    states.add(0, gso);
                else {
                    i = states.indexOf(gso);
                    states.add(0, null);
                    gso = (GameOverState)states.get(i);
                    states.set(0, gso);
                    states.remove(i);
                }
                break;
            case VICTORY:
                if(!states.contains(vs))
                    states.add(0, vs);
                else {
                    System.out.println("\n\nTESTE");
                    i = states.indexOf(vs);
                    states.add(0, null);
                    vs = (VictoryState)states.get(i);
                    states.set(0, vs);
                    states.remove(i);
                }
                break;
        }
    }
    
    
    @Override
    public void update() {
        if(states.get(0) != null)
            states.get(0).update();
        System.out.println("State: " + states.get(0));
    }
    
    @Override
    public void input(KeyHandler key) {
        if(states.get(0) != null)
            states.get(0).input(key);
    }
    
    @Override
    public void render(Graphics2D g) {
        if(states.get(0) != null)
            states.get(0).render(g);
    }
}

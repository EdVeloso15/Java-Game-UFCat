
package states;

import graphics.Font;
import util.KeyHandler;
import java.awt.Graphics2D;
import util.Vector2f;
import static states.GameState.blackFont;


public class GameOverState extends GameState {

    public GameOverState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
        System.out.println("GAME OVER");
    }

    @Override
    public void input(KeyHandler key) {
        
    }

    @Override
    public void render(Graphics2D g) {
        Font.drawArray(g, blackFont, "GAME OVER", new Vector2f(50, 690), 32, 32, 32, 0);
    }

    @Override
    public void stateChange() {
        
    }    
}

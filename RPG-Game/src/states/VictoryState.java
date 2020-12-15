
package states;

import graphics.Font;
import util.KeyHandler;
import java.awt.Graphics2D;
import util.Vector2f;


public class VictoryState extends GameState {

    public VictoryState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void update() {
        System.out.println("VICTORY");
    }

    @Override
    public void input(KeyHandler key) {
        
    }

    @Override
    public void render(Graphics2D g) {
        Font.drawArray(g, whiteFont, "VICTORY", new Vector2f(50, 690), 32, 32, 32, 0);
    }

    @Override
    public void stateChange() {
        
    }

    
    
}

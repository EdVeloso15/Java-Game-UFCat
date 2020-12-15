package game;

import states.GameStateManager;
import util.KeyHandler;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import states.State;

public class GamePanel extends JPanel implements Runnable, State {

    //Tamanho do nosso JPanel. width: largura, height: altura.
    public static int width;
    public static int height;
    
    private Thread thread;
    private boolean running = false;
    
    private BufferedImage img;
    private Graphics2D g;
    
    private KeyHandler key;
    
    private GameStateManager gsm;
    
    public GamePanel(int width, int height) {
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
    }
    
    //Método que executa após a criação do nosso JPanel
    @Override
    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }
    
    public void init() {
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
        //Inicializa nosso input de teclado
        key = new KeyHandler(this);
        //Inicializa o controlador de estados de jogo
        gsm = new GameStateManager();
    }
    
    @Override
    public void run() {
        init();
        
        //Ticks de Sistema para a execução do código
        final double GAME_HERTZ = 60.0;
        //TBU = Time Before Update
        final double TBU = Math.pow(10, 9) / GAME_HERTZ;
        
        final int MUBR = 5; //Must Update before Render
        
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;
        
        final double TARGET_FPS = 60;
        final double TTBR = Math.pow(10, 9) / TARGET_FPS; //Total time Before render
        
        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / Math.pow(10, 9));
        int oldFrameCount = 0;
        
        while(running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                input(key);
                lastUpdateTime += TBU;
                updateCount++;
            }
            
            if(now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }
            
            input(key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;
            
            int thisSecond = (int) (lastUpdateTime / Math.pow(10, 9));
            if(thisSecond > lastSecondTime) {
                if(frameCount != oldFrameCount) {
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }
            
            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                try{
                    Thread.yield();
                }
                catch(Exception e){
                    System.out.println("Erro ao pausar Thread");
                }
                now = System.nanoTime();
            }
        }
    }
    
    @Override
    public void update() {
        gsm.update();
    }
    
    @Override
    public void input(KeyHandler key) {
        gsm.input(key);
    }

    @Override
    public void render(Graphics2D g) {
        throw new UnsupportedOperationException("Não suportado");
    }
    
    //Renderiza o nosso JFrame
    public void render() {
        if(g != null) {
            g.setColor(new Color(0, 100, 100));
            g.fillRect(0, 0, width, height);
            gsm.render(g);
        }
    }

    public void draw() {
        Graphics gTemp = (Graphics) this.getGraphics();
        gTemp.drawImage(img, 0, 0, width, height, null);
        gTemp.dispose();
    }

}

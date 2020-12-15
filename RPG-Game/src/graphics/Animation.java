
package graphics;

import java.awt.image.BufferedImage;


public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;
    private int numFrames;
    private int count = 0;
    private int delay;
    
    private int timesPlayed;
    
    public Animation(BufferedImage[] frames) {
        currentFrame = 0;
        this.frames = frames;
    }
    
    public Animation() {

    }
    
    private void setFrames(BufferedImage [] frames) {
        this.frames = frames;
    }
    
    public void setDelay(int i) { delay = i; }
    public void setFrame(int i) { currentFrame = i; }
    public void setNumFrames(int i) { 
        numFrames = i;
    }
    
    public void resetTimesPlayed() {
        timesPlayed = 0;
    }
    
    public void setAnimation(BufferedImage[] frames, int delay) {
        setFrames(frames);
        setDelay(delay);
    }
    
    public int getDelay() { return delay; }
    public int getFrame() { return currentFrame; } 
    public int getCount() { return count; }
    public int getNumFrames() { return numFrames; }
    public int getTimesPlayed() { return timesPlayed; }
    public BufferedImage[] getFrames() { return frames; }
    
    public BufferedImage getImage() { 
        try{
            return frames[currentFrame];
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: ArrayIndexOutOfBoundsException");
            System.out.println("Tentando novamente...");
            return getImage();
        }
    }
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
    public boolean hasPlayed(int i) { return timesPlayed == i;}
    
    public void update() {
        if(delay == -1) return;
        if(count == delay) {
            currentFrame++;
            count = 0;
        } 
        if(currentFrame > numFrames) {
            currentFrame = 0;
        }
        if(currentFrame == numFrames) {
            currentFrame = 0;
            timesPlayed++;
        }
        count++;
    }
    
}

package windows.basics;

import java.awt.event.KeyEvent;

public class Yogibear extends Sprite{
    private int dx;
    private int dy;

    public Yogibear(int x, int y) {
        super(x, y);
        initBear();
    }

    private void initBear() {
        loadImage("src/resources/yogi.png");
        getImageDimensions();
    }
    public void move() {
        this.x += this.dx;
        this.y += this.dy;

        if (this.x < 1) {
            this.x = 1;
        }
        if (this.y < 1) {
            this.y = 1;
        }
        if (this.y+this.height > 499){
            this.y = 499-this.height;
        }
        if (this.x+this.width > 699){
            this.x = 699-this.width;
        }
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            this.dx = -1;
        }
        if (key == KeyEvent.VK_D) {
            this.dx = 1;
        }
        if (key == KeyEvent.VK_W) {
            this.dy = -1;
        }
        if (key == KeyEvent.VK_S) {
            this.dy = 1;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            this.dx = 0;
        }
        if (key == KeyEvent.VK_D) {
            this.dx = 0;
        }
        if (key == KeyEvent.VK_W) {
            this.dy = 0;
        }
        if (key == KeyEvent.VK_S) {
            this.dy = 0;
        }
    }
}

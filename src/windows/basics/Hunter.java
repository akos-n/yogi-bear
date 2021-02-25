package windows.basics;

import java.awt.*;
import java.util.Random;

public class Hunter extends Sprite{
    private boolean inDirectionX;
    private boolean changeDirection=true;
    public Hunter(int x, int y) {
        super(x, y);
        initHunter();
    }
    private void initHunter() {
        loadImage("src/resources/hunter.png");
        getImageDimensions();
        Random rand = new Random();
        int tmp = rand.nextInt(2);
        if (tmp == 0) {
            this.inDirectionX = false;
        }
        else {
            this.inDirectionX = true;
        }
    }

    public boolean isChangeDirection() {
        return changeDirection;
    }

    public boolean isInDirectionX() {
        return inDirectionX;
    }

    public void setChangeDirection(boolean changeDirection) {
        this.changeDirection = changeDirection;
    }

    public void move(){
        if(this.inDirectionX) {
            if(this.changeDirection){
                this.x += 1;
                if(this.x+this.width == 700) this.changeDirection = false;
            }else{
                this.x -= 1;
                if(this.x==0) this.changeDirection = true;
            }
        }else{
            if(this.changeDirection){
                this.y += 1;
                if(this.y+this.height == 500) this.changeDirection = false;
            }else{
                this.y -= 1;
                if(this.y == 0) this.changeDirection = true;
            }
        }
    }
    
    public Rectangle getBoundsWithSight() {
        if(inDirectionX && changeDirection){return new Rectangle(this.x, this.y, this.width+40, this.height);}
        if(inDirectionX && !changeDirection) {return new Rectangle(this.x-40, this.y, this.width+40, this.height);}
        if(!inDirectionX && changeDirection) {return new Rectangle(this.x, this.y, this.width, this.height+40);}
        if(!inDirectionX && !changeDirection) {return new Rectangle(this.x, this.y-40, this.width, this.height+40);}
        return null;
    }
}


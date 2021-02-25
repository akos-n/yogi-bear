package windows.basics;

import java.awt.*;

public class TerrainBlock extends Sprite {
    public TerrainBlock(int x, int y) {
        super(x, y);
        initTerrainBlock();
    }
    private void initTerrainBlock(){
        loadImage("src/resources/terrain_block.png");
        getImageDimensions();
    }

    public Rectangle getBounds(String init) {
        if (init.equals("init")) return new Rectangle(this.x, this.y, this.width+45, this.height+45);
        return null;
    }
}

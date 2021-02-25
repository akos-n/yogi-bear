package windows.panels;

import windows.GameOverPopup;
import windows.basics.Basket;
import windows.basics.Hunter;
import windows.basics.TerrainBlock;
import windows.basics.Yogibear;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {

    private Timer timer;

    private int score = 0;
    private int level = 1;
    private int yogiLife = 3;
    private int hunterNumber = 1;
    private int terrainNumber = 1;
    private int basketNumber = 1;

    private Yogibear yogibear;
    private List<Basket> baskets;
    private List<Hunter> hunters;
    private List<TerrainBlock> terrains;

    private boolean ingame;

    private final int B_WIDTH = 700;
    private final int B_HEIGHT = 500;
    private final int YOGI_X = 0;
    private final int YOGI_Y = 225;
    private final int DELAY = 10;

    private final Rectangle startZone = new Rectangle(YOGI_X,YOGI_Y-15,60,63);

    //Clock
    private long tStart;
    private long tCurrent;

    public GamePanel() {
        initBoard();
    }

    private void initBoard(){
        addKeyListener(new TAdapter());
        setFocusable(true);
        setDoubleBuffered(true);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        yogibear = new Yogibear(YOGI_X, YOGI_Y);

        initTerrains();
        initBaskets();
        initHunters();

        tStart = System.currentTimeMillis();
        tCurrent = System.currentTimeMillis();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void reInitBoard(){
        if (terrainNumber < 20) terrainNumber += 2;
        if (hunterNumber < 15) hunterNumber +=1;
        basketNumber += 3;

        yogibear = new Yogibear(YOGI_X, YOGI_Y);
        initTerrains();
        initBaskets();
        initHunters();
        level++;
    }

    private void initBaskets(){
        baskets = new ArrayList<>();
        for (int i = 0; i < basketNumber; i++){
            Random random = new Random();
            boolean isDone = false;
            while (!isDone){
                Basket tmpBasket = new Basket(random.nextInt(B_WIDTH-62)+50,random.nextInt(B_HEIGHT-18));
                Rectangle rTmpBasket = tmpBasket.getBounds();
                boolean success = true;
                int j = 0;
                while (j < terrains.size() && success){
                    TerrainBlock terrainBlock = terrains.get(j);
                    Rectangle rTerrain = terrainBlock.getBounds();
                    if ((rTerrain.contains(rTmpBasket)) || (rTerrain.intersects(rTmpBasket))){
                        success = false;
                    }
                    j++;
                }
                if (success){
                    baskets.add(tmpBasket);
                    isDone = true;
                }
            }
        }
    }
    private void initHunters(){
        hunters = new ArrayList<>();
        for (int i = 0; i < hunterNumber; i++){
            Random random = new Random();
            boolean isDone = false;
            while (!isDone){
                Hunter tmpHunter = new Hunter(random.nextInt(B_WIDTH-90),random.nextInt(B_HEIGHT-33));
                Rectangle rTmpHunter = tmpHunter.getBounds();
                boolean success = true;
                int j = 0;
                while (j < terrains.size() && success){
                    TerrainBlock terrainBlock = terrains.get(j);
                    Rectangle rTerrain = terrainBlock.getBounds();
                    if ((rTerrain.contains(rTmpHunter)) || (rTerrain.intersects(rTmpHunter)) || startZone.contains(rTmpHunter) || startZone.intersects(rTmpHunter)){
                        success = false;
                    }
                    j++;
                }
                if (success){
                    hunters.add(tmpHunter);
                    isDone = true;
                }
            }

        }
    }
    private void initTerrains(){
        terrains = new ArrayList<>();
        for (int i = 0; i < terrainNumber; i++){
            Random random = new Random();
            if (i == 0) {
                terrains.add(new TerrainBlock(random.nextInt(B_WIDTH - 130)+40, random.nextInt(B_HEIGHT - 131)+40));
            }else{
                boolean isDone = false;
                boolean isPossible = true;
                int counterPossible = 0;
                while (!isDone && isPossible ){
                    TerrainBlock tmpTerrain = new TerrainBlock(random.nextInt(B_WIDTH - 130)+40, random.nextInt(B_HEIGHT - 131)+40);
                    Rectangle rTmpTerrain = tmpTerrain.getBounds("init");
                    boolean success = true;
                    int j = 0;
                    while (j < terrains.size() && success){
                        TerrainBlock terrainBlock = terrains.get(j);
                        Rectangle rTerrain = terrainBlock.getBounds("init");
                        if ((rTerrain.contains(rTmpTerrain)) || (rTerrain.intersects(rTmpTerrain)) || rTmpTerrain.contains(startZone) || rTmpTerrain.intersects(startZone)){
                            success = false;
                        }
                        j++;
                    }
                    if (success){
                        terrains.add(tmpTerrain);
                        isDone = true;
                    }
                    counterPossible++;
                    if(counterPossible > 500){
                        isPossible = false;
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ImageIcon tmp = new ImageIcon("src/resources/game_background.jpg");
        Image background = tmp.getImage();
        g.drawImage(background,0,0,null);
        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(startZone.x,startZone.y,startZone.width,startZone.height);
        if (yogibear.isVisible()) {
            g.drawImage(yogibear.getImage(), yogibear.getX(), yogibear.getY(), this);
        }

        for (Basket basket : baskets) {
            if (basket.isVisible()) {
                g.drawImage(basket.getImage(), basket.getX(), basket.getY(), this);
            }
        }

        for (Hunter hunter : hunters) {
            if (hunter.isVisible()) {
                g.drawImage(hunter.getImage(), hunter.getX(), hunter.getY(), this);
            }
        }

        for (TerrainBlock terrain : terrains) {
            if (terrain.isVisible()) {
                g.drawImage(terrain.getImage(), terrain.getX(), terrain.getY(), this);
            }
        }

        tCurrent = System.currentTimeMillis();



        g.setColor(Color.BLACK);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        g.drawString("Health: " + yogiLife, 15, 15);
        g.drawString("Collected: " + score, 15, 475);
        g.drawString("Level: " + level, 630, 15);
        g.drawString("Time: "+(int) ((tCurrent-tStart)/1000) ,630,475);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.GRAY);
        g.setFont(small);
        g.drawString(msg,(B_WIDTH-fm.stringWidth(msg))/2,B_HEIGHT/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inGame();

        updateYogi();
        updateBaskets();
        updateHunters();

        checkCollisions();

        repaint();
    }


    private void updateHunters() {
        for (Hunter hunter : hunters){
            hunter.move();
        }
    }

    private void updateBaskets() {
        if(baskets.isEmpty()){
            reInitBoard();
        }else{
            for (int i = 0; i < baskets.size(); i++){
                Basket b = baskets.get(i);
                if(!b.isVisible()){
                    baskets.remove(i);
                }
            }
        }
    }

    private void updateYogi() {
        if(yogibear.isVisible()){
            yogibear.move();
        }
    }

    private void checkCollisions() {
        Rectangle ryogi = yogibear.getBounds();
        for (int i = 0; i < baskets.size(); i++){
            Basket b = baskets.get(i);
            Rectangle rb = b.getBounds();
            if(ryogi.intersects(rb)){
                b.setVisible(false);

                this.score += 1;
            }
        }
        for(Hunter hunter : hunters){
            Rectangle rh = hunter.getBounds();
            Rectangle rh_sight = hunter.getBoundsWithSight();
            if(startZone.intersects(rh) || startZone.contains(rh) || rh.contains(startZone) || rh.intersects(startZone)){
                if (hunter.isChangeDirection() && hunter.isInDirectionX()){
                    hunter.setX(hunter.getX()-1);
                    hunter.setChangeDirection(false);
                }
                else if (!hunter.isChangeDirection() && hunter.isInDirectionX()){
                    hunter.setX(hunter.getX()+1);
                    hunter.setChangeDirection(true);
                }
                else if (hunter.isChangeDirection() && !hunter.isInDirectionX()){
                    hunter.setY(hunter.getY()-1);
                    hunter.setChangeDirection(false);
                }
                else if (!hunter.isChangeDirection() && !hunter.isInDirectionX()){
                    hunter.setY(hunter.getY()+1);
                    hunter.setChangeDirection(true);
                }
            }
            if (ryogi.intersects(rh_sight) && !startZone.contains(ryogi)){
                yogiLife -= 1;
                yogibear = new Yogibear(YOGI_X, YOGI_Y);
                if (yogiLife == 0){
                    ingame = false;
                    new GameOverPopup(score);
                    // <- HERE: POPUP + SAVE SCORE WITH NAME OR IN THE drawGameOver() METHOD!
                }
            }
            for(TerrainBlock terrain : terrains){
                Rectangle rt = terrain.getBounds();
                if(rh.intersects(rt)){
                    if (hunter.isChangeDirection() && hunter.isInDirectionX()){
                        hunter.setX(hunter.getX()-1);
                        hunter.setChangeDirection(false);
                    }
                    else if (!hunter.isChangeDirection() && hunter.isInDirectionX()){
                        hunter.setX(hunter.getX()+1);
                        hunter.setChangeDirection(true);
                    }
                    else if (hunter.isChangeDirection() && !hunter.isInDirectionX()){
                        hunter.setY(hunter.getY()-1);
                        hunter.setChangeDirection(false);
                    }
                    else if (!hunter.isChangeDirection() && !hunter.isInDirectionX()){
                        hunter.setY(hunter.getY()+1);
                        hunter.setChangeDirection(true);
                    }
                }
            }
        }
        for(TerrainBlock terrain : terrains){
            Rectangle rt = terrain.getBounds();
            if (ryogi.intersects(rt)){
                if (yogibear.getDx() > 0){
                    yogibear.setX(yogibear.getX()-2);
                }
                if (yogibear.getDx() < 0){
                    yogibear.setX(yogibear.getX()+2);
                }
                if (yogibear.getDy() > 0){
                    yogibear.setY(yogibear.getY()-2);
                }
                if (yogibear.getDy() < 0){
                    yogibear.setY(yogibear.getY()+2);
                }
            }
        }
    }


    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            yogibear.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            yogibear.keyPressed(e);
        }
    }
}

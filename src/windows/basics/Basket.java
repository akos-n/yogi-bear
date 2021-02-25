package windows.basics;

public class Basket extends Sprite {
    public Basket(int x, int y) {
        super(x, y);
        initBasket();
    }

    private void initBasket(){
        loadImage("src/resources/basket.png");
        getImageDimensions();
    }
}
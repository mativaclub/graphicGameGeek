package Sprite_Game.circles.view;

import Sprite_Game.circles.exceptions.BallsOverflowException;
import Sprite_Game.common.CanvasRepaintListener;
import Sprite_Game.common.Interactable;
import Sprite_Game.common.MainCanvas;
import Sprite_Game.circles.sprites.Background;
import Sprite_Game.circles.sprites.Ball;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MainWindow extends JFrame implements CanvasRepaintListener, Thread.UncaughtExceptionHandler {
    //first interface is for creating a canvas and graphic and the second one is for catching exceptions
    private static final int WIDTH = 800; //Sizes of canvas
    private static final int HEIGHT = 600;
    private static final String TITLE = "Circles"; //Name of canvas
    private static final int DEFAULT_COUNT_SPRITES = 10; //Sprite_Game.circles default qty
    private static final int MAX_COUNT_SPRITES = 15;//we can add or delete Sprite_Game.circles with clicks, but not more than 15
    private static final Random random = new Random();

    private Interactable[] sprites; //Array of Sprite_Game.circles with interactable interface who have 2 methods
    private int countSprites; //current qty of Sprite_Game.circles

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setTitle(TITLE);

        initSprites();
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);

        addMouseListener(new MouseListener(this));

        setVisible(true);
    }

    private void initSprites() { //method that creates starter array
        sprites = new Interactable[MAX_COUNT_SPRITES]; //creating array with max size possible to Sprite_Game.circles
        sprites[0] = new Background(); //On 0 position adding background
        countSprites = 1; // background is also sprite, that is why qty became 1
        for (int i = 1; i < DEFAULT_COUNT_SPRITES; i++) { //adding sprites on random places
            addSprite(random.nextInt(WIDTH), random.nextInt(HEIGHT));
        }
    }

    public void addSprite(int x, int y){
        if (countSprites >= MAX_COUNT_SPRITES){
            throw new BallsOverflowException();
        }
        sprites[countSprites++] = new Ball(x, y);//If current qty of sprites is not max we create and add new ball
    }

    public void removeSprite(){
        if (countSprites <= 1){ //we can't delete the background
            return;
        }
        countSprites--; //only reducing the qty, he is still present, but we do not show it
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime){
        update(canvas, deltaTime); //update the sprites
        render(canvas, g); //repaint
    }

    private void update(MainCanvas canvas, float deltaTime){
        for (int i = 0; i < countSprites; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }
    private void render(MainCanvas canvas, Graphics g){
        for (int i = 0; i < countSprites; i++) {
            sprites[i].render(canvas, g);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof BallsOverflowException){
            e.printStackTrace();
        }
    }
}
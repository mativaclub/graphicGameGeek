package Sprite_Game.common;

import javax.swing.*;
import java.awt.*;

public class MainCanvas extends JPanel {//Element that is responsible for game field
    private final CanvasRepaintListener controller; //Main window implements this interface - this is week connection
    //There is only 2 methods, update and render
    private long lastFrameTime; //save the time of last frame - our animation to be at the same speed on all devices - FPS

    public MainCanvas(CanvasRepaintListener controller){
        this.controller = controller;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) { //Game field
        super.paintComponent(g); //starter picture
        onDraw(g);
        sleep(16);
        repaint();
    }

    private void onDraw(Graphics g) {
        long curFrameTime = System.nanoTime(); //counting current time
        float deltaTime = (curFrameTime - lastFrameTime) * 0.000000001f; //???
        lastFrameTime = curFrameTime; //Change the frames, last frame is current frame
        controller.onDrawFrame(this, g, deltaTime); //update/render
    }

    private void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public int getLeft(){
        return 0;
    } //Methods for balls not to go out from frame

    public int getRight(){
        return getWidth() - 1;
    }

    public int getTop(){
        return 0;
    }

    public int getBottom(){
        return getHeight() - 1;
    }
}
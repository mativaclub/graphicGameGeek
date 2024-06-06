package Sprite_Game.circles.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter { //Put mocks on all methods - we can use only methods we need
    private MainWindow mainWindow;

    public MouseListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void mouseClicked(MouseEvent e) { //One button is for adding ball, another button is for deleting
        if (e.getButton() == MouseEvent.BUTTON1){
            mainWindow.removeSprite();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            mainWindow.addSprite(e.getX(), e.getY()); //Orientation, where was the click done, to add the ball there
        }
    }
}
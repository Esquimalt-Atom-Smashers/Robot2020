package frc.subsystem;

import edu.wpi.first.wpilibj.Joystick;

public class ButtonPanel
{
    //BUTTON PINS
    public static final int LEFT_TOP = 1;
    public static final int LEFT_MIDDLE = 5;
    public static final int LEFT_BOTTOM = 2;
    public static final int CENTER_LEFT = 4;
    public static final int CENTER_MIDDLE = 6;
    public static final int CENTER_RIGHT = 7;
    public static final int RIGHT_TOP = 9;
    public static final int RIGHT_MIDDLE = 8;
    public static final int RIGHT_BOTTOM = 3;

    private Joystick panel;
    //Creates an array of buttons, called 'buttons'
    private Button[] buttons;

    //ButtonPanel is a method which has two parameters, numberOfButtons and port
    public ButtonPanel(int numberOfButtons, int port)
    {
        /*Constructs a instance of the Joystick class and takes in the 'port' variable
        which is used to tell what usb port its plugged into */
        this.panel = new Joystick(port);
        this.buttons = new Button[numberOfButtons];

        for (int i = 0; i < numberOfButtons; i ++)
        {
            buttons[i] = new Button(i, this.panel);
        }
    }

    private class Button
    {
        private int button;

        public boolean down = false;
        public boolean pressed = false;
        public boolean released = false;
        public boolean wasDown = false;

        private Joystick panel;

        public Button(int button, Joystick panel)
        {
            this.button = button;
            this.panel = panel;
        }

        public void update() //update the current states of the buttons
        {
            down = panel.getRawButton(button + 1);
            pressed = released = false;
            if (down && !wasDown) pressed = true;
            if  (!down && wasDown) released = true;
            wasDown = down;
        }
    }

    public void update()
    {
        for (Button button : buttons)
        {
            button.update();
        }
    }

    public boolean wasPressed(int index)
    {

        return buttons[index - 1].pressed;
    }

    public boolean isDown(int index)
    {
        return buttons[index - 1].down;
    }

    public boolean wasReleased(int index)
    {
        return buttons[index - 1].released;
    }

}

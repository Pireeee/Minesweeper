package vue;

import javax.swing.*;

public class Button extends JButton {
    public int state = 0;
    int row,column;
    public Button(int i,int j){
        row = i;
        column= j;
    }
}

package vue;

import javax.swing.*;

public class HotBar extends JPanel {
    private JLabel textMineNumber;
    private JLabel textDisarmedMines;
    int flags;
    public HotBar(int mines){
        textMineNumber = new JLabel();
        textDisarmedMines = new JLabel();
        textMineNumber.setText("Mines = " + mines);
        update(mines);
        this.add(textMineNumber);
        this.add(textDisarmedMines);
    }

    public void update(int flags){
        textDisarmedMines.setText("| Flags left = "+flags);
    }
}

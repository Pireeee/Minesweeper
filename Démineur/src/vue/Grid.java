package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Grid extends JPanel {
    private Button[][] buttons;
    private int flags;
    public int win;
    public Grid(int rowCount, int columnCount,int mineNumber,HotBar hotBar) {
        this.flags = mineNumber;
        this.setLayout(new GridLayout(rowCount, columnCount));
        this.buttons = new Button[rowCount][columnCount];
        int i ,j;
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                buttons[i][j] = new Button(i,j);
                Button b = buttons[i][j];
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        discover(b,rowCount,columnCount);
                        System.out.println(win);

                        if (win == rowCount*columnCount-mineNumber){
                            JOptionPane.showMessageDialog(null,"You Win");
                        }
                    }
                });
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if(e.getButton()==3){
                                if (!b.isEnabled()) {
                                    return;
                                }
                                if (b.getText().equals("X")) {
                                    b.setBackground(null);
                                    b.setText("");
                                    hotBar.update(++flags);
                                } else {
                                    if (flags>0) {
                                    System.out.println("click");
                                    b.setText("X");
                                    b.setBackground(Color.pink);
                                    hotBar.update(--flags);}
                                }
                        }
                        super.mousePressed(e);
                    }
                });
                this.add(buttons[i][j]);

            }
        }

        int placedMines = 0;
        while (placedMines < mineNumber) {
            int RowNumber = (int)(Math.random() * rowCount);
            int ColumnNumber = (int)(Math.random() * columnCount);

            if (buttons[RowNumber][ColumnNumber].state != -1) {
                buttons[RowNumber][ColumnNumber].state = -1;
                placedMines++;
            }
        }

        whoIsThere(rowCount,columnCount);
    }

    public void discover(Button b,int rowCount,int columnCount){
        if(b!=null){
            if(b.isEnabled()){
                try{
                    if(b.state == -1){// Si on a une bombe
                        b.setBackground(Color.red);
                        for(int i=0; i<rowCount;i++ ){
                            for(int j = 0 ; j<columnCount; j++){
                                if (buttons[i][j].state == -1){
                                    buttons[i][j].setText("B");
                                }
                                buttons[i][j].setEnabled(false);

                           }
                        }
                        JOptionPane.showMessageDialog(null,"Game Over");
                        return;
                    }
                    else if(b.state == 0){ //si il n'y a rien
                        b.setEnabled(false);
                        win++;
                        try {
                            discover(buttons[b.row - 1][b.column - 1],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row - 1][b.column],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row - 1][b.column + 1],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row ][b.column - 1],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row ][b.column + 1],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row + 1][b.column - 1],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row + 1][b.column ],rowCount,columnCount);
                        }
                        catch (Exception e){}
                        try {
                            discover(buttons[b.row + 1][b.column + 1],rowCount,columnCount);
                        }
                        catch (Exception e){}

                    }
                    else{  //si il y a une bombe à coté
                        b.setEnabled(false);
                        b.setText(String.valueOf(b.state));
                        win++;
                    }
                }
                catch (Exception e){

                }
            }
        }
    }

    private void whoIsThere(int row,int column){
        int mineCount = 0;
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < column; j++)
            {
                if (this.buttons[i][j].state != - 1)
                {
                    try
                    {
                        if (buttons[i-1][j-1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i-1][j].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i-1][j+1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i][j-1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i][j+1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i+1][j-1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i+1][j].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    try
                    {
                        if (buttons[i+1][j+1].state == -1)
                        {
                            mineCount++;
                        }
                    }
                    catch (Exception e){}
                    buttons[i][j].state=mineCount;
                    mineCount = 0;
                }
            }
        }
    }

    public int getFlags() {
        return flags;
    }
}



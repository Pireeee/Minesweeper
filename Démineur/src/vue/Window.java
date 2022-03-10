package vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame{
    private JPanel Game;
    private Grid thegrid;
    private HotBar hotbar;
    public Window(int row,int column,int mines){
        this.setTitle("Minesweeper");
        this.setSize(700,500);
        this.setLayout(new BorderLayout());
        this.hotbar = new HotBar(mines);
        this.thegrid = new Grid(row,column,mines,hotbar);
        this.add(thegrid, BorderLayout.CENTER);
        MenuBar menuBar = new MenuBar();
        Menu size = new Menu("Size");
        MenuItem ten = new MenuItem("10 x 10");
        MenuItem twenty = new MenuItem("15 x 15");
        MenuItem fifty = new MenuItem("20 x 20");
        size.add(ten);
        size.add(twenty);
        size.add(fifty);
        menuBar.add(size);
        this.setMenuBar(menuBar);
        this.add(hotbar,BorderLayout.NORTH);
        ten.addActionListener(new ActionListener() { // Size selector
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ppour fermer la fenêtre en cours d'execution
                Window myWindow= new Window(10,10,10);
                myWindow.setVisible(true);
            }
        });
        twenty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ppour fermer la fenêtre en cours d'execution
                Window myWindow= new Window(15,15,40);
                myWindow.setVisible(true);
            }
        });
        fifty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // ppour fermer la fenêtre en cours d'execution
                Window myWindow= new Window(20,20,65);
                myWindow.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        Window mywindow = new Window(10,10,10);
        mywindow.setVisible(true);
    }
}

package bank;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class test {
    
    test()
    {
        JFrame frame = new JFrame("A Simple Swing App");
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel text = new JLabel("This is the label!");
        frame.add(text);
        frame.setVisible(true);
        
    }
    
    public static void main(String[] args)
    {
        new test();
    } 
    
    
}

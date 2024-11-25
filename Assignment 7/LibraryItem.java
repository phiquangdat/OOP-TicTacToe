import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;



public class LibraryItem{
    public LibraryItem(){
        JFrame frame = new JFrame();
        
        JButton button = new JButton("Click");
        
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        
        frame.add(panel, BorderLayout.CENTER); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
}
    public static void main(String[] args){
        
        
}
}
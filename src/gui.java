import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.*;

public class gui {
    public static void main(String[] args)
    {
        JLabel label = new JLabel();
        JFrame frame = new JFrame();//creates the frame
        ImageIcon Image = new ImageIcon("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.facebook.com%2Fnortheastcc%2F&psig=AOvVaw2IVpS5EBpcq_jY3EmQbNRt&ust=1634064102515000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJiftu6bw_MCFQAAAAAdAAAAABAD");//creates image

        frame.setTitle("Northeast Community College");// sets title to the frame
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//hides the window
        frame.setResizable(false);//doesn't change size
        frame.setSize(1200, 800);//makes the size of window
        frame.setVisible(true);//lets us see window
        frame.add(label);
        frame.setIconImage(Image.getImage());//change icon of frame

        label.setText("Welcome to Northeast Community College!");// adds text
        label.setIcon(Image);// puts logo on screen
        label.setHorizontalTextPosition(JLabel.CENTER);//horizontal position
        label.setVerticalTextPosition(JLabel.BOTTOM);//vertical position
        label.setFont(new Font("Ariel", Font.BOLD,20));//sets font, effect, size

    }
}
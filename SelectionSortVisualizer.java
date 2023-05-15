import java.awt.BorderLayout;
import javax.swing.JFrame;

public class SelectionSortVisualizer {
    public static void main(String[] args) {
        // creates a frame/window for the program
        JFrame frame = new JFrame();
        
        // sets resolution to 480 pixels wide and 680 pixels tall
        frame.setSize(480, 680);

        // makes window visible
        frame.setVisible(true);

        // terminates application when close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creates new instance of the SelectionSortComponent
        final SelectionSortComponent component = new SelectionSortComponent();

        // adds the instance, component, to the center of the window
        frame.add(component, BorderLayout.CENTER);

        // starts the sorting animation
        component.startAnimation();
    }
}
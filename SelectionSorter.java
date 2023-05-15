import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JComponent;

public class SelectionSorter {
    public SelectionSorter(int[] anArray, JComponent aComponent) {
        a = anArray;
        sortStateLock = new ReentrantLock();
        component = aComponent;
    }

    public void sort() throws InterruptedException {  
        for (int i = 0; i < a.length - 1; i++) {  
            int minPos = minimumPosition(i);
            sortStateLock.lock();
            try {
                swap(minPos, i);
                alreadySorted = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(2);
        }
    }

    private int minimumPosition(int from) throws InterruptedException {  
        int minPos = from;
        for (int i = from + 1; i < a.length; i++) {
            sortStateLock.lock();
            try {
                if (a[i] < a[minPos]) 
                    minPos = i;
                markedPosition = i;
            } finally {
                sortStateLock.unlock();
            }
            pause(2);
        }
        return minPos;
    }

    public void draw(Graphics2D g2) {
        sortStateLock.lock();
        try {
            int deltaX = component.getWidth() / a.length;
            for (int i = 0; i < a.length; i++) {
                if (i == markedPosition)
                    g2.setColor(Color.RED);
                else if (i <= alreadySorted)
                    g2.setColor(Color.BLUE);
                else
                    g2.setColor(Color.BLACK);
                g2.draw(new Line2D.Double(i * deltaX, 0, i * deltaX, a[i]));
            }
        } finally {
            sortStateLock.unlock();
        }
    }

    public void pause(int steps) throws InterruptedException {
        component.repaint();
        Thread.sleep(steps * DELAY);
    }

    // Swaps the values stored at indices i and j into array a
    private void swap(int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // array "a" contains the values that will be sorted
    private int[] a;

    // JComponent object "component" draws animation
    private JComponent component;

    // ensures that multiple threads don't try to access shared state variables simultaneously
    private Lock sortStateLock;

    // markedPosition keeps track of the currently marked position
    private int markedPosition = -1;

    // alreadySorted keeps track of the number of elements already sorted
    private int alreadySorted = -1;

    // DELAY is the amount of milliseconds in between each animation step
    private static final int DELAY = 500;

}

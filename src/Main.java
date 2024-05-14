import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        var frame = new JFrame();
        var icon = new ImageIcon("philosophers.png");
        var label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Semaphore[] forks = new Semaphore[5];

        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }


        Philosopher[] philosophers = new Philosopher[5];

        for (int i = 0; i <5; i++) {
            philosophers[i] = new Philosopher(Integer.toString(i), forks[i], forks[(i + 1) % 5]);
            philosophers[i].start();
        }

        for (int i = 0; i < 5; i++)
        {
            philosophers[i].join();
        }

        System.out.println("\nDinner FINISHED for all!");

    }
}

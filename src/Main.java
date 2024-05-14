import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.concurrent.Semaphore;


public class Main {
    public static void main(String[] args) {

        Semaphore[] forks = new Semaphore[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Semaphore(1);
        }
        Philosopher[] philosophers = makePhilosopherList(forks);

        var frame = new JFrame();
        var icon = new ImageIcon("philosophers.png");
        var label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        for (int i = 0; i <5; i++) {
            philosophers[i].start();
        }

        System.out.println("\nDinner FINISHED for all!");

    }

    static Philosopher[] makePhilosopherList(Semaphore[] _forks)
    {
        Philosopher[] philosophers = new Philosopher[_forks.length];
        for (int i = 0; i <5; i++) {
            philosophers[i] = new Philosopher(Integer.toString(i), 3, _forks[i], _forks[(i + 1) % 5]);
        }
        return philosophers;
    }
}

import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher extends Thread {
    private final String name;
    private final Semaphore leftFork;
    private final Semaphore rightFork;

    public Philosopher(String _name, Semaphore _leftFork, Semaphore _rightFork)
    {
        this.name = _name;
        this.leftFork = _leftFork;
        this.rightFork = _rightFork;
    }

    private void doSomething(String action)
    {
        try
        {
            int num = new Random().nextInt(10);
            System.out.println("Philosopher " + this.name + "  " + action +"s for "+ num +" seconds.\n");
            Thread.sleep(num*1000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted Exception thrown when " + action + "ing with Philosopher " + this.name + ".\n");
        }
    }

    private void takeLeftFork()
    {
        try {
            if (this.leftFork.availablePermits() == 0) {
                System.out.println("Philosopher " + this.name + "  WAITS  for   LEFT   fork.\n");
            }
            
            this.leftFork.acquire();
            System.out.println("Philosopher " + this.name + "  is  HOLDING   LEFT   fork.\n");
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted Exception thrown when picking up   LEFT   Fork with Philosopher " + this.name + ".\n");
        }
    }
    private void takeRightFork()
    {
        try {
            if (this.rightFork.availablePermits() == 0) {
                System.out.println("Philosopher " + this.name + "  WAITS  for   RIGHT   fork.\n");
            }

            this.rightFork.acquire();
            System.out.println("Philosopher " + this.name + "  is  HOLDING  right  fork.\n");
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupted Exception thrown when picking up Right Fork with Philosopher " + this.name + ".\n");
        }
    }
    private void putDownForks() {
        System.out.println("Philosopher " + this.name + "  PUTS DOWN   LEFT   fork.\n");
        this.leftFork.release();
        System.out.println("Philosopher " + this.name + "  PUTS DOWN   RIGHT   fork.\n");
        this.rightFork.release();
    }

    @Override
    public void run()
    {
        int numberOfMeals = 3;
        while (numberOfMeals >=0) {
            this.doSomething("THINK");
            this.takeLeftFork();
            this.takeRightFork();
            this.doSomething("EAT");
            this.putDownForks();
            numberOfMeals -=1;
        }
        System.out.println("Dinner FINISHED for Philosopher " + this.name + "!\n");
    }

}

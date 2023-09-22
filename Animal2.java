import java.util.concurrent.ThreadLocalRandom;

public class Animal2 implements Runnable {

   private Food food1;

    private String fname;

    private int posi;

    private int speed;

    private int rest_Max;

    private static boolean winner = false;

   public Animal2(Food food1) {

        this.food1= food1;

    }

   public String getfname() {

        return fname;

    }

   public void setfname(String fname) {

        this.fname = fname;

    }

   public int get_Posi() {

        return posi;

    }

   public void set_Posi(int posi) {

        this.posi = posi;

    }

   public int get_Speed() {

        return speed;

    }

   public void set_Speed(int speed) {

        this.speed = speed;

    }

   public int getrest_Max() {

        return rest_Max;

    }

   public void setrest_Max(int rest_Max) {

        this.rest_Max = rest_Max;

    }

   public static void main(String[] args) {

       Food food = new Food();

        Animal2 rabbit1 = new Animal2(food);

        rabbit1.setfname("Rabbit");

        rabbit1.set_Posi(0);

        rabbit1.setrest_Max(150);

        rabbit1.set_Speed(5);

        Animal2 turtle1 = new Animal2(food);

        turtle1.setfname("Turtle");

        turtle1.set_Posi(0);

        turtle1.setrest_Max(100);

        turtle1.set_Speed(3);

        Thread rabbitThread1 = new Thread(rabbit1);

        Thread turtleThread1 = new Thread(turtle1);

       rabbitThread1.start();

        turtleThread1.start();

    }

   @Override

    public void run() {

       while (posi <= 100 && !winner) {

            posi += speed;

            System.out.println(getfname()+" started eating.");

            food1.eatprocess(getrest_Max());

            System.out.println(getfname()+" stopped eating.");

            if (posi >= 100) {

                winner = true;

                          }

       }

   }

}


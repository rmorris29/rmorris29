import java.util.concurrent.ThreadLocalRandom;

public class Animal implements Runnable {

   private String animal_name;

    private int pos;

    private int speed_value;

    private int rest_Max;

    private static boolean winprocess = false;

   public String getanimal_name() {

        return animal_name;

    }

   public void setanimal_name(String animal_name) {

        this.animal_name = animal_name;

    }

   public int get_pos() {

        return pos;

    }

   public void set_pos(int pos) {

        this.pos = pos;

    }

   public int get_speed_value() {

        return speed_value;

    }

   public void setspeed_value(int speed_value) {

        this.speed_value = speed_value;

    }

   public int getrest_Max() {

        return rest_Max;

    }

   public void setrest_Max(int rest_Max) {

        this.rest_Max = rest_Max;

    }

   public static void main(String[] args) {

       Animal rabbit1 = new Animal();

        rabbit1.setanimal_name("Rabbit");

        rabbit1.set_pos(0);

        rabbit1.setrest_Max(150);

        rabbit1.setspeed_value(5);

        Animal turtle1 = new Animal();

        turtle1.setanimal_name("Turtle");

        turtle1.set_pos(0);

        turtle1.setrest_Max(100);

        turtle1.setspeed_value(3);

        Thread rabbit_Thread = new Thread(rabbit1);

        Thread turtle_Thread = new Thread(turtle1);

       rabbit_Thread.start();

        turtle_Thread.start();

    }

   @Override

    public void run() {

     

       while (pos <= 100 && !winprocess) {

            pos += speed_value;

            int rand_1 = ThreadLocalRandom.current().nextInt(0, getrest_Max());

            System.out.println("Running: " + getanimal_name() + "\tpos: " + get_pos());

            if (pos >= 100) {

                winprocess = true;

                System.out.println("win: " + getanimal_name());

            }

       }

   }

}
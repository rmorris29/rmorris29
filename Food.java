public class Food {

   synchronized void eatprocess(long sleep1) {

        try {

            Thread.sleep(sleep1);

        } catch (InterruptedException e) {

           e.printStackTrace();

        }

    }

}
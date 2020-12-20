
public class Fibonacci {
    public static void main(String[] args) {
        try {

            int x = 1;
            int y = 1;
            int sum = 1;
            for (int i = 0; i < 10; i++) {
                if (i >= 2) {
                    sum = x + y;
                    x = y;
                    y = sum;
                }
                System.out.print(sum + " ");

                Thread.sleep(1000);
            }
            for (int i = 10; i < 20; i++) {
                if (i >= 2) {
                    sum = x + y;
                    x = y;
                    y = sum;
                }
                System.out.print(sum + " ");
                Thread.sleep(1000 * 3);
            }
            for (int i = 20; i < 30; i++) {
                if (i >= 2) {
                    sum = x + y;
                    x = y;
                    y = sum;
                }
                System.out.print(sum + " ");
                Thread.sleep(1000 * 5);
            }

        } catch (Exception e) {
            System.out.println("Got an exception!");
        }
    }
}









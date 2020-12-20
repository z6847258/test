import java.util.*;
import org.apache.log4j.Logger;
public class Demo {
    private static Logger logger = Logger.getLogger(Demo.class);
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入n的值:");
        final int[] x = {input.nextInt()};
        if (x[0] > 100 || x[0] < 0) {
            System.out.println("请输入0到100之间的数");
        } else {
            while (x[0] < 100000) {
                x[0] = x[0] * 2;
                logger.debug("x="+x[0]);


            }
        }
        if (x[0] > 100000) {
            Thread thread = new Thread(new Runnable(){
                public void run() {
                    while (x[0] > 0){
                        x[0] = x[0] - 10000;
                        logger.debug("x="+x[0]);


                    }
                }
            });thread.start();
            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    while (x[0] > 0){
                        x[0] = x[0] - 5000;
                        logger.debug("x="+x[0]);


                    }
                }
            });thread2.start();
        }

    }


}







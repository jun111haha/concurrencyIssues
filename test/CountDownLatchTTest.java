import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTTest {
    @Test
    void CountDownLatchUnused() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatchT countDownLatchT = new CountDownLatchT();

        for(int i=1; i<=5; i++){
            executorService.execute(() -> {
                countDownLatchT.call();
            });
        }
        System.out.println("Main Thread");
    }

    @Test
    void CountDownLatchUse() throws InterruptedException{
        /*
        new CountDownLatch(5) : 몇개의 스레드가 끝나면 다음 스레드를 시작할지 정한다. 5개를 설정했다.
        countDownLatch.countDown() : 스레드가 끝날 때 마다 카운트를 감소한다.
        countDownLatch.await() : 카운트가 0이되면 대기가 풀리고 이후 스레드가 실행되게 된다.
        * */

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);

        CountDownLatchT countDownLatchT = new CountDownLatchT();

        for(int i=1; i<=5; i++){
            executorService.execute(()->{
                countDownLatchT.call();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("Main Thread");
    }

}
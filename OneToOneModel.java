import java.util.concurrent.CountDownLatch;


public class OneToOneModel {
    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10; // Número de threads (usuário = sistema)
        int iterations = 100_000; // Trabalho por thread


        System.out.printf("Simulando modelo 1:1 (N=%d)\n", numThreads);


        CountDownLatch latch = new CountDownLatch(numThreads);
        Thread[] threads = new Thread[numThreads];


        long start = System.nanoTime();
        for (int i = 0; i < numThreads; i++) {
        threads[i] = new Thread(new WorkloadRunnable(iterations, latch));
        threads[i].start();
        }


        latch.await();
        long end = System.nanoTime();


        long elapsedMs = (end - start) / 1_000_000;
        System.out.printf("Tempo total (1:1): %d ms\n", elapsedMs);
    }


    static class WorkloadRunnable implements Runnable {
        private final int iterations;
        private final CountDownLatch latch;


        WorkloadRunnable(int iterations, CountDownLatch latch) {
            this.iterations = iterations;
            this.latch = latch;
        }


        @Override
        public void run() {
            double result = 0;
            for (int i = 1; i <= iterations; i++) {
                result += Math.sin(i) * Math.sqrt(i);
                if ((i & 0x3FFF) == 0) Thread.yield();
            }
            latch.countDown();
        }
    }
}
import java.util.concurrent.*;


public class NMModel {
public static void main(String[] args) throws InterruptedException {
int numUserThreads = 1000; // Número de threads de usuário (N)
int poolSize = Runtime.getRuntime().availableProcessors(); // Número de threads do sistema (M)
int iterations = 100_000; // Trabalho por thread


System.out.printf("Simulando modelo N:M (N=%d, M=%d)\n", numUserThreads, poolSize);


ExecutorService pool = Executors.newFixedThreadPool(poolSize);
CountDownLatch latch = new CountDownLatch(numUserThreads);


long start = System.nanoTime();
for (int i = 0; i < numUserThreads; i++) {
pool.submit(new WorkloadRunnable(iterations, latch));
}


latch.await();
long end = System.nanoTime();
pool.shutdown();


long elapsedMs = (end - start) / 1_000_000;
System.out.printf("Tempo total (N:M): %d ms\n", elapsedMs);
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
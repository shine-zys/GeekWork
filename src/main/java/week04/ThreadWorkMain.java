package week04;

import java.util.concurrent.*;

public class ThreadWorkMain {

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

//        int result = sum(); //这是得到的返回值

        int result = method1();
//        int result = method2();
//        int result = method3();
//        int result = method4();
//        int result = method5();
//        int result = method6();
//        int result = method7();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    // Callable + ThreadPool + Future
    private static int method1() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future<Integer> future = executorService.submit(ThreadWorkMain::sum);
        Integer result = future.get();
        executorService.shutdown();
        return result;
    }

    // Callable + ThreadPool + FutureTask
    private static int method2() throws Exception {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        FutureTask<Integer> futureTask = new FutureTask<>(ThreadWorkMain::sum);
        executorService.submit(futureTask);
        Integer result = futureTask.get();
        executorService.shutdown();
        return result;
    }

    // Runnable + ThreadPool + Future
    private static int method3() throws Exception {
        final Integer[] result = new Integer[1];
        Runnable runnable = () -> result[0] = sum();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future<Integer[]> future = executorService.submit(runnable, result);
        Integer value = future.get()[0];
        executorService.shutdown();
        return value;
    }

    // Runnable + ThreadPool + FutureTask
    private static int method4() throws Exception {
        final Integer[] result = new Integer[1];
        Runnable runnable = () -> result[0] = sum();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        FutureTask<Integer[]> futureTask = new FutureTask<>(runnable, result);
        executorService.submit(futureTask);
        Integer value = futureTask.get()[0];
        executorService.shutdown();
        return value;
    }

    // Callable + Thread + FutureTask
    private static int method5() throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(ThreadWorkMain::sum);
        Thread thread = new Thread(futureTask);
        thread.start();
        return futureTask.get();
    }

    // Runnable + Thread + FutureTask
    private static int method6() throws Exception {
        final Integer[] result = new Integer[1];
        Runnable runnable = () -> result[0] = sum();
        FutureTask<Integer[]> futureTask = new FutureTask<>(runnable, result);
        Thread thread = new Thread(futureTask);
        thread.start();
        return futureTask.get()[0];
    }

    // CompletableFuture
    private static int method7() throws Exception {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(ThreadWorkMain::sum);
        return completableFuture.get();
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}

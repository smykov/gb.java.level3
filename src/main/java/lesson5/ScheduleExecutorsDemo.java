package lesson5;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorsDemo {

    public static void main(String[] args) {
//        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(4);

//        schedule(scheduledExecutor);
//        scheduleWithFixedDelay(scheduledExecutor);
        scheduleAtFixedRate(scheduledExecutor);
    }

    private static void scheduleAtFixedRate(ScheduledExecutorService scheduledExecutor) {
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13
        // [           ]
        //   [            ]
        //     [      ]
        scheduledExecutor.scheduleAtFixedRate(() -> {
            ThreadUtils.log("[" + new Date() + " ] "+ "Task started");
            ThreadUtils.sleep(0, 3);
            ThreadUtils.log("[" + new Date() + " ] "+ "Task finished");
        }, 0L, 300, TimeUnit.MILLISECONDS);

        System.out.println("Main ended");
    }

    private static void scheduleWithFixedDelay(ScheduledExecutorService scheduledExecutor) {
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13
        // [           ]   [      ]      [      ]
        scheduledExecutor.scheduleWithFixedDelay(() -> {
            System.out.println("[" + new Date() + " ] " + "Task started");
            ThreadUtils.sleep(2, 7);
            System.out.println("[" + new Date() + " ] " + "Task finished");
        }, 0L, 1, TimeUnit.SECONDS);

        System.out.println("Main ended");
    }

    private static void schedule(ScheduledExecutorService scheduledExecutor) {
        scheduledExecutor.schedule(() -> {
            System.out.println("Task finished");
        }, 5, TimeUnit.SECONDS);

        System.out.println("Task scheduled");
    }

}

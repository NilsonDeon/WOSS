import java.util.Arrays;
import java.util.Date;

public class ProcessScheduling {

    int quantum;
    int ioWaitTime;
    int overheadTime;
    String schedulingPolicy;
    int instructionsPerSecond;
    Job jobs[];

    public ProcessScheduling(int quantum, int ioWaitTime, int overheadTime, String schedulingPolicy, int instructionsPerSecond, Job jobs[]) {
        this.quantum = quantum;
        this.ioWaitTime = ioWaitTime;
        this.overheadTime = overheadTime;
        this.schedulingPolicy = schedulingPolicy;
        this.instructionsPerSecond = instructionsPerSecond;
        this.jobs = jobs;
    }

    public void start() {
        switch (schedulingPolicy) {
            case "FIFO":       startFIFO();       break;
            case "SJF":        startSJF();        break;
            case "RoundRobin": startRoundRobin(); break;
            case "Priority":   startPriority();   break;

            default: System.out.println("Invalid scheduling policy: " + schedulingPolicy);
        }
    }
    
    private void startFIFO() {
        Job.setComparisonCriteria(jobs, ComparisonCriteria.ARRIVAL_TIME);
        Arrays.sort(jobs);

        for (Job job : jobs) {
            Date startTime = new Date();

            for (int i = 0; i < job.instructions.length; i++) {
                waitSeconds(job.instructions[i]);
            }

            Date endTime = new Date();
            long executionTimeInSeconds = (endTime.getTime() - startTime.getTime()) / 1000;

            System.out.println("Job ID: " + job.id + ", Execution time: " + executionTimeInSeconds + " seconds");
        }
    }

    private void startSJF() {
    }

    private void startRoundRobin() {
    }
    
    private void startPriority() {
    }

    private void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}

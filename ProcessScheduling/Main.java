import java.util.Date;

public class Main {
    
    public static void main(String[] args) {

        String[] instructionsJob1 = Job.generateInstructions();
        String[] instructionsJob2 = Job.generateInstructions();
        String[] instructionsJob3 = Job.generateInstructions();

        Job job1 = new Job(1, 1, new Date(System.currentTimeMillis() + 20000), instructionsJob1);
        Job job2 = new Job(2, 1, new Date(System.currentTimeMillis() + 30000), instructionsJob2);
        Job job3 = new Job(3, 1, new Date(System.currentTimeMillis() + 10000), instructionsJob3);

        Job jobs[] = {job1, job2, job3};

        System.out.print(job1);
        System.out.print(job2);
        System.out.print(job3);

        ProcessScheduling processScheduling = new ProcessScheduling(0, 0, 0, "FIFO", 0, jobs);
        processScheduling.start();
    }

}

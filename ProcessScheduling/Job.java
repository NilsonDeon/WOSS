import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Job implements Comparable<Job> {

    int id;
    int priority;
    int nextInstruction;
    int duration;
    Date arrivalTime;
    String instructionsStr[];
    int instructions[];
    ComparisonCriteria comparisonCriteria;
    boolean isFinished;

    public Job(int id, int priority, Date arrivalTime, String[] instructionsStr) {
        this.id = id;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.instructionsStr = instructionsStr;
        this.instructions = new int[instructionsStr.length];
        this.isFinished = false;
        this.nextInstruction = 0;

        assignInstructionValues();
    }

    private void assignInstructionValues() {
        for (int i = 0; i < instructionsStr.length; i++) {
            String instruction = instructionsStr[i];
            switch (instruction) {
                case "ADD": case "SUB": case "MULT": case "DIV": instructions[i] = 1; break;
                case "LW": case "SW":                            instructions[i] = 2; break;
                case "READ": case "WRITE":                       instructions[i] = 3; break;
                default:                                         instructions[i] = 0; break;
            }
        }
    }

    public void setComparisonCriteria(ComparisonCriteria newCriteria) {
        this.comparisonCriteria = newCriteria;
    }

    public static void setComparisonCriteria(Job jobs[], ComparisonCriteria newCriteria) {
        for (Job job : jobs) {
            job.setComparisonCriteria(newCriteria);
        }
    }

    public static String[] generateInstructions() {
        String[] instructions = new String[5];
        Random random = new Random();

        for (int i = 0; i < instructions.length; i++) {
            int instructionType = random.nextInt(3);
            switch (instructionType) {
                case 0: instructions[i] = "ADD";  break;
                case 1: instructions[i] = "LW";   break;
                case 2: instructions[i] = "READ"; break;
            }
        }
        return instructions;
    }

    @Override
    public String toString() {
        return "Job { " +
                "id=" + id +
                ", priority=" + priority +
                ", arrivalTime=" + arrivalTime +
                ", instructionsStr=" + Arrays.toString(instructionsStr) +
                " }\n";
    }
    
    @Override
    public int compareTo(Job other) {
        switch (comparisonCriteria) {
            case ARRIVAL_TIME:
                return compareByArrivalTime(other);
            case PRIORITY:
                return compareByPriority(other);
            default:
                throw new IllegalArgumentException("Unknown comparison criteria: " + comparisonCriteria);
        }
    }

    private int compareByArrivalTime(Job other) {
        int result = this.arrivalTime.compareTo(other.arrivalTime);
        if (result == 0) result = Integer.compare(this.priority, other.priority);
        return result;
    }

    private int compareByPriority(Job other) {
        int result = Integer.compare(this.priority, other.priority);
        if (result == 0) result = this.arrivalTime.compareTo(other.arrivalTime);
        return result;
    }
}

enum ComparisonCriteria {
    ARRIVAL_TIME,
    PRIORITY
}



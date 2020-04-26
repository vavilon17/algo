package com.stamford.scheduling;

import com.stamford.common.AlgoUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vit
 * Date: 08.09.13
 */
public class Schedule {

    public long startSubstractScheduling(String fileName) {
        List<JobSubstract> jobs = AlgoUtils.readSubstractionJobs(fileName);
        Job[] array = transformToSubstractJobs(jobs.toArray());
        Arrays.sort(array);
        return calcSum(array);
    }

    public long startMultiplicationScheduling(String fileName) {
        List<JobMultiplication> jobs = AlgoUtils.readMultiplicationJobs(fileName);
        Job[] array = transformToMultiplicationJobs(jobs.toArray());
        Arrays.sort(array);
        return calcSum(array);
    }

    private long calcSum(Job[] jobs) {
        long sum = 0L;
        long completion = 0L;
        for(Job job : jobs) {
            completion += job.getLength();
            sum += job.getWeight() * completion;
        }
        return sum;
    }

    private JobSubstract[] transformToSubstractJobs(Object[] jobs) {
        JobSubstract[] res = new JobSubstract[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            try {
                res[i] = (JobSubstract) jobs[i];
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }

    private JobMultiplication[] transformToMultiplicationJobs(Object[] jobs) {
        JobMultiplication[] res = new JobMultiplication[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            try {
                res[i] = (JobMultiplication) jobs[i];
            } catch (ClassCastException ex) {
                ex.printStackTrace();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Schedule program = new Schedule();
        String filePath = args[0];
        System.out.println("Substract = " + program.startSubstractScheduling(filePath));
        System.out.println("Multiplication = " + program.startMultiplicationScheduling(filePath));
    }
}

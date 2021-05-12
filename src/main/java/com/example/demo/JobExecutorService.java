package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class JobExecutorService {
    enum JobPriority {
        HIGH(2),
        MEDIUM(1),
        LOW(0);

        private int priority;
        private static int noOfPriorities = -1;

        JobPriority (int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        public static JobPriority getJobPriority(int priority) {
            for (JobPriority jobPriority : JobPriority.values()) {
                if (jobPriority.getPriority() == priority) {
                    return jobPriority;
                }
            }

            throw new RuntimeException("Priority Type doesn't exist");
        }

        public static int getNoOfPriorities() {
            if (noOfPriorities == -1) {
                noOfPriorities = 0;
                for (JobPriority jobPriority : JobPriority.values()) {
                    noOfPriorities ++;
                }
            }
            return noOfPriorities;
        }
    }

    public void addJob(long jobID, int priority) {
        Job job = new Job(jobID, JobPriority.getJobPriority(priority));
        JobsHolder.getJobHolder().addJobs(job);
    }

    public void executor() {
        while (true) {
            Job job = JobsHolder.getJobHolder().getJobBasedOnConsumption();
            if (job != null) {
                job.execute();
            }
        }
    }
}
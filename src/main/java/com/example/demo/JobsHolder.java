package com.example.demo;

import java.util.*;

public class JobsHolder {
    private static final JobsHolder JOBHOLDER = new JobsHolder();
    private final Map<JobExecutorService.JobPriority, List<Job>> JOBS = new LinkedHashMap<>();
    private final Map<JobExecutorService.JobPriority, Integer> CONSUMPTION = new LinkedHashMap<>();

    private JobsHolder () {
        this.initQueue();
    }

    private void initQueue() {
        for (JobExecutorService.JobPriority jobPriority : JobExecutorService.JobPriority.values()) {
            JOBS.put(jobPriority, new LinkedList<>());
            CONSUMPTION.put(jobPriority, 0);
        }
    }

    public static JobsHolder getJobHolder() {
        return JOBHOLDER;
    }

    public void addJobs(Job job) {
        JOBS.get(job.getPriority()).add(job);
        CONSUMPTION.put(job.getPriority(), CONSUMPTION.get(job.getPriority()) + 1);
    }

    public Job getJob() {
        Job job = null;
        for (List<Job> jobs : JOBS.values()) {
            if (jobs.size() > 0) {
                job = jobs.remove(0);
                break;
            }
        }
        return job;
    }

    // counter -> consumption
    // consumed >= jobs with lower priority
    //      go for lower priority
    // else
    //      consume current priority

    public Job getJobBasedOnConsumption() {
        Job job = null;
        JobExecutorService.JobPriority[] priorities = JobExecutorService.JobPriority.values();

        for (int i = 0; i < priorities.length; i ++) {
            List<Job> jobs = JOBS.get(priorities[i]);
            if (jobs.size() > 0) {

                int noOfJobsWithLowerPriorities = 0; // computes sum of jobs with lower priorities
                for (int j = 0; j < priorities.length; j ++) {
                    noOfJobsWithLowerPriorities += JOBS.get(priorities[i]).size();
                }

                if (CONSUMPTION.get(priorities[i]) > noOfJobsWithLowerPriorities) {
                    /*
                     * If jobs with higher priorities is consumed more total of sum
                     * jobs with lower priorities, we will go to next lower priority
                     */
                    continue;
                }

                job = jobs.remove(0);
                CONSUMPTION.put(priorities[i], CONSUMPTION.get(priorities[i]) + 1);
                break;
            }
        }
        return job;
    }
}
package com.example.demo;

public class Job {
    private long jobID;
    private JobExecutorService.JobPriority priority;

    public Job(long jobID, JobExecutorService.JobPriority priority) {
        this.jobID = jobID;
        this.priority = priority;
    }

    public long getJobID() {
        return jobID;
    }

    public JobExecutorService.JobPriority getPriority() {
        return priority;
    }

    public void execute() {

    }
}

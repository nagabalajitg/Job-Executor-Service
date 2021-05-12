Job Executor Service
    Executes jobs based on priority.

Class Description
1. Job
    holds job id and priority
2. JobExecutorService
   1. Adds jobs to `JobsHolder`
   2. Consumes jobs and executes it
3. JobsHolder
    1. Add jobs to list based on it's priority type.
    2. getJobs() always get jobs with highest priority.
    3. getJobBasedOnConsumption() get jobs based on consumption.
       Idea : If no of consumed jobs with current priority is higher than rest of unconsumed jobs with lower priority, then job at the next lowest priority will be removed from the holder.
       Improvements : Compared to `getJobs()` , in-definite startvation will be removed. 
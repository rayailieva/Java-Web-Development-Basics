package sboj.service;

import sboj.domain.models.service.JobServiceModel;

import java.util.List;

public interface JobService {

    void createJob(JobServiceModel jobServiceModel);

    List<JobServiceModel> findAllJobs();

    void delete(String id);

    JobServiceModel getJobById(String id);
}

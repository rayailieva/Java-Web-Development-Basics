package sboj.service;

import org.modelmapper.ModelMapper;
import sboj.domain.entities.JobApplication;
import sboj.domain.models.service.JobServiceModel;
import sboj.repository.JobRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createJob(JobServiceModel jobServiceModel) {
        JobApplication jobApplication =
                this.modelMapper.map(jobServiceModel, JobApplication.class);
        this.jobRepository.save(jobApplication);
    }

    @Override
    public List<JobServiceModel> findAllJobs() {
        return this.jobRepository.findAll()
                .stream()
                .map(job -> this.modelMapper.map(job, JobServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.jobRepository.delete(id);
    }

    @Override
    public JobServiceModel getJobById(String id) {
        return
                this.modelMapper.map(this.jobRepository.findById(id), JobServiceModel.class);
    }


}

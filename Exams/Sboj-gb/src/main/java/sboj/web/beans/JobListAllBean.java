package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.view.JobListViewModel;
import sboj.service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class JobListAllBean {

    private List<JobListViewModel> jobs;

    private JobService jobService;
    private ModelMapper modelMapper;

    public JobListAllBean() {

    }

    @Inject
    public JobListAllBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.jobs = this.jobService.findAllJobs()
                .stream()
                .map(j -> this.modelMapper.map(j, JobListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobListViewModel> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<JobListViewModel> jobs) {
        this.jobs = jobs;
    }
}

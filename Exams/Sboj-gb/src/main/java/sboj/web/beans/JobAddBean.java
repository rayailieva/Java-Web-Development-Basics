package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.JobCreateBindingModel;
import sboj.domain.models.service.JobServiceModel;
import sboj.service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class JobAddBean {

    private JobCreateBindingModel jobCreateBindingModel;

    private JobService jobService;
    private ModelMapper modelMapper;

    public JobAddBean() {
    }

    @Inject
    public JobAddBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
        this.jobCreateBindingModel = new JobCreateBindingModel();
    }

    public JobCreateBindingModel getJobCreateBindingModel() {
        return this.jobCreateBindingModel;
    }

    public void setJobCreateBindingModel(JobCreateBindingModel jobCreateBindingModel) {
        this.jobCreateBindingModel = jobCreateBindingModel;
    }

    public void create() throws IOException {

        JobServiceModel jobServiceModel = this.modelMapper
                .map(this.jobCreateBindingModel, JobServiceModel.class);

        this.jobService.createJob(jobServiceModel);

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .redirect("/login");
    }
}

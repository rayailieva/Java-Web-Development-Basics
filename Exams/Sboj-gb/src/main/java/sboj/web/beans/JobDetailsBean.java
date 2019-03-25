package sboj.web.beans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.service.JobServiceModel;
import sboj.domain.models.view.JobListViewModel;
import sboj.service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class JobDetailsBean {

    private JobService jobService;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService) {
        this.jobService = jobService;
    }

    public JobServiceModel getJob(String id){
             return this.jobService.getJobById(id);
    }
}

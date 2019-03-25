package sboj.web.beans;

import sboj.domain.models.service.JobServiceModel;
import sboj.service.JobService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class JobDeleteBean {

    private JobService jobService;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService) {
        this.jobService = jobService;
    }

    public JobServiceModel getJob(String id){
        return this.jobService.getJobById(id);
    }

    public void deleteJob() throws IOException {
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");
        this.jobService.delete(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}


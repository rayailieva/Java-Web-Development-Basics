package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentPrintViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentPrintBean {

    private DocumentPrintViewModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentPrintBean(){}

    @Inject
    public DocumentPrintBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        DocumentServiceModel documentServiceModel = this.documentService.getById(id);

        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        this.model = this.modelMapper.map(documentServiceModel, DocumentPrintViewModel.class);
    }

    public DocumentPrintViewModel getModel() {
        return this.model;
    }

    public void setModel(DocumentPrintViewModel model) {
        this.model = model;
    }

    public void delete() throws IOException {
        String id = ((HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameter("id");

        this.documentService.delete(id);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}

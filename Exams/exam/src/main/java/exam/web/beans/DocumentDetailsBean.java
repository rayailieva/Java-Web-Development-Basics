package exam.web.beans;

import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailsViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailsBean {

    private DocumentDetailsViewModel model;
    
    private DocumentService documentService;
    private ModelMapper modelMapper;
    
    public DocumentDetailsBean(){}

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
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

        this.model = this.modelMapper.map(documentServiceModel, DocumentDetailsViewModel.class);
    }

    public DocumentDetailsViewModel getModel() {
        return this.model;
    }

    public void setModel(DocumentDetailsViewModel model) {
        this.model = model;
    }
}

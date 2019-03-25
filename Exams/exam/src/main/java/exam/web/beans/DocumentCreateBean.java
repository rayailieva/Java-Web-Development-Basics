package exam.web.beans;

import exam.domain.models.binding.DocumentCreateBindingModel;
import exam.domain.models.service.DocumentServiceModel;
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
public class DocumentCreateBean {

    private DocumentCreateBindingModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentCreateBean(){}

    @Inject
    public DocumentCreateBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        this.model = new DocumentCreateBindingModel();
    }

    public DocumentCreateBindingModel getModel() {
        return this.model;
    }

    public void setModel(DocumentCreateBindingModel model) {
        this.model = model;
    }

    public void create() throws IOException {
        DocumentServiceModel documentServiceModel = this.modelMapper
                .map(this.model, DocumentServiceModel.class);

        this.documentService.createDocument(documentServiceModel);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}

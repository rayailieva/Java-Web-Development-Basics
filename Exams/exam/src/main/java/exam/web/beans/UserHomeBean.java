package exam.web.beans;

import exam.domain.models.view.DocumentViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserHomeBean {

    private List<DocumentViewModel> models;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public UserHomeBean(){}

    @Inject
    public UserHomeBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModels();
    }

    private void initModels() {
        this.models = this.documentService
                .getAllDocuments()
                .stream()
                .map(d -> this.modelMapper.map(d, DocumentViewModel.class))
                .collect(Collectors.toList());
    }

    public List<DocumentViewModel> getModels() {
        return this.models;
    }

    public void setModels(List<DocumentViewModel> models) {
        this.models = models;
    }
}

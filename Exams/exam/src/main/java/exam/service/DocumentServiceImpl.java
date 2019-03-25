package exam.service;

import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService{

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createDocument(DocumentServiceModel documentServiceModel) {
        Document document =
                this.modelMapper.map(documentServiceModel, Document.class);

        this.documentRepository.save(document);
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments() {
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentServiceModel getById(String id) {
        Document document = this.documentRepository.findById(id);

        if (document == null) {
            return null;
        }

        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public void delete(String id) {
        this.documentRepository.delete(id);
    }
}

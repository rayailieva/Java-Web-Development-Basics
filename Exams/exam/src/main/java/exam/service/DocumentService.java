package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {

   void createDocument(DocumentServiceModel documentServiceModel);

    List<DocumentServiceModel> getAllDocuments();

    DocumentServiceModel getById(String id);

    void delete(String id);
}

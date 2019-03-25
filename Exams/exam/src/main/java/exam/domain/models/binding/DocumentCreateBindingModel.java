package exam.domain.models.binding;

public class DocumentCreateBindingModel {

    private String id;
    private String title;
    private String content;

    public DocumentCreateBindingModel(){}

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

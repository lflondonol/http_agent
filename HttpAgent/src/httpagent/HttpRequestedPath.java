package httpagent;

public class HttpRequestedPath {
    
    private String fileRequested;
    private String method;
    
    
    public HttpRequestedPath() {

    }

    public String getFileRequested() {
        return fileRequested;
    }

    public void setFileRequested(String fileRequested) {
        this.fileRequested = fileRequested;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    
}

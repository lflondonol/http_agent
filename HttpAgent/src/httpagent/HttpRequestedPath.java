package httpagent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

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

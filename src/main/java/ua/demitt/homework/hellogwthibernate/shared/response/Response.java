package ua.demitt.homework.hellogwthibernate.shared.response;

import java.io.Serializable;
import java.util.Map;

public class Response implements Serializable {
    private ResponseCode code;
    private Map<String, String> data;

    public Response() {   }

    public Response(ResponseCode code) {
        this.code = code;
    }

    public Response(ResponseCode code, Map<String, String> data) {
        this.code = code;
        this.data = data;
    }

    public ResponseCode getCode() {
        return code;
    }


    public Map<String, String> getData() {
        return data;
    }
}

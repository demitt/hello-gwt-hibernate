package ua.demitt.homework.hellogwthibernate.shared.response;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseCode code;
    private String userName;

    public Response() {   }

    public Response(ResponseCode code) {
        this.code = code;
    }

    public Response(ResponseCode code, String userName) {
        this.code = code;
        this.userName = userName;
    }

    public ResponseCode getCode() {
        return code;
    }

    public String getUserName() {
        return userName;
    }
}

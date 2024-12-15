package hms.util;

import org.springframework.http.HttpStatusCode;

/**
 * @author Aditya Gawade
 */
public class ResponseTo {

    private int statusCode;
    private Object data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Builder builder(){
        return new Builder();
    }

    public ResponseTo() {
    }

    public ResponseTo(Builder builder){
        this.statusCode= builder.statusCode;
        this.data=builder.data;
    }

    public static class Builder{
        private int statusCode;
        private Object data;

        public Builder() {
        }

        public Builder statusCode(int statusCode){
            this.statusCode = statusCode;
            return this;
        }

        public Builder data(Object data){
            this.data = data;
            return this;
        }

        public ResponseTo build(){
            return new ResponseTo(this);
        }
    }
}

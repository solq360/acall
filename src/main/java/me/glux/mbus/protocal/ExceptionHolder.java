package me.glux.mbus.protocal;

public class ExceptionHolder {
    private String type;
    private String message;
    public ExceptionHolder(String type,String message){
        this.type=type;
        this.message=message;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
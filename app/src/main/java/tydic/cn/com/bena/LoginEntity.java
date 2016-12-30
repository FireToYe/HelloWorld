package tydic.cn.com.bena;

/**
 * Created by yechenglong on 2016/12/29.
 */

public class LoginEntity {

    /**
     * channelType : ANDROID
     * traceLogId : 2016082712345678
     * custAcct : 15084890539
     * custPwd : 2251022057731868917119086224872421513662
     */

    private String channelType;
    private String traceLogId;
    private String custAcct;
    private String custPwd;
    public LoginEntity(){

    }
    public LoginEntity(String custAcct,String custPwd){
        this.channelType = "ANDROID";
        this.traceLogId ="2016082712345678";
        this.custAcct = custAcct;
        this.custPwd = custPwd;
    }
    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getTraceLogId() {
        return traceLogId;
    }

    public void setTraceLogId(String traceLogId) {
        this.traceLogId = traceLogId;
    }

    public String getCustAcct() {
        return custAcct;
    }

    public void setCustAcct(String custAcct) {
        this.custAcct = custAcct;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }
}

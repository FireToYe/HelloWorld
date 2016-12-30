package tydic.cn.com.bena;

/**
 * Created by yechenglong on 2016/12/29.
 */

public class UserInfoEntity {

    /**
     * accessToken : AT-015560b0-b43b-4a9a-a564-34c0e135ccc0
     * refreshToken : RT-7b23b397-02b8-4bb4-9083-17b959045064
     * userInfo : {"acctBalance":0,"address":"","appIcon":"","birthDate":"","custId":1306,"custName":"15084890539","custState":"3","custTypeId":1,"depositAmount":2000,"sex":"","telephone":"15084890539"}
     */

    private String accessToken;
    private String refreshToken;
    private UserInfoBean userInfo;

    public String getAccessToken() {
        return accessToken;
    }

    @Override
    public String toString() {
        return "UserInfoEntity{" +
                "accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", userInfo={" + userInfo.toString() +"}"+
                '}';
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * acctBalance : 0
         * address :
         * appIcon :
         * birthDate :
         * custId : 1306
         * custName : 15084890539
         * custState : 3
         * custTypeId : 1
         * depositAmount : 2000
         * sex :
         * telephone : 15084890539
         */

        private int acctBalance;
        private String address;
        private String appIcon;
        private String birthDate;
        private int custId;
        private String custName;
        private String custState;
        private int custTypeId;
        private int depositAmount;
        private String sex;
        private String telephone;

        public int getAcctBalance() {
            return acctBalance;
        }

        public void setAcctBalance(int acctBalance) {
            this.acctBalance = acctBalance;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAppIcon() {
            return appIcon;
        }

        public void setAppIcon(String appIcon) {
            this.appIcon = appIcon;
        }

        public String getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        public int getCustId() {
            return custId;
        }

        public void setCustId(int custId) {
            this.custId = custId;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getCustState() {
            return custState;
        }

        public void setCustState(String custState) {
            this.custState = custState;
        }

        public int getCustTypeId() {
            return custTypeId;
        }

        public void setCustTypeId(int custTypeId) {
            this.custTypeId = custTypeId;
        }

        public int getDepositAmount() {
            return depositAmount;
        }

        public void setDepositAmount(int depositAmount) {
            this.depositAmount = depositAmount;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        @Override
        public String toString() {
            return "UserInfoBean{" +
                    "acctBalance=" + acctBalance +
                    ", address='" + address + '\'' +
                    ", appIcon='" + appIcon + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    ", custId=" + custId +
                    ", custName='" + custName + '\'' +
                    ", custState='" + custState + '\'' +
                    ", custTypeId=" + custTypeId +
                    ", depositAmount=" + depositAmount +
                    ", sex='" + sex + '\'' +
                    ", telephone='" + telephone + '\'' +
                    '}';
        }
    }
}

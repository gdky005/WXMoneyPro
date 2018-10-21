package com.gdky005.wxmoneydemo;

import java.util.List;

public class MXBean {


    /**
     * code : 0
     * message : ok
     * result : [{"id":15,"name":"扫码支付","newTime":1540205370,"time":"2018-10-22 18:49:30","money":11,"spendState":true},{"id":16,"name":"扫码支付","newTime":1540196155,"time":"2018-10-22 16:15:55","money":7,"spendState":true},{"id":2,"name":"扫码支付","newTime":1540187440,"time":"2018-10-22 13:50:40","money":7,"spendState":true},{"id":1,"name":"扫码支付","newTime":1540185391,"time":"2018-10-22 13:16:31","money":10,"spendState":true},{"id":6,"name":"扫码支付","newTime":1540185351,"time":"2018-10-22 13:15:51","money":9,"spendState":true},{"id":14,"name":"扫码支付","newTime":1540183147,"time":"2018-10-22 12:39:07","money":8,"spendState":true},{"id":9,"name":"扫码支付","newTime":1540171135,"time":"2018-10-22 09:18:55","money":7,"spendState":true},{"id":4,"name":"扫码支付","newTime":1540170665,"time":"2018-10-22 09:11:05","money":5,"spendState":true},{"id":13,"name":"扫码支付","newTime":1540170398,"time":"2018-10-22 09:06:38","money":9,"spendState":true},{"id":7,"name":"扫码支付","newTime":1540168123,"time":"2018-10-22 08:28:43","money":9,"spendState":true},{"id":5,"name":"扫码支付","newTime":1540159887,"time":"2018-10-22 06:11:27","money":9,"spendState":true},{"id":18,"name":"扫码支付","newTime":1540155613,"time":"2018-10-22 05:00:13","money":10,"spendState":true},{"id":10,"name":"扫码支付","newTime":1540155325,"time":"2018-10-22 04:55:25","money":11,"spendState":true},{"id":11,"name":"扫码支付","newTime":1540151474,"time":"2018-10-22 03:51:14","money":11,"spendState":true},{"id":17,"name":"扫码支付","newTime":1540148753,"time":"2018-10-22 03:05:53","money":12,"spendState":true},{"id":3,"name":"扫码支付","newTime":1540144148,"time":"2018-10-22 01:49:08","money":6,"spendState":true},{"id":19,"name":"扫码支付","newTime":1540138097,"time":"2018-10-22 00:08:17","money":10,"spendState":true},{"id":8,"name":"扫码支付","newTime":1540133037,"time":"2018-10-21 22:43:57","money":6,"spendState":true},{"id":20,"name":"扫码支付","newTime":1540132402,"time":"2018-10-21 22:33:22","money":7,"spendState":true},{"id":12,"name":"扫码支付","newTime":1540118367,"time":"2018-10-21 18:39:27","money":5,"spendState":true}]
     */

    private int code;
    private String message;
    private List<ResultBean> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 15
         * name : 扫码支付
         * newTime : 1540205370
         * time : 2018-10-22 18:49:30
         * money : 11
         * spendState : true
         */

        private int id;
        private String name;
        private int newTime;
        private String time;
        private int money;
        private boolean spendState;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNewTime() {
            return newTime;
        }

        public void setNewTime(int newTime) {
            this.newTime = newTime;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public boolean isSpendState() {
            return spendState;
        }

        public void setSpendState(boolean spendState) {
            this.spendState = spendState;
        }
    }
}

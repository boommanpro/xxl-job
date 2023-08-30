package com.xxl.job.core.config;

public class XxlJobExecutorConfig {

    private String adminAddresses;

    private String accessToken;

    private ExecutorConfig executor;


    public static class ExecutorConfig{
        private String appName;

        private String address;

        private String ip;

        private int port;

        private String logPath;

        private int logRetentionDays;

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getLogPath() {
            return logPath;
        }

        public void setLogPath(String logPath) {
            this.logPath = logPath;
        }

        public int getLogRetentionDays() {
            return logRetentionDays;
        }

        public void setLogRetentionDays(int logRetentionDays) {
            this.logRetentionDays = logRetentionDays;
        }

        @Override
        public String toString() {
            return "ExecutorConfig{" +
                    "appName='" + appName + '\'' +
                    ", address='" + address + '\'' +
                    ", ip='" + ip + '\'' +
                    ", port=" + port +
                    ", logPath='" + logPath + '\'' +
                    ", logRetentionDays=" + logRetentionDays +
                    '}';
        }
    }

    public String getAdminAddresses() {
        return adminAddresses;
    }

    public void setAdminAddresses(String adminAddresses) {
        this.adminAddresses = adminAddresses;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public ExecutorConfig getExecutor() {
        return executor;
    }

    public void setExecutor(ExecutorConfig executor) {
        this.executor = executor;
    }

    @Override
    public String toString() {
        return "XxlJobExecutorConfig{" +
                "adminAddresses='" + adminAddresses + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", executor=" + executor +
                '}';
    }
}

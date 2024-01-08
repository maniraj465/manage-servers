package com.maniraj.servers.enumeration;

public enum Status {
    UP("UP"),
    DOWN("DOWN");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}

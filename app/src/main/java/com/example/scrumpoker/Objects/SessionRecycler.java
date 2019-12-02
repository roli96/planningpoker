package com.example.scrumpoker.Objects;

public class SessionRecycler {
    private String sessionName,sessionId,ownerName;

    public SessionRecycler() {
    }

    public SessionRecycler(String sessionName, String sessionId, String ownerName) {
        this.sessionName = sessionName;
        this.sessionId = sessionId;
        this.ownerName = ownerName;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}

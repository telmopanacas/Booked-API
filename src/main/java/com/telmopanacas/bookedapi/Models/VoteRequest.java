package com.telmopanacas.bookedapi.Models;

public class VoteRequest {
    private Integer avaliacaoId;
    private Integer userId;

    public VoteRequest(Integer avaliacaoId, Integer userId) {
        this.avaliacaoId = avaliacaoId;
        this.userId = userId;
    }

    public Integer getAvaliacaoId() {
        return avaliacaoId;
    }

    public Integer getUserId() {
        return userId;
    }
}

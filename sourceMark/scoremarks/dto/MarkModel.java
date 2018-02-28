package com.core.main.cloudscore.scoremarks.dto;

public class MarkModel {
    private int percent;
    private int markScore;
    private int modelId;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getMarkScore() {
        return markScore;
    }

    public void setMarkScore(int markScore) {
        this.markScore = markScore;
    }
}

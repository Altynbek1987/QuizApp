package com.example.quizapp.model;

public class RadioModel {
    private int iconTheme;
    private boolean isChange;

    public RadioModel(int iconTheme, boolean isChange) {
        this.iconTheme = iconTheme;
        this.isChange = isChange;
    }

    public int getIconTheme() {
        return iconTheme;
    }

    public void setIconTheme(int iconTheme) {
        this.iconTheme = iconTheme;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}

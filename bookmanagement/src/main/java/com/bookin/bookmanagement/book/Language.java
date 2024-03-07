package com.bookin.bookmanagement.book;

public enum Language {
    TAMIL("Tamil"),
    ENGLISH("English"),
    MALAYALAM("Malayalam"),
    TELUGU("Telugu");
    private String language;
    Language(String language) {
        this.language = language;
    }
    public String getLanguage() {
        return this.language;
    }
}

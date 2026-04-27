package com.back.domain.wiseSaying.entity;

public class WiseSaying {
    static int lastQuoteNo = 0;
    int quoteId;
    String quote;
    String author;

    public WiseSaying(String quote, String author) {
        this.quoteId = ++lastQuoteNo;
        this.quote = quote;
        this.author = author.isEmpty() ? "입력없음": author;
    }

    public static int getLastQuoteNo() { return lastQuoteNo;}
    public int getQuoteNo() { return quoteId;}
    public String getQuote() { return quote;}
    public String getAuthor() { return author;}
    public void setQuote(String quote) { this.quote = quote;}
    public void setAuthor(String author) {this.author = author.isEmpty() ? "입력없음": author;}
    public boolean compareNo(int i) {return (quoteId == i);}
}
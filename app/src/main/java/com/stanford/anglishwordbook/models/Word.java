package com.stanford.anglishwordbook.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by m.stanford on 5/26/15.
 */
public class Word {

    @SerializedName("Word")
    private String mEnglishWord;
    @SerializedName("Type")
    private String mType;
    @SerializedName("Attested")
    private List<String> mAttested;
    @SerializedName("Unattested")
    private List<String> mUnAttested;

    public Word() {
    }

    public Word(String englishWord, String type, List<String> attested, List<String> unAttested) {
        mEnglishWord = englishWord;
        mType = type;
        mAttested = attested;
        mUnAttested = unAttested;
    }

    public String getEnglishWord() {
        return mEnglishWord;
    }

    public void setEnglishWord(String englishWord) {
        mEnglishWord = englishWord;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public List getAttested() {
        return mAttested;
    }

    public void setAttested(List attested) {
        mAttested = attested;
    }

    public List getUnAttested() {
        return mUnAttested;
    }

    public void setUnAttested(List unAttested) {
        mUnAttested = unAttested;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mEnglishWord='" + mEnglishWord + '\'' +
                ", mType='" + mType + '\'' +
                ", mAttested='" + mAttested + '\'' +
                ", mUnAttested='" + mUnAttested + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

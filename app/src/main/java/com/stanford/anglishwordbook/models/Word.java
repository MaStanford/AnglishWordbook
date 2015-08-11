package com.stanford.anglishwordbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by m.stanford on 5/26/15.
 */
public class Word implements Parcelable{

    @SerializedName("Word")
    private String mEnglishWord = "";
    @SerializedName("Type")
    private String mType = "";
    @SerializedName("Attested")
    private List<String> mAttested = new ArrayList<>();
    @SerializedName("Unattested")
    private List<String> mUnAttested = new ArrayList<>();

    public Word(String englishWord, String type, List<String> attested, List<String> unAttested) {
        mEnglishWord = englishWord;
        mType = type;
        mAttested = attested;
        mUnAttested = unAttested;
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    public Word(Parcel in) {
        this.mEnglishWord = in.readString();
        this.mType = in.readString();
        this.mAttested = (ArrayList)Arrays.asList(in.readArray(null));
        this.mUnAttested =  (ArrayList)Arrays.asList(null);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mEnglishWord);
        dest.writeString(this.mType);
        dest.writeArray(this.mAttested.toArray());
        dest.writeArray(this.mUnAttested.toArray());

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

    public List<String> getAttested() {
        return mAttested;
    }

    public void setAttested(List attested) {
        mAttested = attested;
    }

    public List<String> getUnAttested() {
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

    @Override
    public int describeContents() {
        return 0;
    }
}

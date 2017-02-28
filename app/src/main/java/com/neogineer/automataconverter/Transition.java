package com.neogineer.automataconverter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by neogineer on 05/02/17.
 */
public class Transition implements Parcelable{
    public String from;
    public String symbol;
    public String to;

    public Transition() {
    }

    public Transition(String from, String symbol, String to) {
        this.from = from;
        this.symbol = symbol;
        this.to = to;
    }

    protected Transition(Parcel in) {
        from = in.readString();
        symbol = in.readString();
        to = in.readString();
    }

    public static final Creator<Transition> CREATOR = new Creator<Transition>() {
        @Override
        public Transition createFromParcel(Parcel in) {
            return new Transition(in);
        }

        @Override
        public Transition[] newArray(int size) {
            return new Transition[size];
        }
    };

    @Override
    public String toString() {
        return this.from + " - " + this.symbol + " - " + this.to;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(from);
        parcel.writeString(symbol);
        parcel.writeString(to);
    }
}

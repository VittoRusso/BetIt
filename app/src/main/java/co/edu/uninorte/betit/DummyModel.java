package co.edu.uninorte.betit;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gabriel on 12/03/2018.
 */

public class DummyModel implements Parcelable {
    private final String id;
    private final String title;
    private final String detail;

    public DummyModel(String id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }


    protected DummyModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        detail = in.readString();
    }

    public static final Creator<DummyModel> CREATOR = new Creator<DummyModel>() {
        @Override
        public DummyModel createFromParcel(Parcel in) {
            return new DummyModel(in);
        }

        @Override
        public DummyModel[] newArray(int size) {
            return new DummyModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(detail);
    }
}

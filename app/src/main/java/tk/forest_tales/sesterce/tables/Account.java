package tk.forest_tales.sesterce.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "accounts")
public class Account {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer mId;

    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @NonNull
    @ColumnInfo(name = "currency")
    private String mCurrency;

    @NonNull
    @ColumnInfo(name = "kind")
    private String mKind;


    @Ignore
    public Account(@NonNull String name, @NonNull String currency, @NonNull String kind) {
        this.mName = name;
        this.mCurrency = currency;
        this.mKind = kind;
    }

    public Account(@NonNull Integer id, @NonNull String name, @NonNull String currency, @NonNull String kind) {
        this.mId = id;
        this.mName = name;
        this.mCurrency = currency;
        this.mKind = kind;
    }

    public Integer getId(){return this.mId;}
    public String getName(){return this.mName;}
    public String getCurrency(){return this.mCurrency;}
    public String getKind(){return this.mKind;}
}

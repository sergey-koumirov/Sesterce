package tk.forest_tales.sesterce.tables;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer mId;

    @NonNull
    @ColumnInfo(name = "account_id_from")
    private Integer mAccountIdFrom;

    @NonNull
    @ColumnInfo(name = "amount_from")
    private Long mAmountFrom;

    @NonNull
    @ColumnInfo(name = "account_id_to")
    private Integer mAccountIdTo;

    @NonNull
    @ColumnInfo(name = "amount_to")
    private Long mAmountTo;

    @NonNull
    @ColumnInfo(name = "tdate")
    private String mTDate;

    @ColumnInfo(name = "description")
    private String mDescription;

    public Transaction(
            @NonNull Integer id,
            @NonNull Integer accountIdFrom,
            @NonNull Long amountFrom,
            @NonNull Integer accountIdTo,
            @NonNull Long amountTo,
            @NonNull String tDate,
            String description
    ) {
        this.mId = id;
        this.mAccountIdFrom = accountIdFrom;
        this.mAmountFrom = amountFrom;
        this.mAccountIdTo = accountIdTo;
        this.mAmountTo = amountTo;
        this.mTDate = tDate;
        this.mDescription = description;
    }

    public Integer getId(){return this.mId;}
    public Integer getAccountIdFrom(){return this.mAccountIdFrom;}
    public Long getAmountFrom(){return this.mAmountFrom;}
    public Integer getAccountIdTo(){return this.mAccountIdTo;}
    public Long getAmountTo(){return this.mAmountTo;}
    public String getTDate(){return this.mTDate;}
    public String getDescription(){return this.mDescription;}

}

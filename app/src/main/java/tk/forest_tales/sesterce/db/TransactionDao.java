package tk.forest_tales.sesterce.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import tk.forest_tales.sesterce.tables.Transaction;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction transaction);

    @Query("DELETE FROM transactions")
    void deleteAll();

    @Query("SELECT * from transactions ORDER BY tdate desc, id desc")
    LiveData<List<Transaction>> getAllTransactions();
}

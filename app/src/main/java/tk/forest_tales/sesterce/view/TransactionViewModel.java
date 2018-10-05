package tk.forest_tales.sesterce.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import tk.forest_tales.sesterce.db.TransactionRepository;
import tk.forest_tales.sesterce.tables.Transaction;

public class TransactionViewModel extends AndroidViewModel {
    private TransactionRepository mRepository;
    private LiveData<List<Transaction>> mAllTransactions;

    public TransactionViewModel(Application application) {
        super(application);
        mRepository = new TransactionRepository(application);
        mAllTransactions = mRepository.getAllTransactions();
    }

    public LiveData<List<Transaction>> getAllTransactions() { return mAllTransactions; }

    public void insert(Transaction transaction) { mRepository.insert(transaction); }
}

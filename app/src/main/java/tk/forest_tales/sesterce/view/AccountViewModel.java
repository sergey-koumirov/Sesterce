package tk.forest_tales.sesterce.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import tk.forest_tales.sesterce.db.AccountRepository;
import tk.forest_tales.sesterce.tables.Account;

public class AccountViewModel extends AndroidViewModel {
    private AccountRepository mRepository;
    private LiveData<List<Account>> mAllAccounts;

    public AccountViewModel (Application application) {
        super(application);
        mRepository = new AccountRepository(application);
        mAllAccounts = mRepository.getAllAccounts();
    }

    public LiveData<List<Account>> getAllAccounts() { return mAllAccounts; }

    public void insert(Account account) { mRepository.insert(account); }

    public void update(Account account) { mRepository.update(account); }

    public void delete(Account account) { mRepository.delete(account); }
}

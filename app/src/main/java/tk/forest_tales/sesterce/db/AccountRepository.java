package tk.forest_tales.sesterce.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import tk.forest_tales.sesterce.tables.Account;

public class AccountRepository {
    private AccountDao mAccountDao;
    private LiveData<List<Account>> mAllAccounts;

    public AccountRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mAccountDao = db.accountDao();
        mAllAccounts = mAccountDao.getAllAccounts();
    }

    public LiveData<List<Account>> getAllAccounts() {
        return mAllAccounts;
    }

    public void insert(Account account) {
        new insertAsyncTask(mAccountDao).execute(account);
    }

    public void delete(Account account) {
        new deleteAsyncTask(mAccountDao).execute(account);
    }

    private static class insertAsyncTask extends AsyncTask<Account, Void, Void> {

        private AccountDao mAsyncTaskDao;

        insertAsyncTask(AccountDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Account, Void, Void> {

        private AccountDao mAsyncTaskDao;

        deleteAsyncTask(AccountDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Account... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}

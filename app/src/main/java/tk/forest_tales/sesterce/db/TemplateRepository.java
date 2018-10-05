package tk.forest_tales.sesterce.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import tk.forest_tales.sesterce.tables.Template;

public class TemplateRepository {
    private TemplateDao mTemplateDao;
    private LiveData<List<Template>> mAllTemplates;

    public TemplateRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mTemplateDao = db.templateDao();
        mAllTemplates = mTemplateDao.getAllTemplates();
    }

    public LiveData<List<Template>> getAllTemplates() {
        return mAllTemplates;
    }

    public void insert(Template template) {
        new insertAsyncTask(mTemplateDao).execute(template);
    }

    public void update(Template account) {
        new updateAsyncTask(mTemplateDao).execute(account);
    }

    public void delete(Template account) {
        new deleteAsyncTask(mTemplateDao).execute(account);
    }

    private static class insertAsyncTask extends AsyncTask<Template, Void, Void> {

        private TemplateDao mAsyncTaskDao;

        insertAsyncTask(TemplateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Template... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Template, Void, Void> {
        private TemplateDao mAsyncTaskDao;

        updateAsyncTask(TemplateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Template... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Template, Void, Void> {

        private TemplateDao mAsyncTaskDao;

        deleteAsyncTask(TemplateDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Template... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}

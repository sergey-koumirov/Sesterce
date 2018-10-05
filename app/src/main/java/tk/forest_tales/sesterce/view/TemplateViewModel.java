package tk.forest_tales.sesterce.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import tk.forest_tales.sesterce.db.TemplateRepository;
import tk.forest_tales.sesterce.tables.Template;

public class TemplateViewModel extends AndroidViewModel {
    private TemplateRepository mRepository;
    private LiveData<List<Template>> mAllTemplates;

    public TemplateViewModel(Application application) {
        super(application);
        mRepository = new TemplateRepository(application);
        mAllTemplates = mRepository.getAllTemplates();
    }

    public LiveData<List<Template>> getAllTemplates() { return mAllTemplates; }

    public void insert(Template template) { mRepository.insert(template); }

    public void update(Template template) { mRepository.update(template); }

    public void delete(Template template) { mRepository.delete(template); }
}

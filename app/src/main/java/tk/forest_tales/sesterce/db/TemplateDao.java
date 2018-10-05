package tk.forest_tales.sesterce.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import tk.forest_tales.sesterce.tables.Template;
import tk.forest_tales.sesterce.tables.Transaction;

@Dao
public interface TemplateDao {
    @Insert
    void insert(Template template);

    @Update
    void update(Template template);

    @Delete
    void delete(Template template);


    @Query("SELECT * from templates ORDER BY description")
    LiveData<List<Template>> getAllTemplates();
}

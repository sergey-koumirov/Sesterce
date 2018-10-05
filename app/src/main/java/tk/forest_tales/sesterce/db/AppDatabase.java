package tk.forest_tales.sesterce.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.Environment;

import tk.forest_tales.sesterce.tables.Account;
import tk.forest_tales.sesterce.tables.Template;
import tk.forest_tales.sesterce.tables.Transaction;

@Database(entities = {Account.class, Transaction.class, Template.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract TransactionDao transactionDao();
    public abstract TemplateDao templateDao();

    private static volatile AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/sesterce_database"
                    ).addMigrations(
                            MIGRATION_1_2,
                            MIGRATION_2_3
                    ).build();
                }
            }
        }
        return INSTANCE;
    }

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("alter table accounts add column kind text not null default 'E'");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL(
                    "CREATE TABLE templates (" +
                        "id INTEGER NOT NULL, " +
                        "account_id_from INTEGER NOT NULL, " +
                        "amount_from INTEGER NOT NULL, " +
                        "account_id_to INTEGER NOT NULL, " +
                        "amount_to INTEGER NOT NULL, " +
                        "description TEXT NOT NULL, " +
                        "PRIMARY KEY(id)" +
                    ")"
            );

            database.execSQL("create index index_transactions_tdate on transactions(tdate);");
            database.execSQL("create index index_transactions_account_id_from on transactions(account_id_from);");
            database.execSQL("create index index_transactions_account_id_to on transactions(account_id_to);");
        }
    };

}


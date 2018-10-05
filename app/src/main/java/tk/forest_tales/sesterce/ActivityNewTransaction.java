package tk.forest_tales.sesterce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityNewTransaction extends AppCompatActivity{

    public static final String EXTRA_REPLY_NAME = "com.example.android.transactionlistsql.REPLY_NAME";
    public static final String EXTRA_REPLY_CURRENCY = "com.example.android.transactionlistsql.REPLY_CURRENCY";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_new);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                finish();
            }
        });

    }

}

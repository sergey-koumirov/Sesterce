package tk.forest_tales.sesterce.editors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

import tk.forest_tales.sesterce.R;

public class AccountEditor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_REPLY_ID = "com.example.android.accountlistsql.REPLY_ID";
    public static final String EXTRA_REPLY_NAME = "com.example.android.accountlistsql.REPLY_NAME";
    public static final String EXTRA_REPLY_CURRENCY = "com.example.android.accountlistsql.REPLY_CURRENCY";
    public static final String EXTRA_REPLY_KIND = "com.example.android.accountlistsql.REPLY_KIND";

    private EditText mNameView;
    private String selectedCurrency = "RUR";
    private String selectedKind = "E";
    private Integer selectedId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_account);

        selectedId = this.getIntent().getIntExtra(EXTRA_REPLY_ID, -1);

        mNameView = findViewById(R.id.edit_name);
        mNameView.setText( this.getIntent().getStringExtra(EXTRA_REPLY_NAME) );
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mNameView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    replyIntent.putExtra(EXTRA_REPLY_NAME, mNameView.getText().toString());
                    replyIntent.putExtra(EXTRA_REPLY_CURRENCY, selectedCurrency);
                    replyIntent.putExtra(EXTRA_REPLY_KIND, selectedKind);
                    replyIntent.putExtra(EXTRA_REPLY_ID, selectedId);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });

        Spinner currencySpinner = findViewById(R.id.currency_spinner);
        currencySpinner.setOnItemSelectedListener(this);

        String[] currencyLabels = getResources().getStringArray(R.array.currency_labels);
        Integer currencyIndex = Arrays.asList(currencyLabels).indexOf( this.getIntent().getStringExtra(EXTRA_REPLY_CURRENCY));
        currencySpinner.setSelection( currencyIndex );

        Spinner kindSpinner = findViewById(R.id.kind_spinner);
        kindSpinner.setOnItemSelectedListener(this);

        String[] kindValues = getResources().getStringArray(R.array.kind_values);
        Integer kindIndex = Arrays.asList(kindValues).indexOf( this.getIntent().getStringExtra(EXTRA_REPLY_KIND));
        kindSpinner.setSelection(kindIndex);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.kind_spinner) {
            selectedKind = getResources().getStringArray(R.array.kind_values)[spinner.getSelectedItemPosition()];
        }
        else if(spinner.getId() == R.id.currency_spinner) {
            selectedCurrency = (String)parent.getItemAtPosition(pos);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}

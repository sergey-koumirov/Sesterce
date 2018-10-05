package tk.forest_tales.sesterce.editors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tk.forest_tales.sesterce.R;

public class TemplateEditor extends AppCompatActivity{

    public static final String EXTRA_REPLY_NAME = "REPLY_NAME";
    public static final String EXTRA_REPLY_CURRENCY = "REPLY_CURRENCY";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_template);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();

                finish();
            }
        });

    }

}

package com.example.taskaway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText requirementField;
    private EditText priceField;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        nameField = (EditText) findViewById(R.id.editText2);
        requirementField = (EditText) findViewById(R.id.editText3);
        priceField = (EditText) findViewById(R.id.editText5);

        saveButton = (Button) findViewById(R.id.button2);

        cancelButton = (Button) findViewById(R.id.button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MyJobs.class);
                String name = nameField.getText().toString();
                if (name.isEmpty()) {
                    nameField.setError("Enter name");
                    return;
                }

                if (name.length() > 30){
                    nameField.setError("Name too long");
                    return;
                }

                String comment = requirementField.getText().toString();
                if (comment.isEmpty()){
                    requirementField.setError("Enter requirement");
                    return;
                }

                if (comment.length()>300) {
                    requirementField.setError("Description too long");
                    return;
                }

                String price = priceField.getText().toString();
                if (price.isEmpty()) {
                    priceField.setError("Enter charge");
                    return;
                }

                intent.putExtra("one", name);
                intent.putExtra("two", comment);
                intent.putExtra("three", price);
                startActivity(intent);

            }
        });
    }




}
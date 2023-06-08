//CONTROLLER
package com.zybooks.petregistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mMicroChipTextView;
    private TextView mNameTextView;
    private TextView mGenderTextView;
    private TextView mEmailTextView;
    private TextView mAccessTextView;
    private TextView mConfirmTextView;
    private TextView mBreedTextView;
    private TextView mNeuteredTextView;

    private EditText mMicroChipEditText;
    private EditText mNameEditText;
    private RadioGroup mGenderRadioGroup;
    private EditText mEmailEditText;
    private EditText mAccessEditText;
    private EditText mConfirmEditText;
    private Spinner mBreedSpinner;
    private CheckBox mNeuteredCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the widgets to fields
        mMicroChipTextView = findViewById(R.id.prompt_microchip);
        mNameTextView = findViewById(R.id.prompt_name);
        mGenderTextView = findViewById(R.id.prompt_gender);
        mEmailTextView = findViewById(R.id.prompt_email);
        mAccessTextView = findViewById(R.id.prompt_access);
        mConfirmTextView = findViewById(R.id.prompt_confirm);
        mBreedTextView = findViewById(R.id.prompt_breed);
        mNeuteredTextView =findViewById(R.id.prompt_neutered);

        mMicroChipEditText = findViewById(R.id.input_microchip);
        mNameEditText = findViewById(R.id.input_name);
        mGenderRadioGroup = findViewById(R.id.gender_radio_group);
        mEmailEditText = findViewById(R.id.input_email);
        mAccessEditText = findViewById(R.id.input_access);
        mConfirmEditText = findViewById(R.id.input_confirm);
        mBreedSpinner = findViewById(R.id.input_breed);
        mNeuteredCheckbox = findViewById(R.id.input_neutered);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.breed_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mBreedSpinner.setAdapter(adapter);
        mBreedSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }

    public void clickReset(View view){

    }

    public void clickSubmit(View view){

    }

}
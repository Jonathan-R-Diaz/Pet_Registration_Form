//CONTROLLER
package com.zybooks.petregistrationform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

    private PetCare mPetCare;

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
        setContentView(R.layout.relative_main);

        mPetCare = new PetCare();

        // Assign the widgets to fields
        mMicroChipTextView = findViewById(R.id.prompt_microchip);
        mNameTextView = findViewById(R.id.prompt_name);
        mEmailTextView = findViewById(R.id.prompt_email);
        mAccessTextView = findViewById(R.id.prompt_access);
        mConfirmTextView = findViewById(R.id.prompt_confirm);

        mMicroChipEditText = findViewById(R.id.input_microchip);
        mNameEditText = findViewById(R.id.input_name);
        mGenderRadioGroup = findViewById(R.id.gender_radio_group);
        mEmailEditText = findViewById(R.id.input_email);
        mAccessEditText = findViewById(R.id.input_access);
        mConfirmEditText = findViewById(R.id.input_confirm);
        mBreedSpinner = findViewById(R.id.input_breed);
        mNeuteredCheckbox = findViewById(R.id.input_neutered);

        ArrayAdapter < CharSequence > adapter = ArrayAdapter.createFromResource(this, R.array.breed_array, android.R.layout.simple_spinner_item);
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
        resetTextViewColors();
        mMicroChipEditText.setText("");
        mNameEditText.setText("");
        mGenderRadioGroup.check(R.id.button_female);
        mEmailEditText.setText("");
        mAccessEditText.setText("");
        mConfirmEditText.setText("");
        mBreedSpinner.setSelection(0);
        mNeuteredCheckbox.setChecked(true);
    }

    public void makeTextViewRed(TextView textView){
        textView.setTextColor(getColor(R.color.red));
    }

    public void resetTextViewColors(){

        mMicroChipTextView.setTextColor(getColor(R.color.gray));
        mNameTextView.setTextColor(getColor(R.color.gray));
        mEmailTextView.setTextColor(getColor(R.color.gray));
        mAccessTextView.setTextColor(getColor(R.color.gray));
        mConfirmTextView.setTextColor(getColor(R.color.gray));
    }
    public void clickSubmit(View view){

        System.out.println("Click");
        boolean submitted = true;

        String microchip = mMicroChipEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();

        int code1, code2;
        try{
            String code1str = mAccessEditText.getText().toString();
            String code2str = mConfirmEditText.getText().toString();
            code1 = Integer.parseInt(code1str);
            code2 = Integer.parseInt(code2str);
        }
        catch (NumberFormatException ex){
            code1 = 0;
            code2 = 1;
        }

        resetTextViewColors();
        if (!mPetCare.checkMicroChip(microchip)) {
            makeTextViewRed(mMicroChipTextView);
            submitted = false;
            Toast.makeText(this, R.string.error_microchip, Toast.LENGTH_SHORT).show();
        }
        if(!mPetCare.checkName(name)){
            makeTextViewRed(mNameTextView);
            submitted = false;
            Toast.makeText(this, R.string.error_name, Toast.LENGTH_SHORT).show();
        }
        if (!mPetCare.checkEmail(email)) {
            makeTextViewRed(mEmailTextView);
            submitted = false;
            Toast.makeText(this, R.string.error_email, Toast.LENGTH_SHORT).show();
        }
        if (!mPetCare.checkCodes(code1, code2)){
            makeTextViewRed(mAccessTextView);
            makeTextViewRed(mConfirmTextView);
            submitted = false;
            Toast.makeText(this, R.string.error_codes, Toast.LENGTH_SHORT).show();
        }
        if (submitted){
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        }
    }

}
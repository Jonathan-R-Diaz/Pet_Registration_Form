//CONTROLLER
package com.zybooks.petregistrationform;

import androidx.annotation.NonNull;
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

import com.zybooks.petregistrationform.model.PetEntry;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private PetCare mPetCare;

    private TextView mMicroChipTextView;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mAccessTextView;
    private TextView mConfirmTextView;

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
        setContentView(R.layout.constraint_main);

        mPetCare = new PetCare(this.getApplication());

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
    public void makeTextViewRed(@NonNull TextView textView){
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

        String[] chips = getResources().getStringArray(R.array.chips);
        System.out.println("Submit Click");
        boolean submitted = true;

        String microchip = mMicroChipEditText.getText().toString();
        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String code1str = mAccessEditText.getText().toString();
        String code2str = mConfirmEditText.getText().toString();

        if(!checkCompletion(microchip, name, email, code1str, code2str)) return;
        int code1, code2;

        try{
            code1 = Integer.parseInt(code1str);
            code2 = Integer.parseInt(code2str);
        }
        catch (NumberFormatException ex){
            code1 = 0;
            code2 = 1;
        }

        if (!mPetCare.checkMicroChip(microchip, chips)) {
            makeTextViewRed(mMicroChipTextView);
            submitted = false;
            if (!mPetCare.checkDatabase(microchip, chips))
                Toast.makeText(this, R.string.error_microchip_duplicate, Toast.LENGTH_SHORT).show();
            if (!mPetCare.checkFormatting(microchip))
                Toast.makeText(this, R.string.error_microchip_formatting, Toast.LENGTH_SHORT).show();

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
            // TODO: Get the gender, breed, and fertility status from radio group, spinner, and checkbox
            PetEntry pet = new PetEntry(microchip, name, true, email, mBreedSpinner.toString(), true);
            mPetCare.getPetRepo().addPetEntry(pet);
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();

        }
    }
    public boolean checkCompletion(String microchip, String name, String email, String code1, String code2){
        resetTextViewColors();
        boolean complete = true;
        if (mPetCare.isBlank(microchip)){
            makeTextViewRed(mMicroChipTextView);
            complete = false;
        }
        if(mPetCare.isBlank(name)){
            makeTextViewRed(mNameTextView);
            complete = false;
        }
        if (mPetCare.isBlank(email)){
            makeTextViewRed(mEmailTextView);
            complete = false;
        }
        if (mPetCare.isBlank(code1)){
            makeTextViewRed(mAccessTextView);
            complete = false;
        }
        if (mPetCare.isBlank(code2)){
            makeTextViewRed(mConfirmTextView);
            complete = false;
        }

        if (!complete){
            Toast.makeText(this, R.string.error_empty_prompt, Toast.LENGTH_LONG).show();
        }

        return complete;
    }

}
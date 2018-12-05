package com.example.checklisttrial;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {
    CheckBox[] checkBoxes;
    Spinner optionSpinner;
    String selectedOption;
    String[] numbersOptions = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    String[] burgerOptions = {"Bacon", "Lettuce", "Tomato", "Sesame Seed Bun", "Barbecue Sauce",
            "Chicken", "Mayonnaise", "Rye", "Hot Sauce", "HP Sauce", "Sriracha"};
    String[] abstractOptions = {"Hell", "Inquisitive", "Petrified", "Mouldy", "Entropic", "Shifting"};
    String[] options = {"Numbers", "Burger", "Abstract"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCheckOptions(numbersOptions);//initial values
        setupSpinner();
        checkSubmitCondition();
    }


    /**
     * Set up a listener for when a box value is changed
     * @param checkBox
     */
    public void setUpSubmitConditionListener(CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkSubmitCondition();
            }
        });
    }


    /**
     * Determines if the current check box configuration can be submitted
     */
    public void checkSubmitCondition(){
        Button submitButton = findViewById(R.id.buttonSubmitSelection);
        if(getCheckedBoxes(checkBoxes).length > 0) submitButton.setEnabled(true);
        else submitButton.setEnabled(false);
    }

    /**
     * Sets which check box options will appear in the UI
     * @param optionList is the list of check options
     */
    public void setCheckOptions(String[] optionList){
        LinearLayout layout = findViewById(R.id.linearLayout);
        layout.removeAllViewsInLayout();
        int numOptions = optionList.length;
        checkBoxes = new CheckBox[numOptions];

        for(int i = 0 ; i < numOptions ; i++){
            TableRow row = new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            CheckBox checkBox = new CheckBox(this);
            checkBox.setTextSize(30);
            checkBox.setId(i);
            checkBox.setText(optionList[i]);
            setUpSubmitConditionListener(checkBox);
            row.addView(checkBox);
            layout.addView(row);
            checkBoxes[i] = checkBox;
        }
    }

    /**
     * Retrieves the names of the boxes that have been checked
     * @param AllCheckBoxes an exhaustive list of the checkbox options
     * @return
     */
    public static String[] getCheckedBoxes(CheckBox[] AllCheckBoxes){
        ArrayList<String> checkBoxArrayList = new ArrayList<String>();
        String[] checkedBoxes;
        int totalNumCheckBoxes = AllCheckBoxes.length;

        for(int i = 0 ; i < totalNumCheckBoxes ; i++){
            if(AllCheckBoxes[i].isChecked()){
                checkBoxArrayList.add(AllCheckBoxes[i].getText().toString());
            }
        }

        checkedBoxes = new String[checkBoxArrayList.size()];
        checkedBoxes = checkBoxArrayList.toArray(checkedBoxes);

        return checkedBoxes;
    }

    /**
     * Moves to the next activity showing the results of the checked boxes
     * @param view
     */
    public void buttonClickAction(View view){
    Intent intent = new Intent(this, DisplayResults.class);
    intent.putExtra("CHECKED_BOXES", getCheckedBoxes(checkBoxes));
    startActivity(intent);
    }

    /**
     * Sets all boxes to checked
     * @param view
     */
    public void buttonSelectAllAction(View view){
        for(CheckBox checkBox : checkBoxes){
            checkBox.setChecked(true);
            //checkSubmitCondition();
        }
    }

    /**
     * Sets all boxes to unchecked
     * @param view
     */
    public void buttonSelectNoneAction(View view){
        for(CheckBox checkBox : checkBoxes){
            checkBox.setChecked(false);
            //checkSubmitCondition();
        }
    }

    /**
     * Sets up the spinner container
     */
    public void setupSpinner() {
        optionSpinner = findViewById(R.id.optionSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        optionSpinner.setAdapter(adapter);
        optionSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object itemAtPosition = parent.getItemAtPosition(position);
        selectedOption = itemAtPosition.toString();
        if(selectedOption.equals(options[0])) setCheckOptions(numbersOptions);
        else if(selectedOption.equals(options[1])) setCheckOptions(burgerOptions);
        else setCheckOptions(abstractOptions);
        checkSubmitCondition();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}

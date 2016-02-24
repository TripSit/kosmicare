package eddiecurtis.github.com.kosmicare;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class EntryForm extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static final String[] DAYS = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    private static final String[] MONTHS = new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] YEARS = new String[] {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"};

    private static final String[] HOURS = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    private static final String[] MINUTES = new String[]{"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"};

    private static final String[] ARRIVED = new String[] {"Him/Herself", "Medics", "Kosmicare Team", "Friends"};
    private static final String[] REASON = new String[] {"Difficult but intentional drug problem", "Accidental drug intake", "Mental crisis unrelated to drugs", "Alcohol intoxication", "Other (specify below)"};
    private static final String[] EFFECTS = new String[] {"Increasing", "Neutral", "Decreasing", "Not drug related"};

    private static final String MALE = "M";
    private static final String FEMALE = "F";

    private int currentId = 0;

    private Map<Integer, FormData> records = new HashMap<Integer, FormData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_entry_form);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        PlaceholderFragment fragment = PlaceholderFragment.newInstance(position + 1);
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
        if (mNavigationDrawerFragment != null) {
            FormData data = position == -1? null : FileManager.instance().getVisitorData(mNavigationDrawerFragment.getIdAtPosition(position));
            fragment.initData(data);
        }
    }

    public void onSectionAttached(int currentId, String visitorName) {
        this.currentId = currentId;
        mTitle = currentId + " - " + visitorName;
        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.entry_form, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean isExternalStorageWritable() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        private View rootView;

        private FormData data;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_entry_form, container, false);
            initBlank(rootView);
            if (data != null) {
                loadFormData();
            }
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            int id = data == null ? FileManager.instance().getNextVisitorId() : data.getVisitorId();
            String name = data == null ? "New visitor" : data.getVisitor();
            ((EntryForm) activity).onSectionAttached(id, name);
        }

        private void initBlank(View view) {
            Calendar calendar = Calendar.getInstance();

            Spinner daySpinner = (Spinner) view.findViewById(R.id.day_spinner);
            daySpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, DAYS));
            daySpinner.setSelection(calendar.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(DAYS[0]));

            Spinner monthSpinner = (Spinner) view.findViewById(R.id.month_spinner);
            monthSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, MONTHS));
            monthSpinner.setSelection(calendar.get(Calendar.MONTH) - 1);

            Spinner yearSpinner = (Spinner) view.findViewById(R.id.year_spinner);
            yearSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, YEARS));
            yearSpinner.setSelection(calendar.get(Calendar.YEAR) - Integer.parseInt(YEARS[0]));

            Spinner arrivedSpinner = (Spinner) view.findViewById(R.id.arrived_spinner);
            arrivedSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, ARRIVED));

            Spinner reasonSpinner = (Spinner) view.findViewById(R.id.reason_spinner);
            reasonSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, REASON));
            
            Spinner effectsSpinner = (Spinner) view.findViewById(R.id.effects_spinner);
            effectsSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, EFFECTS));

            Spinner arrivalHoursSpinner = (Spinner) view.findViewById(R.id.arrival_hour_spinner);
            arrivalHoursSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, HOURS));

            Spinner arrivalMinutesSpinner = (Spinner) view.findViewById(R.id.arrival_minute_spinner);
            arrivalMinutesSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, MINUTES));

            Spinner hoursSpinner = (Spinner) view.findViewById(R.id.hour_spinner);
            hoursSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, HOURS));

            Spinner minutesSpinner = (Spinner) view.findViewById(R.id.minute_spinner);
            minutesSpinner.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_dropdown_item_1line, MINUTES));
        }

        private void initData(FormData data) {
            this.data = data;
        }

        private void loadFormData() {
            setSpinner(R.id.day_spinner, data.getDay());
            setSpinner(R.id.month_spinner, data.getMonth());
            setSpinner(R.id.year_spinner, data.getYear());
            setEditText(R.id.txt_festival, data.getEvent());
            setEditText(R.id.txt_facilitator, data.getFacilitator());
            setEditText(R.id.txt_visitor, data.getVisitor());
            setEditText(R.id.txt_age, data.getAge());
            setRadioButton(R.id.radio_male, data.getGender().equals(MALE));
            setRadioButton(R.id.radio_female, data.getGender().equals(FEMALE));
            setSpinner(R.id.arrived_spinner, data.getArrival());
            setSpinner(R.id.reason_spinner, data.getReason());
            setEditText(R.id.other_reason, data.getReasonOtherText());
            setEditText(R.id.txt_drugs_taken, data.getDrugsTaken());
            setEditText(R.id.txt_approximate_time_drugs_taken, data.getApproximateTimeDrugsTaken());
            setSpinner(R.id.effects_spinner, data.getEffects());
            setCheckBox(R.id.check_terrified, data.isConditionTerrified());
            setCheckBox(R.id.check_disturbed, data.isConditionDisturbed());
            setCheckBox(R.id.check_physically_agitated, data.isConditionPhysicallyAgitated());
            setCheckBox(R.id.check_physically_calm, data.isConditionPhysicallyCalm());
            setCheckBox(R.id.check_verbal, data.isConditionVerbal());
            setCheckBox(R.id.check_nonverbal, data.isConditionNonVerbal());
            setCheckBox(R.id.check_calm, data.isConditionCalm());
            setCheckBox(R.id.check_upset, data.isConditionUpset());
            setCheckBox(R.id.check_paranoid, data.isConditionParanoid());
            setCheckBox(R.id.check_ego_loss, data.isConditionEgoLoss());
            setEditText(R.id.txt_symptoms, data.getSymptoms());
            setEditText(R.id.txt_issues, data.getIssues());
            setRadioButton(R.id.check_effective_listening, data.isTechniqueEffectiveListening());
            setRadioButton(R.id.check_ineffective_listening, data.isTechniqueIneffectiveListening());
            setRadioButton(R.id.check_effective_talking, data.isTechniqueEffectiveTalking());
            setRadioButton(R.id.check_ineffective_talking, data.isTechniqueIneffectiveTalking());
            setRadioButton(R.id.check_effective_hugging, data.isTechniqueEffectiveHugging());
            setRadioButton(R.id.check_ineffective_hugging, data.isTechniqueIneffectiveHugging());
            setRadioButton(R.id.check_effective_sitting, data.isTechniqueEffectiveSitting());
            setRadioButton(R.id.check_ineffective_sitting, data.isTechniqueIneffectiveSitting());
            setRadioButton(R.id.check_effective_holding_hands, data.isTechniqueEffectiveHoldingHands());
            setRadioButton(R.id.check_ineffective_holding_hands, data.isTechniqueIneffectiveHoldingHands());
            setRadioButton(R.id.check_effective_creative_therapy, data.isTechniqueEffectiveCreativeTherapy());
            setRadioButton(R.id.check_ineffective_creative_therapy, data.isTechniqueIneffectiveCreativeTherapy());
            setRadioButton(R.id.check_effective_left_alone, data.isTechniqueEffectiveLeftAlone());
            setRadioButton(R.id.check_ineffective_left_alone, data.isTechniqueIneffectiveLeftAlone());
            setRadioButton(R.id.check_effective_other, data.isTechniqueEffectiveOther());
            setRadioButton(R.id.check_ineffective_other, data.isTechniqueIneffectiveOther());
            setEditText(R.id.txt_other_techniques, data.getTechniqueOtherText());
            setSpinner(R.id.arrival_hour_spinner, data.getArrivalHour());
            setSpinner(R.id.arrival_minute_spinner, data.getArrivalMinute());
            setSpinner(R.id.hour_spinner, data.getLeavingHour());
            setSpinner(R.id.minute_spinner, data.getLeavingMinute());
            setEditText(R.id.txt_duration, data.getDuration());
            setEditText(R.id.txt_feedback, data.getFeedback());
            setEditText(R.id.txt_mental_state_leaving, data.getLeavingCondition());
            setEditText(R.id.txt_resolution, data.getIssuesResolution());
            setEditText(R.id.txt_conclusion, data.getConclusion());
        }

        private void setCheckBox(int id, boolean isChecked) {
            CheckBox box = (CheckBox) rootView.findViewById(id);
            if (box.isChecked() != isChecked) {
                box.toggle();
            }
        }

        private void setEditText(int id, String text) {
            ((EditText) rootView.findViewById(id)).setText(text);
        }

        private void setSpinner(int id, String text) {
            Spinner spinner = (Spinner) rootView.findViewById(id);
            int position = 0;
            for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
                String selection = (String) spinner.getAdapter().getItem(i);
                if (selection.equals(text)) {
                    position = i;
                    break;
                }
            }
            spinner.setSelection(position);
        }

        private void setRadioButton(int id, boolean isChecked) {
            RadioButton button = (RadioButton) rootView.findViewById(id);
            if (button.isChecked() != isChecked) {
                button.toggle();
            }
        }
    }

    @Override
    public void deleteRecord() throws Exception {
        FileManager.instance().deleteRecord(this, currentId, editTextText(R.id.txt_visitor));
    }

    @Override
    public void saveRecords() throws Exception {
        if (isExternalStorageWritable()) {
            FormData data = getFormData();
            FileManager.instance().saveFormData(data);
        } else {
            throw new IOException("Can't read external storage");
        }
    }

    @Override
    public void newRecord() {
        onNavigationDrawerItemSelected(-1);
    }

    public FormData getFormData() {
        FormData data = new FormData();
        data.setVisitorId(currentId);
        data.setDay(spinnerText(R.id.day_spinner));
        data.setMonth(spinnerText(R.id.month_spinner));
        data.setYear(spinnerText(R.id.year_spinner));
        data.setEvent(editTextText(R.id.txt_festival));
        data.setFacilitator(editTextText(R.id.txt_facilitator));
        data.setVisitor(editTextText(R.id.txt_visitor));
        data.setAge(editTextText(R.id.txt_age));
        data.setGender(radioButtonSelected(R.id.radio_male) ? MALE : FEMALE);
        data.setArrival(spinnerText(R.id.arrived_spinner));
        data.setReason(spinnerText(R.id.reason_spinner));
        data.setReasonOtherText(editTextText(R.id.other_reason));
        data.setDrugsTaken(editTextText(R.id.txt_drugs_taken));
        data.setApproximateTimeDrugsTaken(editTextText(R.id.txt_approximate_time_drugs_taken));
        data.setEffects(spinnerText(R.id.effects_spinner));
        data.setConditionTerrified(checkBoxSelected(R.id.check_terrified));
        data.setConditionDisturbed(checkBoxSelected(R.id.check_disturbed));
        data.setConditionPhysicallyAgitated(checkBoxSelected(R.id.check_physically_agitated));
        data.setConditionPhysicallyCalm(checkBoxSelected(R.id.check_physically_calm));
        data.setConditionVerbal(checkBoxSelected(R.id.check_verbal));
        data.setConditionNonVerbal(checkBoxSelected(R.id.check_nonverbal));
        data.setConditionCalm(checkBoxSelected(R.id.check_calm));
        data.setConditionUpset(checkBoxSelected(R.id.check_upset));
        data.setConditionParanoid(checkBoxSelected(R.id.check_paranoid));
        data.setConditionEgoLoss(checkBoxSelected(R.id.check_ego_loss));
        data.setSymptoms(editTextText(R.id.txt_symptoms));
        data.setIssues(editTextText(R.id.txt_issues));
        data.setTechniqueEffectiveListening(radioButtonSelected(R.id.check_effective_listening));
        data.setTechniqueIneffectiveListening(radioButtonSelected(R.id.check_ineffective_listening));
        data.setTechniqueEffectiveTalking(radioButtonSelected(R.id.check_effective_talking));
        data.setTechniqueIneffectiveTalking(radioButtonSelected(R.id.check_ineffective_talking));
        data.setTechniqueEffectiveHugging(radioButtonSelected(R.id.check_effective_hugging));
        data.setTechniqueIneffectiveHugging(radioButtonSelected(R.id.check_ineffective_hugging));
        data.setTechniqueEffectiveSitting(radioButtonSelected(R.id.check_effective_sitting));
        data.setTechniqueIneffectiveSitting(radioButtonSelected(R.id.check_ineffective_sitting));
        data.setTechniqueEffectiveHoldingHands(radioButtonSelected(R.id.check_effective_holding_hands));
        data.setTechniqueIneffectiveHoldingHands(radioButtonSelected(R.id.check_ineffective_holding_hands));
        data.setTechniqueEffectiveCreativeTherapy(radioButtonSelected(R.id.check_effective_creative_therapy));
        data.setTechniqueIneffectiveCreativeTherapy(radioButtonSelected(R.id.check_ineffective_creative_therapy));
        data.setTechniqueEffectiveLeftAlone(radioButtonSelected(R.id.check_effective_left_alone));
        data.setTechniqueIneffectiveLeftAlone(radioButtonSelected(R.id.check_ineffective_left_alone));
        data.setTechniqueEffectiveOther(radioButtonSelected(R.id.check_effective_other));
        data.setTechniqueIneffectiveOther(radioButtonSelected(R.id.check_ineffective_other));
        data.setTechniqueOtherText(editTextText(R.id.txt_other_techniques));
        data.setArrivalHour(spinnerText(R.id.arrival_hour_spinner));
        data.setArrivalMinute(spinnerText(R.id.arrival_minute_spinner));
        data.setLeavingHour(spinnerText(R.id.hour_spinner));
        data.setLeavingMinute(spinnerText(R.id.minute_spinner));
        data.setDuration(editTextText(R.id.txt_duration));
        data.setFeedback(editTextText(R.id.txt_feedback));
        data.setLeavingCondition(editTextText(R.id.txt_mental_state_leaving));
        data.setIssuesResolution(editTextText(R.id.txt_resolution));
        data.setConclusion(editTextText(R.id.txt_conclusion));
        return data;
    }

    private boolean checkBoxSelected(int id) {
        return ((CheckBox) findViewById(id)).isChecked();
    }

    private String editTextText(int id) {
        return ((EditText) findViewById(id)).getText().toString();
    }

    private String spinnerText(int id) {
        return ((Spinner) findViewById(id)).getSelectedItem().toString();
    }

    private boolean radioButtonSelected(int id) {
        return ((RadioButton) findViewById(id)).isChecked();
    }
}

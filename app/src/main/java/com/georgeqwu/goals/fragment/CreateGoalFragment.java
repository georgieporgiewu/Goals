package com.georgeqwu.goals.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.georgeqwu.goals.R;
import com.georgeqwu.goals.utility.DateUtility;

import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContinueToTasksListener} interface
 * to handle interaction events.
 * Use the {@link CreateGoalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateGoalFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText mEndDateEditText;
    private TextInputLayout mEndDateTextInputLayout;
    private TextView mPrioritiesTextView;

    private boolean mPrioritiesSpinnerItemFirstClickedCalled;
    private Spinner mPrioritiesSpinner;
    private String mPrioritySelected;
    private Button mContinueButton;

    private ContinueToTasksListener mListener;

    public CreateGoalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateGoalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateGoalFragment newInstance(String param1, String param2) {
        CreateGoalFragment fragment = new CreateGoalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_goal, container, false);
        setUpButtonsAndListeners(view);
        return view;
    }

    private void setUpButtonsAndListeners(View view) {
        mPrioritiesSpinnerItemFirstClickedCalled = false;
        mEndDateEditText = (EditText) view.findViewById(R.id.end_date_edit_text);
        mEndDateTextInputLayout = (TextInputLayout) view.findViewById(R.id.input_layout_end_date);
        mEndDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(today);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar setCalendar = Calendar.getInstance();
                        setCalendar.set(year, month, dayOfMonth);

                        mEndDateEditText.setText(DateUtility.formatLocalDateAmerican(setCalendar.getTime()));
                        mEndDateTextInputLayout.setHint(getResources().getString(R.string.goal_finish_date));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(today.getTime());
                datePickerDialog.show();
            }
        });

        mPrioritiesTextView = (TextView) view.findViewById(R.id.priorities_text_view);

        mPrioritiesSpinner = (Spinner) view.findViewById(R.id.priorities_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.priorities_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPrioritiesSpinner.setAdapter(adapter);
        mPrioritiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (mPrioritiesSpinnerItemFirstClickedCalled) {
                    mPrioritiesTextView.setTextColor(getResources().getColor(R.color.lightGray));
                }
                mPrioritiesSpinnerItemFirstClickedCalled = true;
                mPrioritySelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.continueToTasksFragment();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ContinueToTasksListener) {
            mListener = (ContinueToTasksListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface ContinueToTasksListener {
        void continueToTasksFragment();
    }
}

package com.example.princ.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditFragment extends Fragment {

    private OnEditFragmentListener mListener;
    Student s;
    TextView textViewEName, textViewEEmail, textViewEDep, textViewEMood;
    EditText editTextEName, editTextEEmail;
    RadioGroup radioGroupEdit;
    SeekBar seekBarEMood;
    Button saveButton;
    Bundle bundle;
    public EditFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        if (getArguments() != null) {
            bundle = getArguments();
            if (bundle.containsKey("editName")) {
                s=(Student) bundle.getSerializable("editName");
            } else if (bundle.containsKey("editEmail")) {
                s=(Student) bundle.getSerializable("editEmail");
            } else if (bundle.containsKey("editDepartment")) {
                s=(Student) bundle.getSerializable("editDepartment");
            } else if (bundle.containsKey("editMood")) {
                s=(Student) bundle.getSerializable("editMood");
            }
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditFragmentListener) {
            mListener = (OnEditFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewEName = (TextView) getActivity().findViewById(R.id.textViewEName);
        textViewEEmail = (TextView) getActivity().findViewById(R.id.textViewEEmail);
        editTextEName = (EditText) getActivity().findViewById(R.id.editTextEName);
        editTextEEmail = (EditText) getActivity().findViewById(R.id.editTextEEmail);
        textViewEDep = (TextView) getActivity().findViewById(R.id.textViewEDep);
        radioGroupEdit = (RadioGroup) getActivity().findViewById(R.id.radioGroupEdit);
        textViewEMood = (TextView) getActivity().findViewById(R.id.textViewEMood);
        seekBarEMood = (SeekBar) getActivity().findViewById(R.id.seekBarEMood);
        saveButton = (Button) getActivity().findViewById(R.id.saveButton);

        if (bundle.containsKey("editName")) {
            textViewEEmail.setVisibility(View.INVISIBLE);
            textViewEDep.setVisibility(View.INVISIBLE);
            textViewEMood.setVisibility(View.INVISIBLE);
            editTextEEmail.setVisibility(View.INVISIBLE);
            radioGroupEdit.setVisibility(View.INVISIBLE);
            seekBarEMood.setVisibility(View.INVISIBLE);
            editTextEName.setText(s.getName());
        } else if (bundle.containsKey("editEmail")) {
            textViewEName.setVisibility(View.INVISIBLE);
            textViewEDep.setVisibility(View.INVISIBLE);
            textViewEMood.setVisibility(View.INVISIBLE);
            editTextEName.setVisibility(View.INVISIBLE);
            radioGroupEdit.setVisibility(View.INVISIBLE);
            seekBarEMood.setVisibility(View.INVISIBLE);
            editTextEEmail.setText(s.getEmail());
        } else if (bundle.containsKey("editMood")) {
            textViewEName.setVisibility(View.INVISIBLE);
            textViewEEmail.setVisibility(View.INVISIBLE);
            textViewEDep.setVisibility(View.INVISIBLE);
            editTextEName.setVisibility(View.INVISIBLE);
            editTextEEmail.setVisibility(View.INVISIBLE);
            radioGroupEdit.setVisibility(View.INVISIBLE);
            seekBarEMood.setProgress(Integer.parseInt(s.getMood()));
        } else if (bundle.containsKey("editDepartment")) {
            textViewEName.setVisibility(View.INVISIBLE);
            textViewEEmail.setVisibility(View.INVISIBLE);
            textViewEMood.setVisibility(View.INVISIBLE);
            editTextEName.setVisibility(View.INVISIBLE);
            editTextEEmail.setVisibility(View.INVISIBLE);
            seekBarEMood.setVisibility(View.INVISIBLE);
            /*RadioButton checkedButton = (RadioButton) getActivity().findViewById(s.getCheckedId());
            if(checkedButton!=null)
                checkedButton.setChecked(true);*/  //----> can be used when radiobuttons in different activities have same ids
            RadioButton checkedButton=(RadioButton)radioGroupEdit.getChildAt(s.getCheckedId());
            checkedButton.setChecked(true);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisplayFragment df = new DisplayFragment();
                Bundle b = new Bundle();
                if (editTextEName.getText().toString().isEmpty()&& getArguments().containsKey("editName")) {
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                }else if (editTextEEmail.getText().toString().isEmpty()&& getArguments().containsKey("editEmail")) {
                    Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(editTextEEmail.getText().toString()).matches()&& getArguments().containsKey("editEmail")){
                    Toast.makeText(getActivity(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }else {
                    if (bundle.containsKey("editName")) {
                        s.setName(editTextEName.getText().toString());
                        b.putSerializable("editedName", s);
                    } else if (bundle.containsKey("editEmail")) {
                            s.setEmail(editTextEEmail.getText().toString());
                            b.putSerializable("editedEmail", s);
                    } else if (bundle.containsKey("editMood")) {
                        s.setMood(String.valueOf(seekBarEMood.getProgress()));
                        b.putSerializable("editedMood", s);
                    } else if (bundle.containsKey("editDepartment")) {
                        s.setDepartment(((RadioButton) getActivity().findViewById(radioGroupEdit.getCheckedRadioButtonId())).getText().toString());
                        int checkedId=radioGroupEdit.indexOfChild(getActivity().findViewById(radioGroupEdit.getCheckedRadioButtonId()));
                        s.setCheckedId(checkedId);
                        b.putSerializable("editedDepartment", s);
                    }
                    if (!b.isEmpty())
                        Log.d("demoMain", "onClick: " + "bundle is not empty " + bundle.toString());
                    df.setArguments(b);
                    mListener.onEditFragmentInteraction(df);
                }
            }
        });
    }

    public interface OnEditFragmentListener {
        void onEditFragmentInteraction(Fragment fragment);
    }
}

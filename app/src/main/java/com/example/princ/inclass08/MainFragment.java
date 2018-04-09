package com.example.princ.inclass08;

import android.content.Context;
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
import android.widget.Toast;


public class MainFragment extends Fragment {

    private OnMainFragmentListener mListener;
    EditText editTextName,editTextEmail;
    RadioGroup radioGroupMain;
    SeekBar seekBarMood;
    Button submitButton;

    public MainFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener=(OnMainFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+" should implement OnFragmentInteractionListener");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        submitButton=(Button) getActivity().findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName=(EditText) getActivity().findViewById(R.id.editTextName);
                editTextEmail=(EditText) getActivity().findViewById(R.id.editTextEmail);
                radioGroupMain=(RadioGroup) getActivity().findViewById(R.id.radioGroupMain);
                seekBarMood=(SeekBar) getActivity().findViewById(R.id.seekBarMood);

                String student_name=editTextName.getText().toString();
                String student_email=editTextEmail.getText().toString();
                String student_mood = String.valueOf(seekBarMood.getProgress());
                String student_dept = ((RadioButton) getActivity().findViewById(radioGroupMain.getCheckedRadioButtonId())).getText().toString();
                int checkedId=radioGroupMain.indexOfChild(getActivity().findViewById(radioGroupMain.getCheckedRadioButtonId()));
                Student student=new Student(student_name,student_email,student_dept,student_mood,checkedId);

                if(student_name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Name", Toast.LENGTH_SHORT).show();
                }else if(student_email.isEmpty()){
                    Toast.makeText(getActivity(), "Enter Email", Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(student_email).matches()){
                    Toast.makeText(getActivity(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }else{
                    DisplayFragment df=new DisplayFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("studentMain",student);
                    if(!bundle.isEmpty()) Log.d("demoMain", "onClick: "+"bundle is not empty "+bundle.toString());
                    df.setArguments(bundle);
                    mListener.onMainFragmentInteraction(df);
                }
            }
        });
    }

    public interface OnMainFragmentListener {
        void onMainFragmentInteraction(Fragment fragment);
    }
}

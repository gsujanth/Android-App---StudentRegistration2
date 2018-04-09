package com.example.princ.inclass08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DisplayFragment extends Fragment {

    private OnDisplayFragmentListener mListener;
    Student student = null;

    TextView textViewDName2, textViewDEmail2, textViewDDepartment2, textViewDMood2;
    ImageButton imageButtonNameEdit, imageButtonEmailEdit, imageButtonDepEdit, imageButtonMoodEdit;

    public DisplayFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnDisplayFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " should implement OnDisplayFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("studentMain")) {
            student = (Student) bundle.getSerializable("studentMain");
        } else if (bundle != null && bundle.containsKey("editedName")) {
            student= (Student) bundle.getSerializable("editedName");
        }else if (bundle != null && bundle.containsKey("editedEmail")) {
            student= (Student) bundle.getSerializable("editedEmail");
        }else if (bundle != null && bundle.containsKey("editedDepartment")) {
            student= (Student) bundle.getSerializable("editedDepartment");
        }else if (bundle != null && bundle.containsKey("editedMood")) {
            student= (Student) bundle.getSerializable("editedMood");
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textViewDName2 = (TextView) getActivity().findViewById(R.id.textViewDName2);
        textViewDEmail2 = (TextView) getActivity().findViewById(R.id.textViewDEmail2);
        textViewDDepartment2 = (TextView) getActivity().findViewById(R.id.textViewDDepartment2);
        textViewDMood2 = (TextView) getActivity().findViewById(R.id.textViewDMood2);

        imageButtonNameEdit = (ImageButton) getActivity().findViewById(R.id.imageButtonNameEdit);
        imageButtonEmailEdit = (ImageButton) getActivity().findViewById(R.id.imageButtonEmailEdit);
        imageButtonDepEdit = (ImageButton) getActivity().findViewById(R.id.imageButtonDepEdit);
        imageButtonMoodEdit = (ImageButton) getActivity().findViewById(R.id.imageButtonMoodEdit);

        if (student != null) {
            textViewDName2.setText(student.getName());
            textViewDEmail2.setText(student.getEmail());
            textViewDDepartment2.setText(student.getDepartment());
            textViewDMood2.setText(String.format(Locale.US, "%s %s", student.getMood(), " % Positive"));
        } else {
            Toast.makeText(getActivity(), "No Data received", Toast.LENGTH_SHORT).show();
        }

        imageButtonNameEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment ef = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("editName", student);
                if (!bundle.isEmpty())
                    Log.d("demoMain", "onClickName: " + "bundle is not empty " + bundle.toString());
                ef.setArguments(bundle);
                mListener.onDisplayFragmentInteraction(ef);
            }
        });

        imageButtonEmailEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment ef = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("editEmail", student);
                if (!bundle.isEmpty())
                    Log.d("demoMain", "onClickEmail: " + "bundle is not empty " + bundle.toString());
                ef.setArguments(bundle);
                mListener.onDisplayFragmentInteraction(ef);
            }
        });

        imageButtonDepEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment ef = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("editDepartment", student);
                if (!bundle.isEmpty())
                    Log.d("demoDisplay", "onClickDep: " + "bundle is not empty " + bundle.toString());
                ef.setArguments(bundle);
                mListener.onDisplayFragmentInteraction(ef);
            }
        });

        imageButtonMoodEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditFragment ef = new EditFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("editMood", student);
                if (!bundle.isEmpty())
                    Log.d("demoMain", "onClickMood: " + "bundle is not empty " + bundle.toString());
                ef.setArguments(bundle);
                mListener.onDisplayFragmentInteraction(ef);
            }
        });

    }

    public interface OnDisplayFragmentListener {
        void onDisplayFragmentInteraction(Fragment fragment);
    }
}

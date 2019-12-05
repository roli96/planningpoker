package com.example.scrumpoker.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.scrumpoker.R;


public class EmployesRateFragment extends Fragment  {

    private static  final String Emplyees="employees";
    private String mText;
    private EmployesRateFragment.OnFragmentInteractionListener mListener;
    private TextView EmployeesText;
    private Button buttonFragment;

    public EmployesRateFragment() {
    }


    public static EmployesRateFragment newInstance(String employees) {
        EmployesRateFragment fragment = new EmployesRateFragment();
        Bundle args = new Bundle();
        args.putString(Emplyees,employees);
        fragment.setArguments(args);///
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mText=getArguments().getString(Emplyees);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_question__fragmant,container,false);
        EmployeesText=view.findViewById(R.id.eployeesText);
        EmployeesText.setText(mText);
        EmployeesText.requestFocus();

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EmployesRateFragment.OnFragmentInteractionListener) {
            mListener = (EmployesRateFragment.OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}

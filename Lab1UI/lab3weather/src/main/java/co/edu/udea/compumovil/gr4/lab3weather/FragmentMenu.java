package co.edu.udea.compumovil.gr4.lab3weather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends DialogFragment {

    OnFragmentMenuListener mCallBack;

    public FragmentMenu() {
        // Required empty public constructor
    }

    public interface OnFragmentMenuListener{
        public void onArticleSelected(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            mCallBack = (OnFragmentMenuListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()
                    + "must implement OnFragmentMenuListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }



}

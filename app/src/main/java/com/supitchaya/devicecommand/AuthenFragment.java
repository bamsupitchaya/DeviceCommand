package com.supitchaya.devicecommand;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenFragment extends Fragment {
    @Nullable
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //SignUp Controller
        signUpController();
       // signInController();

    } //Main Method

    private void signUpController() {
        Button button = getView().findViewById(R.id.btnSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.contentAuthenFragment,new RegisterFragment())
                        .addToBackStack(null).commit();
            }
        });
    } //signUp




    public AuthenFragment()  {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authen, container, false);
    }

}

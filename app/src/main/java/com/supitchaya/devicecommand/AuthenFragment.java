package com.supitchaya.devicecommand;


import android.content.Context;
import android.os.Bundle;
import android.service.autofill.UserData;
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
public class AuthenFragment extends Fragment implements View.OnClickListener {
    private MyFragmentListener listener;

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
                        .beginTransaction().replace(R.id.contentAuthenFragment, new RegisterFragment())
                        .addToBackStack(null).commit();
            }
        });
    } //signUp


    public AuthenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.btnSignIn){
            listener.onButtonOkClick();
        }
    }


    public interface MyFragmentListener {
        public void onButtonOkClick();

        public void onButtonCloseClick();

        public void onLoginSuccess(UserData data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (MyFragmentListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Must implement MyFragmentListener");
        }
    }
}


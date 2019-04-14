package def.hacks.even.better;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by William Zulueta on 4/13/19.
 */
public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();
    private Button logInButton;
    private Button signUpButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);

        logInButton = view.findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                HomeScreenFragment fragment = new HomeScreenFragment();
                fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                fragment.setExitTransition(new Slide(Gravity.LEFT));
                transaction.add(R.id.frameLayout, fragment);
                transaction.addToBackStack(HomeScreenFragment.TAG);
                transaction.commit();
            }
        });
        signUpButton = view.findViewById(R.id.signUpButton);
        return view;
    }

}

package def.hacks.even.better;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by William Zulueta on 4/14/19.
 */
public class HomeScreenFragment extends Fragment {
    public static final String TAG = HomeScreenFragment.class.getSimpleName();

    private Button loanButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, null);
        loanButton = view.findViewById(R.id.loanButton);
        loanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout, new ChooseLoanFragment());
                transaction.addToBackStack(ChooseLoanFragment.TAG);
                transaction.commit();
            }
        });
        return view;
    }
}

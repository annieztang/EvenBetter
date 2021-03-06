package def.hacks.even.better;

import android.content.Intent;
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
 * Created by William Zulueta on 4/14/19.
 */
public class HomeScreenFragment extends Fragment {
    public static final String TAG = HomeScreenFragment.class.getSimpleName();

    private Button loanButton;
    private Button creditHistoryButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_screen, null);
        loanButton = view.findViewById(R.id.loanButton);
        creditHistoryButton = view.findViewById(R.id.creditHistoryButton);
        loanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment fragment = new ChooseLoanFragment();
                fragment.setEnterTransition(new Slide(Gravity.RIGHT));
                fragment.setExitTransition(new Slide(Gravity.LEFT));
                transaction.add(R.id.frameLayout, fragment);
                transaction.addToBackStack(ChooseLoanFragment.TAG);
                transaction.commit();
            }
        });
        creditHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreditHistoryActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}

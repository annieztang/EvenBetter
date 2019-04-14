package def.hacks.even.better;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by William Zulueta on 4/14/19.
 */
public class ChooseLoanFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = ChooseLoanFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.loan_type, null);
        for (int i = 0; i < layout.getChildCount(); ++i) {
            View view = layout.getChildAt(i);
            view.setOnClickListener(this);
        }
        return layout;
    }

    public void onClick(View view) {
        if (view instanceof Button) {
            Button button = (Button) view;
            String loanType = button.getText().toString();
            // pass to next fragment

            if (getActivity() instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setLoanType(loanType);
            }

            // move to next fragment...
        }
    }
}

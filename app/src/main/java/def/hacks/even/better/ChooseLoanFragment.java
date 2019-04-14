package def.hacks.even.better;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by William Zulueta on 4/14/19.
 */
public class ChooseLoanFragment extends Fragment {
    public static final String TAG = ChooseLoanFragment.class.getSimpleName();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loan_type, null);
        return view;
    }
}

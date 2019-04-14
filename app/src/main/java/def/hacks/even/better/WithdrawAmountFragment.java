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
import android.widget.EditText;

public class WithdrawAmountFragment extends Fragment {
    public static final String TAG = WithdrawAmountFragment.class.getSimpleName();
    private EditText enterAmount;
    private Button submitButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.withdraw_amount, null);
        enterAmount = view.findViewById(R.id.withdrawEditText);
        submitButton = view.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loanAmount = Integer.parseInt(enterAmount.getText().toString());
                if (getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setWithdrawAmount(loanAmount);
                }
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout, new OffersFragment());
                transaction.addToBackStack(OffersFragment.TAG);
                transaction.commit();
            }
        });
        return view;
    }

}

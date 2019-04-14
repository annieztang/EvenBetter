package def.hacks.even.better;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import def.hacks.even.coreapi.LoanOffer;


/**
 * Created by William Zulueta on 4/14/19.
 */
public class OfferView extends LinearLayout {
    private View mainView;
    private TextView offerAmount;
    private TextView offerOrigin;

    public OfferView(Context context) {
        super(context);
        mainView = View.inflate(context, R.layout.view_offer, null);
        offerAmount = mainView.findViewById(R.id.offerAmount);
        offerOrigin = mainView.findViewById(R.id.offerOrigin);
        addView(mainView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void bind(LoanOffer loanOffer) {
        offerAmount.setText(String.valueOf(loanOffer.maxAmount));
        offerOrigin.setText(loanOffer.originator.name);
    }
}

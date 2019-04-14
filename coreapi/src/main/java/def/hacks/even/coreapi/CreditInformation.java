package def.hacks.even.coreapi;

public class CreditInformation {
    public enum providedCreditRating {
        excellent(720), good(660), fair(600), poor(0), limited(0);

        public int minValue;
        private providedCreditRating(int minValue) {
            this.minValue = minValue;
        }
    }
    public int providedNumericCreditScore;
}

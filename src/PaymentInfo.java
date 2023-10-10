public class PaymentInfo {
    private String cardholderName;
    private String cardNumber; // Typically, you'd want to use more secure ways to handle this, maybe storing just the last 4 digits.
    private String expiryDate; // Format: MM/YY
    private String cvv; // For added security, don't store CVV in databases.

    // Constructors
    public PaymentInfo() { }

    public PaymentInfo(String cardholderName, String cardNumber, String expiryDate, String cvv) {
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    // Getters and Setters
    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "PaymentInfo{" +
                "cardholderName='" + cardholderName + '\'' +
                ", cardNumber='XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4) + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                '}';
    }
}

//
//    Important Note: This is a very basic representation and lacks security measures required when dealing with payment information. In real-world applications:
//
//        Never store full card numbers. You can save the last 4 digits for reference purposes if required.
//        Never store CVVs. They are meant for transaction verification only.
//        Always encrypt sensitive data if you must store it.
//        Use established payment gateways (like Stripe, PayPal, etc.) to handle payments.
//        Always follow the guidelines provided by the Payment Card Industry Data Security Standard (PCI DSS) when handling cardholder data.
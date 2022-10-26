package atmproject.Exceptions;

public class AmountLessThanZero extends IllegalArgumentException{
    private static final String ERROR_MESSAGE = "Input cannot be less than zero";

    public AmountLessThanZero() {
        super(ERROR_MESSAGE);
    }
}

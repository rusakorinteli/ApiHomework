public enum StatCodes {
    SUCCESS_200(200),
    CREATED_201(201),
    BED_REQUEST_400(400);
    private final int value;

    StatCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

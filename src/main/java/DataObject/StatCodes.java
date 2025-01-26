package DataObject;

public enum StatCodes {
    OK200(200),
    Created201(201),
    BadReq400(400);
    private final int value;

    StatCodes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

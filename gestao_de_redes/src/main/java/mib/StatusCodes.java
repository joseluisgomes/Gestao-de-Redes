package mib;

public enum StatusCodes {
    OK(200), Created(201), Accepted(202),
    Bad_Request(400), Unauthorized(401),
    Forbidden(403), Not_Found(404);
    private final int code;

    StatusCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

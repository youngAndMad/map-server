package danekerscode.akbulakserver.payload;

import lombok.Data;

import static java.lang.System.currentTimeMillis;

@Data
public class ErrorResponse {
    private Long timestamp;
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = currentTimeMillis();
    }
}

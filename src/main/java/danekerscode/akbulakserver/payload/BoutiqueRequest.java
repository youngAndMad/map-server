package danekerscode.akbulakserver.payload;

import java.util.List;

public record BoutiqueRequest(
        String name,
        String comment,
        List<String> categories,
        Integer addressId ,
        String container // container name which will be updated in address which will be found by addressId
) {
}

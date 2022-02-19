package workshop.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {

    private final Integer id;
    private final String name;
    private final String email;
}

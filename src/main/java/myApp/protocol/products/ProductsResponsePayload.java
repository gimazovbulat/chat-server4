package myApp.protocol.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myApp.models.Product;
import myApp.protocol.AbstractPayload;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsResponsePayload extends AbstractPayload {
    private List<Product> products;
}

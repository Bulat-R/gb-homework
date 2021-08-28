package gb.spring.homework.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import gb.spring.homework.model.Company;
import gb.spring.homework.model.Product;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class ProductJsonDeserializer extends JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Product product = new Product();
        Company company = new Company();
        TreeNode treeNode = p.getCodec().readTree(p);
        ObjectNode companyNode = (ObjectNode) treeNode.get("company");
        String productName = ((TextNode) treeNode.get("name")).asText();
        if (companyNode == null && productName.contains(" - ")) {
            String[] arr = productName.split(" - ");
            company.setName(arr[1].trim());
            product.setName(arr[0].trim());
        } else {
            product.setName(productName);
            if (companyNode != null) {
                company.setName(companyNode.get("name").asText());
            }
        }
        NumericNode idNode = (NumericNode) treeNode.get("id");
        if (idNode != null) {
            product.setId(idNode.asLong());
        }
        product.setCost(BigDecimal.valueOf(((NumericNode) treeNode.get("cost")).asDouble()));
        product.setCompany(company);
        return product;
    }
}

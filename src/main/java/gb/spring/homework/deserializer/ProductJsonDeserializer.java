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
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
@Slf4j
public class ProductJsonDeserializer extends JsonDeserializer<Product> {

    @Override
    public Product deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        log.info("Start deserialize product from json");
        Product product = new Product();
        Company company = new Company();
        TreeNode treeNode = p.getCodec().readTree(p);
        ObjectNode companyNode = (ObjectNode) treeNode.get("company");
        TextNode productNameNode = (TextNode) treeNode.get("name");
        String productName = productNameNode == null ? "" : productNameNode.asText();
        if (companyNode == null && productName.contains(" - ")) {
            company.setName(productName.substring(0, productName.lastIndexOf(" - ")));
            product.setName(productName.substring(productName.lastIndexOf(" - ") + 3));
        } else {
            product.setName(productName);
            if (companyNode != null) {
                company.setName(companyNode.get("name") == null ? "" : companyNode.get("name").asText());
                company.setId(companyNode.get("id") == null ? null : companyNode.get("id").asLong());
            }
        }
        NumericNode idNode = (NumericNode) treeNode.get("id");
        if (idNode != null) {
            product.setId(idNode.asLong());
        }
        NumericNode costNode = (NumericNode) treeNode.get("cost");
        if (costNode != null) {
            product.setCost(BigDecimal.valueOf((costNode).asDouble()));
        }
        product.setCompany(company);
        log.info("Deserialized object: {}", product);
        return product;
    }
}

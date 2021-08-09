package gb.spring.servlet;

import gb.spring.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private final ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int amount;
        try {
            amount = Integer.parseInt(req.getParameter("amount"));
        } catch (Exception e) {
            amount = 10;
        }
        resp.getOutputStream().print(service.get(amount));
    }
}

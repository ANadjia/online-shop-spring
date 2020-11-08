package com.esisba.webservice.connector;

import com.esisba.webservice.entitiy.*;
import com.esisba.webservice.repositories.OrderItemProxy;
import com.esisba.webservice.repositories.OrderProxy;
import com.esisba.webservice.repositories.ProductProxy;
import com.google.gson.Gson;
import com.netflix.discovery.converters.Auto;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.annotations.Proxy;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {

    private User session_user = new User();

    private OrderProxy orderProxy;
    private OrderItemProxy orderItemProxy;
    private ProductProxy productProxy;
    @GetMapping("/")
    public String index() {
        if (session_user.getEmail() != null) {
            return "redirect:/products";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/home")
    public String home() {
        if (session_user.getEmail() != null) {
            return "index";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/product/add")
    public String addproduct(ModelMap modelMap) {
        if (session_user.getEmail() != null) {
            modelMap.addAttribute("product", new Product());
            return "addproduct";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/product/add")
    public String addproduct_post(@ModelAttribute Product product) {
        try {
            addProductRequest(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/products";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable("id") Long id, ModelMap modelMap) {
        if (session_user.getEmail() != null) {
            modelMap.addAttribute("product", new Product("Product X", "123456","Sony",100,300));
            modelMap.addAttribute("p_id", id);
            return "product";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/products")
    public String products(ModelMap modelMap) {
        if (session_user.getEmail() != null) {
            ArrayList<Product> productList = new ArrayList<Product>();
            modelMap.addAttribute("products", productList);
            return "products";
        }
        else {
            return "redirect:/login";
        }
    }

    @GetMapping("/cart")
    public String cart(ModelMap modelMap) {
        ArrayList<Product> productList = new ArrayList<Product>();
//        List<Order> carts=  orderProxy.getOrdersByUser((long) 1 );
//        for (Order cart : carts){
//            for (OrderItem item : cart.getOrderItems()) {
//            Product prod=productProxy.getProductById(item.getProductId());
//            productList.add(prod);
//        }}
        ArrayList<Product> cart = new ArrayList<Product>();
        modelMap.addAttribute("carts", productList);
        return "cart";
    }
    //pour la suppretion de cart
    @GetMapping("/deleteCart")
    public String deleteCart() {
        //productService.deleteAll();
        return "redirect:/products";
    }

    // Login
    @GetMapping("/login")
    public String login(ModelMap modelMap) {
        if (session_user.getEmail() != null) {
           modelMap.addAttribute("session_user", session_user);
           return "redirect:products";
        }
        modelMap.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login_post(@ModelAttribute User user) {
        try {
            String responseJSON = loginUserRequest(user);
            if (responseJSON.contains("id")) {
                System.out.println("########################################### "+responseJSON);
                Gson g = new Gson();
                this.session_user = g.fromJson(responseJSON, User.class);
                return "redirect:/products";
            }
            else {
                return "redirect:/login";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }


    // Logout
    @GetMapping("/logout")
    public String logout(ModelMap modelMap) {
        this.session_user = new User();
        return "redirect:/login";
    }

    // Signup
    @GetMapping("/signup")
    public String signup(ModelMap modelMap) {
        if (session_user.getEmail() != null) {
            return "redirect:/";
        }
        else {
            modelMap.addAttribute("user", new User());
            return "signup";
        }
    }

    @PostMapping("/signup")
    public String signup_post(@ModelAttribute User user) {
        try {
            if (addUserRequest(user).getStatusLine().getStatusCode() == 200) {
                return "redirect:/products";
            }
            else {
                return "redirect:/signup";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/signup";
        }
    }

    // ERROR
    @GetMapping("/error404")
    public String error() {
        return "error404";
    }

    // ERROR
    @GetMapping("/*")
    public String error404() {
        return "error404";
    }


    public void addProductRequest(Product p)
            throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:7000/product-service/products");

        String json = "{\"productName\":\""+p.getProductName()+"\"," +
                "\"ref\":\""+p.getRef()+"\","+
                "\"manufacturer\":\""+p.getManufacturer()+"\","+
                "\"price\":"+p.getPrice()+","+
                "\"qte\":"+p.getQte()+
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response);
        client.close();
    }

    public CloseableHttpResponse addUserRequest(User u)
            throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:7000/account-service/api-v1/users/signup");

        String json = "{" +
                "    \"email\": \""+u.getEmail()+"\"," +
                "    \"password\": \""+u.getPassword()+"\"," +
                "    \"first_name\": \""+u.getFirst_name()+"\"," +
                "    \"last_name\": \""+u.getLast_name()+"\"," +
                "    \"active\": 1" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response);
        client.close();
        return response;
    }

    public String loginUserRequest(User u)
            throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:7000/account-service/api-v1/users/login");

        String json = "{" +
                "    \"email\": \""+u.getEmail()+"\"," +
                "    \"password\": \""+u.getPassword()+"\"" +
                "}";
        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        String responseJSON = EntityUtils.toString(response.getEntity());
        System.out.println(responseJSON);
        client.close();
        return responseJSON;
    }







}


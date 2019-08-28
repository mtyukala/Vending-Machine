package com.vending.machine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vending.machine.controller.VendingMachineController;
import com.vending.machine.model.Coin;
import com.vending.machine.model.Product;
import com.vending.machine.model.Purchase;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.restdocs.mockmvc.*;

//import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(SpringRunner.class)
//@DataJpaTest//@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = VendingApplication.class)
@WebMvcTest(VendingMachineController.class)
public class VendingApplicationTests {
    public static final String BASE_URL = "/api/";
    public static final String COIN_URL = BASE_URL + "coins/";
    public static final String PRODUCT_URL = BASE_URL + "products/";
    public static final String BUY_URL = BASE_URL + "buy/";
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;/*
    @Autowired
    private VendingMachineController controller;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CoinRepository coinRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;


    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
*/

    public void whenFindById_theReturnProduct() {
        Product product = new Product("Ice Tea", 10.00f, 500, 10, "");
        //     entityManager.persist(product);
        //entityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnCoin() {
        Coin coin = new Coin(200, "R2.00", 10);
        //entityManager.persist(coin);
        //entityManager.flush();

    }


    public void whenFindById_thenReturnPurchase() {
        Product p1 = new Product("Biscuits", 10f, 500, 10, "");
        Product p2 = new Product("Water", 8f, 300, 10, "");

        Product p3 = new Product("Bar One", 5f, 100, 5, "");
        Product p4 = new Product("Peanuts", 3f, 100, 10, "");

        List<Product> products = Arrays.asList(p1, p2, p3, p4);


        Coin c1 = new Coin(200, "R2.00", 20);
        Coin c2 = new Coin(500, "R5.00", 10);
        Coin c3 = new Coin(100, "R1.00", 20);
        Coin c4 = new Coin(50, "50c", 20);
        List<Coin> coins = Arrays.asList(c1, c2, c3, c4);


        Double amount = (c1.getAmount() * 2) + c2.getAmount() + c3.getAmount();
        Purchase purchase = new Purchase(amount, p1, 1);
        //entityManager.persist(purchase);
        //entityManager.flush();


    }


    public void whenValidInput_thenReturn200() throws Exception {
        mockMvc.perform(get("/api/coins/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andDo(document("coins"));
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }

        mockMvc.perform(get("/api/products/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/buy")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }


    @Test
    public void testCreateProduct() throws Exception {
        byte[] json = toJson(new Product("Biscuits", 10f, 500, 10, ""));

        MvcResult result = createObject(PRODUCT_URL, json).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Biscuits"))).andReturn();

        Product product = fromJsonResult(result, Product.class);
        getObject(PRODUCT_URL, product.getId()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Biscuits")));


    }

    @Test
    public void testCreateCoin() throws Exception {
        byte[] json = toJson(new Coin(200, "R2.00", 20));
        MvcResult result = createObject(COIN_URL, json).andExpect(status().isCreated())
                .andExpect(jsonPath("$.count", is(20))).andReturn();

        Coin coin = fromJsonResult(result, Coin.class);
        getObject(COIN_URL, coin.getCid()).andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(20)));
    }

    @Test
    public void testCreatePurchase() throws Exception {
        Product p1 = new Product("Biscuits", 10f, 500, 10, "");
        Coin c1 = new Coin(200, "R2.00", 20);
        Coin c2 = new Coin(500, "R5.00", 10);
        Coin c3 = new Coin(100, "R1.00", 20);

        Double amount = (c1.getAmount() * 2) + c2.getAmount() + c3.getAmount();

        byte[] json = toJson(new Purchase(amount, p1, 1));
        MvcResult result = createObject(BUY_URL, json).andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount", is(amount))).andReturn();

        Purchase purchase = fromJsonResult(result, Purchase.class);
        getObject(BUY_URL, purchase.getPid()).andExpect(status().isOk())
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    @NotNull
    public ResultActions createObject(@NotNull String url, @NotNull byte[] object) throws Exception {
        return mockMvc.perform(post(url).header("Origin", "*")
                .contentType(MediaType.APPLICATION_JSON)
                .content(object).accept(MediaType.APPLICATION_JSON));
    }

    @NotNull
    private ResultActions updateObject(@NotNull String url, @NotNull Long id, byte[] json) throws Exception {
        return mockMvc.perform(put(url + id).content(json).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
    }

    @NotNull
    public ResultActions getObject(@NotNull String url, @NotNull Long id) throws Exception {
        return mockMvc.perform(get(url + id).header("Origin", "*")
                .accept(MediaType.APPLICATION_JSON));
    }

    @NotNull
    public ResultActions deleteObject(@NotNull String url, @NotNull Long id) throws Exception {
        return mockMvc.perform(delete(url + id)
                .accept(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenRequestBadProduct_return400() throws Exception {

    }

    @Test
    public void whenRequestBadCoin_return400() throws Exception {

    }

    @Test
    public void whenRequestBadPurchase_return400() throws Exception {

    }

    @NotNull
    public ResultActions testGetAllObjects(@NotNull String url) throws Exception {
        return mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON));
    }

    @NotNull
    public <T> T fromJsonResult(@NotNull MvcResult result, Class<T> tClass) throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsString(),
                tClass);
    }

    @NotNull
    private byte[] toJson(@NotNull Object object) throws Exception {
        return objectMapper
                .writeValueAsString(object).getBytes();
    }

    public void whenRequestAllProduct() throws Exception {
    }

    public void whenRequestAllCoins() throws Exception {
    }

    public void whenRequestAllPurchases() throws Exception {
    }
}
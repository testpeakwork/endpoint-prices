package com.andersen.iexapis.endpoint;

import com.andersen.iexapis.endpoint.dao.CompanyDao;
import com.andersen.iexapis.endpoint.dao.StockDao;
import com.andersen.iexapis.endpoint.dto.datastore.Stock;
import com.andersen.iexapis.endpoint.dto.json.CompanyData;
import com.andersen.iexapis.endpoint.services.CompanyService;
import com.google.cloud.Timestamp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.andersen.iexapis.endpoint.dto.datastore.Company;

import java.util.*;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class CompanyServiceTest {
    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyDao companyDao;

    @Autowired
    StockDao stockDao;

    @Before
    public void createTestData() {
        Company company = new Company();
        company.setSymbol("AAPL");
        company.setName("Apple");
        company.setLogo("http://www.appple.com/image.png");
        List<Stock> stocks = new ArrayList<>();
        Stock stock = new Stock();
        stock.setDateTime(Timestamp.of(new Date()));
        stock.setPrice(10.11d);
        stock.setCompanySymbol(company.getSymbol());
        stocks.add(stock);
        companyDao.save(company);
        stockDao.save(stock);
    }
    @After
    public void cleanUp() {
        companyDao.deleteAll();
        stockDao.deleteAll();
    }

    @Test
    public void doTest() {
        List<String> symbols = new ArrayList<>();
        symbols.add("AAPL");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR,-1);
        Date from = cal.getTime();
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_YEAR,1);
        Date to = cal1.getTime();
        List<CompanyData> companies = companyService.getCompaniesStocks(symbols,null, null);
        CompanyData c = companies.get(0);
        assertEquals("Apple", c.getName());
        assertEquals(1, c.getStocks().size());

        companies = companyService.getCompaniesStocks(symbols,from, null);
        c = companies.get(0);
        assertEquals("Apple", c.getName());
        assertEquals(1, c.getStocks().size());

        companies = companyService.getCompaniesStocks(symbols,null, to);
        c = companies.get(0);
        assertEquals("Apple", c.getName());
        assertEquals(1, c.getStocks().size());

        companies = companyService.getCompaniesStocks(symbols,from, to);
        c = companies.get(0);
        assertEquals("Apple", c.getName());
        assertEquals(1, c.getStocks().size());

        from = Calendar.getInstance().getTime();
        companies = companyService.getCompaniesStocks(symbols,from, to);
        c = companies.get(0);
        assertEquals("Apple", c.getName());
        assertEquals(0, c.getStocks().size());
    }
}

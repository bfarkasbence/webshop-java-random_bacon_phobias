package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier firstCraftBeer = new Supplier("First Craft Beer", "Beer");
        supplierDataStore.add(firstCraftBeer);
        Supplier legenda = new Supplier("Legenda Brewing", "Beer");
        supplierDataStore.add(legenda);
        Supplier monyo = new Supplier("MONYO Brewing Co.", "Beer");
        supplierDataStore.add(monyo);
        Supplier szentAndrasSerfozde = new Supplier("Szent Andras Brewing","Beer");
        supplierDataStore.add(szentAndrasSerfozde);
        Supplier craftwater = new Supplier("Simple Water","Wter");
        supplierDataStore.add(craftwater);


        //setting up a new product category
        ProductCategory beer = new ProductCategory("Craft Beer", "Beer", "Beer is a teast flued witch make you smile");
        productCategoryDataStore.add(beer);
        ProductCategory other = new ProductCategory("Craft Water", "Water", "Water is good for you");
        productCategoryDataStore.add(other);


        //setting up products and printing it
        productDataStore.add(new Product("Fake Coffee", 650, "HUF", "Stout style black craft beer with good old coffee taste", beer, firstCraftBeer));
        productDataStore.add(new Product("Tropical Ipa", 750, "HUF", "A FIRST Tropical IPA  with awsome tropical taste", beer, firstCraftBeer));
        productDataStore.add(new Product("Twisted Pils", 550, "HUF", "Traditional czech lager craft beer.", beer, firstCraftBeer));
        productDataStore.add(new Product("Kohatu Kohatu",1200,"HUF","Single hop NEw england IPA",beer,firstCraftBeer));
        productDataStore.add(new Product("Milk Stout",900,"HUF","Black stout beer",beer,legenda));
        productDataStore.add(new Product("Black Jack",500,"HUF","Good old hungarian IPA",beer,legenda));
        productDataStore.add(new Product("Buldozer",700,"HUF","Simple IPA.",beer,legenda));
        productDataStore.add(new Product("Six Fingers",800,"HUF","Harmonic taste in weeise beer",beer,legenda));
        productDataStore.add(new Product("American Beauty",750,"HUF","American Pale Ale",beer,monyo));
        productDataStore.add(new Product("Black Mamba",950,"HUF","Black milk stout!",beer,monyo));
        productDataStore.add(new Product("Flying Rabbit",650,"HUF","American IPA",beer,monyo));
        productDataStore.add(new Product("Schatzi",800,"HUF","Simple Lager",beer,monyo));
        productDataStore.add(new Product("Bandiba",650,"HUF","Tasty American IPA",beer,szentAndrasSerfozde));
        productDataStore.add(new Product("Esthajnal'18",1000,"HUF","Strong stout with plum flavoring",beer,szentAndrasSerfozde));
        productDataStore.add(new Product("Ogre",540,"HUF","Strong tasty lager",beer,szentAndrasSerfozde));
        productDataStore.add(new Product("Water",0,"HUF","This is a beer shop!",other,craftwater));

    }
}

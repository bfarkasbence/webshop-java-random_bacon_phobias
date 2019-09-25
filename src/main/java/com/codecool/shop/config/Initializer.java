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
        Supplier szentAndrasSerfozde = new Supplier("Szent Andras Brewing", "Beer");
        supplierDataStore.add(szentAndrasSerfozde);
        Supplier craftwater = new Supplier("Simple Water", "Water");
        supplierDataStore.add(craftwater);
        Supplier codecool = new Supplier("CodeCool", "Beer");
        supplierDataStore.add(codecool);


        //setting up a new product category
        ProductCategory ipa = new ProductCategory("IPA", "Beer", "Beer is a teast flued witch make you smile");
        productCategoryDataStore.add(ipa);
        ProductCategory apa = new ProductCategory("APA", "Beer", "Beer is a teast flued witch make you smile");
        productCategoryDataStore.add(apa);
        ProductCategory stout = new ProductCategory("Stout", "Beer", "Beer is a teast flued witch make you smile");
        productCategoryDataStore.add(stout);
        ProductCategory lager = new ProductCategory("Lager", "Beer", "Beer is a teast flued witch make you smile");
        productCategoryDataStore.add(lager);
        ProductCategory water = new ProductCategory("Water", "Water", "Water is health!");
        productCategoryDataStore.add(water);


        //setting up products and printing it
        productDataStore.add(new Product("Fake Coffee", 650, "HUF", "Stout style black craft beer with good old coffee taste", stout, firstCraftBeer));
        productDataStore.add(new Product("Tropical Ipa", 750, "HUF", "A FIRST Tropical IPA  with awsome tropical taste", ipa, firstCraftBeer));
        productDataStore.add(new Product("Twisted Pils", 550, "HUF", "Traditional czech lager craft beer.", lager, firstCraftBeer));
        productDataStore.add(new Product("Kohatu Kohatu", 1200, "HUF", "Single hop New england IPA", ipa, firstCraftBeer));
        productDataStore.add(new Product("Milk Stout", 900, "HUF", "Black stout beer", stout, legenda));
        productDataStore.add(new Product("Black Jack", 500, "HUF", "Good old hungarian IPA", ipa, legenda));
        productDataStore.add(new Product("Buldozer", 700, "HUF", "Simple IPA.", ipa, legenda));
        productDataStore.add(new Product("Six Fingers", 800, "HUF", "Harmonic taste in weeise beer", lager, legenda));
        productDataStore.add(new Product("American Beauty", 750, "HUF", "American Pale Ale", apa, monyo));
        productDataStore.add(new Product("Black Mamba", 950, "HUF", "Black milk stout!", stout, monyo));
        productDataStore.add(new Product("Flying Rabbit", 650, "HUF", "American IPA", ipa, monyo));
        productDataStore.add(new Product("Schatzi", 800, "HUF", "Simple Lager", lager, monyo));
        productDataStore.add(new Product("Bandiba", 650, "HUF", "Tasty American IPA", ipa, szentAndrasSerfozde));
        productDataStore.add(new Product("Esthajnal'18", 1000, "HUF", "Strong stout with plum flavoring", stout, szentAndrasSerfozde));
        productDataStore.add(new Product("Ogre", 540, "HUF", "Strong tasty lager", lager, szentAndrasSerfozde));
        productDataStore.add(new Product("CodeCool Beer", 900, "HUF", "Tasty IPA from the best!", ipa, codecool));
        productDataStore.add(new Product("Tap water", 15000, "HUF", "Quality from wall!", water, craftwater));


    }
}

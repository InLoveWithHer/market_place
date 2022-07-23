import Model.Product;
import Model.PurchasesProduct;
import Model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Product> products = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static List<PurchasesProduct> purchasesProducts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void addData() {
        //add products
        products.add(new Product(1, "Cheese", 499.99));
        products.add(new Product(2, "Bread", 199));
        products.add(new Product(3, "Wine", 1566));

        //add users
        users.add(new User(1, "David", "Stratulat", 5000));
        users.add(new User(2, "Max", "Loik", 455));
        users.add(new User(3, "Cristian", "Greinger", 750));
    }

    public static void printFunctionsMarket() {
        System.out.println("Welcome to the market!");
        System.out.println("Our store has several functions:");
        System.out.println("'0' - Add prepared test data.");
        System.out.println("'1' - Add product.");
        System.out.println("'2' - Add user.");
        System.out.println("'3' - Show list of products.");
        System.out.println("'4' - Show list of Users.");
        System.out.println("'5' - Buy a product.");
        System.out.println("'6' - Show list of user products by user id.");
        System.out.println("'7' - Show list of users that bought product by product id.");
        System.out.println("'8' - Delete product by id.");
        System.out.println("'9' - Delete user by id.\n");
        System.out.println("Choose the function that suits you and press the number corresponding to it + Enter.");
    }

    public static long scannerUserId() {
        System.out.println("Please enter User id:");
        long userId = scanner.nextLong();
        if (userId <= 0) {
            System.out.println("User id cannot be negative or zero. Please try again.");
            userId = scanner.nextLong();
        }

        return userId;
    }

    public static long scannerProductId() {
        System.out.println("Please enter Product id:");
        long productId = scanner.nextLong();
        if (productId <= 0) {
            System.out.println("Product id cannot be negative or zero. Please try again.");
            productId = scanner.nextLong();
        }

        return productId;
    }

    public static void addUser() {
        int i = users.size() + 1;

        System.out.println("Enter your first name.");
        String firstName = scanner.next();
        if (firstName == null) {
            System.out.println("First name cannot be null. Please try again.");
            firstName = scanner.next();
        }

        System.out.println("Enter your last name.");
        String lastName = scanner.next();
        if (lastName == null) {
            System.out.println("Last name cannot be null. Please try again.");
            lastName = scanner.next();
        }

        System.out.println("Enter your amount of money.");
        double amountOfMoney = scanner.nextDouble();
        if (amountOfMoney < 0) {
            System.out.println("Amount cannot be negative. Please try again.");
            amountOfMoney = scanner.nextDouble();
        }

        for (User user : users) {
            if (user.getId() == i) {
                i++;
            }
        }

        users.add(new User(i, firstName, lastName, amountOfMoney));
        System.out.println("\nUser created successfully!");
    }

    public static void addProduct() {
        int i = products.size() + 1;

        System.out.println("Enter product title.");
        String title = scanner.next();
        if (title == null) {
            System.out.println("Product title cannot be null. Please try again.");
            title = scanner.next();
        }

        System.out.println("Enter product price.");
        double price = scanner.nextDouble();
        if (price <= 0) {
            System.out.println("Product price cannot be negative and zero. Please try again.");
            price = scanner.nextDouble();
        }

        for (Product product : products) {
            if (product.getId() == i) {
                i++;
            }
        }

        products.add(new Product(i, title, price));
        System.out.println("\nProduct created successfully!");
    }

    public static void allProducts() {
        if (products.size() != 0) {
            System.out.println("All products:");
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public static void allUsers() {
        if (users.size() != 0) {
            System.out.println("All users:");
        }
        for (User user : users) {
            System.out.println(user);
        }
    }

    public static void buyProduct(long userId, long productId) {
        double priceProduct = 0, amountUser = 0;
        int i = purchasesProducts.size() + 1;

        for (User user : users) {
            if (user.getId() == userId) {
                amountUser = user.getAmountOfMoney();
                break;
            }
        }
        for (Product product : products) {
            if (product.getId() == productId) {
                priceProduct = product.getPrice();
                break;
            }
        }

        if (amountUser < priceProduct) {
            System.out.println("Sorry, you don't have enough money");
            System.out.println("You have " + amountUser + " in your account and the product costs " + priceProduct);
        } else {
            System.out.println("You have successfully purchased the product");
            for (User user : users) {
                if (user.getId() == userId) {
                    user.setAmountOfMoney((amountUser - priceProduct));
                    break;
                }
            }
            for (PurchasesProduct purchasesProduct : purchasesProducts) {
                if (purchasesProduct.getId() == i) {
                    i++;
                }
            }
            purchasesProducts.add(new PurchasesProduct(i, userId, productId));
        }
    }

    public static void showPurchasesByUserId(long userId) {
        for (PurchasesProduct purchasesProduct : purchasesProducts) {
            if (purchasesProduct.getUserId() == userId) {
                for (Product product : products) {
                    if (product.getId() == purchasesProduct.getProductId()) {
                        System.out.println(product);
                    }
                }

            }
        }
    }

    public static void showPurchasesByProductId(long productId) {
        for (PurchasesProduct purchasesProduct : purchasesProducts) {
            if (purchasesProduct.getProductId() == productId) {
                for (User user : users) {
                    if (user.getId() == purchasesProduct.getUserId()) {
                        System.out.println(user);
                    }
                }

            }
        }
    }

    public static void deleteProductById(long productId) {
        Iterator<Product> productIterator = products.iterator(); //create iterator
        Iterator<PurchasesProduct> purchasesProductIterator = purchasesProducts.iterator();
        while (productIterator.hasNext()) { //as long as there are elements in the list
            Product nextProduct = productIterator.next(); //get the next element
            if (nextProduct.getId() == productId) {
                productIterator.remove(); //delete the product with the desired id
            }
        }

        while (purchasesProductIterator.hasNext()) { //as long as there are elements in the list
            PurchasesProduct nextPurchasesProduct = purchasesProductIterator.next(); //get the next element
            if(nextPurchasesProduct.getProductId() == productId) {
                purchasesProductIterator.remove(); //remove the product with the desired id from the shopping list
            }
        }
    }

    public static void deleteUserById(long userId) {
        Iterator<User> userIterator = users.iterator(); //create iterator
        Iterator<PurchasesProduct> purchasesProductIterator = purchasesProducts.iterator();
        while (userIterator.hasNext()) { //as long as there are elements in the list
            User nextUser = userIterator.next(); //get the next element
            if (nextUser.getId() == userId) {
                userIterator.remove(); //delete the user with the desired id
            }
        }

        while (purchasesProductIterator.hasNext()) { //as long as there are elements in the list
            PurchasesProduct nextPurchasesProduct = purchasesProductIterator.next(); //get the next element
            if(nextPurchasesProduct.getUserId() == userId) {
                purchasesProductIterator.remove(); //remove the user with the desired id from the shopping list
            }
        }
    }


    public static void main(String[] args) {
        int index = 1;

        while (index == 1) {
            printFunctionsMarket();
            int action = scanner.nextInt();
            if (action < 0 || action >= 10) {
                System.out.println("You entered the wrong number. Try again.");
            }
            switch (action) {
                case 0 -> addData();
                case 1 -> addProduct();
                case 2 -> addUser();
                case 3 -> allProducts();
                case 4 -> allUsers();
                case 5 -> buyProduct(scannerUserId(), scannerProductId());
                case 6 -> showPurchasesByUserId(scannerUserId());
                case 7 -> showPurchasesByProductId(scannerProductId());
                case 8 -> deleteProductById(scannerProductId());
                case 9 -> deleteUserById(scannerUserId());
            }

            System.out.println("\nDo you want to continue?");
            System.out.println("'1' - Yes");
            System.out.println("'2' - No");
            index = scanner.nextInt();
            if (index != 1) {
                System.out.println("Bye Bye");
                scanner.close();
            }
        }
    }
}
